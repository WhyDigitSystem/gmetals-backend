package com.efit.ganapathi.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.PackingListVO;

@Repository
public interface PackingListRepo extends JpaRepository<PackingListVO, Long> {

	@Query(value = "SELECT r.*\r\n" + "FROM packinglist r\r\n" + "WHERE (:orgId IS NULL OR r.orgid = :orgId)\r\n"
			+ "  AND (\r\n" + "        :search IS NULL OR :search = '' OR (\r\n"
			+ "		   LOWER(r.exporter)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.importer)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.packinglistnumber)              LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.packingtype)          LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "        ))", countQuery = "SELECT count(*)\r\n" + "FROM packinglist r\r\n"
					+ "WHERE (:orgId IS NULL OR r.orgid = :orgId)\r\n" + "  AND (\r\n"
					+ "        :search IS NULL OR :search = '' OR (\r\n"
					+ "		   LOWER(r.exporter)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.importer)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.packinglistnumber)              LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.packingtype)          LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "        ))", nativeQuery = true)
	Page<PackingListVO> getAllPackingListByOrgId(@Param("orgId") Long orgId, @Param("search") String search,
			Pageable pageable);

	@Query(nativeQuery = true, value = "select * from packinglist where packinglistid=?1")
	PackingListVO getPackingListById(Long id);

	boolean existsByPackingListNumberAndOrgId(String packingListNumber, Long orgId);

}
