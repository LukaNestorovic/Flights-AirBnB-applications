package com.example.suites.controller;

import com.example.suites.model.Suite;
import com.example.suites.model.dto.SuitDTO;
import com.example.suites.service.SuiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('GUEST') or hasRole('HOST')")
    public ResponseEntity<List<Suite>> findAll(){
        List<Suite> suites = suiteService.findAll();
        return new ResponseEntity<>(suites, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<Suite> createSuite(@RequestBody Suite suite) throws Exception {
        Suite newSuite = suiteService.create(suite);
        return new ResponseEntity<>(newSuite, HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<?> deleteSuite(@PathVariable("id") String id){
        return new ResponseEntity<>(suiteService.delete(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/host/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<?> deleteAllByHostId(@PathVariable("id") String id){
        suiteService.deleteAllByHostId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<Suite> findSuite(@PathVariable("id") String id){
        Suite suite = suiteService.findOneById(id);
        return new ResponseEntity<>(suite, HttpStatus.OK);
    }

    @GetMapping(path="/host/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<List<Suite>> findSuiteByHostId(@PathVariable("id") String id){
        List<Suite> suites = suiteService.findAllByHostId(id);
        return new ResponseEntity<>(suites, HttpStatus.OK);
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<Suite> updateSuite(@RequestBody Suite newSuiteInfo, @PathVariable("id") String id) {
        Suite retVal = suiteService.update(newSuiteInfo, id);
        if(retVal==null){
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(retVal,HttpStatus.ACCEPTED);
    }

    @PostMapping(path="/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST') or hasRole('HOST')")
    public ResponseEntity<List<Suite>> search(@RequestBody SuitDTO suitDTO){
        List<Suite> suites = suiteService.findAllByLocation(suitDTO);
        return new ResponseEntity<>(suites, HttpStatus.OK);
    }
}
