package org.example.alexandria.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
    String errorMessage,
    int statusCode,
    LocalDateTime timeStamp
){}
