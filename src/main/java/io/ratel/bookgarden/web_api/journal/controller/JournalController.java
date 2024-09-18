package io.ratel.bookgarden.web_api.journal.controller;

import io.ratel.bookgarden.web_api.journal.application.JournalApplication;
import io.ratel.bookgarden.web_api.journal.dto.JournalCreateRequestDto;
import io.ratel.bookgarden.web_api.journal.dto.JournalGetResponseDto;
import io.ratel.bookgarden.web_api.journal.dto.JournalModifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/journals")
@RequiredArgsConstructor
public class JournalController {

    private final JournalApplication journalApplication;

    @GetMapping("{id}")
    public ResponseEntity<JournalGetResponseDto> getJournalById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(journalApplication.getJournalById(id));
    }

    @PostMapping("")
    public ResponseEntity<Void> createJournal(
            @RequestBody(required = true) JournalCreateRequestDto journalCreateRequestDto) {
        journalApplication.createJournal(journalCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("")
    public ResponseEntity<Void> modifyJournal(@RequestBody(required = true) JournalModifyRequestDto journalModifyRequestDto) {
        boolean isModified = journalApplication.modifyJournal(journalModifyRequestDto);

        if (isModified) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeJournal(@PathVariable("id") Long id) {
        boolean isRemoved = journalApplication.removeJournal(id);
        if (isRemoved) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
