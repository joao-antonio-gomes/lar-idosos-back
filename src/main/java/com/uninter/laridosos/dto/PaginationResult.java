package com.uninter.laridosos.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResult<T> {
    private Long count;
    private Integer offset;
    private Integer limit;
    private List<T> result;
}
