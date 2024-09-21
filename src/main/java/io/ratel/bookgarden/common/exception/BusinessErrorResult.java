package io.ratel.bookgarden.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BusinessErrorResult {
    NOT_FOUND(HttpStatus.BAD_REQUEST,"not found"),
    WRONG_USER_ID(HttpStatus.BAD_REQUEST,"The ID was entered incorrectly."),
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Exception"),

    /**
     * 도서정보
     * */
    FAILED_TO_RETRIEVE_BOOK_INFO(HttpStatus.INTERNAL_SERVER_ERROR, "도서정보를 조회하는데 실패하였습니다."),
    BOOK_INFO_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "도서정보를 찾지 못했습니다.."),
    WRONG_BOOK_ID(HttpStatus.INTERNAL_SERVER_ERROR,"잘못된 도서ID 값입니다." ),

    /**
    * 사용자도서
    * */
    USER_BOOK_ALREADY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR, "이미 등록된 사용자도서입니다."),
    USER_BOOK_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "사용자도서 정보를 찾을 수 없습니다."),
    USER_BOOK_ALREADY_EXISTS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"사용자도서가 이미 존재합니다." ),

    /**
     * 사용자정보
     * */
    USER_INFO_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "사용자정보를 찾지 못했습니다." ),
    WRONG_USER_BOOK_ID(HttpStatus.INTERNAL_SERVER_ERROR,"잘못된 사용자도서ID 값입니다." ),

    /**
     * 독서기록
     * */
    JOURNAL_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "독서기록을 찾지 못하였습니다." ),
    JOURNAL_NOT_FOUND_FOR_REMOVE(HttpStatus.INTERNAL_SERVER_ERROR,"삭제할 독서기록을 찾지 못하였습니다." ),
    JOURNAL_NOT_FOUND_FOR_MODIFY(HttpStatus.INTERNAL_SERVER_ERROR,"수정할 독서기록을 찾지 못하였습니다." ),
    WRONG_JOURNAL_ID(HttpStatus.INTERNAL_SERVER_ERROR,"잘못된 독서기록ID 값입니다." ),
    JOURNAL_ALREADY_EXISTS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"이미 독서기록이 존재합니다." );


    private final HttpStatus httpStatus;
    private final String message;
}
