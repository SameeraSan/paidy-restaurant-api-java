package com.paidy.restaurant.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.paidy.restaurant.database.DatabaseProcessing;
import com.paidy.restaurant.exception.InvalidDataException;
import com.paidy.restaurant.model.Items;

@Path("/item")
public class TablelService {

	/*
	 * Get all Items per Table Service
	 * */
	@RolesAllowed("ADMIN")
	@GET
	@Path("/{tableNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Items> getItems(@PathParam("tableNo") int tableNo) throws Exception {
		if(tableNo == 0)
			throw new InvalidDataException("Invalid Table Number");
		
		return DatabaseProcessing.getTableItems(tableNo);
	}
	
	/*
	 * Get data by Table and Item Service
	 * */
	@GET
	@Path("/{tableNo}/{itemNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItems(@PathParam("tableNo") int tableNo, @PathParam("itemNo") int itemNo) throws Exception {
		
		if(tableNo == 0 || itemNo == 0)
			throw new InvalidDataException("Invalid Table Number or Item Number");
		
		Items item =  DatabaseProcessing.getTableSpecifiedItems(tableNo, itemNo);
		
		if(item != null) {
			return Response.status(Status.OK).entity(item).build();
		} else {
			return Response.status(Status.NO_CONTENT).build();
		}
	}
	
}
