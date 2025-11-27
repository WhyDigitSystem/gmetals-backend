package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.InvoiceDetailsVO;
import com.efit.ganapathi.entity.InvoiceVO;

@Repository
public interface InvoiceDetailsRepo extends JpaRepository<InvoiceDetailsVO, Long>{

	List<InvoiceDetailsVO> findByInvoiceVO(InvoiceVO invoiceVO);

}
