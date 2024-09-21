package io.ratel.bookgarden.domain.userbook.repository;

import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;

import java.util.List;
import java.util.Optional;

/**
 * UserBookRepository Interface
 * [UserBook][Repository] - interface
 */
public interface UserBookRepository{

    /**
     * Select by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<UserBookEntity> selectByUserId(Long userId);

    /**
     * Select by user id and book id optional.
     *
     * @param userId the user id
     * @param bookId the book id
     * @return the optional
     */
    Optional<UserBookEntity> selectByUserIdAndBookId(Long userId, Long bookId);

    /**
     * Select by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<UserBookEntity> selectById(Long id);

    /**
     * Save user book entity.
     *
     * @param userBookEntity the user book entity
     * @return the user book entity
     */
    UserBookEntity save(UserBookEntity userBookEntity);

    /**
     * Delete by id long.
     *
     * @param id the id
     * @return the long
     */
    Long deleteById(Long id);
}