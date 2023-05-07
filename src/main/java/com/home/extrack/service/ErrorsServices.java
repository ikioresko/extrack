package com.home.extrack.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.extrack.database.ArchiveDB;
import com.home.extrack.database.ErrorEntityDB;
import com.home.extrack.database.TicketEntityDB;
import com.home.extrack.database.UserInfoDB;
import com.home.extrack.entity.*;
import com.home.extrack.model.AddErrorModel;
import com.home.extrack.model.ErrorDTO;
import com.home.extrack.model.TicketDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class ErrorsServices {
    private final UserInfoDB userInfoDB;
    private final TicketEntityDB ticketEntityDB;
    private final ErrorEntityDB errorsDB;
    private final TicketService ticketService;
    private final TicketPool ticketPool;
    private final FilterService filterService;
    private final ArchiveDB archiveDB;
    private final DtoPool dtoPool;

    public ErrorsServices(UserInfoDB userInfoDB, TicketEntityDB ticketEntityDB, ErrorEntityDB errorsDB,
                          TicketService ticketService, TicketPool ticketPool, FilterService filterService,
                          ArchiveDB archiveDB, DtoPool dtoPool) {
        this.userInfoDB = userInfoDB;
        this.ticketEntityDB = ticketEntityDB;
        this.errorsDB = errorsDB;
        this.ticketService = ticketService;
        this.ticketPool = ticketPool;
        this.filterService = filterService;
        this.archiveDB = archiveDB;
        this.dtoPool = dtoPool;
    }

    /**
     * Create object from Data
     *
     * @param addErrorModel Data from service
     * @return InfoEntity
     */
    public InfoEntity createUserInfoEntityFromAddErrorModel(AddErrorModel addErrorModel) {
        var userInfo = new InfoEntity(addErrorModel.getOrderNumber(), addErrorModel.getUserName(), addErrorModel.getTicket());
        return userInfo;
    }

    /**
     * Check error duplicate
     *
     * @param dto object with error id and login
     * @return true when new error
     */
    private boolean checkErrorDTO(ErrorDTO dto) {
        return dtoPool.addToMapErrorDTO(dto);
    }



    /**
     * Save in archive
     *
     * @param errorItem text error
     * @param errorType type error
     * @param errorId   id error
     */
    private void saveInArchive(String errorItem, String errorType, Long errorId) {
        archiveDB.save(new EntityArchive(errorItem, errorType, errorId));
    }

    /**
     * Check time
     *
     * @throws IllegalAccessException Not working time
     */
    private void checkWorkTime() throws IllegalAccessException {
        if (LocalTime.now().isAfter(LocalTime.of(23, 05))
                || LocalTime.now().isBefore(LocalTime.of(6, 0))) {
            throw new IllegalAccessException("Not working time");
        }
    }

    /**
     * Set object for response
     *
     * @param returnData   Map <String, String>
     * @param ticketNumber ticket number
     * @param ticketId     ticket Id
     * @param errorId      error Id
     */
    private void setReturnData(Map<String, String> returnData,
                               String ticketNumber, String ticketId,
                               String errorId) {
        returnData.put("ticketNumber", ticketNumber);
        returnData.put("ticketId", ticketId);
        returnData.put("errorId", errorId);
    }

    /**
     * Set object for response
     *
     * @param returnData      Map <String, String>
     * @param ticketNumber    ticket number
     * @param ticketId        ticket Id
     * @param errorId         error Id
     * @param replacementText replace text
     */
    private void setReturnData(Map<String, String> returnData,
                               String ticketNumber, String ticketId,
                               String errorId, String replacementText) {
        returnData.put("replacement", replacementText);
        setReturnData(returnData, ticketNumber, ticketId, errorId);
    }

    /**
     * Create TicketDTO
     *
     * @param errorId       error id
     * @param errorItem     text error
     * @param addErrorModel any input parameters
     * @return TicketDTO
     */
    private TicketDTO createTicketDTO(Long errorId, String errorItem, AddErrorModel addErrorModel) {
        return new TicketDTO(errorId, addErrorModel.getCode(), addErrorModel.getErrorItem(), errorItem, "open", "");
    }

    /**
     * Remove ticket from cache pool
     *
     * @param ticketInfo info about closed ticket
     * @throws JsonProcessingException exception
     */
    public void removeTicketFromMemPool(String ticketInfo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(ticketInfo);
        long OTRSTicketID = Long.parseLong(root.get("TicketID").asText());
        log.info("ticketID {} should be removed from pool", OTRSTicketID);
        TicketEntity ticketEntity = ticketEntityDB.findTicketEntityByTicketID(OTRSTicketID);
        ConcurrentHashMap<Long, TicketEntity> map = ticketPool.getMap();
        Long errorId = 0L;
        for (Map.Entry<Long, TicketEntity> entry : map.entrySet()) {
            if (entry.getValue().equals(ticketEntity)) {
                errorId = entry.getKey();
                break;
            }
        }
        map.remove(errorId);
    }
}