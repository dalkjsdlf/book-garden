package io.ratel.bookgarden.domain.phrase.service;


import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.phrase.entity.PhraseEntity;
import io.ratel.bookgarden.domain.phrase.repository.PhraseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * [Phrase][Service] 글귀 서비스
 */
@Service
@RequiredArgsConstructor
public class PhraseService {

    private final PhraseRepository phraseRepository;
    private final PhraseSupportService phraseSupportService;

    /**
     * [조회 - 다건] 글귀 정보 조회
     *
     * @param userBookId the user book id
     * @return the phrase by user book id
     */
    public List<PhraseEntity> getPhraseByUserBookId(Long userBookId) {
        return phraseRepository.selectByUserBookId(userBookId);
    }

    /**
     * [조회 - 단건] 글귀 정보 조회
     *
     * @param phraseId the phrase id
     * @return the phrase by id
     */
    public PhraseEntity getPhraseById(Long phraseId) {
        Optional<PhraseEntity> optionalPhraseEntity = phraseRepository.selectById(phraseId);
        return optionalPhraseEntity.orElseThrow(() -> new BusinessException(BusinessErrorResult.PHRASE_NOT_FOUND));
    }

    /**
     * [입력 - 단건] 글귀 입력
     *
     * @param phraseEntity the phrase entity
     * @return the phrase entity
     */
    public PhraseEntity createPhrase(PhraseEntity phraseEntity) {

        Long phraseId  = phraseEntity.getId();
        Long userBookId = phraseEntity.getUserBookId();

        /*
         * userBookId 값이 올바른지 검사
         * */
        phraseSupportService.validateUserBookId(userBookId);

        /*
         * 저장된 phraseId 값이 존재한지 검사(입력가능한지 검사)
         * */
        phraseSupportService.validatePossibleCreate(phraseId);

        /*
         * 글귀 입력
         * */
        return phraseRepository.save(phraseEntity);
    }

    /**
     * [수정 - 단건] 글귀 정보 수정
     *
     * @param phraseEntity the phrase entity
     * @return the phrase entity
     */
    public PhraseEntity modifyPhrase(PhraseEntity phraseEntity) {
        Long phraseId = phraseEntity.getId();
        Long userBookId = phraseEntity.getUserBookId();

        /*
         * phraseId 값이 올바른지 검사
         * */
        phraseSupportService.validatePhraseId(phraseId);

        /*
         * userBookId 값이 올바른지 검사
         * */
        phraseSupportService.validateUserBookId(userBookId);

        /*
         * 수정대상이 존재하는지 확인
         * */
        Optional<PhraseEntity> optionalPhraseEntity = phraseRepository.selectById(phraseId);

        /*
         * 수정 대상이 존재하지 않을 시 예외
         * */
        optionalPhraseEntity.orElseThrow(() -> new BusinessException(BusinessErrorResult.PHRASE_NOT_FOUND_FOR_MODIFY));

        return phraseRepository.save(phraseEntity);
    }


    /**
     * [삭제 - 단건] 글귀 정보 삭제
     *
     * @param phraseId the phrase id
     * @return the long
     */
    public Long removeById(Long phraseId) {
        /*
         * 삭제대상이 존재하는지 확인
         * */
        Optional<PhraseEntity> optionalPhraseEntity = phraseRepository.selectById(phraseId);

        /*
         * 삭제 대상이 존재하지 않을 시 예외
         * */
        optionalPhraseEntity.orElseThrow(() -> new BusinessException(BusinessErrorResult.PHRASE_NOT_FOUND_FOR_REMOVE));

        /*
         * 삭제
         * */
        return phraseRepository.deleteById(phraseId);
    }
}
