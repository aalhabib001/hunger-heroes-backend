package com.hungerheroes.backend.jwt.controller;

import com.hungerheroes.backend.dto.ApiResponse;
import com.hungerheroes.backend.jwt.dto.request.LoginForm;
import com.hungerheroes.backend.jwt.dto.request.PassChangeRequest;
import com.hungerheroes.backend.jwt.dto.request.SignUpForm;
import com.hungerheroes.backend.jwt.dto.response.JwtResponse;
import com.hungerheroes.backend.jwt.dto.response.ProfileResponse;
import com.hungerheroes.backend.jwt.services.SignUpAndSignInService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SignUpAndSignInService signUpAndSignInService;
//    private final ForgetPasswordService forgetPasswordService;


    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<String>> signUpUser(@RequestBody SignUpForm signUpRequest) throws ValidationException {
        return signUpAndSignInService.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    public ResponseEntity<ApiResponse<JwtResponse>> signInUser(@RequestBody LoginForm loginRequest) {
        return signUpAndSignInService.signIn(loginRequest);
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<ProfileResponse>> getLoggedInProfile(@RequestHeader(name = "Authorization", required = true)
                                                                                   String jwtToken) {
        return signUpAndSignInService.getLoggedUserProfile(jwtToken);
    }

    @PutMapping("/changePass")
    public ResponseEntity<ApiResponse<String>> changePassword(@RequestHeader(name = "Authorization",
            required = true) String jwtToken, @RequestBody PassChangeRequest passChangeRequest) {

        return signUpAndSignInService.changePassword(jwtToken, passChangeRequest);
    }

    @GetMapping("/serverCheck")
    public String getServerStatStatus() {
        return "The Server is Running";
    }

}
