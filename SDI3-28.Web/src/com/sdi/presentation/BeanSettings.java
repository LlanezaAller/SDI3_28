package com.sdi.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.sdi.infraestructure.factories.Factories;

@ManagedBean(name = "settings")
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	public Locale getLocale() {
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
	}

	public String resetDB() {
		Factories.business.getSystemService().resetDB();
		return "EXITO";
	}

}
