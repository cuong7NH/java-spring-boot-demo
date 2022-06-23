package com.example.eventuser.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class EventRequest extends PagingDataRequest {
    private Long id;
    private String username;

    @Override
    public String toString() {
        return "EventRequest{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
    //    private LocalDateTime startDate;
//private LocalDateTime endDate;
}
