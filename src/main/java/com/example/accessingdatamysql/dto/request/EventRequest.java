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
    private Long gameId;
//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startDate;
//    @JsonFormat(pattern="yyyy-MM-dd")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
private LocalDateTime endDate;
}
