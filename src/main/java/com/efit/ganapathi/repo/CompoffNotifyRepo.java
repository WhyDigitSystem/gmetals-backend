package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.CompensatoryOffVO;
import com.efit.ganapathi.entity.CompoffNotifyVO;

@Repository
public interface CompoffNotifyRepo extends JpaRepository<CompoffNotifyVO, Long>{

	List<CompoffNotifyVO> findByCompensatoryOffVO(CompensatoryOffVO vo);

}
