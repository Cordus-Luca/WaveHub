package com.example.WaveHub.Controller;

import com.example.WaveHub.Controller.Requests.AuthenticationRequest;
import com.example.WaveHub.Controller.Requests.RegisterRequest;
import com.example.WaveHub.Controller.Responses.AuthenticationResponse;
import com.example.WaveHub.ServiceLayer.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
