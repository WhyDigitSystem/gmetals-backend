package com.efit.ganapathi.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.Form1VO;

@Repository
public interface Form1Repo extends JpaRepository<Form1VO, Long> {

	@Query(value = "SELECT r.*\r\n" + "FROM form1 r\r\n" + "WHERE (:orgId IS NULL OR r.orgid = :orgId)\r\n"
			+ "  AND (\r\n" + "        :search IS NULL OR :search = '' OR (\r\n"
			+ "		   LOWER(r.production)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.origin)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.import)              LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.wobtained)          LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "        ))", countQuery = "SELECT count(*)\r\n" + "FROM form1 r\r\n"
					+ "WHERE (:orgId IS NULL OR r.orgid = :orgId)\r\n" + "  AND (\r\n"
					+ "        :search IS NULL OR :search = '' OR (\r\n"
					+ "		   LOWER(r.production)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.origin)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.import)              LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.wobtained)          LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "        ))", nativeQuery = true)
	Page<Form1VO> getAllForm1ByOrgId(@Param("orgId") Long orgId, @Param("search") String search, Pageable pageable);

	@Query(nativeQuery = true, value = "select * from form1 where form1id=?1")
	Form1VO getForm1ById(Long id);

}
