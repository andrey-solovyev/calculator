package com.surf_test.calculator.service;

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
}
