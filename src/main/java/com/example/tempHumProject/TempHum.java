package com.example.tempHumProject;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table(name="temptable")
@Data
public class TempHum {
    @Column(name="temperature")
    private double temperature;
    @Column(name="humidity")
    private double humidity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int index;

    public TempHum() {
    }

    public TempHum(double temperature, double humidity, int index) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.index = index;
    }
}
