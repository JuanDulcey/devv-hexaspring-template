package com.devv.hexaspring.domain.ports.in;

import com.devv.hexaspring.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface UserUseCase {
    User createUser(String name, String email);
    User findById(UUID id);
    List<User> findAll();
    void deleteUser(UUID id);
}
