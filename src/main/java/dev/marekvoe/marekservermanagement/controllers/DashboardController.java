package dev.marekvoe.marekservermanagement.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Vitej na dashboardu, " + SecurityContextHolder.getContext().getAuthentication().getName() + " !";
    }
}
