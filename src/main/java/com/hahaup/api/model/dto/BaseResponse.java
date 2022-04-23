package com.hahaup.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class BaseResponse<T> {

    private boolean success = true;
    private Integer code = 0;
    private String message;
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(Exception e) {
        this.message = e.getMessage();
        this.success = false;
        this.code = 1;
    }
}
