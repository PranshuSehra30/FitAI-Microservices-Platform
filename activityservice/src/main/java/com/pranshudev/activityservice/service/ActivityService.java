package com.pranshudev.activityservice.service;

import com.pranshudev.activityservice.dto.ActivityRequestDTO;

import com.pranshudev.activityservice.dto.ActivityResponseDTO;
import com.pranshudev.activityservice.model.Activity;
import com.pranshudev.activityservice.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;

@Service

@RequiredArgsConstructor
public class ActivityService {
    private final UserValidationService userValidationService;
private final ActivityRepository activityRepository;
  private final KafkaTemplate<String, ActivityResponseDTO> kafkaTemplate;
    @Value("${kafka.topic.name}")
    private String topicName;

    public ActivityResponseDTO trackActivity(ActivityRequestDTO activityRequest) {

      Boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());

      if(!isValidUser){
          throw new RuntimeException("Invalid user:"+activityRequest.getUserId());
      }
        Activity activity = Activity.builder()
                .userId(activityRequest.getUserId())
                .activityType(activityRequest.getActivityType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics())

                .build();

        Activity savedActivity = activityRepository.save(activity);
        ActivityResponseDTO responseDTO = mapToResponseDTO(savedActivity);

        try {
            kafkaTemplate.send(topicName, savedActivity.getUserId(), responseDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDTO;

    }

    private ActivityResponseDTO mapToResponseDTO(Activity activity) {
        ActivityResponseDTO dto = new ActivityResponseDTO();
        dto.setId(activity.getId());
        dto.setUserId(activity.getUserId());
        dto.setActivityType(activity.getActivityType());
        dto.setDuration(activity.getDuration());
        dto.setCaloriesBurned(activity.getCaloriesBurned());
        dto.setStartTime(activity.getStartTime());
        dto.setAdditionalMetrics(activity.getAdditionalMetrics());
        dto.setCreatedAt(activity.getCreatedAt());
        dto.setUpdatedAt(activity.getUpdatedAt());
        return dto;
    }}
