package com.cleanhub.restapi.service;

import com.cleanhub.restapi.dto.CompanyHistory;
import com.cleanhub.restapi.entity.Company;

import java.util.List;

public interface CleanHubService {
    List<Company> getAllCompanies();
    List<Company> getTopCompaniesByQuantity(String startDate, String endDate, int topX);
    List<CompanyHistory> getCompanyHistory(String landingPageRoute);
}
