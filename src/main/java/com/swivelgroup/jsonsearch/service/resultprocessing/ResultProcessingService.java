package com.swivelgroup.jsonsearch.service.resultprocessing;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ResultProcessingService {
    public abstract void fillData(Map<String,String> userMap);
}
