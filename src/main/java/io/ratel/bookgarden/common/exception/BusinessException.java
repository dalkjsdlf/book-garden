package io.ratel.bookgarden.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException{

    private final BusinessErrorResult errorResult;
}
