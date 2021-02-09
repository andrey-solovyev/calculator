package com.surf_test.calculator.data.repository;

import com.surf_test.calculator.data.models.HistoryOfComputing;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

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
    @Query("select h from HistoryOfComputing h where h.created between TO_TIMESTAMP(?1,'YYYY-MM-DD HH:MI:SS') AND TO_TIMESTAMP(?2,'YYYY-MM-DD HH:MI:SS')")
    List<HistoryOfComputing> findAllByCreated(String date_start, String date_end);
}
