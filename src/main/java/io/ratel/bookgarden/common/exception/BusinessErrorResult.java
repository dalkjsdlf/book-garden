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
    FAILED_TO_RETRIEVE_BOOK_INFO(HttpStatus.INTERNAL_SERVER_ERROR, "도서정보를 조회하는데 실패하였습니다."),
    USER_BOOK_ALREADY_EXISTS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "이미 등록된 도서입니다."),
    BOOK_INFO_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "도서정보를 찾지 못했습니다.."),
    USER_INFO_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "사용자정보를 찾지 못했습니다." );

    private final HttpStatus httpStatus;
    private final String message;
}
