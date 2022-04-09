package com.example.tempHumProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TempHumRepository extends JpaRepository<TempHum,Integer> {

    @Query("SELECT d.temp FROM TempHum d WHERE d.device_id=?1")
    List<Double> getAllTemps(int device_id);

    @Query("SELECT d.hum FROM TempHum d WHERE d.device_id=?1")
    List<Double> getAllHums(int device_id);

    @Query("SELECT d.id FROM TempHum d WHERE d.device_id=?1")
    List<Double> getAllIndexes(int device_id);

}
