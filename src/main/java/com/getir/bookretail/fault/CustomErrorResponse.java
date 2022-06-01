package com.getir.bookretail.fault;

import lombok.*;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

    private String error;
    private Integer status;
}
