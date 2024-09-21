package io.ratel.bookgarden.domain.userbook.service;

import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.userbook.repository.UserBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBookSupportService {

    private final UserBookRepository userBookRepository;

    public void validateBookId(Long bookId){
        if(bookId == null) {
            throw new BusinessException(BusinessErrorResult.WRONG_BOOK_ID);
        }
    }

    public void validateUserBookId(Long userBookId){
        if(userBookId == null) {
            throw new BusinessException(BusinessErrorResult.WRONG_USER_BOOK_ID);
        }
    }

    public void validatePossibleCreate(Long userBookId){
        if(userBookId != null) {
            userBookRepository.selectById(userBookId).ifPresent(value -> {
                throw new BusinessException(BusinessErrorResult.USER_BOOK_ALREADY_EXISTS_ERROR);
            });
        }
    }
    
}
