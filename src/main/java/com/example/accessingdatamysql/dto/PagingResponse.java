package com.example.accessingdatamysql.dto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class PagingResponse<T> {
    private int     total;
    private List<T> data;
}
