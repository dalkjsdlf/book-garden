package io.ratel.bookgarden.web_api.phrase.dto;

import io.ratel.bookgarden.domain.phrase.entity.PhraseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The type Phrase create request dto.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PhraseCreateRequestDto {
    private String content;
    private Long userBookId;

    /**
     * From dto phrase entity.
     *
     * @param phraseCreateRequestDto the phrase create request dto
     * @return the phrase entity
     */
    public static PhraseEntity fromDto(PhraseCreateRequestDto phraseCreateRequestDto) {
        return PhraseEntity.
                of(phraseCreateRequestDto.userBookId,
                        phraseCreateRequestDto.content);
    }
}
