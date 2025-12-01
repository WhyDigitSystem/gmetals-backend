package com.efit.ganapathi.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.Form6VO;

@Repository
public interface Form6Repo extends JpaRepository<Form6VO, Long> {

	@Query(value = "SELECT r.*\r\n" + "FROM form6 r\r\n" + "WHERE (:orgId IS NULL OR r.orgid = :orgId)\r\n"
			+ "  AND (\r\n" + "        :search IS NULL OR :search = '' OR (\r\n"
			+ "		   LOWER(r.exporter)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.contactperson)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.importer)              LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.wgenerator)          LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "        ))", countQuery = "SELECT count(*)\r\n" + "FROM form6 r\r\n"
					+ "WHERE (:orgId IS NULL OR r.orgid = :orgId)\r\n" + "  AND (\r\n"
					+ "        :search IS NULL OR :search = '' OR (\r\n"
					+ "		   LOWER(r.exporter)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.contactperson)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.importer)              LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.wgenerator)          LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "        ))", nativeQuery = true)
	Page<Form6VO> getAllForm6ByOrgId(@Param("orgId") Long orgId, @Param("search") String search, Pageable pageable);

	@Query(nativeQuery = true, value = "select * from form6 where form6id=?1")
	Form6VO getForm6ById(Long id);

}
