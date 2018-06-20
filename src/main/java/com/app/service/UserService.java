package com.app.service;

import com.app.model.dto.UserDto;

import java.util.Optional;

public interface UserService {
    void addOrUpdateUser(UserDto userDto);
    void deleteUser(Long id);
    Optional<UserDto> getOne(Long id);
}
