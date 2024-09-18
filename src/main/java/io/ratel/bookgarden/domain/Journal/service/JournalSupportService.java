package io.ratel.bookgarden.domain.Journal.service;

import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.Journal.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JournalSupportService {

    private final JournalRepository journalRepository;

    public void validateJournalId(Long journalId){
        if(journalId == null) {
            throw new BusinessException(BusinessErrorResult.WRONG_JOURNAL_ID);
        }
    }

    public void validateUserBookId(Long userBookId){
        if(userBookId == null) {
            throw new BusinessException(BusinessErrorResult.WRONG_USER_BOOK_ID);
        }
    }

    public void validatePossibleCreate(Long journalId){
        if(journalId != null) {
            journalRepository.selectById(journalId).ifPresent(value -> {
                throw new BusinessException(BusinessErrorResult.JOURNAL_ALREADY_EXISTS_ERROR);
            });
        }
    }
}
