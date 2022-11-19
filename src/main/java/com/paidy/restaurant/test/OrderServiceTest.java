package com.paidy.restaurant.test;


import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.paidy.restaurant.service.OrderService;


public class OrderServiceTest extends JerseyTest{

	
	/*
	 * Implement only few test cases for demo purposes
	 * */

	@Override
    protected Application configure() {
        return new ResourceConfig(OrderService.class);
    }
	
	@Test
	public void testSuccessCreateOrder() {
		
		
	    Response response = target("/order/create").request()
	    		.post(Entity.json("{\r\n" + 
	    				"    \"orderId\" : \"Order 1\",\r\n" + 
	    				"    \"tableId\":1,\r\n" + 
	    				"    \"notes\":\"hot\",\r\n" + 
	    				"    \"items\":[{\r\n" + 
	    				"        \"itemNo\":5,\r\n" + 
	    				"        \"quantity\":2   \r\n" + 
	    				"    }, {\r\n" + 
	    				"        \"itemNo\":6,\r\n" + 
	    				"        \"quantity\":3   \r\n" + 
	    				"    }\r\n" + 
	    				"    ]\r\n" + 
	    				"}"));

	    assertEquals("Http Response should be 201: ", Status.CREATED.getStatusCode(), response.getStatus());
	    assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	    assertEquals("Content of ressponse is: ", "The Order Placed!", response.readEntity(String.class));
	    
	}
	
	

	
	@Test
	public void testSuccessDeleteItem() {
	    Response response = target("/order/delete/1/1").request()
	        .delete();

	    assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	    assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

	}


}
