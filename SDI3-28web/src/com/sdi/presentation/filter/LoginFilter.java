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
		// Si no es petición HTTP nada que hacer
		if (!(request instanceof HttpServletRequest)) {
			chain.doFilter(request, response);
			return;
		}
		// En el resto de casos se verifica que se haya hecho login previamente
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if (session.getAttribute("user") == null) {
			String loginForm = config.getInitParameter("LoginParam");
			// Si no hay login, redirección al formulario de login
			String url = "";
			if (request instanceof HttpServletRequest) {
				url = ((HttpServletRequest) request).getRequestURL().toString()
						+ "/" + ((HttpServletRequest) request).getQueryString();
			}
			Log.error("Usuario no autorizado ha intentado acceder a %s", url);
			/**
			 * FacesContext context = FacesContext .getCurrentInstance();
			 * ResourceBundle bundle = context.getApplication()
			 * .getResourceBundle(context, "msgs");
			 * context.getExternalContext().getFlash() .setKeepMessages(true);
			 * context.addMessage(null, new
			 * FacesMessage(bundle.getString("notAllowedPageMessage")));
			 */
			res.sendRedirect(req.getContextPath() + loginForm);
			return;
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
