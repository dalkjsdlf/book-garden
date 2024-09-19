package io.ratel.bookgarden.userinfo.repository;

import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.userInfo.entity.UserInfoEntity;
import io.ratel.bookgarden.domain.userInfo.repository.UserInfoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DisplayName("사용자 정보 테스트")
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserInfoRepositoryTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

    private final Logger Log = LoggerFactory.getLogger(UserInfoRepositoryTest.class);

    private boolean isTestWithInitData = false;

    @DisplayName("[성공] 사용자 정보 조회 전체 사용자 조회")
    @Test
    public void 조건없이_전체사용자조회_사용자목록(){
        //given

        //when
        List<UserInfoEntity> users = userInfoRepository.selectAll();

        Log.debug("#DEBUG users size: {}", users.size());

        //then
        assertThat(users).isNotNull();
        assertThat(users.size()).isGreaterThan(0);
    }

    @DisplayName("[성공] 사용자 정보 조회 특정 사용자 조회")
    @Test
    public void 사용자ID_ID로사용자조회_사용자1건(){
        //given
        // 사용자 ID 1L
        Long userId = 1L;

        //when
        Optional<UserInfoEntity> optUserInfo = userInfoRepository.selectById(userId);

        UserInfoEntity userInfoEntity = optUserInfo.orElseThrow(()->new BusinessException(BusinessErrorResult.USER_INFO_NOT_FOUND));


        //then
        assertThat(userInfoEntity).isNotNull();
    }
}
