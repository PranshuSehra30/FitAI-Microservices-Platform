package com.pranshudev.userservice.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String LastName;

    private String password;
private String keycloakId;
    private String email;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;
}
