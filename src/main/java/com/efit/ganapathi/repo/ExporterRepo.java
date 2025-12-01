package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.ExporterVO;

@Repository
public interface ExporterRepo extends JpaRepository<ExporterVO, Long>{


	boolean existsByCompanyNameAndOrgId(String companyName, Long orgId);

	List<ExporterVO> findByOrgId(Long orgId);
	@Query(
		    value = 
		        "SELECT * FROM exporter e " +
		        "WHERE e.orgid = :orgId " +
		        "AND (:branchCode IS NULL OR :branchCode = '' OR e.branchcode = :branchCode) " +
		        "AND ( " +
		        "   :search IS NULL OR :search = '' OR " +
		        "   LOWER(e.companyname) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
		        "   LOWER(e.contactperson) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
		        "   LOWER(e.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
		        "   LOWER(e.phone) LIKE LOWER(CONCAT('%', :search, '%')) " +
		        ")",
		    countQuery =
		        "SELECT COUNT(*) FROM exporter e " +
		        "WHERE e.orgid = :orgId " +
		        "AND (:branchCode IS NULL OR :branchCode = '' OR e.branchcode = :branchCode) " +
		        "AND ( " +
		        "   :search IS NULL OR :search = '' OR " +
		        "   LOWER(e.companyname) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
		        "   LOWER(e.contactperson) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
		        "   LOWER(e.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
		        "   LOWER(e.phone) LIKE LOWER(CONCAT('%', :search, '%')) " +
		        ")",
		    nativeQuery = true
		)
		Page<ExporterVO> getExportersByFilters(
		        @Param("orgId") Long orgId,
		        @Param("branchCode") String branchCode,
		        @Param("search") String search,
		        Pageable pageable
		);

	

}
