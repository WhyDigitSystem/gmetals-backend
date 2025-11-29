package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.OrderBookingChargesVO;
import com.efit.ganapathi.entity.OrderBookingVO;

@Repository
public interface OrderBookingChargesRepo extends JpaRepository<OrderBookingChargesVO, Long> {

	List<OrderBookingChargesVO> findByOrderBookingVO(OrderBookingVO orderBookingVO);

}
