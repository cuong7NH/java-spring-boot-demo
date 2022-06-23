package com.example.eventuser.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
public class PagingResponse<T> {
    private int     total;
    private List<T> data;
}
