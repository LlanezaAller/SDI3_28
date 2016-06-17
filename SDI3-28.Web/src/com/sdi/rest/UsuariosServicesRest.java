package com.sdi.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.SimpleResponse;
import com.sdi.model.Trip;
import com.sdi.model.User;

@Path("/UsuariosService")
public interface UsuariosServicesRest {
	@POST
	@Path("/save")
	@Consumes({MediaType.APPLICATION_XML})
	void saveUser(User user) throws EntityAlreadyExistsException;
	
	@GET
	@Path("/findUser/{login}")
	@Produces({MediaType.APPLICATION_XML})
	User findUser(@PathParam("login")String user) throws EntityNotFoundException;

	@DELETE
	@Path("/cancelApplication/{login}/{tripID}")
	@Produces({MediaType.APPLICATION_XML})
	Trip cancelarAplicacion(@PathParam("login") String login,@PathParam("tripID") Long tripId);
	
	@POST
	@Path("/apply/{login}/{tripID}")
	@Produces({MediaType.APPLICATION_XML})
	Trip aplicar(@PathParam("login") String login,@PathParam("tripID") Long tripId);

	@PUT
	@Path("/confirmUser")
	@Produces({MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	Trip confirmUser(@FormParam("userLogin") String login, @FormParam("tripID") Long tripID);
	
	@POST
	@Path("/applyUserSet")
	@Consumes({MediaType.APPLICATION_XML})
	void aplicarUsuarios(List<User> apps, Trip trip);

	@GET
	@Path("/findAll")
	List<User> findAll();

	@GET
	@Path("/findUnconfirmedUsersByTrip/{id}")
	@Produces({MediaType.APPLICATION_XML})
	List<User> findUnconfirmedUsersByTrip(@PathParam("id") long id);

	@PUT
	@Path("/disable/{login}")
	void disableUser(@PathParam("login") String login);
	
	@GET
	@Path("/getID")
	@Produces({MediaType.APPLICATION_XML})
	SimpleResponse<Long> getIdByLogin(@Context HttpServletRequest request) throws EntityNotFoundException;

}
