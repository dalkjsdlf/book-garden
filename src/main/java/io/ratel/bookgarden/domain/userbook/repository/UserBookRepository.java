package io.ratel.bookgarden.domain.userbook.repository;

import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;

import java.util.List;
import java.util.Optional;

public interface UserBookRepository{

    List<UserBookEntity> selectByUserId(Long userId);
    Optional<UserBookEntity> selectByUserIdAndBookId(Long userId, Long bookId);
    Optional<UserBookEntity> selectById(Long id);
    UserBookEntity save(UserBookEntity userBookEntity);
    Long deleteById(Long id);
}