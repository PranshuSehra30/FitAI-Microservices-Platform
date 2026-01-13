package com.pranshudev.activityservice.controller;


import com.pranshudev.activityservice.dto.ActivityRequestDTO;
import com.pranshudev.activityservice.dto.ActivityResponseDTO;
import com.pranshudev.activityservice.model.Activity;
import com.pranshudev.activityservice.service.ActivityService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    @GetMapping
    public List<Activity> getActivities() {
        return new ArrayList<>();
    }


    @PostMapping
    public ResponseEntity<ActivityResponseDTO> addActivity(@RequestBody ActivityRequestDTO activityRequest) {
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }

}
