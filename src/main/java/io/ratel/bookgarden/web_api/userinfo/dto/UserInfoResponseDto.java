package io.ratel.bookgarden.web_api.userinfo.dto;

import io.ratel.bookgarden.domain.userInfo.entity.UserInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponseDto {
    private Long id;
    private String name;
    private String email;
    private String password;

    public static UserInfoResponseDto fromEntity(UserInfoEntity userInfoEntity) {
        return UserInfoResponseDto.builder().
                id(userInfoEntity.getId()).
                name(userInfoEntity.getName()).
                email(userInfoEntity.getEmail()).
                password(userInfoEntity.getPassword())
                .build();
    }

    public static List<UserInfoResponseDto> fromEntities(List<UserInfoEntity> userInfoEntities) {
        return userInfoEntities.
                stream().
                map(UserInfoResponseDto::fromEntity).collect(Collectors.toList());
    }
}
