package io.ratel.bookgarden.web_api.userbook.application;

import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;
import io.ratel.bookgarden.domain.Journal.service.JournalService;
import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import io.ratel.bookgarden.domain.bookinfo.service.BookInfoService;
import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.service.UserBookService;
import io.ratel.bookgarden.web_api.userbook.dto.UserBookCreateRequestDto;
import io.ratel.bookgarden.web_api.userbook.dto.UserBookDetailGetResponseDto;
import io.ratel.bookgarden.web_api.userbook.dto.UserBookGetResponseDto;
import io.ratel.bookgarden.web_api.userbook.dto.UserBookModifyRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 사용자도서 Application 클래스
 * [UserBook][Application]
 *
 * @author 최연호
 * @date 2024.09.21
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserBookApplication {

    private final UserBookService userBookService;
    private final BookInfoService bookInfoService;
    private final JournalService journalService;

    /**
     * [조회 - 다건] 사용자도서 다건 조회
     *
     * @param userId the user id
     * @return the user books
     */
    public List<UserBookGetResponseDto> getUserBooks(Long userId) {

        /*
         * 도서정보 조회
         */
        List<BookInfoEntity> bookInfoEntities =  bookInfoService.getBooks();

        /*
         * 사용자도서정보 조회
         */
        List<UserBookEntity> userBookEntities = userBookService.getUserBooks(userId);

        /*
         * 도서정보와 사용자도서정보를 매핑하여 Dto로 변환
         */
         return userBookEntities.stream().map(userBookEntity->{
                     /*
                     * 사용자도서의 BookID와 일치하는 BookInfoEntity를 찾아서 반환
                     */
                    BookInfoEntity filteredBookInfo = bookInfoEntities.stream().
                            filter(bookInfoEntity -> bookInfoEntity.getId().equals(userBookEntity.getBookId())).
                            findFirst().orElse(null);

                    /*
                    *   BookInfoEntity가 존재할 경우 UserBookGetResponseDto로 변환하여 반환
                    */
                    if(filteredBookInfo != null){
                        return UserBookGetResponseDto.fromEntity(userBookEntity, filteredBookInfo);
                    }
                    return null;
                }).
                    filter(Objects::nonNull).
                    collect(Collectors.toList());
    }

    /**
     * [조회 - 단건] 사용자도서 단건 조회
     *
     * @param id the id
     * @return the user book by id
     */
    public UserBookDetailGetResponseDto getUserBookById(Long id) {

        /*
         * 사용자도서정보 조회
         */
        UserBookEntity userBookEntity = userBookService.getUserBookById(id);

        /*
         * 도서정보과 사용자도서정보를 조회하기 위한 키값 추출
         */
        Long bookId = userBookEntity.getBookId();
        Long userBookId = userBookEntity.getId();

        /*
         * 사용자도서정보에 해당하는 도서정보 조회 메서드 호출
         */
        BookInfoEntity bookInfoEntity =  bookInfoService.getBookById(bookId);

        /*
         * 사용자도서정보에 해당하는 독서기록 조회 메서드 호출
         */
        List<JournalEntity> journalEntities = journalService.getJournalByUserBookId(userBookId);

        /*
         * Entity를 Dto로 변환 후 반환
         */
        return UserBookDetailGetResponseDto.fromEntity(userBookEntity, bookInfoEntity, journalEntities);
    }

    /**
     * [수정 - 단건] 사용자도서 단건 생성
     *
     * @param userBookCreateRequestDto the user book create request dto
     * @return the boolean
     */
    public boolean createUserBook(UserBookCreateRequestDto userBookCreateRequestDto) {

        /*
         * UserBook 수정 메서드 호출
         */
        UserBookEntity entityForCreation = UserBookCreateRequestDto.fromDto(userBookCreateRequestDto);

        /*
         * UserBook 생성 메서드 호출
         */
        UserBookEntity createdEntity = userBookService.createUserBook(entityForCreation);

        /*
         * 생성 성공시 true 반환
         */
        return createdEntity != null;
    }

    /**
     * [수정 - 단건] 사용자도서 단건 수정
     *
     * @param userBookModifyRequestDto the user book modify request dto
     * @return the boolean
     */
    public boolean modifyUserBook(UserBookModifyRequestDto userBookModifyRequestDto) {

        /*
         * Dto를 Entity로 변환
         */
        UserBookEntity entityForModification = UserBookModifyRequestDto.fromDto(userBookModifyRequestDto);

        /*
         * UserBook 수정 메서드 호출
         */
        UserBookEntity userBookEntity = userBookService.modifyUserBook(entityForModification);

        /*
         * 수정 성공시 true 반환
         */
        return userBookEntity != null;
    }

    /**
     * [삭제 - 단건] 사용자도서 단건 삭제
     *
     * @param id     the id
     * @return the boolean
     */
    public boolean removeUserBook(Long id) {

        /*
         * UserBook 삭제 메서드 호출
         */
        Long removedRow = userBookService.removeUserBook(id);

        /*
         * 삭제 성공시 true 반환
         */
        return removedRow != null && removedRow >= 0;
    }
}
