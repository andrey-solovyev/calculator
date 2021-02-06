package com.surf_test.calculator.data.repository;

import com.surf_test.calculator.data.models.HistoryOfComputing;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoryOfComputingRepository extends PagingAndSortingRepository<HistoryOfComputing, UUID> {
    /**
     * поиск всех выражений
     */
    @Override
    Iterable<HistoryOfComputing> findAll();

    /**
     * поиск по id, который в формате uuid
     */
    @Query("select h from HistoryOfComputing h where h.id = ?1")
    HistoryOfComputing findById(String id);

    /**
     * поиск всех выражений в бд
     */
    @Query("select h from HistoryOfComputing h where h.originalExpression = ?1")
    Iterator<HistoryOfComputing> findAllByOriginalExpression(String expression);

    /**
     * поиск между датами
     */
//    TEST!!!
//    @Query("select h from HistoryOfComputing h where h.created between TO_TIMESTAMP(date_start,'YYYY-MM-DD HH:MI:SS' AND TO_TIMESTAMP(date_end,'YYYY-MM-DD HH:MI:SS'")
//    Iterator<HistoryOfComputing> findAllByCreated(String date_start, String date_end);
}
