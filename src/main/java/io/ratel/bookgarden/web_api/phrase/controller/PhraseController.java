package io.ratel.bookgarden.web_api.phrase.controller;

import io.ratel.bookgarden.web_api.phrase.application.PhraseApplication;
import io.ratel.bookgarden.web_api.phrase.dto.PhraseCreateRequestDto;
import io.ratel.bookgarden.web_api.phrase.dto.PhraseGetResponseDto;
import io.ratel.bookgarden.web_api.phrase.dto.PhraseModifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/phrases")
@RequiredArgsConstructor
public class PhraseController {

    private final PhraseApplication phraseApplication;

    @GetMapping("{id}")
    public ResponseEntity<PhraseGetResponseDto> getPhraseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(phraseApplication.getPhraseById(id));
    }

    @PostMapping("")
    public ResponseEntity<Void> createPhrase(
            @RequestBody(required = true) PhraseCreateRequestDto phraseCreateRequestDto) {
        phraseApplication.createPhrase(phraseCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("")
    public ResponseEntity<Void> modifyPhrase(@RequestBody(required = true) PhraseModifyRequestDto phraseModifyRequestDto) {
        boolean isModified = phraseApplication.modifyPhrase(phraseModifyRequestDto);

        if (isModified) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removePhrase(@PathVariable("id") Long id) {
        boolean isRemoved = phraseApplication.removePhrase(id);
        if (isRemoved) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
