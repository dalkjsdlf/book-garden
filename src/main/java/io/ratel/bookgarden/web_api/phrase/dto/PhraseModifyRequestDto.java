package io.ratel.bookgarden.web_api.phrase.dto;

import io.ratel.bookgarden.domain.phrase.entity.PhraseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The type Phrasel modify request dto.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PhraseModifyRequestDto {
    private Long id;
    private String content;
    private Long userBookId;

    /**
     * From dto phrase entity.
     *
     * @param phraseCreateRequestDto the phrase create request dto
     * @return the phrase entity
     */
    public static PhraseEntity fromDto(PhraseModifyRequestDto phraseCreateRequestDto) {
        PhraseEntity phraseEntity = PhraseEntity.
                of(phraseCreateRequestDto.userBookId,
                        phraseCreateRequestDto.content);

        phraseEntity.setId(phraseCreateRequestDto.getId());

        return phraseEntity;
    }
}
