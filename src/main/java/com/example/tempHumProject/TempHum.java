package com.example.tempHumProject;

import com.example.tempHumProject.security.Role;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="temphumdata")
@Data
public class TempHum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    @Column(name="temp")
    private double temp;
    @Column(name="hum")
    private double hum;
    @Column(name="sent_date")
    private Date sent_date;
    @Column(name = "device_id")
    private int device_id;
    private Role role;



    public TempHum() {
    }

    public TempHum(double temperature, double humidity, int id) {
        this.temp = temperature;
        this.hum = humidity;
        this.id = id;
    }
}
