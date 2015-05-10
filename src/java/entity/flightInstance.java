/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Alex
 */
@Entity
public class flightInstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String datee;
    private String arrival;
    private String departure;
    private String price;
    private List<Integer> freeSeats;
    
    @OneToOne
    private airport airportFrom;
    
    @OneToOne
    private airport airportTo;
    
    @ManyToOne
    private flight flight;
    
    @OneToMany(mappedBy = "flightInstance")
    private List<reservation> reservations;
    
 
    public flightInstance(String datee, String arrival, String departure, String price, List<Integer> freeSeats) {
        this.datee = datee;
        this.arrival = arrival;
        this.departure = departure;
        this.price = price;
        this.freeSeats = freeSeats;
    }

    public flightInstance() {
    }
    

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Integer> getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(List<Integer> freeSeats) {
        this.freeSeats = freeSeats;
    }
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

      
}
