package com.efit.ganapathi.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.base.basesetup.dto.OrderBookingChargesDTO;
import com.base.basesetup.dto.OrderBookingDTO;
import com.base.basesetup.entity.OrderBookingChargesVO;
import com.base.basesetup.entity.OrderBookingVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.OrderBookingChargesRepo;
import com.base.basesetup.repo.OrderBookingRepo;

@Service
public class OrderBookingServiceImpl implements OrderBookingService {

	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	OrderBookingRepo orderBookingRepo;

	@Autowired
	OrderBookingChargesRepo orderBookingChargesRepo;

	@Autowired
	PaginationService paginationService;

	// OrderBooking

	@Override
	public Map<String, Object> getAllOrderBookingByOrgId(Long orgId, String search, int page, int size) {

		if (search != null) {
			search = search.trim();
			if (search.isEmpty()) {
				search = null;
			}
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("sname").ascending());
		Page<OrderBookingVO> customerPage = orderBookingRepo.getAllOrderBookingByOrgId(orgId, search, pageable);

		return paginationService.buildResponse(customerPage);

	}

	@Override
	public OrderBookingVO getOrderBookingById(Long id) {

		return orderBookingRepo.getOrderBookingById(id);
	}

	@Override
	@Transactional
	public Map<String, Object> updateCreateOrderBooking(OrderBookingDTO orderBookingDTO, MultipartFile invoiceFile) throws ApplicationException, IOException {

		OrderBookingVO orderBookingVO = new OrderBookingVO();

		String message;

		if (ObjectUtils.isNotEmpty(orderBookingDTO.getId())) {

			orderBookingVO = orderBookingRepo.findById(orderBookingDTO.getId())
					.orElseThrow(() -> new ApplicationException("OrderBooking Not Found!"));
			orderBookingVO.setUpdatedBy(orderBookingDTO.getCreatedBy());

			message = "OrderBooking Updated Successfully";
		} else {

//			// GETDOCID API
//
//			String docId = quotationRepo.getQuotationDocId(OrderBookingDTO.getOrgId(), OrderBookingDTO.getFinYear(),
//					OrderBookingDTO.getBranchCode(), screenCode);
//			OrderBookingVO.setDocId(docId);
//
//			// GETDOCID LASTNO +1
//			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
//					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(OrderBookingDTO.getOrgId(), OrderBookingDTO.getFinYear(),
//							OrderBookingDTO.getBranchCode(), screenCode);
//			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
//			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);
			orderBookingVO.setUpdatedBy(orderBookingDTO.getCreatedBy());
			orderBookingVO.setCreatedBy(orderBookingDTO.getCreatedBy());
			message = "OrderBooking Created Successfully";
		}

		if (invoiceFile != null && !invoiceFile.isEmpty()) {
			orderBookingVO.setAttachments(invoiceFile.getBytes());
		}

		createUpdateOrderBookingVOByOrderBookingDTO(orderBookingDTO, orderBookingVO);
		orderBookingRepo.save(orderBookingVO);
		Map<String, Object> response = new HashMap<>();
		response.put("orderBookingVO", orderBookingVO);
		response.put("message", message);
		return response;
	}

