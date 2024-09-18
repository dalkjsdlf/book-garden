package io.ratel.bookgarden.domain.userbook.repository;

import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBookJpaRepository extends JpaRepository<UserBookEntity, Long> {

    List<UserBookEntity> findByUserId(Long userId);

    Optional<UserBookEntity> findByUserIdAndBookId(Long userId, Long bookId);
}
