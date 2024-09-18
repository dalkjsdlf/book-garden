package io.ratel.bookgarden.web_api.userinfo.controller;

import io.ratel.bookgarden.common.constants.WebApiConst;
import io.ratel.bookgarden.web_api.userinfo.application.UserInfoApplication;
import io.ratel.bookgarden.web_api.userinfo.dto.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoApplication userInfoApplication;

    @GetMapping
    public ResponseEntity<List<UserInfoResponseDto>> getUsers(
            @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId
    ) {
        List<UserInfoResponseDto> users = userInfoApplication.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponseDto> getUsersById(
            @RequestHeader(WebApiConst.USER_ID_HEADER) Long userId,
            @PathVariable Long id
    ) {
        UserInfoResponseDto user = userInfoApplication.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
