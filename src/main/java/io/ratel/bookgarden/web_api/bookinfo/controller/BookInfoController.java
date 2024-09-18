package io.ratel.bookgarden.web_api.bookinfo.controller;

import io.ratel.bookgarden.web_api.bookinfo.application.BookInfoApplication;
import io.ratel.bookgarden.web_api.bookinfo.dto.BookInfoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BookInfoController {

    private final BookInfoApplication bookInfoApplication;

    @GetMapping("")
    public ResponseEntity<List<BookInfoResponseDto>> getBooks(){
      log.debug("책 정보");
      List<BookInfoResponseDto> bookInfoResponseDtos = bookInfoApplication.getBooks();
      return ResponseEntity.ok(bookInfoResponseDtos);
    }

    @GetMapping("/title")
    public ResponseEntity<List<BookInfoResponseDto>> getBooksByTitle(
            @RequestParam("title") String title
    ){
        log.debug("책 정보");
        List<BookInfoResponseDto> bookInfoResponseDtos = bookInfoApplication.getBooksByTitle(title);
        return ResponseEntity.ok(bookInfoResponseDtos);
    }
}
