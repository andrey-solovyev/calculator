package com.surf_test.calculator.data.repository;

import com.surf_test.calculator.data.models.HistoryOfComputing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
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
    @Query("select h from HistoryOfComputing h where h.id = :id")
    HistoryOfComputing findById(@Param("id") String id);

    /**
     * поиск всех выражений в бд
     */
    @Query("select h from HistoryOfComputing h where h.originalExpression = :expression")
    List<HistoryOfComputing> findAllByOriginalExpression(@Param("expression") String expression);

    /**
     * поиск между датами
     */
//    TEST!!!
    @Query("select h from HistoryOfComputing h where h.created between TO_TIMESTAMP(:date_start,'YYYY-MM-DD') AND TO_TIMESTAMP(:date_end,'YYYY-MM-DD')")
    List<HistoryOfComputing> findAllByCreated(@Param("date_start") String date_start,@Param("date_end") String date_end);
}
