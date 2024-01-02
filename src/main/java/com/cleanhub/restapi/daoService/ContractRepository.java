package com.cleanhub.restapi.daoService;

import com.cleanhub.restapi.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContractRepository extends JpaRepository<Contract, UUID> {

    @Transactional
    @Query("SELECT c FROM Contract c WHERE c.landingPageRoute = :landingPageRoute")
    List<Contract> findByLandingPageRoute(String landingPageRoute);
}
