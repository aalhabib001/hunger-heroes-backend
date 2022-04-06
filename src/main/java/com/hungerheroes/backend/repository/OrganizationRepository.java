package com.hungerheroes.backend.repository;

import com.hungerheroes.backend.model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationModel, Long> {
    List<OrganizationModel> findAllByIsDeleted(boolean b);
}
