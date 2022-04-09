package com.example.tempHumProject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TempHumService {

    TempHumRepository tempHumRepository;


    public TempHumService(TempHumRepository tempHumRepository) {
        this.tempHumRepository = tempHumRepository;
    }

    public void saveTempHum(TempHum tempHum){
        tempHumRepository.saveAndFlush(tempHum);
    }

    public List<TempHum> getAllTempHums(){
        return tempHumRepository.findAll();
    }

    public List<Double> getAllTemps(int device_id){
        return tempHumRepository.getAllTemps(device_id);
    }

    public List<Double> getAllHums(int device_id){
        return tempHumRepository.getAllHums(device_id);
    }

    public List<Double> getAllIndexes(int device_id){
        return tempHumRepository.getAllIndexes(device_id);
    }

}
