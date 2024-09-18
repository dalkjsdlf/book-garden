package io.ratel.bookgarden.common.http.response;

public record ErrorResponse(
        String code,
        String message
) {
}
