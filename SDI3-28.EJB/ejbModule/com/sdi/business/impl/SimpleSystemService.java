package com.sdi.business.impl;

import com.sdi.business.SystemService;
import com.sdi.business.impl.classes.system.ResetDB;
import com.sdi.business.impl.classes.system.Unlink;

public class SimpleSystemService implements SystemService {

	@Override
	public void resetDB() {
		new ResetDB().execute();
	}

	@Override
	public void unlink(Object o) {
		new Unlink(o).execute();

	}

}
