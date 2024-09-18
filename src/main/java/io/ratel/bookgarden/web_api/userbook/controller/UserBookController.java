package io.ratel.bookgarden.web_api.userbook.controller;

import io.ratel.bookgarden.common.constants.WebApiConst;
import io.ratel.bookgarden.web_api.userbook.application.UserBookApplication;
import io.ratel.bookgarden.web_api.userbook.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자 도서 관리 컨트롤러.
 * <p>
 * 사용자 도서에 대한 CRUD 작업을 위한 엔드포인트를 제공합니다.
 * </p>
 *
 * @author 최연호
 * @date 2024-08-27
 */
@RestController
@RequestMapping("/api/v1/userbooks")
@RequiredArgsConstructor
public class UserBookController {

    private final UserBookApplication userBookApplication;

    /**
     * 모든 사용자 도서 조회.
     *
     * @param userId 요청 헤더의 사용자 ID
     * @return 사용자 도서 목록
     */
    @GetMapping
    public ResponseEntity<List<UserBookGetResponseDto>> getAllUserBooks(@RequestHeader(WebApiConst.USER_ID_HEADER) Long userId) {
        List<UserBookGetResponseDto> userBooks = userBookApplication.getUserBooks(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBookDetailGetResponseDto> getUserBookById(@PathVariable Long id,
                                                                        @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId) {
        UserBookDetailGetResponseDto userBook = userBookApplication.getUserBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userBook);
    }

    @PostMapping
    public ResponseEntity<UserBookResponseDto> createUserBook(@Valid @RequestBody UserBookCreateRequestDto requestDto,
                                                              @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId) {
        UserBookResponseDto createdUserBook = userBookApplication.createUserBook(requestDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserBookResponseDto> updateUserBook(@PathVariable Long id,
                                                         @Valid @RequestBody UserBookUpdateRequestDto requestDto,
                                                         @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId) {
        UserBookResponseDto updatedUserBookDto = userBookApplication.updateUserBook(requestDto, id, userId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUserBookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserBook(@PathVariable Long id,
                                               @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId) {
        userBookApplication.deleteUserBook(id, userId);
        return ResponseEntity.noContent().build();
    }
}