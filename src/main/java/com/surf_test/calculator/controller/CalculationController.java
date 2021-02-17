package com.surf_test.calculator.controller;

import com.surf_test.calculator.calculations.Calculation;
import com.surf_test.calculator.data.dto.ExpressionDto;
import com.surf_test.calculator.data.dto.HistoryOfComputingDto;
import com.surf_test.calculator.data.models.HistoryOfComputing;
import com.surf_test.calculator.service.HistoryOfComputingService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
     *
     * @param expressionDto - выражение
     * @return HistoryOfComputingDto - объект с данными о вычислении
     * ПРИМЕР json
     * {
     * "expression": "(1+2)*(4+(5-6/3))"
     * }
     */
    @RequestMapping(method = GET, path = "/expression")
    public ResponseEntity<HistoryOfComputingDto> getResult(@RequestBody ExpressionDto expressionDto) {
        Double result;
        HistoryOfComputingDto historyOfComputingDto = null;

        try {
            HistoryOfComputing historyOfComputing = null;
            result = new Calculation(expressionDto.getExpression()).start();
            historyOfComputing = new HistoryOfComputing(expressionDto.getExpression(), result);
            historyOfComputingService.addOrUpdate(historyOfComputing);
            historyOfComputingDto = historyOfComputingService.convertToDto(historyOfComputing);
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
     *
     * @param uuid - id записи
     * @return HistoryOfComputingDto - объект с данными о вычислении
     */
    @RequestMapping(method = GET, path = "/expression/id/{uuid}")
    public ResponseEntity<HistoryOfComputingDto> expressionById(@PathVariable String uuid) {
        HistoryOfComputingDto historyOfComputing = historyOfComputingService.convertToDto(historyOfComputingService.findById(uuid));
        return historyOfComputing != null
                ? new ResponseEntity<HistoryOfComputingDto>(historyOfComputing, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * метод для поиска арифмитического выражения периоду времени
     *
     * @param dateStart, endDate - даты для поиска
     * @return List<HistoryOfComputingDto> - объект с данными о вычислении
     */
    @RequestMapping(method = GET, path = "/expression/period")
    @ResponseBody
    public ResponseEntity<List<HistoryOfComputingDto>> expressionByPeriodOfTime(@RequestParam String dateStart, @RequestParam String endDate) {
        List<HistoryOfComputingDto> historyOfComputingDto = historyOfComputingService.findByPeroidOfTime(dateStart, endDate)
                .stream()
                .collect(Collectors.mapping(e -> historyOfComputingService.convertToDto(e), Collectors.toList()));
        return historyOfComputingDto != null
                ? new ResponseEntity<List<HistoryOfComputingDto>>(historyOfComputingDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * метод для поиска арифмитических выражений по исходному выражению
     *
     * @param expression - id записи
     * @return list<HistoryOfComputing></HistoryOfComputing> - объектов с данными о вычислении
     */
    @RequestMapping(method = GET, path = "/all")
    public ResponseEntity<List<HistoryOfComputingDto>> finAllExpressionForUser(@RequestParam String expression) {
        List<HistoryOfComputingDto> historyOfComputingDto = historyOfComputingService.findByExpression(expression)
                .stream()
                .collect(Collectors.mapping(e -> historyOfComputingService.convertToDto(e), Collectors.toList()));
        return historyOfComputingDto != null
                ? new ResponseEntity<List<HistoryOfComputingDto>>(historyOfComputingDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
