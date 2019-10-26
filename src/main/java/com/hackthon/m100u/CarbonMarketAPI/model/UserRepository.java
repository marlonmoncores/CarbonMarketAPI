package com.hackthon.m100u.CarbonMarketAPI.model;

import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSystemEntity, Long> {

    Optional<UserSystemEntity> findById(Long id);
    Optional<UserSystemEntity> findByCpf(String cpf);
}
