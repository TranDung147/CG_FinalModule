package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.DTO.order.OrderDetailRevenueDTO;
import com.codegym.finalModule.DTO.product.ProductStatisticalDTO;
import com.codegym.finalModule.DTO.statistical.DailyRevenueDTO;
import com.codegym.finalModule.DTO.statistical.RevenueDetailDTO;
import com.codegym.finalModule.DTO.statistical.RevenueSummaryDTO;
import com.codegym.finalModule.DTO.statistical.TopSellingProductDTO;
import com.codegym.finalModule.mapper.statistical.StatisticalMapper;
import com.codegym.finalModule.model.Order;
import com.codegym.finalModule.model.OrderDetail;
import com.codegym.finalModule.repository.IRevenueRepository;
import com.codegym.finalModule.service.interfaces.IStatisticalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class StatisticalService implements IStatisticalService {

    private final IRevenueRepository irevenueRepository;
    private final StatisticalMapper statisticalMapper;

    public StatisticalService(IRevenueRepository repository, StatisticalMapper mapper) {
        this.irevenueRepository = repository;
        this.statisticalMapper = mapper;
    }

    @Override
    public RevenueSummaryDTO getRevenueSummary(List<Order> orderList) {
        return this.statisticalMapper.convertToRevenueSummaryDTO(orderList);
    }
    @Override
    public List<DailyRevenueDTO> getDailyRevenue(List<Order> orderList , String type) {
        return this.statisticalMapper.convertToDailyRevenueDTO(orderList , type);
    }

    @Override
    public List<TopSellingProductDTO> getTopSellingProduct(List<Order> orderList) {
        List<Integer> integerList = orderList.stream().map(Order::getOrderID).toList();
        return this.irevenueRepository.findTopSellingProducts(integerList);
    }

    @Override
    public List<Order> getListOrdersByDate(int day, int month, int year) {
        LocalDateTime startDate;
        LocalDateTime endDate;

        if (day > 0) {
            LocalDate date = LocalDate.of(year, month, day);
            startDate = date.atStartOfDay();
            endDate = date.atTime(LocalTime.MAX);
        } else if (month > 0) {
            LocalDate date = LocalDate.of(year, month, 1);
            startDate = date.atStartOfDay();
            endDate = date.plusMonths(1).atStartOfDay();
        } else {
            LocalDate date = LocalDate.of(year, 1, 1);
            startDate = date.atStartOfDay();
            endDate = date.plusYears(1).minusDays(1).atTime(LocalTime.MAX);
        }
        return this.irevenueRepository.findAllByOrderByCreateAt(startDate, endDate);
    }

    @Override
    public List<Order> getAllOrders() {
        return this.irevenueRepository.findAllOrders();
    }
    @Override
    public Page<OrderDetailRevenueDTO> getOrderDetailRevenue(List<Order> orderList, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        List<OrderDetailRevenueDTO> orderDetailRevenueDTOList = orderList.stream()
                .map(this.statisticalMapper::orderDetailRevenueDTO)
                .toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), orderDetailRevenueDTOList.size());
        List<OrderDetailRevenueDTO> pagedList = orderDetailRevenueDTOList.subList(start, end);
        return new PageImpl<>(pagedList, pageable, orderDetailRevenueDTOList.size());
    }


    @Override
    public Page<ProductStatisticalDTO> getProductStatistical(List<Order> orderList , int page , int size) {
        List<Integer> integerList = orderList.stream().map(Order::getOrderID).toList();
        Pageable pageable = PageRequest.of(page - 1, size) ;
        List<ProductStatisticalDTO> productStatisticalDTOS = this.irevenueRepository.findProductsSales(integerList) ;
        int start =(int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), productStatisticalDTOS.size());
        List<ProductStatisticalDTO> pagedList = productStatisticalDTOS.subList(start, end);
        return new PageImpl<>(pagedList , pageable, productStatisticalDTOS.size()) ;
    }

    @Override
    public Page<RevenueDetailDTO> getRevenueDetail(List<Order> orderList , int page , int size) {
        Pageable pageable = PageRequest.of(page - 1, size) ;
        List<RevenueDetailDTO> revenueDetailDTOS = this.statisticalMapper.convertToRevenueDetailDTO(orderList) ;
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), revenueDetailDTOS.size());
        List<RevenueDetailDTO> pagedList = revenueDetailDTOS.subList(start, end);
        return new PageImpl<>(pagedList , pageable, revenueDetailDTOS.size()) ;
    }

    @Override
    public List<RevenueDetailDTO> getAllRevenueDetail(List<Order> orderList) {
        return this.statisticalMapper.convertToRevenueDetailDTO(orderList);
    }


    @Override
    public Integer getTotalProductsSales(List<Order> orderList) {
        return orderList.stream()
                .flatMap(order -> order.getOrderDetails().stream())
                .mapToInt(OrderDetail::getQuantity)
                .sum();
    }

    @Override
    public Integer getStockProducts(List<Order> orderList) {
        return orderList.size();
    }

    @Override
    public HashMap<String, Double> getTotalDetailRevenue(List<RevenueDetailDTO> revenueDetailDTOS) {

        HashMap<String, Double> map = new HashMap<>();
        map.put("totalSellingPrice" , revenueDetailDTOS.stream().mapToDouble(RevenueDetailDTO::getTotalSellingPrice).sum()) ;
        map.put("totalImportCost" , revenueDetailDTOS.stream().mapToDouble(RevenueDetailDTO::getTotalImportCost).sum()) ;
        map.put("profit" , revenueDetailDTOS.stream().mapToDouble(RevenueDetailDTO::getProfit).sum()) ;
        map.put("profitRate" , revenueDetailDTOS.stream().mapToDouble(RevenueDetailDTO::getProfitRate).sum()) ;
        return map;
    }


}
