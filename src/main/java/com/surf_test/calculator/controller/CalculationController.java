package com.surf_test.calculator.controller;

import com.surf_test.calculator.data.models.HistoryOfComputing;
import com.surf_test.calculator.data.repository.HistoryOfComputingRepository;
import com.surf_test.calculator.service.HistoryOfComputingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/v1/calculation")
public class CalculationController {
    private HistoryOfComputingService historyOfComputingService;

    @Autowired
    public CalculationController(HistoryOfComputingService historyOfComputingService) {
        this.historyOfComputingService = historyOfComputingService;
    }




    @RequestMapping(method = GET,path = "/{value}")
    public ResponseEntity<Iterable<HistoryOfComputing>> getResult(@PathVariable String value){
        return null;
    }
}
