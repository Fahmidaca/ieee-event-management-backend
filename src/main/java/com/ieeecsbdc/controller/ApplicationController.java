package com.ieeecsbdc.controller;

import com.ieeecsbdc.model.Application;
import com.ieeecsbdc.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {

    // Trivial comment added to trigger rebuild

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping
    public ResponseEntity<Application> submitApplication(@Valid @RequestBody Application application) {
        Application savedApplication = applicationRepository.save(application);
        return ResponseEntity.ok(savedApplication);
    }
}
