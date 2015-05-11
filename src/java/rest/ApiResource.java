/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    Gson gson = new Gson();
    testData td1 = new testData("Airways1", 220.5, "AW311", "07-05-2015", "07-07-2015", "CPH", "MAD", 151, 71, false);
    testData td2 = new testData("Airways2", 220.5, "AW312", "07-07-2015", "07-08-2015", "SGF", "BBK", 152, 72, true);
    testData td3 = new testData("Airways3", 220.5, "AW313", "07-07-2015", "07-13-2015", "CPH", "PAH", 153, 73, false);
    testData td4 = new testData("Airways4", 220.5, "AW314", "07-05-2015", "07-07-2015", "FYV", "DND", 154, 74, true);
    ArrayList<testData> tdList = new ArrayList();
    passengers pas = new passengers("Kasper", "Pont", "Fredby", "Danmark", "Danvang");
    passengers pas2 = new passengers("Chris", "Kron", "Fredby", "Danmark", "Benvej");
    ArrayList<passengers> aPas = new ArrayList();
    reservation res = new reservation("a358sd", "AW311", aPas, 441.3);
    ArrayList<reservation> aRes = new ArrayList();
//    facade fac = new facade();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
        
          
          tdList.add(td1);
          tdList.add(td2);
          tdList.add(td3);
          tdList.add(td4);
          
          res.getPassengers().add(pas);
          res.getPassengers().add(pas2);
          aRes.add(res);
    }

    /**
     * Retrieves representation of an instance of rest.ApiResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        String str = "";
        for(int i = 0; i < tdList.size(); i++) {
            str += gson.toJson(tdList.get(i));
        }
             
        return str;
    }

    /**
     * PUT method for updating or creating an instance of ApiResource
     * @param pas
     * @param flightId
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("{flightId}")
    public String postJson(String pas, @PathParam("flightId") String flightId) {
        String str = "";
        boolean excp = true;
        for (int i = 0; i < tdList.size(); i++) {
            if(tdList.get(i).getFlightId().equals(flightId)){
                excp = false;
            }
        }
        if(excp){
            throw new NotValidException("No flight found with the given ID");
        }
        Type collectionType = new TypeToken<ArrayList<passengers>>(){}.getType();
        ArrayList<passengers> list = gson.fromJson(pas, collectionType);
        
        reservation res = new reservation("a358sd", flightId, list, 441.3);
        str = gson.toJson(res);
        return str;
    }
    
    @GET
    @Produces("application/json")
    @Path("{startAirport}/{date}")
    public String getFlight(@PathParam("startAirport") String start, @PathParam("date") String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date datee = df.parse(date);
        //Date datee = new Date(date);
        long utcDateInMil = datee.getTime();
        String str = "";
        boolean fail = true;
        for (int i = 0; i < tdList.size(); i++) {
            if(tdList.get(i).getDeparture().equals(start) && tdList.get(i).getTakeOffDate().equals(date)){
                str += gson.toJson(tdList.get(i));
                fail = false;
            }
        }
        if(fail){
            throw new NotValidException("No flight found with the given ID");
        }
        return str;
    }
    
    @GET
    @Produces("application/json")
    @Path("{startAirport}/{endAirport}/{date}")
    public String getFlightStartEndDate(@PathParam("startAirport") String start, @PathParam("endAirport") String end, @PathParam("date") String date) {
        String str = "";
        boolean fail = true;
        for (int i = 0; i < tdList.size(); i++) {
            if(tdList.get(i).getDeparture().equals(start) && tdList.get(i).getDestination().equals(end) && (tdList.get(i).getTakeOffDate().equals(date) || tdList.get(i).getLandingDate().equals(date))){
                str += gson.toJson(tdList.get(i));
                fail = false;
            }
        }
        if(fail){
            throw new WebApplicationException("No flight found with the given ID",Response.Status.NOT_FOUND);
        }
        return str;
    }
    
    @GET
    @Produces("application/json")
    @Path("{reservationId}")
    public String getResId(@PathParam("reservationId") String resId) {
        String str = "";
        for (int i = 0; i < aRes.size(); i++) {
            if(aRes.get(i).getReservationID().equals(resId)){
                str += gson.toJson(aRes.get(i));
            }
        }
        return str;
    }
    @DELETE
    @Produces("application/json")
    @Path("{reservationId}")
    public String deleteResId(@PathParam("reservationId") String resId) {
        String str = "";
        for (int i = 0; i < aRes.size(); i++) {
            if(aRes.get(i).getReservationID().equals(resId)){
                str += gson.toJson(aRes.get(i));
                aRes.remove(i);
            }
        }
        return str;
    }
}


