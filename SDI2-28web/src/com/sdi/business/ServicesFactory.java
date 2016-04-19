package com.sdi.business;

public interface ServicesFactory {

	public UsuariosService createUsuariosService();

	public ViajesService createViajesService();

	public AsientosService createAsientosService();

	public SystemService createSystemService();
}
