package io.ratel.bookgarden.web_api.phrase.dto;

import io.ratel.bookgarden.domain.phrase.entity.PhraseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Phrase get response dto.
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhraseGetResponseDto {

    private Long id;
    private String content;
    private Long userBookId;

    /**
     * From entity phrase get response dto.
     *
     * @param phraseEntity the phrase entity
     * @return the phrase get response dto
     */
    public static PhraseGetResponseDto fromEntity(PhraseEntity phraseEntity) {
        return PhraseGetResponseDto.builder().
                id(phraseEntity.getId()).
                content(phraseEntity.getContent()).
                userBookId(phraseEntity.getUserBookId())
                .build();
    }

    /**
     * From entities list.
     *
     * @param phraseEntity the phrase entity
     * @return the list
     */
    public static List<PhraseGetResponseDto> fromEntities(List<PhraseEntity> phraseEntity) {
        return phraseEntity.
                stream().
                map(PhraseGetResponseDto::fromEntity).collect(Collectors.toList());
    }
}
