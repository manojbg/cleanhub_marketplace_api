package com.cleanhub.restapi.service;

import com.cleanhub.restapi.entity.Company;

import java.util.List;

public interface CleanHubService {
    List<Company> getAllCompanies();
    public List<Company> getTopCompaniesByQuantity(String startDate, String endDate, int topX);
}
