package io.ratel.bookgarden.domain.userInfo.repository;

import io.ratel.bookgarden.domain.userInfo.entity.UserInfoEntity;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {

    public List<UserInfoEntity> findAll();

    public Optional<UserInfoEntity> findById(Long id);
}
