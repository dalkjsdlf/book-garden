package io.ratel.bookgarden.web_api.bookinfo.application;

import io.ratel.bookgarden.domain.bookinfo.service.BookInfoService;
import io.ratel.bookgarden.web_api.bookinfo.dto.BookInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookInfoApplication {

    private final BookInfoService bookInfoService;

    public List<BookInfoResponseDto>  getBooks() {
        return BookInfoResponseDto.convertToBookInfosResponseDtoList(bookInfoService.getBooks());
    }

    public List<BookInfoResponseDto> getBooksByTitle(String title) {
        return BookInfoResponseDto.convertToBookInfosResponseDtoList(bookInfoService.getBooksByTitle(title));
    }
}
