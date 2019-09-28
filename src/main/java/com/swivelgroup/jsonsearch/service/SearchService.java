package com.swivelgroup.jsonsearch.service;

import com.swivelgroup.jsonsearch.pojo.SearchMainField;
import com.swivelgroup.jsonsearch.jsonread.JsonDataMapper;
import com.swivelgroup.jsonsearch.pojo.Container;
import com.swivelgroup.jsonsearch.service.resultprocessing.OrganizationsResultProcessingService;
import com.swivelgroup.jsonsearch.service.resultprocessing.ResultProcessingService;
import com.swivelgroup.jsonsearch.service.resultprocessing.TicketResultProcessingService;
import com.swivelgroup.jsonsearch.service.resultprocessing.UserResultProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class SearchService {
    @Autowired
    UserResultProcessingService userResultProcessingService;
    @Autowired
    TicketResultProcessingService ticketResultProcessingService;
    @Autowired
    OrganizationsResultProcessingService organizationsResultProcessingService;
    @Autowired
            UtillService utillService;

    Map<Integer, Container> firstLevelSearchMap = new LinkedHashMap();

    @PostConstruct
    public void init() {
        firstLevelSearchMap = new LinkedHashMap() {{
            put(1, new Container(JsonDataMapper.usersMap, userResultProcessingService));
            put(2, new Container(JsonDataMapper.ticketsMap, ticketResultProcessingService));
            put(3, new Container(JsonDataMapper.organizationsMap, organizationsResultProcessingService));
        }};
    }

    public List<SearchMainField> getFirstLevelSearchKeys() {
        return Arrays.asList(SearchMainField.values());
    }

    public Set<String> getAllKeysOfHashMap(int key) {
        Map<String, Map<String, String>> mapOjJson = firstLevelSearchMap.get(key).getJsonMap();
        Map.Entry<String, Map<String, String>> entry = mapOjJson.entrySet().iterator().next();
        String firstkey = entry.getKey();
        return firstLevelSearchMap.get(key).getJsonMap().get(firstkey).keySet();
    }

    public List<Map<String, String>> getAllSearchResultOfHashMap(int firstLevelKey, String secondLevelKey, String searchVal) {
        List<Map<String, String>> resultmap = new ArrayList<>();
        Map<Integer, Container> firstLevel = firstLevelSearchMap;
        ResultProcessingService resultProcessingService = this.firstLevelSearchMap.get(firstLevelKey).getResultProcessingService();
        firstLevelSearchMap.get(firstLevelKey).getJsonMap().forEach((key, map) -> {
            if (utillService.getFieldFromHashMap(map,secondLevelKey).equals(searchVal)) {
                if (resultProcessingService != null) {
                    resultProcessingService.fillData(map);
                }
                resultmap.add(map);
            }
        });
        return resultmap;
    }

}
