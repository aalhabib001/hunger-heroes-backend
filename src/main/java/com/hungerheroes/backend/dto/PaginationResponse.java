package com.hungerheroes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse<T> {

    private Integer pageNo;

    private Integer pageSize;

    private Integer totalPages;

    private Long totalElements;

    private Integer items;

    private Boolean isLast;

    private List<T> data;

    public PaginationResponse(Page<T> data){
        this.data = data.getContent();
        this.pageNo = data.getNumber();
        this.pageSize = data.getSize();
        this.totalPages = data.getTotalPages();
        this.totalElements = data.getTotalElements();
        this.items = data.getNumberOfElements();
        this.isLast = data.isLast();
    }

}
