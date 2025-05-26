package dev.marekvoe.marekservermanagement.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/status")
    public ResponseEntity<Boolean> isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null
                && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.UserDetails;
        return ResponseEntity.ok(isAuthenticated);
    }
}
