package com.plivo.msgapi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.plivo.msgapi.model.DataBaseMessageService;
import com.plivo.msgapi.model.Message;
import com.plivo.msgapi.model.LocalMessageService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/messagedb")
public class CreateMessagesResource {
	
	private static DataBaseMessageService mDBMessageService = new DataBaseMessageService();
	
	//@RolesAllowed("ADMIN")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String createTable() {		
		
		String str = "Table created successfully";

		try {
			mDBMessageService.createMessageTable();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			str = "Table creation failed";
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			str = "Table creation failed";
			e.printStackTrace();
		}
		return str;
		
	}

	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTable() {	
		
		String str = "Table dropped successfully";

		try {
			mDBMessageService.dropMessageTable();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			str = "Table drop failed";
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			str = "Table drop failed";
			e.printStackTrace();
		}
		return str;

		
	}
	
	

}
