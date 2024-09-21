package io.ratel.bookgarden.domain.userbook.service;


import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.repository.UserBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
/**
 * [UserBook][Service] UserBookService 클래스
 *
 * @author 최연호
 * @date 2024.09.21
 */
public class UserBookService {

    /**
     * UserBook Repository
     * */
    private final UserBookRepository userBookRepository;

    /**
     * UserBook supporter for Service
     * */
    private final UserBookSupportService userBookSupportService;

    /**
     * [조회 - 다건] 사용자 ID로 사용자 도서 다건 조회
     *
     * @param userId the user id
     * @return the user books
     */
    public List<UserBookEntity> getUserBooks(Long userId) {
        return userBookRepository.selectByUserId(userId);
    }

    /**
     * [조회 - 단건] ID로 사용자도서 단건 조회
     *
     * @param id the id
     * @return the user book by id
     */
    public UserBookEntity getUserBookById(Long id) {
        Optional<UserBookEntity> optionalUserBookEntity = userBookRepository.selectById(id);
        return optionalUserBookEntity.orElseThrow(() -> new BusinessException(BusinessErrorResult.USER_BOOK_NOT_FOUND));
    }

    /**
     * [생성 - 단건] 사용자도서 단건 생성
     *
     * @param userBookEntity the user book entity
     * @return the user book entity
     */
    public UserBookEntity createUserBook(UserBookEntity userBookEntity) {
        Long bookId = userBookEntity.getBookId();
        Long userBookId = userBookEntity.getId();

        /*
          bookId 값이 올바른지 검사
          */
        userBookSupportService.validateBookId(bookId);

        /*
         * 저장된 userBookId 값이 존재한지 검사(입력가능한지 검사)
         * */
        userBookSupportService.validatePossibleCreate(userBookId);

        /*
         * 사용자도서 입력 메서드 호출
         * */
        return userBookRepository.save(userBookEntity);
    }

    /**
     * [수정 - 단건] 사용자 도서 단건 수정
     *
     * @param userBookEntity the user book entity
     * @return the user book entity
     */
    public UserBookEntity modifyUserBook(UserBookEntity userBookEntity) {

        Long bookId = userBookEntity.getBookId();
        Long userBookId = userBookEntity.getId();

        /*
         * bookId 값이 올바른지 검사
         * */
        userBookSupportService.validateUserBookId(userBookId);

        /*
         * bookId 값이 올바른지 검사
         * */
        userBookSupportService.validateBookId(bookId);

        /*
         * 수정대상이 존재하는지 확인
         * */
        Optional<UserBookEntity> optionalUserBookEntity = userBookRepository.selectById(userBookId);

        /*
         * 수정 대상이 존재하지 않을 시 예외
         * */
        optionalUserBookEntity.orElseThrow(() -> new BusinessException(BusinessErrorResult.USER_BOOK_NOT_FOUND));

        /*
         * 사용자도서 정보 수정 메서드 호출
         * */
        return userBookRepository.save(userBookEntity);
    }

    /**
     * [삭제 - 단건] 사용자 도서 단건 삭제
     *
     * @param id the id
     * @return the long
     */
    public Long removeUserBook(Long id) {

        /*
         * 사용자도서 삭제 메서드 호출
         * */
        return userBookRepository.deleteById(id);
    }
}