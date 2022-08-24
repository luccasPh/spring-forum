package br.com.lucas.forum.config.security;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.lucas.forum.models.User;
import br.com.lucas.forum.repositories.UsersRepository;

public class AuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UsersRepository usersRepository;

    public AuthenticationFilter(TokenService tokenService, UsersRepository usersRepository) {
        this.tokenService = tokenService;
        this.usersRepository = usersRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authToken = request.getHeader("Authorization");
        String token = verifyToken(authToken);
        boolean isValidToken = tokenService.isValidToken(token);
        if (isValidToken) {
            setAuthentication(token);
        }

        filterChain.doFilter(request, response);

    }

    private void setAuthentication(String token) {
        Long userId = tokenService.getUserId(token);
        User user = usersRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String verifyToken(String authToken) throws AuthenticationException {
        if (authToken == null || authToken.isEmpty() || !authToken.startsWith("Bearer ")) {
            return null;
        }

        return authToken.substring(7);
    }

}
