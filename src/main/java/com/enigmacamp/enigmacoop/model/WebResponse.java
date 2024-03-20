package com.enigmacamp.enigmacoop.model;

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