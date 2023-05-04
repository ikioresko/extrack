package com.home.extrack.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.home.extrack.entity.TicketEntity;
import com.home.extrack.model.TicketDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketService {
    private final RestTemplate restTemplate;
    @Value("${serviceHeader}")
    private String serviceHeader;
    @Value("${serviceKey}")
    private String serviceKey;
    @Value("${serviceURLNewT}")
    private String serviceURLNewT;
    @Value("${serviceURLAdd}")
    private String serviceURLAdd;

    public TicketService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Format Json from TicketDTO
     *
     * @param ticketDTO contains param
     * @return String Json
     */
    private String convertDataToJson(TicketDTO ticketDTO) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(ticketDTO);
    }

    /**
     * Send ticket to service
     *
     * @param data Json object
     * @return JsonNode
     */
    private JsonNode sendTicketRestTemplate(String data, String url) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(serviceHeader, serviceKey);
        HttpEntity<String> request = new HttpEntity<>(data, headers);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root;
        String personResultAsJsonStr =
                restTemplate.postForObject(url, request, String.class);
        root = objectMapper.readTree(personResultAsJsonStr);
        return root;
    }

    /**
     * Form entity after getting params
     *
     * @param ticketDTO object with data
     * @return TicketEntity
     */
    public TicketEntity openTicketInOTRS(TicketDTO ticketDTO) throws Exception {
        String data = convertDataToJson(ticketDTO);
        JsonNode node = sendTicketRestTemplate(data, serviceURLNewT);
        long ticketNumber = Long.parseLong(node.get("Number").asText());
        long ticketID = Long.parseLong(node.get("ID").asText());
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTicketID(ticketID);
        ticketEntity.setTicketNumber(ticketNumber);
        return ticketEntity;
    }

    /**
     * Add info into ticket
     *
     * @param ticketDTO object with data
     * @throws JsonProcessingException exception
     */
    public void addInExistingTicket(TicketDTO ticketDTO) throws JsonProcessingException {
        String data = convertDataToJson(ticketDTO);
        sendTicketRestTemplate(data, serviceURLAdd);
    }
}
