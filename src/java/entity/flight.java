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
import javax.persistence.OneToMany;

/**
 *
 * @author Alex
 */
@Entity
public class flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String type;
    private List<String> totalSeats;
    @OneToMany(mappedBy = "flight")
    private List<flightInstance> flightInstances;

    public flight() {
    }
    
    public flight(String type, List<String> totalSeats) {
        this.type = type;
        this.totalSeats = totalSeats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(List<String> totalSeats) {
        this.totalSeats = totalSeats;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    
}
