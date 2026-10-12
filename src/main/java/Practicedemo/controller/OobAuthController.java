package com.musab.secureoobbanking.controller;

import com.musab.secureoobbanking.oob.OobSession;
import com.musab.secureoobbanking.oob.OobSessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class OobAuthController {

    private final OobSessionRepository oobSessionRepository;

    public OobAuthController(OobSessionRepository oobSessionRepository) {
        this.oobSessionRepository = oobSessionRepository;
    }

    @PostMapping("/initiate-oob")
    public ResponseEntity<String> initiateSession(@RequestParam String phoneNumber) {
        OobSession session = new OobSession();
        session.setId(UUID.randomUUID().toString());
        session.setPhoneNumber(phoneNumber);
        session.setStatus("PENDING_VERIFICATION");

        oobSessionRepository.save(session);
        return ResponseEntity.ok("OOB session saved in Redis for phone number: " + phoneNumber);
    }
}