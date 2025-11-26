package com.efit.ganapathi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efit.ganapathi.entity.ResponsibilityVO;
import com.efit.ganapathi.entity.ScreensVO;

public interface ScreensRepo extends JpaRepository<ScreensVO, Long> {

	List<ScreensVO> findByResponsibilityVO(ResponsibilityVO responsibilityVO);

}
