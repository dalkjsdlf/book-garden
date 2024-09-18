package io.ratel.bookgarden.domain.userbook.repository;

import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserBookRepositoryImpl implements UserBookRepository {

    private final UserBookJpaRepository userBookJpaRepository;

    @Override
    public List<UserBookEntity> selectByUserId(Long userId) {
        return userBookJpaRepository.findByUserId(userId);
    }

    @Override
    public Optional<UserBookEntity> selectByUserIdAndBookId(Long userId, Long bookId) {
        return userBookJpaRepository.findByUserIdAndBookId(userId, bookId);
    }

    @Override
    public Optional<UserBookEntity> selectById(Long id) {
        return userBookJpaRepository.findById(id);
    }

    @Override
    public void insert(UserBookEntity userBookEntity) {
        userBookJpaRepository.save(userBookEntity);
    }

    @Override
    public void update(UserBookEntity userBookEntity) {
        userBookJpaRepository.save(userBookEntity);
    }

    @Override
    public void deleteById(Long id) {

    }
}