package io.ratel.bookgarden.domain.bookinfo.repository;

import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;

import java.util.List;
import java.util.Optional;

public interface BookInfoRepository {
    public List<BookInfoEntity> findAll();
    public Optional<BookInfoEntity> findById(Long id);
    public List<BookInfoEntity> findByTitle(String title);
}
