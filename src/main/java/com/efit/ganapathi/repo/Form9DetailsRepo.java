package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.Form9DetailsVO;
import com.efit.ganapathi.entity.Form9VO;

@Repository
public interface Form9DetailsRepo extends JpaRepository<Form9DetailsVO, Long> {

	List<Form9DetailsVO> findByForm9VO(Form9VO form9vo);

}
