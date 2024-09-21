package io.ratel.bookgarden.web_api.userbook.controller;

import io.ratel.bookgarden.common.constants.WebApiConst;
import io.ratel.bookgarden.web_api.userbook.application.UserBookApplication;
import io.ratel.bookgarden.web_api.userbook.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2024 -08-27
 */
@RestController
@RequestMapping("/api/v1/userbooks")
@RequiredArgsConstructor
@Slf4j
public class UserBookController {

    private final UserBookApplication userBookApplication;

    /**
     * 모든 사용자 도서 조회.
     *
     * @param userId 요청 헤더의 사용자 ID
     * @return 사용자 도서 목록
     */
    @GetMapping("")
    public ResponseEntity<List<UserBookGetResponseDto>> getAllUserBooks(
            @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId) {
        log.debug("#DEBUG UserBook Controller - getAllUserBooks");
        List<UserBookGetResponseDto> userBooks = userBookApplication.getUserBooks(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userBooks);
    }

    /**
     * 사용자도서ID로 사용자도서 조회.
     *
     * @param id     the id
     * @param userId the user id
     * @return the user book by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserBookDetailGetResponseDto> getUserBookById(@PathVariable Long id,
                                                                        @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId) {
        UserBookDetailGetResponseDto userBook = userBookApplication.getUserBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userBook);
    }

    /**
     * Create user book response entity.
     *
     * @param requestDto the request dto
     * @param userId     the user id
     * @return the response entity
     */
    @PostMapping("")
    public ResponseEntity<Void> createUserBook(@Valid @RequestBody UserBookCreateRequestDto requestDto,
                                                              @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId) {
        log.debug("#DEBUG UserBook Controller - createUserBook");
        requestDto.setUserId(userId);
        boolean isCreated = userBookApplication.createUserBook(requestDto);

        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    /**
     * Modify user book response entity.
     *
     * @param id         the id
     * @param requestDto the request dto
     * @param userId     the user id
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserBookResponseDto> modifyUserBook(@PathVariable Long id,
                                                         @Valid @RequestBody UserBookModifyRequestDto requestDto,
                                                         @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId) {

        /**
         * userId, Id, Dto setting
         * */
        requestDto.setId(id);
        requestDto.setUserId(userId);

        boolean isModified = userBookApplication.modifyUserBook(requestDto);

        if (isModified) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    /**
     * Remove user book response entity.
     *
     * @param id     the id
     * @param userId the user id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUserBook(@PathVariable Long id,
                                               @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId) {
        boolean isDeleted = userBookApplication.removeUserBook(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}