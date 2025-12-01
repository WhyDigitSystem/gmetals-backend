package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.CertificateDetailsVO;
import com.efit.ganapathi.entity.CertificateVO;

@Repository
public interface CertificateDetailsRepo extends JpaRepository<CertificateDetailsVO, Long> {

	List<CertificateDetailsVO> findByCertificateVO(CertificateVO certificateVO);

}
