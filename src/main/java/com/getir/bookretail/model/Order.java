package com.getir.bookretail.model;

import com.getir.bookretail.enums.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author İkbal Arslan
 */

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Orders")
public class Order {
    @Id
    private String id;
    private String customerId;
    private String bookId;
    private int count;
    private OrderStatus status;
    private LocalDateTime orderTime;
}
