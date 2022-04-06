package com.hungerheroes.backend.service;

import com.hungerheroes.backend.dto.ApiMessageResponse;
import com.hungerheroes.backend.dto.PaginationResponse;
import com.hungerheroes.backend.dto.request.FoodShareRequest;
import com.hungerheroes.backend.jwt.model.UserModel;
import com.hungerheroes.backend.jwt.repository.UserRepository;
import com.hungerheroes.backend.model.FoodShareModel;
import com.hungerheroes.backend.repository.FoodShareRepository;
import com.hungerheroes.backend.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodShareService {

    private final FoodShareRepository foodShareRepository;
    private final UserRepository userRepository;


    public ResponseEntity<ApiMessageResponse> createFoodShare(FoodShareRequest foodShareRequest) {
        FoodShareModel foodShareModel = new FoodShareModel();
        BeanUtils.copyProperties(foodShareRequest, foodShareModel);

        String userName = AuthUtils.getUserName();
        System.out.println(userName);
        System.out.println(AuthUtils.getAuthInfo().toString());
        UserModel userModel = userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        foodShareModel.setUserModel(userModel);
        foodShareModel.setIsApproved(false);

        foodShareRepository.save(foodShareModel);

        return new ResponseEntity<>(new ApiMessageResponse(201, "Food Share created successfully"), HttpStatus.OK);
    }

    public ResponseEntity<PaginationResponse<FoodShareModel>> getFoodShare(int pageNo, int pageSize, Boolean isApproved) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "createdOn"));

        Page<FoodShareModel> foodShareModelPage = foodShareRepository.findAllByIsApprovedAndIsDeleted(isApproved, false, pageable);

        PaginationResponse<FoodShareModel> paginationResponse = new PaginationResponse<>(foodShareModelPage);

        return new ResponseEntity<>(paginationResponse, HttpStatus.OK);
    }

    public ResponseEntity<ApiMessageResponse> approveFoodShare(Long id) {
        FoodShareModel foodShareModel = foodShareRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Share not found"));

        foodShareModel.setIsApproved(true);
        foodShareRepository.save(foodShareModel);

        return new ResponseEntity<>(new ApiMessageResponse(200, "Food Share approved successfully"), HttpStatus.OK);
    }

    public ResponseEntity<ApiMessageResponse> editFoodShare(Long id, FoodShareRequest foodShareRequest) {
        FoodShareModel foodShareModel = foodShareRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Share not found"));

        BeanUtils.copyProperties(foodShareRequest, foodShareModel);

        foodShareRepository.save(foodShareModel);

        return new ResponseEntity<>(new ApiMessageResponse(200, "Food Share edited successfully"), HttpStatus.OK);
    }

    public ResponseEntity<ApiMessageResponse> deleteFoodShare(Long id) {
        FoodShareModel foodShareModel = foodShareRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Share not found"));

        foodShareModel.setIsDeleted(true);
        foodShareRepository.save(foodShareModel);

        return new ResponseEntity<>(new ApiMessageResponse(200, "Food Share deleted successfully"), HttpStatus.OK);
    }

    public ResponseEntity<List<FoodShareModel>> getOwnFoodShare() {
        String userName = AuthUtils.getUserName();
        UserModel userModel = userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<FoodShareModel> foodShareModelList = foodShareRepository.findAllByUserModelAndIsDeleted(userModel, false);

        return new ResponseEntity<>(foodShareModelList, HttpStatus.OK);
    }
}
