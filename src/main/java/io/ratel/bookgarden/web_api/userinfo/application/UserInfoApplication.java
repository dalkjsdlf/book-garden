package io.ratel.bookgarden.web_api.userinfo.application;

import io.ratel.bookgarden.domain.userInfo.entity.UserInfoEntity;
import io.ratel.bookgarden.web_api.userinfo.dto.UserInfoResponseDto;
import io.ratel.bookgarden.domain.userInfo.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserInfoApplication {

    private final UserInfoService userInfoService;

    public List<UserInfoResponseDto> getUsers() {
        List<UserInfoEntity> userInfoEntities = userInfoService.getUsers();
        return UserInfoResponseDto.fromEntities(userInfoEntities);
    }

    public UserInfoResponseDto getUserById(Long id) {
        UserInfoEntity userInfoEntity = userInfoService.getUserById(id);
        return UserInfoResponseDto.fromEntity(userInfoEntity);
    }
}
