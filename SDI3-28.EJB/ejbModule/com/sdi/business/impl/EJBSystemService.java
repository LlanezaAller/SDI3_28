package com.sdi.business.impl;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.classes.system.ResetDB;
import com.sdi.business.impl.classes.system.Unlink;
@WebService(name="SystemService")
@Stateless
public class EJBSystemService implements LocalSystemService, RemoteSystemService {

	@Override
	public void resetDB() {
		new ResetDB().execute();
	}

	@Override
	public void unlink(Object o) {
		new Unlink(o).execute();

	}

}
