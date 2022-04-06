package com.hungerheroes.backend.controller;

import com.hungerheroes.backend.dto.ApiMessageResponse;
import com.hungerheroes.backend.dto.request.OrganizationRequest;
import com.hungerheroes.backend.model.OrganizationModel;
import com.hungerheroes.backend.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<ApiMessageResponse> createOrganization(@RequestBody OrganizationRequest organizationRequest) {
        return organizationService.createOrganization(organizationRequest);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationModel>> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiMessageResponse> updateOrganization(@PathVariable("id") Long id, @RequestBody OrganizationRequest organizationRequest) {
        return organizationService.updateOrganization(id, organizationRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiMessageResponse> deleteOrganization(@PathVariable("id") Long id) {
        return organizationService.deleteOrganization(id);
    }

}
