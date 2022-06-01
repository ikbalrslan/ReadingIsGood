package com.getir.bookretail.fault;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidationErrorRestResponse {

    private final List<FieldErrorDetail> fields = new ArrayList<>();

    public void addFieldError(String field, String description) {
        fields.add(new FieldErrorDetail(field, description));
    }
}
