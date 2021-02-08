package com.surf_test.calculator.controller;

import com.surf_test.calculator.calculations.Calculation;
import com.surf_test.calculator.data.models.HistoryOfComputing;
import com.surf_test.calculator.data.repository.HistoryOfComputingRepository;
import com.surf_test.calculator.service.HistoryOfComputingService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/v1/calculation")
@Slf4j
public class CalculationController {
    private static Logger log = LoggerFactory.getLogger(CalculationController.class);

    private HistoryOfComputingService historyOfComputingService;

    @Autowired
    public CalculationController(HistoryOfComputingService historyOfComputingService) {
        this.historyOfComputingService = historyOfComputingService;
    }

    /**
     * метод для вычисления арифмитического выражения
     * @param value - выражение
     * @return HistoryOfComputing - объект с данными о вычислении
     */
    @RequestMapping(method = GET, path = "/expression={value}")
    public ResponseEntity<HistoryOfComputing> getResult(@PathVariable String value) {
        Double result;
        HistoryOfComputing historyOfComputing = null;
        try {
            result = Calculation.calculate(value);
            historyOfComputing = new HistoryOfComputing(value, result);
            historyOfComputingService.addOrUpdate(historyOfComputing);
        } catch (Exception e) {
            result = null;
            log.error(e.toString());
        }
        return result != null && historyOfComputing != null
                ? new ResponseEntity<HistoryOfComputing>(historyOfComputing, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /**
     * метод для поиска арифмитического выражения по id
     * @param uuid - id записи
     * @return HistoryOfComputing - объект с данными о вычислении
     */
    @RequestMapping(method = GET,path = "/expression/{id}")
    public ResponseEntity<HistoryOfComputing> expressionById(@PathVariable String uuid){
         HistoryOfComputing historyOfComputing = historyOfComputingService.findById(uuid);
        return historyOfComputing != null
                ? new ResponseEntity<HistoryOfComputing>(historyOfComputing, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * метод для поиска арифмитических выражений по исходному выражению
     * @param expression - id записи
     * @return list<HistoryOfComputing></HistoryOfComputing> - объектов с данными о вычислении
     */

    @RequestMapping(method = GET,path = "/all/{expression}")
    public ResponseEntity<HistoryOfComputing> finAllExpressionForUser(@PathVariable String expression){
        HistoryOfComputing historyOfComputing = historyOfComputingService.findByExpression(expression);
        return historyOfComputing != null
                ? new ResponseEntity<HistoryOfComputing>(historyOfComputing, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
