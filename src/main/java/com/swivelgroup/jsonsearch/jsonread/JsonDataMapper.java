package com.swivelgroup.jsonsearch.jsonread;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JsonDataMapper {

    public static Map<String, Map<String, String>> organizationsMap = new LinkedHashMap();
    public static Map<String, Map<String, String>> ticketsMap =new LinkedHashMap();
    public static Map<String, Map<String, String>> usersMap = new LinkedHashMap();

    static {
        organizationsMap = getHashMapOfJson("organizations.json", "_id");
        ticketsMap = getHashMapOfJson("tickets.json", "_id");
        usersMap = getHashMapOfJson("users.json", "_id");
    }

    private static JSONArray readJsonArrayFromFile(String fileName) {
        JSONArray jsonarr = new JSONArray();
        try {
            ClassPathResource classPathResource = new ClassPathResource(fileName);
            InputStream inputStream = classPathResource.getInputStream();
//            File f = ResourceUtils.getFile("classpath:" + fileName);
//            if (f.exists()) {
//                FileInputStream is = new FileInputStream(f);
//            InputStream is = JsonParsing.class.getResourceAsStream("classpath:organizations.json");
                String jsonTxt = IOUtils.toString(inputStream, "UTF-8");
                jsonarr = new JSONArray(jsonTxt);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonarr;
    }

    private static List<Map<String, String>> getJsonArrayAsHashMapList(JSONArray jsonArray) {
        List<Map<String, String>> dataStruct = new ArrayList<Map<String, String>>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject OneEntry = jsonArray.getJSONObject(i);
                int OneEntrySize = OneEntry.length();
                JSONArray EntKey = OneEntry.names();
                Map<String, String> JsonItem = new LinkedHashMap<>();
                for (int j = 0; j < OneEntrySize; j++) {
                    String EntVal = EntKey.getString(j);
                    String GenCell = OneEntry.opt(EntVal).toString();
                    JsonItem.put(EntVal, GenCell);
                }
                dataStruct.add(JsonItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataStruct;
    }

    private static Map<String, Map<String, String>> getHashMapOfJson(String jsonName, String field) {
        List<Map<String, String>> listOfMap = getJsonArrayAsHashMapList(readJsonArrayFromFile(jsonName));
        return getHashMapListAsMapByGivenField(listOfMap, field);
    }

    private static Map<String, Map<String, String>> getHashMapListAsMapByGivenField(List<Map<String, String>> mapList, String Field) {
        Map<String, Map<String, String>> map = new LinkedHashMap();
        for (Map i : mapList) map.put((String) i.get(Field), i);
        return map;
    }



}
