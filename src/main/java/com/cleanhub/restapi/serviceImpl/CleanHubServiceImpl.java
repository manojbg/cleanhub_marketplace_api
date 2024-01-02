package com.cleanhub.restapi.serviceImpl;

import com.cleanhub.restapi.daoService.CompanyRepository;
import com.cleanhub.restapi.daoService.ContractRepository;
import com.cleanhub.restapi.dto.CompanyHistory;
import com.cleanhub.restapi.entity.Company;
import com.cleanhub.restapi.entity.Contract;
import com.cleanhub.restapi.service.CleanHubService;
import com.cleanhub.restapi.utils.CleanHubServiceUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CleanHubServiceImpl implements CleanHubService {

    private String logoApiURL;

    private String orderApiURL;

    @Autowired
    CleanHubServiceUtil cleanHubServiceUtil;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Value("${cleanhub.api.logo}")
    public void setLogoApiURL(String logoApiUrl) {
        this.logoApiURL = logoApiUrl;
    }

    @Value("${cleanhub.api.order}")
    public void setOrderApiURL(String orderApiURL){this.orderApiURL = orderApiURL;}


    private static final Logger logger = LoggerFactory.getLogger(CleanHubServiceImpl.class);

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

    public void updateCompanyData() {
        JsonNode companies = cleanHubServiceUtil.getData(logoApiURL);

        for (JsonNode node : companies) {
            Company company = Company.create(node);
            JsonNode companyOrder = cleanHubServiceUtil.getData(orderApiURL+company.getLandingPageRoute());
            if(companyOrder != null){
                company.setState(companyOrder.get("state").asText());
                company.setQuantity(companyOrder.get("quantity").asDouble());
                company.setRecoveredQuantity(companyOrder.get("recoveredQuantity").asDouble());
                company.setContractStartDate(getDate(companyOrder.get("contractStartDate").asText()));
                JsonNode contracts = companyOrder.get("contracts");
                updateCompanyHistory(contracts, company.getLandingPageRoute());
            }
            companyRepository.save(company);
        }
    }

    public void updateCompanyHistory(JsonNode contracts, String landingPageRoute) {
        for (JsonNode node : contracts) {
            Contract contract = new Contract(
                    UUID.fromString(node.get("uuid").asText()),
                    landingPageRoute,
                    node.get("quantity").asDouble(),
                    node.get("recoveredQuantity").asDouble(),
                    node.get("period").asText(),
                    getDate(node.get("startDate").asText()),
                    getDate(node.get("endDate").asText()),
                    getDate(node.get("createdAt").asText()),
                    node.get("isFulfilled").asBoolean(),
                    node.get("type").asText()
            );
            contractRepository.save(contract);
        }
    }

    public Date getDate(String date){
        if(date.equals("null")){
            return null;
        }else {
            Instant instant1 = Instant.from(formatter.parse(date));
            return Date.from(instant1);
        }
    }

    @Override
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    @Override
    public List<Company> getTopCompaniesByQuantity(String startDate, String endDate, int topX) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PageRequest pageRequest = PageRequest.of(0, topX);
        try {
            return companyRepository.findTopRecordsByQuantityAndDateRange(dateFormat.parse(startDate), dateFormat.parse(endDate), pageRequest);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 600000) // Specify the interval in milliseconds (e.g., 60 seconds)
    public void fetchDataPeriodically() {
        try {
            logger.info("Fetching data from CleanHub Marketplace API...");
            updateCompanyData();
            logger.info("Data fetched successfully.");
        } catch (Exception e) {
            logger.error("Error fetching data: " + e.getMessage());
        }
    }

    @Override
    public List<CompanyHistory> getCompanyHistory(String landingPageRoute){
        List<CompanyHistory> histories = new ArrayList<>();
        Company company = companyRepository.findByLandingPageRoute(landingPageRoute);
        List<Contract> contracts = contractRepository.findByLandingPageRoute(landingPageRoute);
        CompanyHistory history = new CompanyHistory();
        history.setCompanyName(company.getCompanyName());
        history.setLandingPageRoute(company.getLandingPageRoute());
        history.setQuantity(company.getQuantity());
        history.setState(company.getState());
        history.setContracts(contracts);
        histories.add(history);
        return histories;
    }
}
