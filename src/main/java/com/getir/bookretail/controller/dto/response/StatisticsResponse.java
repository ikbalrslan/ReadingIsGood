package com.getir.bookretail.controller.dto.response;

import com.getir.bookretail.model.Order;
import com.getir.bookretail.model.Statistic;
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
public class StatisticsResponse {

    private List<Statistic> statistics;
}
