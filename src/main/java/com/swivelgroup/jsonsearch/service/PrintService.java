package com.swivelgroup.jsonsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PrintService {
    @Autowired
    SearchService searchService;
    @Autowired
    UtillService utillService;

    public void printSearchableKeys() {
        System.out.println("________________________________________________");
        System.out.println("Search Users with");
        printAllValuesInList(searchService.getAllKeysOfHashMap(1));
        System.out.println();
        System.out.println("________________________________________________");
        System.out.println("Search Tickets with");
        printAllValuesInList(searchService.getAllKeysOfHashMap(2));
        System.out.println();
        System.out.println("________________________________________________");
        System.out.println("Search Organizations with");
        printAllValuesInList(searchService.getAllKeysOfHashMap(3));
        System.out.println();
        System.out.println("________________________________________________");
    }

    public void printAllValuesInList(Set<String> stringList) {
        stringList.forEach((str) -> System.out.println(str));
    }
    public void initialPrint(){
        System.out.println("Select search options: ");
        System.out.println(" * Press 1 to search: ");
        System.out.println(" * press 2 to view a list of searchable fields: ");
        System.out.println(" * Type 'quite' to exit: ");
    }
    public void endPrinting(){
        System.out.println();
        System.out.println("________________________________________________");
        System.out.println();
        initialPrint();
    }
    public void printAllSearchJsons(List<Map<String, String>> jsonList) {

        jsonList.forEach(map -> {
            SortedSet<String> keySet = new TreeSet<>(map.keySet());
            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                String key = it.next();
                System.out.println();
                System.out.print(key);
                for (int i = 0; i < 30 - key.length(); i++) {
                    System.out.print(" ");
                }
                System.out.print(utillService.getFieldFromHashMap(map, key));
            }

        });
    }

}
