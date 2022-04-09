package com.example.tempHumProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class TempHumController {

    final TempHumService tempHumService;

    public TempHumController(TempHumService tempHumService) {
        this.tempHumService = tempHumService;
    }

    @PostMapping("/post")
    public void sendData(@RequestBody TempHum tempHum){
        tempHumService.saveTempHum(tempHum);
    }

    @GetMapping("/get")
    public List<TempHum> getAllTempHums(){
        List<TempHum> list= tempHumService.getAllTempHums();
        return list;
    }

    @PostMapping("/getTemps")
    public List<Double> getAllTemps(@RequestBody int device_id){
        List<Double> list= tempHumService.getAllTemps(device_id);
        return list;
    }

    @PostMapping("/getHums")
    public List<Double> getAllHums(@RequestBody int device_id){
        List<Double> list= tempHumService.getAllHums(device_id);
        return list;
    }

    @PostMapping("/getIndexes")
    public List<Double> getAllIndexes(@RequestBody int device_id){
        List<Double> list= tempHumService.getAllIndexes(device_id);
        System.out.println(list.toString());
        return list;
    }
}
