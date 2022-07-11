package com.example.pointplay.api;


import com.example.pointplay.services.PointPlayServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/point-play/me")
@RequiredArgsConstructor
public class PointPlayController {
    private final PointPlayServices service;

    @GetMapping("")
    public ResponseEntity<String> getPointPlay() {
        return ResponseEntity.ok("Point Play: 13");
    }

}
