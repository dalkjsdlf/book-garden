package io.ratel.bookgarden.domain.phrase.service;

import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.phrase.repository.PhraseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhraseSupportService {

    private final PhraseRepository phraseRepository;

    public void validatePhraseId(Long phraseId){
        if(phraseId == null) {
            throw new BusinessException(BusinessErrorResult.WRONG_PHRASE_ID);
        }
    }

    public void validateUserBookId(Long userBookId){
        if(userBookId == null) {
            throw new BusinessException(BusinessErrorResult.WRONG_USER_BOOK_ID);
        }
    }

    public void validatePossibleCreate(Long phraseId){
        if(phraseId != null) {
            phraseRepository.selectById(phraseId).ifPresent(value -> {
                throw new BusinessException(BusinessErrorResult.JOURNAL_ALREADY_EXISTS_ERROR);
            });
        }
    }
}
