package com.swivelgroup.jsonsearch.service.resultprocessing;

import com.swivelgroup.jsonsearch.jsonread.JsonDataMapper;
import com.swivelgroup.jsonsearch.service.UtillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserResultProcessingService implements ResultProcessingService {
@Autowired
    UtillService utillService;
    @Override
    public void fillData(Map<String,String> userMap) {
        insertFromTicketMap(userMap);
        userMap.put("organization_name",utillService.getFieldFromHashMap(JsonDataMapper.organizationsMap.get(userMap.get("organization_id")),"name"));
    }
    private void insertFromTicketMap(Map<String, String> userMap) {
        AtomicInteger ticketCounter = new AtomicInteger();
        JsonDataMapper.ticketsMap.forEach((key, ticketMap) -> {
            if (ticketMap.containsKey("assignee_id") && ticketMap.get("assignee_id").equals(userMap.get("_id"))) {
                userMap.put("ticket_" + ticketCounter.get(), utillService.getFieldFromHashMap(ticketMap, "subject"));
                ticketCounter.getAndIncrement();
            }
        });
    }
}
