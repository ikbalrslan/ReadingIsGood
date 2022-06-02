package com.getir.bookretail.controller.dto.response;

import com.getir.bookretail.model.Order;
import lombok.*;

import java.util.List;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrdersResponse {

    private List<Order> orders;
}
