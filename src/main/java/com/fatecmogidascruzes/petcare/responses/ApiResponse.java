package com.fatecmogidascruzes.petcare.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public ApiResponse() {}

    public ApiResponse(int code, String message, T data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
