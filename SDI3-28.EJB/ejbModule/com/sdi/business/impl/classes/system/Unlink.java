package com.sdi.business.impl.classes.system;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;

public class Unlink implements Command {
	Object o;

	public Unlink(Object o) {
		this.o = o;
	}

	@Override
	public Object execute() {
		Factories.persistence.CreateSystemFinder().unlink(o);
		return null;
	}

}
