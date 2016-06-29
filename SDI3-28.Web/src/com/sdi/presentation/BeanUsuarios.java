package com.sdi.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import alb.util.log.Log;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.util.SdiUtil;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.User;
import com.sdi.model.type.UserStatus;

@ManagedBean
@SessionScoped
public class BeanUsuarios implements Serializable {
	private static final long serialVersionUID = 55555L;
	private User user;

	public String register() {
		try {
			user.setStatus(UserStatus.ACTIVE);
			Factories.business.getUsuariosService().saveUser(user);
			login();
		} catch (EntityAlreadyExistsException e) {
			return "FRACASO";
		}
		return "EXITO";
	}

	@PostConstruct
	public void init() {
		user = new User();
	}

	public String login() {
		if (SdiUtil.assertCampos(user.getLogin(), user.getPassword())) {
			if (SdiUtil.assertCampos(user.getPassword(), user.getLogin())) {
				User userDB;
				try {
					userDB = Factories.business.getUsuariosService().findUser(
							user.getLogin());
					FacesContext context = FacesContext.getCurrentInstance();
					ResourceBundle bundle = context.getApplication()
							.getResourceBundle(context, "msgs");
					if (userDB != null) {
						if (user.getPassword().equals(userDB.getPassword()) && userDB.getStatus() == UserStatus.ACTIVE) {
							user = userDB;

							context.getExternalContext().getSessionMap()
									.put("user", userDB);
							context.getExternalContext().getFlash()
									.setKeepMessages(true);
							context.addMessage(
									null,
									new FacesMessage(String.format(
											bundle.getString("welcome"),
											user.getName())));
							Log.info("Usuario %s logueado con éxito.",
									user.getLogin());
							return "EXITO";
						}
					}
					Log.error("Usuario o password incorrecto: %s",
							user.getLogin());
					context.getExternalContext().getFlash()
							.setKeepMessages(true);
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											bundle.getString("loginFailedMessage"),
											""));
				} catch (EntityNotFoundException e) {

				}
			} else {
				Log.error("Es necesario rellenar usuario y contraseña para loguearse");
			}
		} else {

		}
		return "FRACASO";
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "EXITO";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
