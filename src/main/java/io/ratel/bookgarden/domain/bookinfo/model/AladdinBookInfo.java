package io.ratel.bookgarden.domain.bookinfo.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AladdinBookInfo {
    @JacksonXmlProperty(localName = "itemId")
    private String itemId;

    @JacksonXmlProperty(localName = "title")
    private String title;

    @JacksonXmlProperty(localName = "link")
    private String link;

    @JacksonXmlProperty(localName = "author")
    private String author;

    @JacksonXmlProperty(localName = "pubDate")
    private String pubDate;

    @JacksonXmlProperty(localName = "description")
    private String description;

    @JacksonXmlProperty(localName = "isbn")
    private String isbn;

    @JacksonXmlProperty(localName = "isbn13")
    private String isbn13;

    @JacksonXmlProperty(localName = "priceSales")
    private int priceSales;

    @JacksonXmlProperty(localName = "priceStandard")
    private int priceStandard;

    @JacksonXmlProperty(localName = "mallType")
    private String mallType;

    @JacksonXmlProperty(localName = "stockStatus")
    private String stockStatus;

    @JacksonXmlProperty(localName = "mileage")
    private int mileage;

    @JacksonXmlProperty(localName = "cover")
    private String cover;

    @JacksonXmlProperty(localName = "categoryId")
    private int categoryId;

    @JacksonXmlProperty(localName = "categoryName")
    private String categoryName;

    @JacksonXmlProperty(localName = "publisher")
    private String publisher;

    @JacksonXmlProperty(localName = "salesPoint")
    private int salesPoint;

    @JacksonXmlProperty(localName = "adult")
    private boolean adult;

    @JacksonXmlProperty(localName = "fixedPrice")
    private boolean fixedPrice;

    @JacksonXmlProperty(localName = "customerReviewRank")
    private int customerReviewRank;

    @JacksonXmlProperty(localName = "subInfo")
    private int subInfo;

    @JacksonXmlProperty(localName = "seriesInfo")
    private int seriesInfo;
}


//    private Long id;

//    @JacksonXmlProperty(localName = "title")
//    private String title;

//    private String author;
//
//    private String publisher;
//
//    private String description;
//
//    private Category category;
//
//    private String link;
//
//    private String isbn;
//
//    private String isbn13;
//
//    private String cover;
//
//    private LocalDateTime publishedDate;

//    @JsonCreator
//    public BookInfo(@JacksonXmlProperty(localName = "title") String title,
//                    @JacksonXmlProperty(localName = "author") String author,
//                    @JacksonXmlProperty(localName = "publisher") String publisher,
//                    @JacksonXmlProperty(localName = "description") String description,
//                    @JacksonXmlProperty(localName = "link") String link,
//                    @JacksonXmlProperty(localName = "isbn") String isbn,
//                    @JacksonXmlProperty(localName = "isbn13") String isbn13,
//                    @JacksonXmlProperty(localName = "cover") String cover,
//                    @JacksonXmlProperty(localName = "pubDate") LocalDateTime publishedDate) {
//        this.title = title;
//        this.author = author;
//        this.publisher = publisher;
//        this.description = description;
//        this.link = link;
//        this.isbn = isbn;
//        this.isbn13 = isbn13;
//        this.cover = cover;
//        this.publishedDate = publishedDate;
//    }

    //    private BookInfo(String title,
//                     String author,
//                     String publisher,
//                     String description,
//                     Category category,
//                     String link,
//                     String isbn,
//                     String isbn13,
//                     String cover,
//                     LocalDateTime publishedDate) {
//        this.title = title;
//        this.author = author;
//        this.publisher = publisher;
//        this.description = description;
//        this.link = link;
//        this.isbn = isbn;
//        this.isbn13 = isbn13;
//        this.cover = cover;
//        this.publishedDate = publishedDate;
//    }
//
//    public static BookInfo of(String title,
//                              String author,
//                              String publisher,
//                              String description,
//                              Category category,
//                              String link,
//                              String isbn,
//                              String isbn13,
//                              String cover,
//                              LocalDateTime publishedDate) {
//        return new BookInfo(title,
//                            author,
//                            publisher,
//                            description,
//                            category,
//                            link,
//                            isbn,
//                            isbn13,
//                            cover,
//                            publishedDate);
//    }

//    public static BookInfo convertEntityToModel(BookInfoEntity bookInfoEntity){
//        return new BookInfo(bookInfoEntity.getTitle(),
//                bookInfoEntity.getAuthor(),
//                bookInfoEntity.getPublisher(),
//                bookInfoEntity.getDescription(),
//                bookInfoEntity.getCategory(),
//                bookInfoEntity.getLink(),
//                bookInfoEntity.getIsbn(),
//                bookInfoEntity.getIsbn13(),
//                bookInfoEntity.getCover(),
//                bookInfoEntity.getPublishedDate());
//    }
//
//    public static BookInfo convertModelToEntity(BookInfo bookInfo){
//        return new BookInfo(bookInfo.title,
//                bookInfo.author,
//                bookInfo.publisher,
//                bookInfo.description,
//                bookInfo.category,
//                bookInfo.link,
//                bookInfo.isbn,
//                bookInfo.isbn13,
//                bookInfo.cover,
//                bookInfo.publishedDate);
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        BookInfo bookInfo = (BookInfo) o;
//        return Objects.equals(id, bookInfo.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
//    }
//}
