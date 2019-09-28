package com.swivelgroup.jsonsearch.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UtillService {
    public String getFieldFromHashMap(Map<String, String> map, String key){
        if(map.containsKey(key)){
            return map.get(key);
        }
        return "";
    }
}
