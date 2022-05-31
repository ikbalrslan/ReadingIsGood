package com.getir.bookretail.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author İkbal Arslan
 */

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Statistics")
public class Statistic {
    @Id
    private String id;
    private String customerId;
    private int totalOrderCount;
    private int totalBookCount;
    private double totalPurchasedAmount;
    private String month;
}
