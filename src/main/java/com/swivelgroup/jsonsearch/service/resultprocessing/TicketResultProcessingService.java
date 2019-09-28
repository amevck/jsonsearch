package com.swivelgroup.jsonsearch.service.resultprocessing;

import com.swivelgroup.jsonsearch.jsonread.JsonDataMapper;
import com.swivelgroup.jsonsearch.service.UtillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TicketResultProcessingService implements ResultProcessingService {
@Autowired
    UtillService utillService;
    @Override
    public void fillData(Map<String,String> ticketMap) {
      insertFromUserMap("assignee_id","asignee_name",ticketMap);
      insertFromUserMap("submitter_id","submitter_name",ticketMap);
      insertFromOrganizationMap("organization_id","organization_name",ticketMap);
    }

   private void insertFromUserMap(String checkField,String insertName, Map<String, String>  ticketMap){
       if(ticketMap.containsKey(checkField)){
           Map<String, String>  userMap = JsonDataMapper.usersMap.get(ticketMap.get(checkField));
           if(userMap != null) {
               ticketMap.put(insertName,utillService.getFieldFromHashMap(userMap,"name"));
           }
       }
    }
    private void insertFromOrganizationMap(String checkField,String insertName, Map<String, String>  ticketMap){
        if(ticketMap.containsKey(checkField)){
            Map<String, String>  userMap = JsonDataMapper.organizationsMap.get(ticketMap.get(checkField));
            if(userMap != null) {
                ticketMap.put(insertName,utillService.getFieldFromHashMap(userMap,"name"));
            }
        }
    }
}
