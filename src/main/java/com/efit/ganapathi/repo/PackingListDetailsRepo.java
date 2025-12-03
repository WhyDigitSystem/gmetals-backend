package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.PackingListDetailsVO;
import com.efit.ganapathi.entity.PackingListVO;

@Repository
public interface PackingListDetailsRepo extends JpaRepository<PackingListDetailsVO, Long> {

	List<PackingListDetailsVO> findByPackingListVO(PackingListVO packingListVO);

}
