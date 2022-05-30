package com.hungerheroes.backend.model;

import com.hungerheroes.backend.jwt.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FOOD_SHARE")
public class FoodShareModel extends BaseEntity {

    private String title;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = UserModel.class)
    private UserModel userModel;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String phoneNo;

    private Boolean isApproved;

    private String imageLink;

    private Boolean isConfirmed;

    private String confirmedBy;

}
