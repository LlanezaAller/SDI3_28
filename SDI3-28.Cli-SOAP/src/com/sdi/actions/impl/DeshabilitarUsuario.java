package com.sdi.actions.impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.sdi.actions.Action;
import com.sdi.ws.EJBUsuariosServiceService;

public class DeshabilitarUsuario  implements Action{

private BufferedReader console;
	
	public DeshabilitarUsuario(BufferedReader console) {
		this.console=console;
	}
	@Override
	public void execute() {
		System.out.println("Introduzca el login del usuario a deshabilitar...");	
			try {
				String login = console.readLine();
				new EJBUsuariosServiceService().getUsuariosServicePort().disableUser(login);
				System.out.println("Usuario [" + login + "] desactivado");
			} catch (IOException e) {
				System.out.println("Error con el login del usuario");
				e.printStackTrace();
			}		
	}

	@Override
	public String describe() {
		return "Desactivar usuario";
	}

}