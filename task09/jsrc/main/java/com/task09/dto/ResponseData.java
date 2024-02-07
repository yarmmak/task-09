package com.task09.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ResponseData
{
    private String statusCode;
    private String message;
}
