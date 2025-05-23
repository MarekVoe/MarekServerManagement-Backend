package dev.marekvoe.marekservermanagement.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @GetMapping("/username")
    public String getUsername() {
            return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/role")
    public String getRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }

    @GetMapping("/details")
    public String getDetails() {
        return SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
    }
}
