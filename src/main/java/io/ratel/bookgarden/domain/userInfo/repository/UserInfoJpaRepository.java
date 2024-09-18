package io.ratel.bookgarden.domain.userInfo.repository;

import io.ratel.bookgarden.domain.userInfo.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoJpaRepository extends JpaRepository<UserInfoEntity, Long> {
}
