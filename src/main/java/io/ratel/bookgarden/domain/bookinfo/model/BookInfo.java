package io.ratel.bookgarden.domain.bookinfo.model;

import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class BookInfo {
    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String description;

    private Category category;

    private String link;

    private String isbn;

    private String isbn13;

    private String cover;

    private LocalDateTime publishedDate;


        private BookInfo(String title,
                     String author,
                     String publisher,
                     String description,
                     Category category,
                     String link,
                     String isbn,
                     String isbn13,
                     String cover,
                     LocalDateTime publishedDate) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.link = link;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.cover = cover;
        this.publishedDate = publishedDate;
    }

    public static BookInfo of(String title,
                              String author,
                              String publisher,
                              String description,
                              Category category,
                              String link,
                              String isbn,
                              String isbn13,
                              String cover,
                              LocalDateTime publishedDate) {
        return new BookInfo(title,
                            author,
                            publisher,
                            description,
                            category,
                            link,
                            isbn,
                            isbn13,
                            cover,
                            publishedDate);
    }

    public static BookInfo convertEntityToModel(BookInfoEntity bookInfoEntity){
        return new BookInfo(bookInfoEntity.getTitle(),
                bookInfoEntity.getAuthor(),
                bookInfoEntity.getPublisher(),
                bookInfoEntity.getDescription(),
                bookInfoEntity.getCategory(),
                bookInfoEntity.getLink(),
                bookInfoEntity.getIsbn(),
                bookInfoEntity.getIsbn13(),
                bookInfoEntity.getCover(),
                bookInfoEntity.getPublishedDate());
    }

    public static BookInfo convertModelToEntity(BookInfo bookInfo){
        return new BookInfo(bookInfo.title,
                bookInfo.author,
                bookInfo.publisher,
                bookInfo.description,
                bookInfo.category,
                bookInfo.link,
                bookInfo.isbn,
                bookInfo.isbn13,
                bookInfo.cover,
                bookInfo.publishedDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookInfo bookInfo = (BookInfo) o;
        return Objects.equals(id, bookInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
