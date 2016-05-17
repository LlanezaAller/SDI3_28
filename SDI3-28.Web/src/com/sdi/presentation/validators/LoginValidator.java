package com.sdi.presentation.validators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import alb.util.log.Log;

import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.User;

@FacesValidator("com.sdi.presentation.validators.LoginValidator")
public class LoginValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		try {
			User u = Factories.business.getUsuariosService().findUser(
					value.toString());
			if (u != null) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				ResourceBundle bundle = facesContext.getApplication()
						.getResourceBundle(facesContext, "msgs");
				FacesMessage msg = new FacesMessage(
						bundle.getString("errorLogin"));
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		} catch (EntityNotFoundException e) {
			Log.info("El login no existe.");
		}
	}
}
