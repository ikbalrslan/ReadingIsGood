package com.getir.bookretail.model;

import lombok.*;

/**
 * @author İkbal Arslan
 */

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private  String country;
    private  String city;
    private  String postCode;
}
