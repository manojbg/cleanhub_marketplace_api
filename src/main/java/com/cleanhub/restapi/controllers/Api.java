package com.cleanhub.restapi.controllers;

import com.cleanhub.restapi.dto.CompanyHistory;
import com.cleanhub.restapi.entity.Company;
import com.cleanhub.restapi.service.CleanHubService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/cleanhubmarketplace")
public class Api {

    @Autowired
    private CleanHubService cleanHubService;

    @GetMapping("/companies")
    @Operation(summary = "Get all customers/Companies", description = "Get all listed customers/companies with cleanhub")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(cleanHubService.getAllCompanies());
    }

    @GetMapping("/companyOrders")
    @Operation(summary = "Get orders of all customers/Companies", description = "Get all orders placed by customers/companies with cleanhub")
    public ResponseEntity<List<Company>> getAllCompanyOrders() {
        return ResponseEntity.ok(cleanHubService.getAllCompanies());
    }

    @GetMapping("/topCompanies")
    @Operation(summary = "Get top companies by quantity", description = "Get top companies based on the quantity within a date range (yyyy-mm-dd)")
    public ResponseEntity<List<Company>> getTopCompaniesByQuantity(
            @RequestParam int topX,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return ResponseEntity.ok(cleanHubService.getTopCompaniesByQuantity(startDate,endDate,topX));
    }

    @GetMapping("/companyhistory")
    @Operation(summary = "Get company history by landingpageroute", description = "Get historical data of a company by landingpageroute")
    public ResponseEntity<List<CompanyHistory>> getTopCompaniesHistoryByCompanyName(@RequestParam String landingPageRoute) {
        return ResponseEntity.ok(cleanHubService.getCompanyHistory(landingPageRoute));
    }
}
