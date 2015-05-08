/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kasper
 */
public class NotValidException extends WebApplicationException{
    public NotValidException(String message) {
         super(Response.status(Response.Status.BAD_REQUEST)
             .entity(message).type(MediaType.TEXT_PLAIN).build());
     }
}
