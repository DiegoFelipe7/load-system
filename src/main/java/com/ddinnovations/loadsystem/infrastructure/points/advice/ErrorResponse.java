package com.ddinnovations.loadsystem.infrastructure.points.advice;

public record ErrorResponse (
    String message,
    String backendMessage,
    String method,
    String url
){

}
