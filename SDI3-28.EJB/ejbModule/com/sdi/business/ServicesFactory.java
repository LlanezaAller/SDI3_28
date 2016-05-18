package com.sdi.business;

public interface ServicesFactory {

	public UsuariosService getUsuariosService();

	public ViajesService getViajesService();

	public AsientosService getAsientosService();
	
	public ValoracionesService getValoracionesService();

	public SystemService getSystemService();
}
