package com.hungerheroes.backend.service;

import com.hungerheroes.backend.dto.ApiMessageResponse;
import com.hungerheroes.backend.dto.request.OrganizationRequest;
import com.hungerheroes.backend.model.OrganizationModel;
import com.hungerheroes.backend.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public ResponseEntity<ApiMessageResponse> createOrganization(OrganizationRequest organizationRequest) {
        OrganizationModel organizationModel = new OrganizationModel();
        BeanUtils.copyProperties(organizationRequest, organizationModel);

        organizationRepository.save(organizationModel);

        return new ResponseEntity<>(new ApiMessageResponse(201, "Organization created successfully"),
                HttpStatus.CREATED);
    }

    public ResponseEntity<List<OrganizationModel>> getAllOrganizations() {
        return new ResponseEntity<>(organizationRepository.findAllByIsDeleted(false), HttpStatus.OK);
    }

    public ResponseEntity<ApiMessageResponse> updateOrganization(Long id, OrganizationRequest organizationRequest) {
        OrganizationModel organizationModel = organizationRepository.findById(id).get();
        BeanUtils.copyProperties(organizationRequest, organizationModel);

        organizationRepository.save(organizationModel);

        return new ResponseEntity<>(new ApiMessageResponse(200, "Organization updated successfully"),
                HttpStatus.OK);
    }

    public ResponseEntity<ApiMessageResponse> deleteOrganization(Long id) {
        OrganizationModel organizationModel = organizationRepository.findById(id).get();
        organizationModel.setIsDeleted(true);
        organizationRepository.save(organizationModel);

        return new ResponseEntity<>(new ApiMessageResponse(200, "Organization deleted successfully"),
                HttpStatus.OK);
    }
}
