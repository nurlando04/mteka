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

    public List<Double> getAllTemps(){
        return tempHumRepository.getAllTemps();
    }

    public List<Double> getAllHums(){
        return tempHumRepository.getAllHums();
    }

    public List<Double> getAllIndexes(){
        return tempHumRepository.getAllIndexes();
    }

}
