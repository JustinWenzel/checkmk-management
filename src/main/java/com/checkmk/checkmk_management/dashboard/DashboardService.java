package com.checkmk.checkmk_management.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class DashboardService {
    private final RestClient checkmkRestClient;

    public ResponseEntity<Map<String, Object>> getCurrentProblems(){

        // Filtering the call for big datasets (performance)
        String serviceQuery = """
            {
                "op": "and",
                "expr": [
                    {
                        "op": "or",
                        "expr": [
                            {"op": "=", "left": "state", "right": "1"},
                            {"op": "=", "left": "state", "right": "2"}
                        ]
                    },
                    {"op": "=", "left": "acknowledged", "right": "0"},
                    {"op": "=", "left": "scheduled_downtime_depth", "right": "0"},
                    {"op": "=", "left": "host_scheduled_downtime_depth", "right": "0"},
                    {"op": "=", "left": "host_state", "right": "0"}
                ]
            }
            """;

        // Filter which columns to retrieve
        return checkmkRestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/domain-types/service/collections/all")
            .queryParam("query", serviceQuery)
            .queryParam("columns", "host_name")
            .queryParam("columns", "host_state")
            .queryParam("columns", "description")
            .queryParam("columns", "state")
            .queryParam("columns", "state_type")
            .queryParam("columns", "acknowledged")
            .queryParam("columns", "scheduled_downtime_depth")
            .queryParam("columns", "host_scheduled_downtime_depth")
            .queryParam("columns", "plugin_output")
            .queryParam("columns", "last_check")
            .build())
        .retrieve()
        .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {}); //EIGENES DTO 
}
