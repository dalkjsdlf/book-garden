package io.ratel.bookgarden.userinfo.service;

import io.ratel.bookgarden.domain.userInfo.entity.UserInfoEntity;
import io.ratel.bookgarden.domain.userInfo.service.UserInfoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@Transactional
@DisplayName("사용자 정보 서비스 조회 테스트")
@SpringBootTest
@ActiveProfiles("test")
public class UserInfoServiceTest {

    @Autowired
    private UserInfoService userInfoService;

    private final Logger Log = LoggerFactory.getLogger(UserInfoServiceTest.class);

    @DisplayName("[성공] 사용자 정보 조회, 다건조회")
    @Test
    public void 조건없이_사용자정보조회_사용자다건목록(){
        //given

        //when
        List<UserInfoEntity> userInfoEntities = userInfoService.getUsers();

        Log.debug("#DEBUG users size: {}", userInfoEntities.size());

        //then
        assertThat(userInfoEntities).isNotEmpty();
        assertThat(userInfoEntities.size()).isGreaterThan(0);

    }

    @DisplayName("[성공] 사용자 정보 조회, ID로 단건조회")
    @Test
    public void 사용자ID_ID로사용자정보조회_사용자단건(){
        //given
        Long userId = 1L;

        //when
        UserInfoEntity userInfoEntity = userInfoService.getUserById(userId);

        Log.debug("#DEBUG userInfoEntity ID: {}", userInfoEntity.getId());
        Log.debug("#DEBUG userInfoEntity NAME: {}", userInfoEntity.getName());

        //then
        assertThat(userInfoEntity).isNotNull();
    }
}
