package com.getir.bookretail.controller.dto.request;

import com.getir.bookretail.model.Address;
import com.getir.bookretail.model.Gender;
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
public class CustomerRestRequest {
    @NotEmpty(message = "This field is required")
    private String firstName;
    @NotEmpty(message = "This field is required")
    private String lastName;
    @NotEmpty(message = "This field is required")
    private String email;
    private Gender gender;
    private Address address;
}
