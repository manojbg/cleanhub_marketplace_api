package com.cleanhub.restapi.serviceImpl;

import com.cleanhub.restapi.daoService.CompanyRepository;
import com.cleanhub.restapi.entity.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CleanHubServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CleanHubServiceImpl cleanHubService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCompanies() {
        // Mock company repository method
        List<Company> mockCompanies = new ArrayList<>();
        when(companyRepository.findAll()).thenReturn(mockCompanies);

        // Call the method to be tested
        List<Company> result = cleanHubService.getAllCompanies();

        // Verify the result
        assertEquals(mockCompanies, result);
    }

    @Test
    void testGetTopCompaniesByQuantity() throws ParseException {
        // Mock the company repository method
        List<Company> mockCompanies = new ArrayList<>();
        when(companyRepository.findTopRecordsByQuantityAndDateRange(any(Date.class), any(Date.class), any(PageRequest.class)))
                .thenReturn(mockCompanies);

        // Call the method to be tested
        List<Company> result = cleanHubService.getTopCompaniesByQuantity("2022-01-01", "2022-12-31", 5);

        // Verify the result
        assertEquals(mockCompanies, result);
    }

    @Test
    public void testGetTopCompaniesByQuantityWithParseException() {
        // Mock data
        String startDate = "invalidStartDate";
        String endDate = "2023-01-31";
        int topX = 5;
        try {
            List<Company> result = cleanHubService.getTopCompaniesByQuantity(startDate, endDate, topX);
            System.out.println(result);
        } catch (RuntimeException e) {
            assertEquals(ParseException.class, e.getCause().getClass());
        }
    }
}
