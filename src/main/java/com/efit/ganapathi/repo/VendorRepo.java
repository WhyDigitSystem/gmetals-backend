package com.efit.ganapathi.repo;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.VendorVO;

@Repository
public interface VendorRepo extends JpaRepository<VendorVO, Long>{

	@Query(value = "SELECT v.* FROM vendor v " +
	        "WHERE v.orgid = :orgId " +
	        "AND v.active = true " +
	        "AND (:branchCode IS NULL OR v.branchcode = :branchCode) " +
	        "AND (" +
	        "LOWER(v.vendorcode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.organization) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.gst) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.address) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.pocname) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.pocemail) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.pocnumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.primaryemail) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.primaryphonenumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.approvalstatus) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.tags) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.vendortype) LIKE LOWER(CONCAT('%', :search, '%'))" +
	        ")",
	        countQuery = "SELECT COUNT(*) FROM vendor v " +
	        "WHERE v.orgid = :orgId " +
	        "AND v.active = true " +
	        "AND (:branchCode IS NULL OR v.branchcode = :branchCode) " +
	        "AND (" +
	        "LOWER(v.vendorcode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.organization) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.gst) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.address) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.pocname) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.pocemail) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.pocnumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.primaryemail) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.primaryphonenumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.approvalstatus) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.tags) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	        "LOWER(v.vendortype) LIKE LOWER(CONCAT('%', :search, '%'))" +
	        ")",
	        nativeQuery = true)
	Page<Map<String, Object>> getVendorByOrgId(
	        @Param("orgId") Long orgId,
	        @Param("branchCode") String branchCode,
	        @Param("search") String search,
	        Pageable pageable
	);



}
