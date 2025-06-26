package com.fatecmogidascruzes.petcare.responses;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
    private int code;
    private String error;
    private String message;
    private Map<String, List<String>> errors;

    public ApiError() {}

    public ApiError(int code, String error, String message)
    {
        this.code = code;
        this.error = error;
        this.message = message;
    }
}
