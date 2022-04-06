package com.hungerheroes.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORGANIZATION")
public class OrganizationModel extends BaseEntity {

    private String orgName;

    private String orgDescription;

    private String orgWebsite;

    private String orgPhone;

    private String orgAddress;

    private String orgImageLink;
}
