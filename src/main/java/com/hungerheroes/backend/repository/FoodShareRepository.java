package com.hungerheroes.backend.repository;

import com.hungerheroes.backend.model.FoodShareModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodShareRepository extends JpaRepository<FoodShareModel, Long> {

    Page<FoodShareModel> findAllByIsApprovedAndIsDeleted(Boolean isApproved, boolean b, Pageable pageable);
}
