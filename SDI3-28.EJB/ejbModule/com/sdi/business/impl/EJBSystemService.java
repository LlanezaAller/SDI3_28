package com.sdi.business.impl;

import javax.ejb.Stateless;

import com.sdi.business.impl.classes.system.ResetDB;
import com.sdi.business.impl.classes.system.Unlink;

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
