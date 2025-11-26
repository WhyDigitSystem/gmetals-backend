package com.efit.ganapathi.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.base.basesetup.dto.OrderBookingDTO;
import com.base.basesetup.entity.OrderBookingVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface OrderBookingService {

	// OrderBookingCharges

	Map<String, Object> getAllOrderBookingByOrgId(Long orgId, String search, int page, int size);

	OrderBookingVO getOrderBookingById(Long id);

//	OrderBookingVO uploaAttachmentsInBloob(MultipartFile file, Long id) throws IOException;

	Map<String, Object> updateCreateOrderBooking(OrderBookingDTO orderBookingDTO, MultipartFile invoiceFile)
			throws ApplicationException, IOException;

}
