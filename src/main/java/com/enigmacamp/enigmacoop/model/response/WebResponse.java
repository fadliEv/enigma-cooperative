package com.enigmacamp.enigmacoop.model.response;

import com.enigmacamp.enigmacoop.model.response.PagingResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebResponse<T> {
    private String status;
    private String message; // berhasil create data nasabah, berhasil delte data nasabah. etc.
    private PagingResponse paging;
    private T data;
}