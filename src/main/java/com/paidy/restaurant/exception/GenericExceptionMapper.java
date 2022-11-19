package com.paidy.restaurant.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.paidy.restaurant.model.ErrorMessage;
import com.paidy.restaurant.util.UtilMethods;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception>{

	@Override
	public Response toResponse(Exception e) {
		e.printStackTrace();
		ErrorMessage error = new ErrorMessage(UtilMethods.getErrorId(), e.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}

}
