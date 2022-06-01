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
public class Statistic {
    private int totalOrderCount;
    private int totalBookCount;
    private double totalPurchasedAmount;
    private String month;
}
