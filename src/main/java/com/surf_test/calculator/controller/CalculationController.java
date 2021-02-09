package com.surf_test.calculator.controller;

import com.surf_test.calculator.calculations.Calculation;
import com.surf_test.calculator.data.dto.ExpressionDto;
import com.surf_test.calculator.data.dto.HistoryOfComputingDto;
import com.surf_test.calculator.data.models.HistoryOfComputing;
import com.surf_test.calculator.data.repository.HistoryOfComputingRepository;
import com.surf_test.calculator.service.HistoryOfComputingService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
     * @param expressionDto - выражение
     * @return HistoryOfComputing - объект с данными о вычислении
     * ПРИМЕР json
     * {
     *     "expression": "(1+2)*(4+(5-6/3))"
     * }
     */
    @RequestMapping(method = GET, path = "/expression")
    public ResponseEntity<HistoryOfComputingDto> getResult(@RequestBody ExpressionDto expressionDto) {
        Double result;
        HistoryOfComputingDto historyOfComputingDto  = null;

        try {
            HistoryOfComputing historyOfComputing = null;
            result = Calculation.calculate(expressionDto.getExpression());
            historyOfComputing = new HistoryOfComputing(expressionDto.getExpression(),result);
            historyOfComputingService.addOrUpdate(historyOfComputing);
            historyOfComputingDto =historyOfComputingService.convertToDto(historyOfComputing);
        } catch (Exception e) {
            result = null;
            log.error(e.toString());
        }
        return result != null && historyOfComputingDto != null
                ? new ResponseEntity<HistoryOfComputingDto>(historyOfComputingDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /**
     * метод для поиска арифмитического выражения по id
     * @param uuid - id записи
     * @return HistoryOfComputing - объект с данными о вычислении
     */
    @RequestMapping(method = GET,path = "/expression/id/{uuid}")
    public ResponseEntity<HistoryOfComputingDto> expressionById(@PathVariable String uuid){
         HistoryOfComputingDto historyOfComputing = historyOfComputingService.convertToDto(historyOfComputingService.findById(uuid));
        return historyOfComputing != null
                ? new ResponseEntity<HistoryOfComputingDto>(historyOfComputing, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * метод для поиска арифмитического выражения по id
     * @param uuid - id записи
     * @return HistoryOfComputing - объект с данными о вычислении
     */
    @RequestMapping(method = GET,path = "/expression/period?dateStart={dateStart}?endDate={endDate}")
    public ResponseEntity<List<HistoryOfComputingDto>> expressionByPeriodOfTime(@PathVariable String dateStart,@PathVariable String endDate){
        HistoryOfComputingDto historyOfComputing = historyOfComputingService.convertToDto(historyOfComputingService.f(uuid));
        return historyOfComputing != null
                ? new ResponseEntity<HistoryOfComputing>(historyOfComputing, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * метод для поиска арифмитических выражений по исходному выражению
     * @param expression - id записи
     * @return list<HistoryOfComputing></HistoryOfComputing> - объектов с данными о вычислении
     */

//    @RequestMapping(method = GET,path = "/all/{expression}")
//    public ResponseEntity<HistoryOfComputing> finAllExpressionForUser(@PathVariable String expression){
//        List<HistoryOfComputing> historyOfComputing = historyOfComputingService.findByExpression(expression);
//        return historyOfComputing != null
//                ? new ResponseEntity<HistoryOfComputing>(historyOfComputing, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
