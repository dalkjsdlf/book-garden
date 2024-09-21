package io.ratel.bookgarden.web_api.phrase.application;

import io.ratel.bookgarden.domain.phrase.entity.PhraseEntity;
import io.ratel.bookgarden.domain.phrase.service.PhraseService;
import io.ratel.bookgarden.web_api.phrase.dto.PhraseCreateRequestDto;
import io.ratel.bookgarden.web_api.phrase.dto.PhraseGetResponseDto;
import io.ratel.bookgarden.web_api.phrase.dto.PhraseModifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The type Phrase application.
 */
@Component
@RequiredArgsConstructor
public class PhraseApplication {
    private final PhraseService phraseService;


    /**
     * Gets phrase by id.
     *
     * @param id the id
     * @return the phrase by id
     */
    public PhraseGetResponseDto getPhraseById(Long id) {
        PhraseEntity phraseEntity = phraseService.getPhraseById(id);
        return PhraseGetResponseDto.fromEntity(phraseEntity);
    }

    /**
     * Create phrase.
     *
     * @param phraseCreateRequestDto the phrase create request dto
     */
    public void createPhrase(PhraseCreateRequestDto phraseCreateRequestDto) {
        PhraseEntity phraseEntity = PhraseCreateRequestDto.fromDto(phraseCreateRequestDto);
        phraseService.createPhrase(phraseEntity);
    }

    /**
     * Modify phrase boolean.
     *
     * @param phraseModifyRequestDto the phrase modify request dto
     * @return the boolean
     */
    public boolean modifyPhrase(PhraseModifyRequestDto phraseModifyRequestDto) {
        PhraseEntity phraseEntity = phraseService.getPhraseById(phraseModifyRequestDto.getId());
        PhraseEntity result = phraseService.modifyPhrase(phraseEntity);
        if (result == null)return false;
        else return true;
    }

    /**
     * Remove phrase boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean removePhrase(Long id) {
        Long affectedRow = phraseService.removeById(id);

        if(affectedRow == null)return false;
        else return true;
    }
}
