package com.efit.ganapathi.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.BranchVO;
import com.efit.ganapathi.entity.CountryVO;

@Repository
public interface CountryRepo extends JpaRepository<CountryVO, Long> {

//	@Query("select a.id,a.countryname from CountryVO a where a.orgId=?1")
//	Set<Object[]> findCountryAndCountryid(Long orgId);

	@Query(value = "SELECT * FROM country WHERE orgid = ?1", nativeQuery = true)
	List<CountryVO> findAll(Long orgId);

	boolean existsByCountryNameAndCountryCodeAndOrgId(String countryName, String countryCode, Long orgId);

	boolean existsByCountryNameAndOrgId(String countryName, Long orgId);

	boolean existsByCountryCodeAndOrgId(String countryCode, Long orgId);

//	Optional<BranchVO> findById(String country);

}
