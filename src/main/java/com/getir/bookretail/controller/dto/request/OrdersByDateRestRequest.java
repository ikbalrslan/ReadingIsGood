package com.getir.bookretail.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdersByDateRestRequest {
    @NotEmpty(message = "This field is required")
    private String startDate;
    @NotEmpty(message = "This field is required")
    private String endDate;
}
