package com.paidy.restaurant.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;

import com.paidy.restaurant.model.Items;
import com.paidy.restaurant.service.TablelService;


public class TableServiceTest extends JerseyTest  {
	
	/*
	 * Implement only few test cases for demo purposes
	 * */

	@Override
    protected Application configure() {
        return new ResourceConfig(TablelService.class);
    }
	
	@Test
	public void testSuccessTableNoAndItemNo() {
	    Response response = target("/item/1/1").request()
	        .get();

	    assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	    assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

	    Items item = response.readEntity(Items.class);
	    assertEquals(1, item.getItemNo());
	    assertEquals("item 1", item.getItemName());
	    assertTrue(item.getCookTime() >= 5 && item.getCookTime() <= 15);
	}
	
	@Test
	public void testFailableNoAndItemNoContentNotFound() {
	    Response response = target("/item/10/1").request()
	        .get();

	    assertEquals("Http Response should be 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());

	}
	
	@Test
	public void testSuccessTableNo() {
	    Response response = target("/item/1").request()
	        .get();

	    assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	    assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

	    List<Items> item =  response.readEntity(new GenericType<List<Items>>() {});
	    assertTrue(item.size() == 2);
	}
	
	@Test
	public void testFailTableNoEmpltyList() {
	    Response response = target("/item/10").request()
	        .get();

	    assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	    assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

	    List<Items> item =  response.readEntity(new GenericType<List<Items>>() {});
	    assertTrue(item.size() == 0);
	}
}