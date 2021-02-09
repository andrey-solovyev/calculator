package com.surf_test.calculator.service;

import com.surf_test.calculator.data.dto.HistoryOfComputingDto;
import com.surf_test.calculator.data.models.HistoryOfComputing;
import com.surf_test.calculator.data.repository.HistoryOfComputingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryOfComputingService {
    private HistoryOfComputingRepository historyOfComputingRepository;

    public HistoryOfComputingService(HistoryOfComputingRepository historyOfComputingRepository) {
        this.historyOfComputingRepository = historyOfComputingRepository;
    }

    public List<HistoryOfComputing> getAllHistoryOfComputing() {
        ArrayList<HistoryOfComputing> historyOfComputings = new ArrayList<>();
        historyOfComputingRepository.findAll().forEach(historyOfComputings::add);
        return historyOfComputings;
    }

    public void addOrUpdate(HistoryOfComputing historyOfComputing) {
        this.historyOfComputingRepository.save(historyOfComputing);
    }

    public HistoryOfComputing findById(String uuid) {
        return this.historyOfComputingRepository.findById(uuid);
    }

    public List<HistoryOfComputing> findByExpression(String expression) {
        return this.historyOfComputingRepository.findAllByOriginalExpression(expression);

    }
    public List<HistoryOfComputing> findByPeroidOfTime(String startDate,String endDate){
        List<HistoryOfComputing> historyOfComputings = historyOfComputingRepository.findAllByCreated(startDate,endDate);
        return historyOfComputings;
    }
    public HistoryOfComputingDto convertToDto(HistoryOfComputing historyOfComputing){
        HistoryOfComputingDto historyOfComputingDto=new HistoryOfComputingDto();
        historyOfComputingDto.setResult(historyOfComputing.getResult());
        historyOfComputingDto.setOriginalExpression(historyOfComputing.getOriginalExpression());
        historyOfComputingDto.setId(historyOfComputing.getId());
        return historyOfComputingDto;
    }
}
