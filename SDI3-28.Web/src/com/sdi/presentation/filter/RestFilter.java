package com.sdi.presentation.filter;

import java.io.IOException;
import java.net.HttpURLConnection;

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
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import alb.util.log.Log;

import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.User;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/rest/*" }, initParams = { @WebInitParam(name = "LoginParam", value = "/index.xhtml") })
public class RestFilter implements Filter {
	FilterConfig config = null;

	/**
	 * Default constructor.
	 */
	public RestFilter() {
	
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
		String authHeader = req.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Basic ")) {
		        String credentialsBase64 = authHeader.substring("Basic ".length()).trim();
		        String decodedCredentials = new String(DatatypeConverter.parseBase64Binary(credentialsBase64));
		        String[] userAndPassword = decodedCredentials.split(":");
		        if(userAndPassword.length==2){
		        	try {
						User user = Factories.business.getUsuariosService().findUser(userAndPassword[0]);
						if(user.getPassword().equals(userAndPassword[1])){					
							req.setAttribute("user", user);
							chain.doFilter(req, response);
				        	return;
						}
					} catch (EntityNotFoundException e) {
						Log.error("Usuario no encontrado %s", userAndPassword[0]);
					}
		    	
		        }
		}
		res.sendError(HttpURLConnection.HTTP_UNAUTHORIZED);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// Iniciamos la variable de instancia config
		config = fConfig;
	}

}
