package com.plivo.msgapi;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
 
import com.plivo.msgapi.AuthenticationFilter;

 
public class CustomApplication// extends ResourceConfig
{
    public CustomApplication()
    {
      /*  packages("com.plivo.msgapi");
        register(LoggingFilter.class);
        register(GsonMessageBodyHandler.class);
 
        //Register Auth Filter here
        register(AuthenticationFilter.class);*/
    }
}