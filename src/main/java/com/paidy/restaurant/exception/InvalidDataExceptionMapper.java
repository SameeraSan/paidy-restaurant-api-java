package com.paidy.restaurant.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.paidy.restaurant.model.ErrorMessage;
import com.paidy.restaurant.util.UtilMethods;

@Provider
public class InvalidDataExceptionMapper implements ExceptionMapper<InvalidDataException>{

	@Override
	public Response toResponse(InvalidDataException e) {
		e.printStackTrace();
		ErrorMessage error = new ErrorMessage(UtilMethods.getErrorId(), e.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(error).build();
	}

}
