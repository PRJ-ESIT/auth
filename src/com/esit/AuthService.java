package com.esit;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/AuthService")
public class AuthService {
	@POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response validate(MultivaluedMap<String, String> formParams) throws NamingException {
        AuthManager mgr = new AuthManager(formParams);
        String result = mgr.validate();

        JSONObject jsonObj = new JSONObject();
        if(result != null && !result.isEmpty()) {
            jsonObj.put("token", result);
            return Response.status(200).entity(jsonObj + "").build();
        } else {
            return Response.status(400).build();
        }
    }
}

