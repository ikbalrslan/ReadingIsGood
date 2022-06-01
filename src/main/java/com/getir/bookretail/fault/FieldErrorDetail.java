package com.getir.bookretail.fault;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorDetail {

    private String field;
    private String description;
}
