package com.uninter.laridosos.controller;

import com.uninter.laridosos.enumerator.GenderEnum;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @GET
    @Path("/gender")
    public Response getGenders() {
        return Response.ok(GenderEnum.getAllGenders()).build();
    }
}
