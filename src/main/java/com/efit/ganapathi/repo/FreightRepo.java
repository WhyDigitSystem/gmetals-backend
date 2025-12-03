package com.efit.ganapathi.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.FreightVO;

@Repository
public interface FreightRepo extends JpaRepository<FreightVO, Long> {
	
	@Query(value = "SELECT r.*\r\n" + "FROM freight r\r\n" + "WHERE (:orgId IS NULL OR r.orgid = :orgId)\r\n"
			+ "  AND (\r\n" + "        :search IS NULL OR :search = '' OR (\r\n"
			+ "		   LOWER(r.freightname)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.freightcode)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.fromlocation)              LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.tolocation)          LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "        ))", countQuery = "SELECT count(*)\r\n" + "FROM freight r\r\n"
					+ "WHERE (:orgId IS NULL OR r.orgid = :orgId)\r\n" + "  AND (\r\n"
					+ "        :search IS NULL OR :search = '' OR (\r\n"
					+ "		   LOWER(r.freightname)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.freightcode)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.fromlocation)              LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.tolocation)          LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "        ))", nativeQuery = true)
	Page<FreightVO> getAllFreightByOrgId(@Param("orgId") Long orgId, @Param("search") String search, Pageable pageable);

	@Query(nativeQuery = true, value = "select * from freight where freightid=?1")
	FreightVO getFreightById(Long id);

	boolean existsByFreightNameAndOrgId(String freightName, Long orgId);

	boolean existsByFreightCodeAndOrgId(String freightCode, Long orgId);

	
}
