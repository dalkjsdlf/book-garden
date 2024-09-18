package io.ratel.bookgarden.domain.bookinfo.entity;

import io.ratel.bookgarden.common.persistence.entity.AuditableFields;
import io.ratel.bookgarden.domain.bookinfo.model.Category;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Table(name = "BOOK_INFO")
@Entity
@Getter
public class BookInfoEntity extends AuditableFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 255)
    private String title;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @Column(name = "DESCRIPTION", nullable = true, length = 4000)
    private String description;

    @Column(name = "CATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "LINK", nullable = true)
    private String link;

    @Column(name = "ISBN", nullable = false, length = 10)
    private String isbn;

    @Column(name = "ISBN13", nullable = false, length = 13)
    private String isbn13;

    @Column(name = "COVER", nullable = true)
    private String cover;

    @Column(name = "PUBLISHED_DATE", nullable = false)
    private LocalDateTime publishedDate;

    protected BookInfoEntity() {
    }

    private BookInfoEntity(
            String title,
            String author,
            String publisher,
            String description,
            Category category,
            String link,
            String isbn,
            String isbn13,
            String cover,
            LocalDateTime publishedDate
    ) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.category = category;
        this.link = link;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.cover = cover;
        this.publishedDate = publishedDate;
    }

    public static BookInfoEntity of(String title,
                                    String author,
                                    String publisher,
                                    String description,
                                    Category category,
                                    String link,
                                    String isbn,
                                    String isbn13,
                                    String cover,
                                    LocalDateTime publishedDate) {

        return new BookInfoEntity(title,
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

}
