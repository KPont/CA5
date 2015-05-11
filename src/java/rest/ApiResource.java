/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import controllers.Facade;
import controllers.exceptions.NonexistentEntityException;
import entity.Customer;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Kasper
 */
@Path("flights")
public class ApiResource {

    Facade facade = new Facade();
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {

    }

    /**
     * Retrieves representation of an instance of rest.ApiResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Object getJson() {
        return facade.getjson();
    }

    /**
     * PUT method for updating or creating an instance of ApiResource
     *
     * @param pas
     * @param seats
     * @param flightId
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("{flightId}")
    public Object postJson(String pas, List<String> seats, @PathParam("flightId") String flightId) {
        Type collectionType = new TypeToken<List<Customer>>() {
        }.getType();
        List<Customer> customers = gson.fromJson(pas, collectionType);
        return facade.createRes(customers.get(0), seats, Integer.parseInt(flightId));

    }

    @GET
    @Produces("application/json")
    @Path("{startAirport}/{date}")
    public Object getFlight(@PathParam("startAirport") String start, @PathParam("date") String date) throws ParseException {
        return facade.getFlight(start, date);
    }

    @GET
    @Produces("application/json")
    @Path("{startAirport}/{endAirport}/{date}")
    public Object getFlightStartEndDate(@PathParam("startAirport") String start, @PathParam("endAirport") String end, @PathParam("date") String date) {
        return facade.getFlightStartEndDate(start, end, date);
    }

    @GET
    @Produces("application/json")
    @Path("{reservationId}")
    public Object getResId(@PathParam("reservationId") String resId) {
        return facade.getResId(Integer.parseInt(resId));
    }

    @DELETE
    @Produces("application/json")
    @Path("{reservationId}")
    public Object deleteResId(@PathParam("reservationId") String resId) throws NonexistentEntityException {
        return facade.deleteRes(Integer.parseInt(resId));
    }
}
