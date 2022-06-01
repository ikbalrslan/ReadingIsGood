package com.getir.bookretail.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRestRequest {
    @NotEmpty(message = "This field is required")
    private String bookId;
    @NotNull(message = "This field is required")
    private Integer stock;
}
