package com.sdi.presentation.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.User;
import com.sdi.model.type.UserStatus;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/user/*" }, initParams = { @WebInitParam(name = "LoginParam", value = "/index.xhtml") })
public class LoginFilter implements Filter {
	FilterConfig config = null;

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
	
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (!(request instanceof HttpServletRequest)) {
			chain.doFilter(request, response);
			return;
		}

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		try {
			if (user == null || Factories.business.getUsuariosService().findUser(user.getLogin()).getStatus()==UserStatus.CANCELLED) {
				String loginForm = config.getInitParameter("LoginParam");

				String url = "";
				if (request instanceof HttpServletRequest) {
					url = ((HttpServletRequest) request).getRequestURL().toString()
							+ "/" + ((HttpServletRequest) request).getQueryString();
				}
				Log.error("Usuario no autorizado ha intentado acceder a %s", url);

				res.sendRedirect(req.getContextPath() + loginForm);
				return;
			}
		} catch (EntityNotFoundException e) {
			Log.error("El usuario no existe, ", e);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// Iniciamos la variable de instancia config
		config = fConfig;
	}

}
