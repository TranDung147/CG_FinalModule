package com.codegym.finalModule.DTO.statistical;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OverViewRevenueDTO {

    private RevenueSummaryDTO revenueSummary;
    private List<DailyRevenueDTO> dailyRevenue;
    private List<TopSellingProductDTO> topSellingProducts;
}
