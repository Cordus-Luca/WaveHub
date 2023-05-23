package com.example.WaveHub.Interfaces.User;

import com.example.WaveHub.DataBaseLayer.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepoJPA extends JpaRepository<UserEntity, Long> {
}
