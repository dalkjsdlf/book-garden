package io.ratel.bookgarden.domain.userbook.repository;

import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * UserBookJpaRepository interface.
 * [UserBook][Repository]
 */
public interface UserBookJpaRepository extends JpaRepository<UserBookEntity, Long> {

    /**
     * Find by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<UserBookEntity> findByUserId(Long userId);

    /**
     * Find by user id and book id optional.
     *
     * @param userId the user id
     * @param bookId the book id
     * @return the optional
     */
    Optional<UserBookEntity> findByUserIdAndBookId(Long userId, Long bookId);
}
