package com.paidy.restaurant.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.paidy.restaurant.database.DatabaseProcessing;
import com.paidy.restaurant.exception.InvalidDataException;
import com.paidy.restaurant.model.Order;

@Path("/order")
public class OrderService {
	
	/*
	 * Create order Service
	 * */
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createOrder(Order order) throws Exception {

		
		if(order == null || order.getOrderId() == null || order.getOrderId().isEmpty()) {
			throw new InvalidDataException("The Order ID cannot be empty");
		}
			
		if(order.getTableId() == 0) {
			throw new InvalidDataException("Invalid Table ID");
		}
		
		if(order.getItems() == null || order.getItems().size() < 1) {
			throw new InvalidDataException("Need at least one item per order");
		}
		
		DatabaseProcessing.insertOrder(order);
		System.out.println("The Order is created");
		
		return Response.status(Status.CREATED).entity("The Order Placed!").build();
	}

	
	/*
	 * Delete item from Table Service
	 * */
	
	@DELETE
	@Path("/delete/{tableNo}/{itemNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteItemFromOrder(@PathParam("tableNo") int tableNo, @PathParam("itemNo") int itemNo) throws Exception {
		
		if(tableNo == 0 || itemNo == 0)
			throw new InvalidDataException("Invalid Table Number or Item Number");
		
		if(DatabaseProcessing.deleteOrder(tableNo,itemNo) > 0) {
			return "Item Deleted from the Order";
		} else {
			return "No Item Deleted from the Order";
		}
	}
	
	
}
