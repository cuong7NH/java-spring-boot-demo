package com.example.accessingdatamysql.dto.request;

        import com.fasterxml.jackson.annotation.JsonFormat;
        import lombok.Getter;
        import lombok.Setter;
        import lombok.experimental.Accessors;
        import org.springframework.format.annotation.DateTimeFormat;

        import java.time.LocalDateTime;
        import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class EventRequest extends PagingDataRequest{
    private Long id;
    private String username;
//    private LocalDateTime startDate;
//private LocalDateTime endDate;
}
