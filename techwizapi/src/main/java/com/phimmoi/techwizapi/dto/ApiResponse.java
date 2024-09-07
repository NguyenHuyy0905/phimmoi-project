package com.phimmoi.techwizapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ApiResponse {

    public ResponseEntity<Response> ok() {
        return ResponseEntity.ok(Response.of(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), null));
    }

    public ResponseEntity<Response> ok(String msg) {
        return ResponseEntity.ok(Response.of(msg, HttpStatus.OK.value(), null));
    }

    public ResponseEntity<Response> ok(Object data) {
        return ResponseEntity.ok(Response.of(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), data));
    }

    public ResponseEntity<Response> ok(Boolean data) {
        return ResponseEntity.ok(Response.of(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), data));
    }

    public ResponseEntity<Response> ok(String msg, Object data) {
        return ResponseEntity.ok(Response.of(msg, HttpStatus.OK.value(), data));
    }

    public ResponseEntity<Response> badRequest() {
        return ResponseEntity.ok(Response.of(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value(), null));
    }

    public ResponseEntity<Response> badRequest(String msg) {
        return ResponseEntity.ok(Response.of(msg, HttpStatus.BAD_REQUEST.value(), null));
    }

    public ResponseEntity<Response> notFound() {
        return ResponseEntity.ok(Response.of(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), null));
    }

    public ResponseEntity<Response> notFound(String msg) {
        return ResponseEntity.ok(Response.of(msg, HttpStatus.NOT_FOUND.value(), null));
    }

    public ResponseEntity<Response> unauthorized() {
        return ResponseEntity.ok(Response.of(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED.value(), null));
    }

    public ResponseEntity<Response> unauthorized(String msg) {
        return ResponseEntity.ok(Response.of(msg, HttpStatus.UNAUTHORIZED.value(), null));
    }

    public ResponseEntity<Response> forbidden() {
        return ResponseEntity.ok(Response.of(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN.value(), null));
    }

    public ResponseEntity<Response> forbidden(String msg) {
        return ResponseEntity.ok(Response.of(msg, HttpStatus.FORBIDDEN.value(), null));
    }

    public ResponseEntity<Response> created() {
        return ResponseEntity.ok(Response.of(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(), null));
    }

    public ResponseEntity<Response> created(String msg) {
        return ResponseEntity.ok(Response.of(msg, HttpStatus.CREATED.value(), null));
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor(staticName = "of")
    public static class Response {
        protected String message;
        protected int code;
        protected Object data;
    }
}
