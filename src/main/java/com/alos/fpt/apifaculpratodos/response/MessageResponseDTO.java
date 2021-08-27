package com.alos.fpt.apifaculpratodos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Component
public class MessageResponseDTO {
    private String message;

    @JsonInclude(Include.NON_NULL)
    private String id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
