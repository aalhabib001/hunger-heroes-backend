package com.hungerheroes.backend.jwt.services;

import com.hungerheroes.backend.dto.ApiResponse;
import com.hungerheroes.backend.jwt.dto.request.LoginForm;
import com.hungerheroes.backend.jwt.dto.request.PassChangeRequest;
import com.hungerheroes.backend.jwt.dto.request.SignUpForm;
import com.hungerheroes.backend.jwt.dto.response.JwtResponse;
import com.hungerheroes.backend.jwt.model.Role;
import com.hungerheroes.backend.jwt.model.RoleName;
import com.hungerheroes.backend.jwt.model.UserModel;
import com.hungerheroes.backend.jwt.repository.RoleRepository;
import com.hungerheroes.backend.jwt.repository.UserRepository;
import com.hungerheroes.backend.jwt.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SignUpAndSignInService {

    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ResponseEntity<ApiResponse<String>> signUp(SignUpForm signUpRequest) throws ValidationException {

        if (userRepository.existsByUsername(signUpRequest.getUserName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken!");

        Set<String> rolesString = new HashSet<>();
        rolesString.add(signUpRequest.getUserType().toString());

        UserModel userModel = UserModel.builder()
                .name(signUpRequest.getName())
                .phoneNo(signUpRequest.getPhoneNo())
                .username(signUpRequest.getUserName())
                .roles(getRolesFromStringRoles(rolesString))
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();

        userModel.setUuid(UUID.randomUUID().toString());
        userRepository.saveAndFlush(userModel);

        return new ResponseEntity<>(new ApiResponse<String>(201, "Account Created", null), HttpStatus.CREATED);
    }


    public ResponseEntity<ApiResponse<JwtResponse>> signIn(LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        return new ResponseEntity<>(new ApiResponse<>(200, "Login Successful",
                new JwtResponse(jwt, "Bearer")), HttpStatus.OK);
    }

/*    public ResponseEntity<ApiResponse<ProfileResponse>> getLoggedUserProfile(String jwtToken) {

        String userName = jwtProvider.getUserNameFromJwt(jwtToken);
        Optional<ProfileModel> profileModelOptional = profileRepository.findByUsername(userName);

        if (profileModelOptional.isPresent()) {
            ProfileModel profileModel = profileModelOptional.get();


            ProfileResponse profileResponse = new ProfileResponse(profileModel.getUsername(), profileModel.getEmail(),
                    profileModel.getName(), profileModel.getPhoneNo(), profileModel.getAddress(),
                    profileModel.getDateOfBirth(), profileModel.getThana(), profileModel.getDistrict());

            return new ResponseEntity<>(new ApiResponse<>(200, "User Found", profileResponse), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User Not Found");
        }

    }*/

/*    public ResponseEntity<ApiResponse<ProfileResponse>> editLoggedInProfile(String jwtToken, EditProfile editProfile) {
        String userName = jwtProvider.getUserNameFromJwt(jwtToken);
        Optional<ProfileModel> profileModelOptional = profileRepository.findByUsername(userName);

        if (profileModelOptional.isPresent()) {
            ProfileModel profileModel = profileModelOptional.get();

            if (profileModel.getEmail() == null) {
                profileModel.setEmail(editProfile.getEmail());
            } else if (profileModel.getEmail().equals(editProfile.getEmail())) {

            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't Change Default Email");
            }


            if (profileModel.getPhoneNo() == null) {
                profileModel.setPhoneNo(editProfile.getPhoneNo());
            } else if (profileModel.getPhoneNo().equals(editProfile.getPhoneNo())) {

            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't Change Default Phone No");
            }

            profileModel.setName(editProfile.getName());
            profileModel.setAddress(editProfile.getAddress());
            profileModel.setThana(editProfile.getThana());
            profileModel.setDistrict(editProfile.getDistrict());
            profileModel.setDateOfBirth(editProfile.getDateOfBirth());

            profileRepository.save(profileModel);

            ProfileResponse profileResponse = new ProfileResponse(profileModel.getUsername(), profileModel.getEmail(),
                    profileModel.getName(), profileModel.getPhoneNo(), profileModel.getAddress(),
                    profileModel.getDateOfBirth(), profileModel.getThana(), profileModel.getDistrict());

            return new ResponseEntity<>(new ApiResponse<>(200, "Profile Edit Successful", profileResponse), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User Not Found");
        }

    }*/

    public ResponseEntity<ApiResponse<String>> changePassword(String jwtToken, PassChangeRequest passChangeRequest) {
        String userName = jwtProvider.getUserNameFromJwt(jwtToken);
        Optional<UserModel> userModelOptional = userRepository.findByUsername(userName);

        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();

            if (encoder.matches(passChangeRequest.getOldPass(), userModel.getPassword())) {
                userModel.setPassword(encoder.encode(passChangeRequest.getNewPass()));

                userRepository.save(userModel);

                return new ResponseEntity<>(new ApiResponse<>(200,
                        "Password Change Successful", null), HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password Doesn't match");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No User Found");
        }
    }

    public Set<Role> getRolesFromStringRoles(Set<String> roles2) throws ValidationException {
        Set<Role> roles = new HashSet<>();
        for (String role : roles2) {
            Optional<Role> roleOptional = roleRepository.findByName(RoleName.valueOf(role));
            if (roleOptional.isEmpty()) {
                throw new ValidationException("Role '" + role + "' does not exist.");
            }
            roles.add(roleOptional.get());
        }
        return roles;
    }

    private Set<String> getRolesStringFromRole(Set<Role> roles2) {
        Set<String> roles = new HashSet<>();
        for (Role role : roles2) {
            roles.add(role.getName().toString());
        }
        return roles;
    }


//
//    public ResponseEntity changePass(PassChangeRequest passChangeRequest) {
//
//        Optional<User> userOptional = userRepository.findByUsername(getLoggedAuthUser().getBody().getUsername());
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (encoder.matches(passChangeRequest.getOldPass(), user.getPassword())) {
//                user.setPassword(encoder.encode(passChangeRequest.getNewPass()));
//
//                userRepository.save(user);
//
//                MessageResponse messageResponse = new MessageResponse("Pass Changed Successful", 200);
//                return new ResponseEntity(messageResponse, HttpStatus.OK);
//            } else {
//                MessageResponse messageResponse = new MessageResponse("Old Pass Not Matched", 400);
//                return new ResponseEntity(messageResponse, HttpStatus.BAD_REQUEST);
//            }
//        } else {
//            MessageResponse messageResponse = new MessageResponse("No User Found", 204);
//            return new ResponseEntity(messageResponse, HttpStatus.NO_CONTENT);
//        }
//    }

//    public String deleteUser(String email) {
//
//        if (userRepository.findByEmail(email).isPresent()) {
//
//            userRepository.deleteById(userRepository.findByEmail(email).get().getId());
//            return "Deleted";
//        } else {
//            return "Not Found";
//        }
//
//    }
//
//    public String editProfile(EditProfile editProfile) {
//        String username = getLoggedAuthUserName();
//
//        if (!username.isEmpty()) {
//            //System.out.println(username);
//            Optional<User> userOptional = userRepository.findByUsername(username);
//
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                if (!editProfile.getName().isEmpty()) {
//                    user.setName(editProfile.getName());
//                }
//                if (!editProfile.getPhoneNo().isEmpty()) {
//                    user.setPhoneNo(editProfile.getPhoneNo());
//                }
//                if (!editProfile.getNewPassword().isEmpty() && !editProfile.getCurrentPassword().isEmpty()) {
//                    if (encoder.matches(editProfile.getCurrentPassword(), userOptional.get().getPassword())) {
//
//                        user.setPassword(encoder.encode(editProfile.getNewPassword()));
//                    } else {
//                        return "Wrong Current Password";
//                    }
//                }
//
//                userRepository.save(user);
//                return "Saved Successfully";
//            } else {
//                return "User Not Found";
//            }
//
//        } else {
//            return "Unsuccessful";
//        }
//
//
//    }
//
//    public String addAreaList(AreaNameRequestsResponse areaNameRequestsResponse) {
//        for (String names : areaNameRequestsResponse.getAreaNames()) {
//            AreaNames areaNames = new AreaNames(names);
//            areaNameRepository.save(areaNames);
//        }
//        return "Saved";
//    }
//
//    public AreaNameRequestsResponse getAreaList() {
//        List<AreaNames> areaNamesOptional = areaNameRepository.findAll();
//
//        AreaNameRequestsResponse areaNameRequestsResponse = new AreaNameRequestsResponse();
//        List<String> areaNamesList = new ArrayList<>();
//        for (AreaNames areaNames : areaNamesOptional) {
//            areaNamesList.add(areaNames.getAreaName());
//        }
//        areaNameRequestsResponse.setAreaNames(areaNamesList);
//        return areaNameRequestsResponse;
//    }

//    public LoggedUserDetailsResponse getLoggedUserDetails(Authentication authentication) {
//
//        System.out.println(authentication.toString());
//        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
//        List<String> userRoleList = new ArrayList<>();
//        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
//            userRoleList.add(grantedAuthority.getAuthority());
//        }
//        LoggedUserDetailsResponse loggedUserDetailsResponse = new LoggedUserDetailsResponse();
//        loggedUserDetailsResponse.setUserName(authentication.getName());
//        loggedUserDetailsResponse.setUserRole(userRoleList);
//        loggedUserDetailsResponse.setIsAuthenticated(authentication.isAuthenticated());
//        return loggedUserDetailsResponse;
//    }


}
