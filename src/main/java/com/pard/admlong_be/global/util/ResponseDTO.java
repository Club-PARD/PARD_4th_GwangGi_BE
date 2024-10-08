package com.pard.admlong_be.global.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDTO {
    private boolean success;
    private String message;
    private Object response_object;
    private String error_code;


    public ResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseDTO(boolean success, String message, Object response_object) {
        this.success = success;
        this.message = message;
        this.response_object = response_object;
    }
}
