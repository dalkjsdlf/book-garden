package io.ratel.bookgarden.domain.bookinfo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static io.ratel.bookgarden.domain.bookinfo.entity.QBookInfoEntity.bookInfoEntity;

@Repository
public class BookInfoRepositoryImpl implements BookInfoRepository {

    private final JPAQueryFactory queryFactory;

    public BookInfoRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<BookInfoEntity> findAll() {
        return queryFactory
                .selectFrom(bookInfoEntity).fetch();
    }

    @Override
    public Optional<BookInfoEntity> findById(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(bookInfoEntity)
                .where(bookInfoEntity.id.eq(id)).fetchOne());
    }

    @Override
    public List<BookInfoEntity> findByTitle(String title) {
        return queryFactory
                .selectFrom(bookInfoEntity)
                .where(bookInfoEntity.title.like("%" + title + "%"))
                .fetch();
    }
}