	private void createUpdateOrderBookingVOByOrderBookingDTO(@Valid OrderBookingDTO orderBookingDTO,
			OrderBookingVO orderBookingVO) throws ApplicationException {
		orderBookingVO.setShipperName(orderBookingDTO.getShipperName());
		orderBookingVO.setBranchCode(orderBookingDTO.getBranchCode());
		orderBookingVO.setShipperContactPerson(orderBookingDTO.getShipperContactPerson());
		orderBookingVO.setShipperPhonEmail(orderBookingDTO.getShipperPhonEmail());
		orderBookingVO.setCreatedBy(orderBookingDTO.getCreatedBy());
		orderBookingVO.setShipperAddress(orderBookingDTO.getShipperAddress());
		orderBookingVO.setConsigneeName(orderBookingDTO.getConsigneeName());
		orderBookingVO.setConsigneeContactPerson(orderBookingDTO.getConsigneeContactPerson());
		orderBookingVO.setConsigneePhoneEmail(orderBookingDTO.getConsigneePhoneEmail());
		orderBookingVO.setOrgId(orderBookingDTO.getOrgId());
		orderBookingVO.setConsigneeAddress(orderBookingDTO.getConsigneeAddress());
		orderBookingVO.setCargoType(orderBookingDTO.getCargoType());
		orderBookingVO.setPackagingType(orderBookingDTO.getPackagingType());
		orderBookingVO.setDescriptions(orderBookingDTO.getDescriptions());
		orderBookingVO.setQuantity(orderBookingDTO.getQuantity());
		orderBookingVO.setWeight(orderBookingDTO.getWeight());
		orderBookingVO.setLength(orderBookingDTO.getLength());
		orderBookingVO.setWidth(orderBookingDTO.getWidth());

		orderBookingVO.setHeight(orderBookingDTO.getHeight());
		orderBookingVO.setPickupDate(orderBookingDTO.getPickupDate());
		orderBookingVO.setPickupTimeStart(orderBookingDTO.getPickupTimeStart());
		orderBookingVO.setPickupTimeEnd(orderBookingDTO.getPickupTimeEnd());
		orderBookingVO.setPickuplocation(orderBookingDTO.getPickuplocation());
		orderBookingVO.setDeliveryDate(orderBookingDTO.getDeliveryDate());
		orderBookingVO.setDeliveryTimeStart(orderBookingDTO.getDeliveryTimeStart());
		orderBookingVO.setDeliveryTimeEnd(orderBookingDTO.getDeliveryTimeEnd());
		orderBookingVO.setDeliverylocation(orderBookingDTO.getDeliverylocation());
		orderBookingVO.setTransportMode(orderBookingDTO.getTransportMode());
		orderBookingVO.setServiceLevel(orderBookingDTO.getServiceLevel());

		orderBookingVO.setVehicleType(orderBookingDTO.getVehicleType());
		orderBookingVO.setFreightCharges(orderBookingDTO.getFreightCharges());
		orderBookingVO.setPaymentMethod(orderBookingDTO.getPaymentMethod());
		orderBookingVO.setEstTransitTime(orderBookingDTO.getEstTransitTime());
		orderBookingVO.setNotes(orderBookingDTO.getNotes());

		if (orderBookingDTO.getId() != null) {
			List<OrderBookingChargesVO> orderBookingChargesVO1 = orderBookingChargesRepo
					.findByOrderBookingVO(orderBookingVO);
			orderBookingChargesRepo.deleteAll(orderBookingChargesVO1);

		}

		BigDecimal totalamount = BigDecimal.ZERO;
		List<OrderBookingChargesVO> OrderBookingChargesVOs = new ArrayList<>();
		for (OrderBookingChargesDTO orderBookingChargesDTO : orderBookingDTO.getOrderBookingChargesDTO()) {
			OrderBookingChargesVO orderBookingChargesVO = new OrderBookingChargesVO();

			orderBookingChargesVO.setAdditionalCharges(orderBookingChargesDTO.getAdditionalCharges());
			orderBookingChargesVO.setAmount(orderBookingChargesDTO.getAmount());
			totalamount = totalamount.add(orderBookingChargesVO.getAmount());
			orderBookingChargesVO.setOrderBookingVO(orderBookingVO);
			OrderBookingChargesVOs.add(orderBookingChargesVO);
		}

		orderBookingVO.setFreightCharges(orderBookingDTO.getFreightCharges());
		orderBookingVO.setTotalCost(orderBookingVO.getFreightCharges().add(totalamount));
		BigDecimal totalVolume = orderBookingVO.getLength().multiply(orderBookingVO.getWidth())
				.multiply(orderBookingVO.getHeight().divide(BigDecimal.valueOf(1000000)));
		orderBookingVO.setTotalVolume(totalVolume);
		orderBookingVO.setOrderBookingChargesVO(OrderBookingChargesVOs);

	}

//	@Override
//	public OrderBookingVO uploaAttachmentsInBloob(MultipartFile file, Long id) throws IOException {
//		int targetWidth = 800;
//		int targetHeight = 600;
//
//		byte[] compressedImage = compressImage(file.getBytes(), targetWidth, targetHeight);
//
//		OrderBookingVO orderBookingVO = orderBookingRepo.findById(id)
//				.orElseThrow(() -> new IOException("OrderBooking not found"));
//
//		orderBookingVO.setAttachments(compressedImage);
//
//		return orderBookingRepo.save(orderBookingVO);
//	}
//
//	private byte[] compressImage(byte[] imageData, int targetWidth, int targetHeight) throws IOException {
//
//		BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageData));
//
//		Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
//		BufferedImage resizedBufferedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
//		Graphics2D g = resizedBufferedImage.createGraphics();
//		g.drawImage(resizedImage, 0, 0, null);
//		g.dispose();
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ImageIO.write(resizedBufferedImage, "jpg", baos);
//		return baos.toByteArray();
//	}
//

}
