package com.vitoriaferreira.curso.resource.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp; // Timestamp do erro
    private Integer status; // Status HTTP do erro
    private String error; // Mensagem de erro
    private String message; // Detalhes da mensagem de erro
    private String path; // Caminho da requisição que causou o erro

    public StandardError() {

    }

    public StandardError(Instant timestamp, Integer status, String error, String message, String path) {

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

}
