package io.ratel.bookgarden.web_api.journal.application;

import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;
import io.ratel.bookgarden.domain.Journal.service.JournalService;
import io.ratel.bookgarden.web_api.journal.dto.JournalCreateRequestDto;
import io.ratel.bookgarden.web_api.journal.dto.JournalGetResponseDto;
import io.ratel.bookgarden.web_api.journal.dto.JournalModifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JournalApplication {
    private final JournalService journalService;


    public JournalGetResponseDto getJournalById(Long id) {
        JournalEntity journalEntity = journalService.getJournalById(id);
        return JournalGetResponseDto.fromEntity(journalEntity);
    }

    public void createJournal(JournalCreateRequestDto journalCreateRequestDto) {
        JournalEntity journalEntity = JournalCreateRequestDto.fromDto(journalCreateRequestDto);
        journalService.createJournal(journalEntity);
    }

    public boolean modifyJournal(JournalModifyRequestDto journalModifyRequestDto) {
        JournalEntity journalEntity = journalService.getJournalById(journalModifyRequestDto.getId());
        JournalEntity result = journalService.modifyJournal(journalEntity);
        if (result == null)return false;
        else return true;
    }

    public boolean removeJournal(Long id) {
        Long affectedRow = journalService.removeById(id);

        if(affectedRow == null)return false;
        else return true;
    }
}
