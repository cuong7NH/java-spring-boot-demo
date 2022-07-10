package com.example.timeplay.api;


import com.example.eventuser.dto.PagingResponse;
import com.example.eventuser.dto.request.EventRequest;
import com.example.eventuser.dto.response.EventResponse2;
import com.example.timeplay.services.TimePlayServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/time-play/me")
@RequiredArgsConstructor
public class TimePlayController {
    private final TimePlayServices service;

    @GetMapping("")
    public ResponseEntity<String> getTimePlay() {
        return ResponseEntity.ok("Public Time Play");
    }

}
