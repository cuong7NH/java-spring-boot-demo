

package com.example.accessingdatamysql.dto.request;

        import lombok.Getter;
        import lombok.Setter;
        import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class GameRequest extends PagingDataRequest{
    private Long id;
}
