package org.data.nowgnas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private int status;
    private String returnCode;
    private String message;

    public ApiResponse(int status, String returnCode, String message) {
        this.status = status;
        this.returnCode = returnCode;
        this.message = message;
    }
}
