package com.cleanhub.restapi.daoService;

import com.cleanhub.restapi.entity.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    @Transactional
    @Query("SELECT c FROM Company c WHERE c.contractStartDate BETWEEN :startDate AND :endDate ORDER BY c.quantity DESC")
    List<Company> findTopRecordsByQuantityAndDateRange(Date startDate, Date endDate, Pageable topX);

    @Transactional
    @Query("SELECT c FROM Company c WHERE c.landingPageRoute = :landingPageRoute")
    Company findByLandingPageRoute(String landingPageRoute);
}
