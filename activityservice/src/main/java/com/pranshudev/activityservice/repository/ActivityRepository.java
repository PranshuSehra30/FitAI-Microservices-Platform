package com.pranshudev.activityservice.repository;

import com.pranshudev.activityservice.dto.ActivityRequestDTO;
import com.pranshudev.activityservice.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {
}
