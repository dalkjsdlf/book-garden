package io.ratel.bookgarden.journal.repository;

import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;
import io.ratel.bookgarden.domain.Journal.repository.JournalRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("독서기록 Repository 테스트")
@SpringBootTest
@ActiveProfiles("test")
public class JournalRepositoryTest {

    @Autowired
    private JournalRepository journalRepository;

    private final Logger Log = LoggerFactory.getLogger(JournalRepositoryTest.class);

    @DisplayName("[성공] 독서기록 조회 사용자도서에 해당하는 모든 독서기록 조회")
    @Test
    public void 사용자도서ID_전체독서기록조회_독서기록목록(){
        //given
        Long userBookId = 1L;

        //when
        List<JournalEntity> journals = journalRepository.selectByUserBookId(userBookId);

        Log.debug("#DEBUG journals size: {}", journals.size());

        //then
        assertThat(journals).isNotNull();
        assertThat(journals.size()).isGreaterThan(0);
    }

    @DisplayName("[성공] 독서기록 정보 조회 특정 독서기록 조회")
    @Test
    public void 독서기록ID_ID로독서기록조회_독서기록1건(){
        //given
        // 독서기록 ID 1L
        Long journalId = 1L;

        //when
        Optional<JournalEntity> optJournal = journalRepository.selectById(journalId);

        JournalEntity journalEntity = optJournal.orElseThrow(()->new BusinessException(BusinessErrorResult.USER_INFO_NOT_FOUND));

        Log.debug("#DEBUG journalEntity TITLE: {}", journalEntity.getTitle());

        //then
        assertThat(journalEntity).isNotNull();
    }

    @DisplayName("[성공] 독서기록 정보 입력")
    @Test
    @Transactional
    public void 독서기록입력값_ID로독서기록입력_독서기록입력1건(){
        //given
        Long userBookId = 1L;
        String title = "사회의 어두운 면을 지켜보며 시작";
        String content = "이 책의 첫 페이지의 내용부터 시작되는 부분을 보면 ...";
        JournalEntity journalEntity = JournalEntity.of(userBookId, title, content);
        int beforeSize = journalRepository.selectByUserBookId(userBookId).size();
        Log.debug("#DEBUG before journalEntity size : {}", beforeSize);

        //when
        JournalEntity insertedJournal = journalRepository.save(journalEntity);

        Log.debug("#DEBUG inserted journalEntity TITLE: {}", insertedJournal.getTitle());

        int afterSize = journalRepository.selectByUserBookId(userBookId).size();
        Log.debug("#DEBUG after journalEntity size : {}", afterSize);

        //then
        assertThat(insertedJournal).isNotNull();
        assertThat(insertedJournal.getTitle()).isEqualTo(title);
        assertThat(afterSize).isEqualTo(beforeSize + 1);
    }

    @DisplayName("[성공] 독서기록 정보 수정")
    @Test
    @Transactional
    public void 수정할독서기록ID_ID로독서기록수정_독서기록수정1건(){
        //given
        // 수정할 독서기록 ID
        Long journalId = 1L;
        String title = "생성형 AI 를 바라보는 우리자세에 대한 이야기[수정]";

        // 수정할 독서기록 조회
        Optional<JournalEntity> optionalJournalEntity = journalRepository.selectById(journalId);
        JournalEntity journalEntity = optionalJournalEntity.orElseThrow(()->new BusinessException(BusinessErrorResult.JOURNAL_NOT_FOUND));
        Log.debug("#DEBUG before journalEntity title : {}", journalEntity.getTitle());


        //when
        // 독서기록 타이틀 수정
        journalEntity.setTitle(title);

        JournalEntity updatedJournalEntity = journalRepository.save(journalEntity);
        Log.debug("#DEBUG after journalEntity title : {}", updatedJournalEntity.getTitle());

        //then
        assertThat(updatedJournalEntity).isNotNull();
        assertThat(updatedJournalEntity.getTitle()).isEqualTo(title);
    }

    @DisplayName("[성공] 독서기록 정보 삭제")
    @Test
    @Transactional
    public void 삭제할독서기록ID_ID로독서기록삭제_독서기록삭제1건(){
        //given
        // 독서기록 ID 1L
        Long userBookId = 1L;
        Long journalId = 1L;
        int beforeSize = journalRepository.selectByUserBookId(userBookId).size();
        Log.debug("#DEBUG before journalEntity size : {}", beforeSize);

        //when
        Long deletedRow = journalRepository.deleteById(journalId);

        Log.debug("#DEBUG deleted journalEntity Row : {}", deletedRow);

        int afterSize = journalRepository.selectByUserBookId(userBookId).size();
        Log.debug("#DEBUG after journalEntity size : {}", afterSize);

        //then
        assertThat(deletedRow).isNotNull();
        assertThat(deletedRow).isGreaterThan(0);
        assertThat(afterSize).isEqualTo(beforeSize - 1);
    }
}
