package io.ratel.bookgarden.web_api.journal.dto;

import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JournalGetResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long userBookDto;

    public static JournalGetResponseDto fromEntity(JournalEntity journalEntity) {
        return JournalGetResponseDto.builder().
                id(journalEntity.getId()).
                title(journalEntity.getTitle()).
                content(journalEntity.getContent()).
                userBookDto(journalEntity.getUserBookId())
                .build();
    }

    public static List<JournalGetResponseDto> fromEntities(List<JournalEntity> journalEntity) {
        return journalEntity.
                stream().
                map(JournalGetResponseDto::fromEntity).collect(Collectors.toList());
    }
}
