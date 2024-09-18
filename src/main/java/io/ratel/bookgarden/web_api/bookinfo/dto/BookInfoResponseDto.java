package io.ratel.bookgarden.web_api.bookinfo.dto;

import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record BookInfoResponseDto(String title, String author, String publisher, String description, String link,
                                  @Size(min = 10, max = 10, message = "Field must be exactly 10 characters long") String isbn,
                                  @Size(min = 13, max = 13, message = "Field must be exactly 13 characters long") String isbn13,
                                  String cover) {

    //private final LocalDateTime publishedDate;

    public static BookInfoResponseDto convertToBookInfoResponseDto(BookInfoEntity bookInfo) {
        return BookInfoResponseDto.builder()
                .title(bookInfo.getTitle())
                .author(bookInfo.getAuthor())
                .publisher(bookInfo.getPublisher())
                .description(bookInfo.getDescription())
                .link(bookInfo.getLink())
                .isbn(bookInfo.getIsbn())
                .isbn13(bookInfo.getIsbn13())
                .cover(bookInfo.getCover())
                //.publishedDate(bookInfo.getPublishedDate())
                .build();
    }

    public static List<BookInfoResponseDto> convertToBookInfosResponseDtoList(List<BookInfoEntity> books) {
        return books.stream().map(BookInfoResponseDto::convertToBookInfoResponseDto).collect(Collectors.toList());
    }
}
