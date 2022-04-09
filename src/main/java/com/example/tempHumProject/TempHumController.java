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

    @GetMapping("/getTemps")
    public List<Double> getAllTemps(){
        List<Double> list= tempHumService.getAllTemps();
        return list;
    }

    @GetMapping("/getHums")
    public List<Double> getAllHums(){
        List<Double> list= tempHumService.getAllHums();
        return list;
    }

    @GetMapping("/getIndexes")
    public List<Double> getAllIndexes(){
        List<Double> list= tempHumService.getAllIndexes();
        System.out.println(list.toString());
        return list;
    }


}
