package com.example.demo.controller;

import com.example.demo.model.Suite;
import com.example.demo.model.dto.SuitDTO;
import com.example.demo.model.dto.UpdateSuiteDTO;
import com.example.demo.service.SuiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suites")
@CrossOrigin(origins = "*")
public class SuiteController {

    private final SuiteService suiteService;
    public SuiteController(SuiteService suiteService){
        this.suiteService = suiteService;
    }

    @GetMapping
    public ResponseEntity<List<Suite>> findAll(){
        List<Suite> suites = suiteService.findAll();
        return new ResponseEntity<>(suites, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Suite> createSuite(@RequestBody Suite suite) throws Exception {
        Suite newSuite = suiteService.create(suite);
        return new ResponseEntity<>(newSuite, HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> deleteSuite(@PathVariable("id") String id){
        return new ResponseEntity<>(suiteService.delete(id), HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Suite> findSuite(@PathVariable("id") String id){
        Suite suite = suiteService.findOneById(id);
        System.out.println(suite.getName());
        return new ResponseEntity<>(suite, HttpStatus.OK);
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Suite> updateSuite(@RequestBody Suite newSuiteInfo, @PathVariable("id") String id) {
        Suite retVal = suiteService.update(newSuiteInfo, id);
        if(retVal==null){
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(retVal,HttpStatus.ACCEPTED);
    }

    @PostMapping(path="/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Suite>> search(@RequestBody SuitDTO suitDTO){
        List<Suite> suites = suiteService.findAllByLocation(suitDTO);
        return new ResponseEntity<>(suites, HttpStatus.OK);
    }
}
