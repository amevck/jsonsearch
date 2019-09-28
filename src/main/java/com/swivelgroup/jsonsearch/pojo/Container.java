package com.swivelgroup.jsonsearch.pojo;

import com.swivelgroup.jsonsearch.service.resultprocessing.ResultProcessingService;

import java.util.Map;

public class Container {
   private ResultProcessingService resultProcessingService;
   private Map<String, Map<String, String>> jsonMap;

   public Container(Map<String, Map<String, String>> jsonMap,ResultProcessingService resultProcessingService){
        setResultProcessingService(resultProcessingService);
        setJsonMap(jsonMap);
    }

    public ResultProcessingService getResultProcessingService() {
        return resultProcessingService;
    }

    public void setResultProcessingService(ResultProcessingService resultProcessingService) {
        this.resultProcessingService = resultProcessingService;
    }

    public Map<String, Map<String, String>> getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(Map<String, Map<String, String>> jsonMap) {
        this.jsonMap = jsonMap;
    }
}
