package com.sdi.infraestructure.factories;

import com.sdi.business.ServicesFactory;
import com.sdi.persistence.PersistenceFactory;

public class Factories {
	private static final String CONFIG_FILE = "/factories.properties";
	public static ServicesFactory business = (ServicesFactory) FactoriesHelper
			.createFactory(CONFIG_FILE, "SERVICES_FACTORY");
	public static PersistenceFactory persistence = (PersistenceFactory) FactoriesHelper
			.createFactory(CONFIG_FILE, "PERSISTENCE_FACTORY");
}
