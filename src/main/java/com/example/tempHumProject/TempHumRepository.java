package com.example.tempHumProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TempHumRepository extends JpaRepository<TempHum,Integer> {

    @Query("SELECT d.temp FROM TempHum d")
    List<Double> getAllTemps();

    @Query("SELECT d.hum FROM TempHum d")
    List<Double> getAllHums();

    @Query("SELECT d.id FROM TempHum d")
    List<Double> getAllIndexes();

}
