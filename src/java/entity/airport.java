/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Alex
 */
@Entity
public class airport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer code;
    
    private String city;

    public airport() {
    }
    

    public airport(Integer code, String city) {
        this.code = code;
        this.city = city;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    

    public Integer getId() {
        return code;
    }

    public void setId(Integer code) {
        this.code = code;
    }

    
}
