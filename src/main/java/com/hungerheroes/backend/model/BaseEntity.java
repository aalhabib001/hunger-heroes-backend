package com.hungerheroes.backend.model;

import com.hungerheroes.backend.jwt.model.UserModel;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @CreatedBy
    @NotNull
    @Column(name = "created_by", nullable = false, length = 20)
    private String createdBy;

    @CreatedDate
    @NotNull
    private LocalDateTime createdOn;

    @LastModifiedBy
    @NotNull
    @Column(name = "updated_by", nullable = false, length = 20)
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedOn;


}
