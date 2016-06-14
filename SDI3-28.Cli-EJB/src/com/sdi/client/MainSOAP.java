package com.sdi.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.sdi.actions.Action;
import com.sdi.actions.impl.DeshabilitarUsuario;
import com.sdi.actions.impl.EliminarValoracion;
import com.sdi.actions.impl.ExitAction;
import com.sdi.actions.impl.ListarUsuarios;
import com.sdi.actions.impl.ListarValoraciones;

public class MainSOAP {
	private Map<Integer, Action> actions;
	public static void main(String[] args){
		try {
			new MainSOAP().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws Exception{
		System.out.println("Bienvenido a la consola de administración de ShareMyTrip.");
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		initializeActionMap(console);	
		Action action;
		while(true){
			describeActionMap();
		    String key = console.readLine();
		    action = actions.get(Integer.parseInt(key));
		    if(action!=null){
		    	action.execute();
		    }else {
		    	System.out.println("Opción incorrecta: ");
		    }
		}
	}
	
	public void initializeActionMap(BufferedReader console){
		actions = new HashMap<>();
		actions.put(1, new ListarUsuarios());
		actions.put(2, new DeshabilitarUsuario(console));
		actions.put(3, new ListarValoraciones());
		actions.put(4, new EliminarValoracion(console));
		actions.put(5, new ExitAction());		
	}
	
	public void describeActionMap(){
		System.out.println("Seleccione el número de la opción:");
		for(int key: actions.keySet()){
			System.out.println("\t"+key +" - " + actions.get(key).describe());
		}
	}
}
