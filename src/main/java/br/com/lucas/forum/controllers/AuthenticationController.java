package br.com.lucas.forum.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lucas.forum.config.security.TokenService;
import br.com.lucas.forum.controllers.dtos.TokenDto;
import br.com.lucas.forum.controllers.form.AuthenticationForm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/auth")
@Profile(value = { "prod", "test" })
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authLogin(@RequestBody @Valid AuthenticationForm authenticationForm) {
        UsernamePasswordAuthenticationToken authData = authenticationForm.convert();
        try {

            Authentication authenticate = authenticationManager.authenticate(authData);
            String authToken = tokenService.getAuthToken(authenticate);
            TokenDto tokenDto = new TokenDto(authToken);

            return ResponseEntity.ok(tokenDto);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
