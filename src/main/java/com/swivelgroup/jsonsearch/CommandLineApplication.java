package com.swivelgroup.jsonsearch;
import com.swivelgroup.jsonsearch.exception.ExceptionHandler;
import com.swivelgroup.jsonsearch.exception.JsonSearchException;
import com.swivelgroup.jsonsearch.pojo.SearchMainField;
import com.swivelgroup.jsonsearch.service.PrintService;
import com.swivelgroup.jsonsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import static java.lang.System.exit;

@Component
public class CommandLineApplication {
    @Autowired
    SearchService searchService;
    @Autowired
    PrintService printService;

    public void run() {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        printService.initialPrint();
        String name = "";
        int firstInput = 1;
        int firstLevelKey = 1;
        while (!name.contains("quit")) {
            try {
                firstInput= getFirstInput(reader);
                switch (firstInput){
                    case 1:{
                        System.out.println("Select 1) Users 2) Tickets or 3) Organizations");
                        firstLevelKey= getFirstInput(reader);
                        if(firstLevelKey > 3 || firstLevelKey < 0){
                            throw new JsonSearchException("Please enter a number within 1-3");
                        }
                        printService.printAllSearchJsons(getSearchFields(reader, firstLevelKey));
                        break;
                    }
                    case 2:{
                        printService.printSearchableKeys();
                        break;
                    }
                    default:{
                        System.out.println("Please insert a correct number");
                    }
                }

            } catch (JsonSearchException e) {
                e.printStackTrace();
                new ExceptionHandler(e.getMessage());

            } catch (Exception e) {
                new ExceptionHandler("Please enter correct values");
            }
            printService.endPrinting();
        }
        exit(0);
    }

    public void printlnCommandLine(String value) {
        System.out.println(value);
    }

    private List<Map<String, String>> getSearchFields(BufferedReader reader, int firstLevelKey) throws JsonSearchException {

        String term = null;
        String value = null;
        try {
            printlnCommandLine("Enter search term");
            term = reader.readLine();
            printlnCommandLine("Enter search value");
            value = reader.readLine();
        } catch (Exception e) {
            throw new JsonSearchException("Please enter a correct values.");
        }
        return  searchService.getAllSearchResultOfHashMap(firstLevelKey, term, value);
    }

    public int getFirstInput(BufferedReader reader) throws JsonSearchException {
        int firstKey = 0;
        String str = "";
        try {
            str = reader.readLine();
            if(str.equals("quit")){
                exit(0);
            }
            firstKey = Integer.parseInt(str);
        } catch (Exception e) {
            throw new JsonSearchException("Please enter a correct number.");
        }
        return firstKey;
    }

    public int getSearchValues(BufferedReader reader, int firstLevelValue) throws IOException {
        searchService.getFirstLevelSearchKeys().forEach(val -> System.out.println(val.toString()));
        return Integer.parseInt(reader.readLine());
    }



    public void printFirstLevelValues(List<SearchMainField> stringList) {
        int i = 1;
        for (SearchMainField str : stringList) {
            System.out.print(str.toString() + " " + i + " ");
            i++;
        }
        System.out.println();
    }

}
