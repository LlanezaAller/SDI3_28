package com.sdi.infraestructure.factories;

import com.sdi.business.ServicesFactory;
import com.sdi.business.impl.LocalEjbServicesLocator;
import com.sdi.persistence.PersistenceFactory;
import com.sdi.persistence.impl.JpaPersistenceFactory;

public class Factories {

	public static PersistenceFactory persistence = new JpaPersistenceFactory();

	public static ServicesFactory business = new LocalEjbServicesLocator();
}
