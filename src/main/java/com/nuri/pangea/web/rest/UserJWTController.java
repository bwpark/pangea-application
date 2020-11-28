package com.nuri.pangea.web.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nuri.pangea.security.jwt.JWTFilter;
import com.nuri.pangea.security.jwt.TokenProvider;
import com.nuri.pangea.service.AvatarService;
import com.nuri.pangea.service.dto.AvatarDTO;
import com.nuri.pangea.web.rest.vm.LoginVM;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {
    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final AvatarService avatarService;

    public UserJWTController(
        TokenProvider tokenProvider,
        AuthenticationManagerBuilder authenticationManagerBuilder,
        AvatarService avatarService
    ) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.avatarService = avatarService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Optional<AvatarDTO>> authorize(@Valid @RequestBody LoginVM loginVM) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginVM.getUsername(),
            loginVM.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<AvatarDTO> avatar = avatarService.findOneByCurrentUser();

        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(avatar, httpHeaders, HttpStatus.OK);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {
        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
