package com.sdi.business.impl.classes.system;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;

public class ResetDB implements Command {

	@Override
	public Object execute() {
		Factories.persistence.CreateSystemFinder().deleteAll();
		Factories.persistence.CreateSystemFinder().createElements();
		return null;
	}

}
