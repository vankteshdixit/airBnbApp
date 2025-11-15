package com.vanktesh.project.airBnbApp.service;

import com.vanktesh.project.airBnbApp.dto.ProfileUpdateRequestDto;
import com.vanktesh.project.airBnbApp.entity.User;

public interface UserService {
    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);
}
