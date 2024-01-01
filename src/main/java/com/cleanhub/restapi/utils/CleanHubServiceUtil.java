package com.cleanhub.restapi.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CleanHubServiceUtil {

    private static final Logger logger = LoggerFactory.getLogger(CleanHubServiceUtil.class);
    
    public JsonNode getData(String apiUrl){
        JsonNode jsonNode = null;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            logger.info("responseCode = "+responseCode);
            if(responseCode == 200){
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    logger.info("API Response:\n" + response.toString());
                    ObjectMapper objectMapper = new ObjectMapper();
                    jsonNode = objectMapper.readTree(response.toString());
                }
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return jsonNode;
    }
}
