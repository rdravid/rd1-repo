package com.plivo.msgapi;

import java.io.IOException;
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
@Path("/message")
public class MessagesResource {
	
	//public static LocalMessageService mLocalMessageService = new LocalMessageService();
	private static DataBaseMessageService mDBMessageService = new DataBaseMessageService();
	
	//@RolesAllowed("ADMIN")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("start") long start,
									 @QueryParam("size") long size) {		
		
		if(start > 0 && size > 0) {
			return mDBMessageService.getAllMessage(start, size);
		}
		
		return mDBMessageService.getAllMessage();
		
		//return mLocalMessageService.getAllMessages();
		//return mLocalMessageService.addDummyMessages();
	}

	

	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id) {	
		
		//return mLocalMessageService.getMessage(id);
		return mDBMessageService.getMessage(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		//return mLocalMessageService.addMessage(message);
		return mDBMessageService.addMessage(message);
	}
	
/*	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(Message message) {
		return mLocalMessageService.updateMessage(message);
	}
*/	
	@PUT
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id,Message message) {	
		
		//return mLocalMessageService.updateMessage(id, message);
		return mDBMessageService.updateMessage(id,  message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message removeMessage(@PathParam("messageId") long id) {	
		//return mLocalMessageService.removeMessage(id);
		return mDBMessageService.removeMessage(id);
	}
	
	

}
