package com.sdi.infraestructure.factories;

import com.sdi.business.ServicesFactory;
import com.sdi.business.impl.LocalEjbServicesLocator;
import com.sdi.persistence.PersistenceFactory;
import com.sdi.persistence.impl.JpaPersistenceFactory;

public class Factories {

	private static String CONFIG_FILE = "/factories.properties";
	public static ServicesFactory business = (ServicesFactory) FactoriesHelper
			.createFactory(CONFIG_FILE, "SERVICES_FACTORY");
	public static PersistenceFactory persistence = (PersistenceFactory) FactoriesHelper
			.createFactory(CONFIG_FILE, "PERSISTENCE_FACTORY");
}