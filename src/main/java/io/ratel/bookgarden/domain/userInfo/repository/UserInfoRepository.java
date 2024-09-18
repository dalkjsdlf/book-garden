package io.ratel.bookgarden.domain.userInfo.repository;

import io.ratel.bookgarden.domain.userInfo.entity.UserInfoEntity;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {

    public List<UserInfoEntity> selectAll();

    public Optional<UserInfoEntity> selectById(Long id);
}
