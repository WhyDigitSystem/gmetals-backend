package com.efit.ganapathi.repo;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.PartyVO;

@Repository
public interface PartyRepo extends JpaRepository<PartyVO, Long> {

    boolean existsByPartyCodeAndOrgId(String partyCode, Long orgId);

    @Query("SELECT p FROM PartyVO p WHERE "
            + "(:orgId IS NULL OR p.orgId = :orgId) AND "
            + "(:branchCode IS NULL OR p.branchCode = :branchCode) AND "
            + "(:search IS NULL OR LOWER(p.partyName) LIKE LOWER(CONCAT('%', :search, '%')) "
            + "OR LOWER(p.partyCode) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<PartyVO> getPartyByFilters(
            @Param("orgId") Long orgId,
            @Param("branchCode") String branchCode,
            @Param("search") String search,
            Pageable pageable
    );

}
