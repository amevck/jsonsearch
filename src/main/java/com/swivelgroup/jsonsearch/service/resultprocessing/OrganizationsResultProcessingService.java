package com.swivelgroup.jsonsearch.service.resultprocessing;

import com.swivelgroup.jsonsearch.jsonread.JsonDataMapper;
import com.swivelgroup.jsonsearch.service.UtillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrganizationsResultProcessingService implements ResultProcessingService {
    @Autowired
    UtillService utillService;

    @Override
    public void fillData(Map<String, String> organizationMap) {
        insertFromUserMap(organizationMap);
        insertFromTicketMap(organizationMap);

    }

    private void insertFromUserMap(Map<String, String> organizationMap) {

        AtomicInteger userCount = new AtomicInteger();
        JsonDataMapper.usersMap.forEach((key, userMap) -> {
            if (userMap.containsKey("organization_id") && userMap.get("organization_id").equals(organizationMap.get("_id"))) {
                organizationMap.put("user_" + userCount.get(), utillService.getFieldFromHashMap(userMap, "name"));
                userCount.getAndIncrement();
            }
        });
    }

    private void insertFromTicketMap(Map<String, String> organizationMap) {
        AtomicInteger ticketCounter = new AtomicInteger();
        JsonDataMapper.ticketsMap.forEach((key, userMap) -> {
            if (userMap.containsKey("organization_id") && userMap.get("organization_id").equals(organizationMap.get("_id"))) {
                organizationMap.put("ticket_" + ticketCounter.get(), utillService.getFieldFromHashMap(userMap, "subject"));
                ticketCounter.getAndIncrement();
            }
        });
    }
}
