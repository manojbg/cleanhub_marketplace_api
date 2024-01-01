package com.cleanhub.restapi.controllers;

import com.cleanhub.restapi.entity.Company;
import com.cleanhub.restapi.service.CleanHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cleanhubmarketplace")
public class Api {

    @Autowired
    private CleanHubService cleanHubService;

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(cleanHubService.getAllCompanies());
    }

    @GetMapping("/companyOrders")
    public ResponseEntity<List<Company>> getAllCompanyOrders() {
        return ResponseEntity.ok(cleanHubService.getAllCompanies());
    }

    @GetMapping("/topCompanies")
    public ResponseEntity<List<Company>> getTopCompaniesByQuantity(
            @RequestParam int topX,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return ResponseEntity.ok(cleanHubService.getTopCompaniesByQuantity(startDate,endDate,topX));
    }
}
