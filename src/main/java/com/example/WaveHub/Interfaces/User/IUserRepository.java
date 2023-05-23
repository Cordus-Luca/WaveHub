package com.example.WaveHub.Interfaces.User;

import com.example.WaveHub.Models.User;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> findByEmail(String email);
}
