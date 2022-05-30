package com.hungerheroes.backend.controller;

import com.hungerheroes.backend.dto.ApiMessageResponse;
import com.hungerheroes.backend.dto.PaginationResponse;
import com.hungerheroes.backend.dto.request.FoodShareRequest;
import com.hungerheroes.backend.model.FoodShareModel;
import com.hungerheroes.backend.service.FoodShareService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/food-share")
public class FoodShareController {

    private final FoodShareService foodShareService;

    @PostMapping
    public ResponseEntity<ApiMessageResponse> createFoodShare(@RequestBody FoodShareRequest foodShareRequest) {
        return foodShareService.createFoodShare(foodShareRequest);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<FoodShareModel>> getFoodShare(@RequestParam(defaultValue = "0") int pageNo,
                                                                           @RequestParam(defaultValue = "20") int pageSize,
                                                                           @RequestParam(defaultValue = "true") Boolean isApproved) {
        return foodShareService.getFoodShare(pageNo, pageSize, isApproved);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<ApiMessageResponse> approveFoodShare(@PathVariable("id") Long id) {
        return foodShareService.approveFoodShare(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiMessageResponse> editFoodShare(@PathVariable("id") Long id,
                                                            @RequestBody FoodShareRequest foodShareRequest) {
        return foodShareService.editFoodShare(id, foodShareRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiMessageResponse> deleteFoodShare(@PathVariable("id") Long id) {
        return foodShareService.deleteFoodShare(id);
    }

    @GetMapping("/own")
    public ResponseEntity<List<FoodShareModel>> getOwnFoodShare() {
        return foodShareService.getOwnFoodShare();
    }

    @PostMapping("/{food-id}/confirm")
    public ResponseEntity<ApiMessageResponse> confirmFood(@PathVariable("food-id") Long foodId){
        return foodShareService.confirmFood(foodId);
    }
}
