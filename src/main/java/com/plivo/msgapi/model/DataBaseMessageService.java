package com.plivo.msgapi.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DataBaseMessageService {
	
	
	
	private  Connection mConnection = null;
	
	public DataBaseMessageService() {
	}
	
	
	
	
	public void createMessageTable() throws URISyntaxException, SQLException {
		
		if(null == mConnection) {
			mConnection = getConnection();
		}
        
		//String dummy="'message dummy'";
		Statement stmt = mConnection.createStatement();
        //stmt.executeUpdate("DROP TABLE IF EXISTS messages");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS messages (msg_id serial PRIMARY KEY, msg_body VARCHAR (100) NOT NULL, created_on timestamp)");
        System.out.println("messages table created!!!");
       /* stmt.executeUpdate("INSERT INTO messages(msg_body,created_on) VALUES ("+dummy+", now())");
        ResultSet rs = stmt.executeQuery("SELECT * FROM messages");
        while (rs.next()) {
            System.out.println("Read from DB: " + rs.getString("msg_body") + " " + rs.getTimestamp("created_on"));
        }*/
	}
	
	public void dropMessageTable() throws URISyntaxException, SQLException {
		
		if(null == mConnection) {
			mConnection = getConnection();
		}
 		
		Statement stmt = mConnection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS messages");
        System.out.println("messages table dropped!!!");
        
	}

	
	
	public List<Message> getAllMessage()  {
		
		ArrayList<Message> arrList = null;
		try {
		if(null == mConnection) {
			mConnection = getConnection();
		}

        Statement stmt = mConnection.createStatement();   
        ResultSet rs = stmt.executeQuery("SELECT * FROM messages");
        arrList = new ArrayList<>();
        while (rs.next()) {
            System.out.println("Read from DB: " + rs.getString("msg_body") + " " + rs.getTimestamp("created_on"));
            arrList.add(new Message(rs.getInt("msg_id"),rs.getString("msg_body"),rs.getTimestamp("created_on")));
            
        }
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
        return arrList;
	}
	
	public List<Message> getAllMessage(long start, long size)  {
		
		ArrayList<Message> arrList = null;
		try {
		if(null == mConnection) {
			mConnection = getConnection();
		}

        Statement stmt = mConnection.createStatement();   
        ResultSet rs = stmt.executeQuery("SELECT * FROM messages order by msg_id offset " + start + " limit " + size);
        arrList = new ArrayList<>();
        while (rs.next()) {
            System.out.println("Read from DB: " + rs.getString("msg_body") + " " + rs.getTimestamp("created_on"));
            arrList.add(new Message(rs.getInt("msg_id"),rs.getString("msg_body"),rs.getTimestamp("created_on")));
            
        }
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
        return arrList;
	}

	
	public Message getMessage(long id) {
		
		Message msg = null;
		
		try {
			 msg = getMessageFromDB(id);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}

	private  Message getMessageFromDB(long id) throws URISyntaxException, SQLException {
		
		if(null == mConnection) {
			mConnection = getConnection();
		}

        Statement stmt = mConnection.createStatement();   
                
        ResultSet rs = stmt.executeQuery("SELECT * FROM messages where msg_id=" + id);
        rs.next();
        
        Message msg = new Message(id, rs.getString("msg_body"),rs.getTimestamp("created_on"));
        
        rs.close();
        stmt.close();
               
        return msg;
	}
	

	public Message removeMessage(long id) {
		
		Message msg = null;
		
		try {
			 msg = removeMessageFromDB(id);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}

	private  Message removeMessageFromDB(long id) throws URISyntaxException, SQLException {
		
		if(null == mConnection) {
			mConnection = getConnection();
		}

        Statement stmt = mConnection.createStatement();   
                
        ResultSet rs = stmt.executeQuery("SELECT * FROM messages where msg_id=" + id);
        rs.next();
        
        Message msg = new Message(id, rs.getString("msg_body"),rs.getTimestamp("created_on"));
        
        rs.close();
        stmt.close();
        
        Statement stmt1 = mConnection.createStatement();
        stmt1.executeUpdate("DELETE FROM messages where msg_id=" + id);
        stmt1.close();
       
               
        return msg;
	}


	public Message addMessage(Message message) {
		
		Message msg = null;
		
		try {
			msg = insertMessage(message.getMsgBody());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	
	private Message insertMessage(String message) throws URISyntaxException, SQLException {
		
		if(null == mConnection) {
			mConnection = getConnection();
		}

        Statement stmt = mConnection.createStatement();   
        ResultSet rs = stmt.executeQuery("INSERT INTO messages(msg_body,created_on) VALUES ("  + "'" + message + "'" +  ", now())" + " RETURNING msg_id");
        rs.next();
        int new_id = rs.getInt("msg_id");
        System.out.println("created new id: " +  new_id);
        
        
        rs = stmt.executeQuery("SELECT * FROM messages where msg_id=" + new_id);
        rs.next();
        
        Message msg = new Message(new_id, rs.getString("msg_body"),rs.getTimestamp("created_on"));
        
        rs.close();
        stmt.close();
               
        return msg;
	}
	
	
	public Message updateMessage(long id , Message message) {
		
		Message msg = null;
		
		try {
			msg = updateMessageInDB(id , message.getMsgBody());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	private  Message updateMessageInDB(long id, String message) throws URISyntaxException, SQLException {
		
		if(null == mConnection) {
			mConnection = getConnection();
		}

        Statement stmt = mConnection.createStatement();   
        String sql = "UPDATE messages set msg_body = " + "'" + message + "'" + " where msg_id=" + id;
        stmt.executeUpdate(sql);
        
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM messages where msg_id=" + id);
        rs.next();
        
        Message msg = new Message(id, rs.getString("msg_body"),rs.getTimestamp("created_on"));
        
        rs.close();
        stmt.close();
               
        return msg;
	}


	
	private  Connection getConnection() throws URISyntaxException, SQLException {
		
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
	    return DriverManager.getConnection(dbUrl);

/*		
		  if(null == System.getenv("DATABASE_URL")) {
	        	System.out.println("DATABASE_URL null");
	        	return null;
	      }else {
	    	  System.out.println("DATABASE_URL: " + System.getenv("DATABASE_URL"));
	      }
		  
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
      

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
*/   
	    }
	
}
