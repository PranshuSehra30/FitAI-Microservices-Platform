package com.pranshudev.activityservice.dto;

import com.pranshudev.activityservice.model.ActivityType;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.Map;
@Data
public class ActivityRequestDTO {


    private String userId;


    private ActivityType activityType;

    // duration in minutes

    private Integer duration;


    private Integer caloriesBurned;


    private LocalDateTime startTime;

    // distance, heartRate, reps, sets, etc.
    private Map<String, Object> additionalMetrics;

    // getters and setters
}
