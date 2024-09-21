package io.ratel.bookgarden.domain.userbook.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static io.ratel.bookgarden.domain.userbook.entity.QUserBookEntity.userBookEntity;

/**
 * The type User book repository.
 * [UserBook][Repository]
 */
@Component
@RequiredArgsConstructor
@Repository
public class UserBookRepositoryImpl implements UserBookRepository {

    private final UserBookJpaRepository userBookJpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserBookEntity> selectByUserId(Long userId) {
        return queryFactory.
                selectFrom(userBookEntity).
                where(userBookEntity.userId.eq(userId)).
                fetch();
    }

    @Override
    public Optional<UserBookEntity> selectByUserIdAndBookId(Long userId, Long bookId) {
        return Optional.ofNullable(
                queryFactory.
                selectFrom(userBookEntity).
                where(
                        userBookEntity.userId.eq(userId).
                    and(userBookEntity.bookId.eq(bookId))).
                        fetchFirst()
                );
    }

    @Override
    public Optional<UserBookEntity> selectById(Long id) {
        return Optional.ofNullable(
                queryFactory.
                selectFrom(userBookEntity).
                where(userBookEntity.id.eq(id)).
                fetchOne()
        );
    }

    @Override
    public UserBookEntity save(UserBookEntity userBookEntity) {
        return userBookJpaRepository.save(userBookEntity);
    }

    @Override
    public Long deleteById(Long id) {
        return queryFactory.
                delete(userBookEntity).
                where(userBookEntity.id.eq(id)).
                execute();
    }
}