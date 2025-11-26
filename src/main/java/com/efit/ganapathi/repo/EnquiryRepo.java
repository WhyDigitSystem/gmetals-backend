package com.efit.ganapathi.repo;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efit.ganapathi.entity.EnquiryVO;


@Repository
public interface EnquiryRepo extends JpaRepository<EnquiryVO, Long> {
	@Query(value = "SELECT r.*\r\n" + "FROM enquiry r\r\n" + "WHERE (:orgId IS NULL OR r.orgid = :orgId)\r\n"
			+ "  AND (\r\n" + "        :search IS NULL OR :search = '' OR (\r\n"
			+ "		   LOWER(r.cname)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.caddress)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.eid)              LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "          OR LOWER(r.cstatus)          LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
			+ "        ))", countQuery = "SELECT count(*)\r\n" + "FROM enquiry r\r\n"
					+ "WHERE (:orgId IS NULL OR r.orgid = :orgId)\r\n" + "  AND (\r\n"
					+ "        :search IS NULL OR :search = '' OR (\r\n"
					+ "		   LOWER(r.cname)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.caddress)       LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.eid)              LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "          OR LOWER(r.cstatus)          LIKE LOWER(CONCAT('%', :search, '%'))\r\n"
					+ "        ))", nativeQuery = true)
	Page<EnquiryVO> getAllEnquiryByOrgId(@Param("orgId") Long orgId, @Param("search") String search, Pageable pageable);

	@Query(nativeQuery = true, value = "select * from enquiry where enquiryid=?1")
	EnquiryVO getEnquiryById(Long id);

	@Query("SELECT COALESCE(MAX(e.enquiryId), 0) FROM EnquiryVO e")
	int getMaxEnquiryId();

	boolean existsByCustomerIdAndOrgId(String customerId, Long orgId);
	
	
	@Query(nativeQuery = true, value = "select partyname,partycode from partymaster where orgid=?1 and partytype='CUSTOMER' and active=1 and cancel=0")
	Set<Object[]> getCustomerNameAndCode(Long orgId);
	
	@Query(nativeQuery = true, value = "select user_name from users where org_id=?1 and active=1 and cancel=0")
	Set<Object[]> getAssignedAgent(Long orgId);
	
	@Query(nativeQuery = true, value = "select sum(totalenquiries) totalenquiries,sum(opens) opens,sum(inprogress) inprogress,sum(closed) closed from (\r\n"
			+ "select count(*) totalenquiries,0 opens,0 inprogress,0 closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and 'Customer Details'=?3\r\n"
			+ " union \r\n"
			+ " select 0 totalenquiries,count(*) opens,0 inprogress,0 closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and cstatus='Open' and 'Customer Details'=?3\r\n"
			+ " union  select 0 totalenquiries,0 opens,count(*)inprogress,0 closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and cstatus='In Progress' and 'Customer Details'=?3\r\n"
			+ " union select 0 totalenquiries,0 opens,0 inprogress,count(*) closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and cstatus='Closed' and 'Customer Details'=?3\r\n"
			+ "  union all\r\n"
			+ "  select count(*) totalenquiries,0 opens,0 inprogress,0 closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and 'Enquiry Information'=?3\r\n"
			+ " union \r\n"
			+ " select 0 totalenquiries,count(*) opens,0 inprogress,0 closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and estatus='Open' and 'Enquiry Information'=?3\r\n"
			+ " union  select 0 totalenquiries,0 opens,count(*)inprogress,0 closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and estatus='In Progress' and 'Enquiry Information'=?3\r\n"
			+ " union select 0 totalenquiries,0 opens,0 inprogress,count(*) closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and estatus='Closed' and 'Enquiry Information'=?3\r\n"
			+ " union all\r\n"
			+ "select count(*) totalenquiries,0 opens,0 inprogress,0 closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and 'ALL'=?3\r\n"
			+ " union \r\n"
			+ " select 0 totalenquiries,count(*) opens,0 inprogress,0 closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and (cstatus='Open' or estatus='Open') and 'ALL'=?3\r\n"
			+ " union  select 0 totalenquiries,0 opens,count(*)inprogress,0 closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and (cstatus='In Progress' or estatus='In Progress') and 'ALL'=?3\r\n"
			+ " union select 0 totalenquiries,0 opens,0 inprogress,count(*) closed from enquiry\r\n"
			+ " where orgid=?1 and branchcode=?2 and  (cstatus='Closed' or estatus='Closed') and 'ALL'=?3\r\n"
			+ " ) a where ('Customer Details'=?3 or  'Enquciry Information'=?3 or  'ALL'=?3)")
	Set<Object[]> getEnquiryCount(Long orgId,String branchCode,String Type);

}
