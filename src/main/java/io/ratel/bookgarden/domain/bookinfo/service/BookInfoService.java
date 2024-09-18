package io.ratel.bookgarden.domain.bookinfo.service;

import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import io.ratel.bookgarden.domain.bookinfo.repository.BookInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookInfoService {

    private final BookInfoRepository bookInfoRepository;

    public BookInfoEntity getBookById(Long id){
        Optional<BookInfoEntity> optBookInfoEntity = bookInfoRepository.findById(id);
        return optBookInfoEntity.orElseThrow(()->new BusinessException(BusinessErrorResult.BOOK_INFO_NOT_FOUND));
    }

    public List<BookInfoEntity> getBooks(){
        return bookInfoRepository.findAll();
    }

    public List<BookInfoEntity> getBooksByTitle(String title){
        return bookInfoRepository.findByTitle(title);
    }
}
