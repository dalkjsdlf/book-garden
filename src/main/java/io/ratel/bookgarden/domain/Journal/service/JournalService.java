package io.ratel.bookgarden.domain.Journal.service;

import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;
import io.ratel.bookgarden.domain.Journal.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;
    private final JournalSupportService journalSupportService;

    public List<JournalEntity> getJournalByUserBookId(Long userBookId) {
        return journalRepository.selectByUserBookId(userBookId);
    }

    public Long removeById(Long journalId) {
        /**
         * 삭제대상이 존재하는지 확인
         * */
        Optional<JournalEntity> optionalJournalEntity = journalRepository.selectById(journalId);

        /**
         * 삭제 대상이 존재하지 않을 시 예외
         * */
        optionalJournalEntity.orElseThrow(() -> new BusinessException(BusinessErrorResult.JOURNAL_NOT_FOUND_FOR_REMOVE));

        /**
         * 삭제
         * */
        return journalRepository.deleteById(journalId);
    }

    public JournalEntity getJournalById(Long journalId) {
        Optional<JournalEntity> optionalJournalEntity = journalRepository.selectById(journalId);
        return optionalJournalEntity.orElseThrow(() -> new BusinessException(BusinessErrorResult.JOURNAL_NOT_FOUND));
    }

    public JournalEntity createJournal(JournalEntity journalEntity) {

        Long journalId  = journalEntity.getId();
        Long userBookId = journalEntity.getUserBookId();

        /**
         * userBookId 값이 올바른지 검사
         * */
        journalSupportService.validateUserBookId(userBookId);

        /**
         * 저장된 journalId 값이 존재한지 검사(입력가능한지 검사)
         * */
        journalSupportService.validatePossibleCreate(journalId);

        /**
         * 독서기록 입력
         * */
        return journalRepository.save(journalEntity);
    }

    public JournalEntity modifyJournal(JournalEntity journalEntity) {
        Long journalId = journalEntity.getId();
        Long userBookId = journalEntity.getUserBookId();

        /**
         * journalId 값이 올바른지 검사
         * */
        journalSupportService.validateJournalId(journalId);

        /**
         * userBookId 값이 올바른지 검사
         * */
        journalSupportService.validateUserBookId(userBookId);

        /**
         * 수정대상이 존재하는지 확인
         * */
        Optional<JournalEntity> optionalJournalEntity = journalRepository.selectById(journalId);

        /**
         * 수정 대상이 존재하지 않을 시 예외
         * */
        optionalJournalEntity.orElseThrow(() -> new BusinessException(BusinessErrorResult.JOURNAL_NOT_FOUND_FOR_MODIFY));

        return journalRepository.save(journalEntity);
    }
}
