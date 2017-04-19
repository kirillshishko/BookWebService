package com.epam.methods;

public enum StatusCode {


    STATUS_CODE_200_OK(" 200 OK \r\n"),
    STATUS_CODE_201_CREATED(" 201 Created \r\n"),
    STATUS_CODE_400_BAD_REQUEST("400 Bad Request \r\n");

    private final String code;

    StatusCode(String code){
        this.code = code;
    }
}
