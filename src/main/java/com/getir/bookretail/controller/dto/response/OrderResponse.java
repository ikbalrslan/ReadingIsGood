package com.getir.bookretail.controller.dto.response;

import com.getir.bookretail.enums.OrderStatus;
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
public class OrderResponse {

    private String customerId;
    private String bookId;
    private int count;
    private OrderStatus status;
    private String orderTime;
}
