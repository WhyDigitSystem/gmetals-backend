package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.VendorDetailsVO;
import com.efit.ganapathi.entity.VendorVO;

@Repository
public interface VendorDetailsRepo extends JpaRepository<VendorDetailsVO, Long>{

	List<VendorDetailsVO> findByVendorVO(VendorVO vendorVO);

}
