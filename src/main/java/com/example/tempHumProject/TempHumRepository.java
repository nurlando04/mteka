package com.example.tempHumProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TempHumRepository extends JpaRepository<TempHum,Integer> {

    @Query("SELECT d.temperature FROM TempHum d")
    List<Double> getAllTemps();

    @Query("SELECT d.humidity FROM TempHum d")
    List<Double> getAllHums();

    @Query("SELECT d.index FROM TempHum d")
    List<Double> getAllIndexes();

}
