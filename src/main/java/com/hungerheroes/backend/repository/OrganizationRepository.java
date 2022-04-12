package com.hungerheroes.backend.repository;

import com.hungerheroes.backend.model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationModel, Long> {
    List<OrganizationModel> findAllByIsDeleted(boolean b);

    Optional<OrganizationModel> findByIsDeletedAndCreatedBy(boolean b, String userName);
}
