package com.example.accessingdatamysql.dto.request;

import lombok.Data;

@Data
public class PagingDataRequest {
    protected Integer page = 0;
    protected Integer size = 200;

}