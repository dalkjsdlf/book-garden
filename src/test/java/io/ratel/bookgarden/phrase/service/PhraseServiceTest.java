package io.ratel.bookgarden.phrase.service;

import io.ratel.bookgarden.domain.phrase.entity.PhraseEntity;
import io.ratel.bookgarden.domain.phrase.service.PhraseService;
import io.ratel.bookgarden.phrase.repository.PhraseRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("글귀 Service 테스트")
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PhraseServiceTest {

    @Autowired
    private PhraseService phraseService;

    private final Logger Log = LoggerFactory.getLogger(PhraseRepositoryTest.class);

    @DisplayName("[성공] 글귀 조회 사용자도서에 해당하는 모든 글귀 조회")
    @Test
    public void 사용자도서ID_전체글귀조회_글귀목록(){
        //given
        Long userBookId = 1L;

        //when
        List<PhraseEntity> phrases = phraseService.getPhraseByUserBookId(userBookId);

        Log.debug("#DEBUG phrases size: {}", phrases.size());

        //then
        assertThat(phrases).isNotNull();
        assertThat(phrases.size()).isGreaterThan(0);
    }

    @DisplayName("[성공] 글귀 정보 조회 특정 글귀 조회")
    @Test
    public void 글귀ID_ID로글귀조회_글귀1건(){
        //given
        // 글귀 ID 1L
        Long phraseId = 1L;

        //when
        PhraseEntity phraseEntity = phraseService.getPhraseById(phraseId);

        Log.debug("#DEBUG phraseEntity CONTENT: {}", phraseEntity.getContent());

        //then
        assertThat(phraseEntity).isNotNull();
    }

    @DisplayName("[성공] 글귀 정보 입력")
    @Test
    @Transactional
    public void 글귀입력값_ID로글귀입력_글귀입력1건(){
        //given
        Long userBookId = 1L;
        String content = "생성형 AI 를 바라보는 우리자세에 대한 이야기";
        PhraseEntity phraseEntity = PhraseEntity.of(userBookId, content);
        int beforeSize = phraseService.getPhraseByUserBookId(userBookId).size();
        Log.debug("#DEBUG before phraseEntity size : {}", beforeSize);

        //when
        PhraseEntity createdPhrase = phraseService.createPhrase(phraseEntity);

        Log.debug("#DEBUG addedPhrase phraseEntity CONTENT: {}", createdPhrase.getContent());

        int afterSize = phraseService.getPhraseByUserBookId(userBookId).size();
        Log.debug("#DEBUG after phraseEntity size : {}", afterSize);

        //then
        assertThat(createdPhrase).isNotNull();
        assertThat(createdPhrase.getContent()).isEqualTo(content);
        assertThat(afterSize).isEqualTo(beforeSize + 1);
    }

    @DisplayName("[성공] 글귀 정보 수정")
    @Test
    @Transactional
    public void 수정할글귀ID_ID로글귀수정_글귀수정1건(){
        //given
        // 수정할 글귀 ID
        Long phraseId = 1L;
        String content = "생성형 AI 를 바라보는 우리자세에 대한 이야기[수정]";

        // 수정할 글귀 조회
        PhraseEntity phraseEntity = phraseService.getPhraseById(phraseId);
        Log.debug("#DEBUG before phraseEntity content : {}", phraseEntity.getContent());


        //when
        // 글귀 타이틀 수정
        phraseEntity.setContent(content);

        PhraseEntity modifiedPhraseEntity = phraseService.modifyPhrase(phraseEntity);
        Log.debug("#DEBUG after phraseEntity content : {}", modifiedPhraseEntity.getContent());

        //then
        assertThat(modifiedPhraseEntity).isNotNull();
        assertThat(modifiedPhraseEntity.getContent()).isEqualTo(content);
    }

    @DisplayName("[성공] 글귀 정보 삭제")
    @Test
    @Transactional
    public void 삭제할글귀ID_ID로글귀삭제_글귀삭제1건(){
        //given
        // 글귀 ID 1L
        Long userBookId = 1L;
        Long phraseId = 1L;
        int beforeSize = phraseService.getPhraseByUserBookId(userBookId).size();
        Log.debug("#DEBUG before phraseEntity size : {}", beforeSize);

        //when
        Long removedRow = phraseService.removeById(phraseId);

        Log.debug("#DEBUG deleted phraseEntity Row : {}", removedRow);

        int afterSize = phraseService.getPhraseByUserBookId(userBookId).size();
        Log.debug("#DEBUG after phraseEntity size : {}", afterSize);

        //then
        assertThat(removedRow).isNotNull();
        assertThat(removedRow).isGreaterThan(0);
        assertThat(afterSize).isEqualTo(beforeSize - 1);
    }
}
