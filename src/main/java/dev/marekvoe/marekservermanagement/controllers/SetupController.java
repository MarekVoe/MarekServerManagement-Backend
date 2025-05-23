package dev.marekvoe.marekservermanagement.controllers;

import dev.marekvoe.marekservermanagement.dto.AdminSetupDto;
import dev.marekvoe.marekservermanagement.dto.DbSetupDto;
import dev.marekvoe.marekservermanagement.services.SetupService;
import dev.marekvoe.marekservermanagement.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/setup")
public class SetupController {

    private final SetupService setupService;
    private final UserService userService;

    public SetupController(SetupService setupService, UserService userService) {
        this.setupService = setupService;
        this.userService = userService;
    }

    @GetMapping("/status")
    public ResponseEntity<Boolean> status() {
        return ResponseEntity.ok(setupService.isSetupComplete());
    }

    @PostMapping("/database")
    public ResponseEntity<Void> createDatabase(@RequestBody DbSetupDto dto) {
        if (setupService.isSetupComplete()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            setupService.createDatabase(dto.getDbName());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> createAdmin(@RequestBody AdminSetupDto dto) {
        if (setupService.isSetupComplete()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        userService.createAdmin(dto.getUsername(), dto.getPassword(), dto.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> completeSetup() {
        if (setupService.isSetupComplete()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        setupService.markSetupComplete();
        return ResponseEntity.ok().build();
    }
}
