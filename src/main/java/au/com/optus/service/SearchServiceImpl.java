package au.com.optus.service;

import au.com.optus.model.DataCountResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by revathy.ms on 14/5/19.
 */
public class SearchServiceImpl implements SearchService {

    private static final String FILE_NAME = "dataFile.txt";
    private static Map<String, Integer> textTokenMap;
    private static  File file;

    static {
        textTokenMap = new HashMap<String, Integer>();
        ClassLoader classLoader = new SearchServiceImpl().getClass().getClassLoader();
        file = new File(classLoader.getResource(FILE_NAME).getFile());
        retrieveCountMap();
    }

    public static void retrieveCountMap(){

        List<String> wordList;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                line = line.replaceAll("[\\.$|,|;]", "");
                wordList = Arrays.asList(line.split(" "));
                for (String text : wordList) {
                    text = text.toLowerCase();
                    if (textTokenMap.containsKey(text)) {
                        textTokenMap.put(text, textTokenMap.get(text) + 1);
                    } else {
                        textTokenMap.put(text, 1);
                    }
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("inside getStringTokenMap(): IOException occured " + e.getMessage());
        }
    }

    @Override
    public Map getWordCountMap() {
        return textTokenMap;
    }

    @Override
    public String getTopOrderList(Map<String, Integer> sortedMapDesc, int count) {
        int i = 0;
        List<DataCountResult> topOrderList = new ArrayList<DataCountResult>();

        for (Map.Entry<String, Integer> entry : sortedMapDesc.entrySet()) {
            if (i < count) {
                DataCountResult countResult = new DataCountResult();
                countResult.setText(entry.getKey());
                countResult.setCount(entry.getValue());
                topOrderList.add(countResult);
                i++;
            }
        }
        StringBuilder csvBuilder = new StringBuilder();
        for (DataCountResult dataCount : topOrderList) {
            csvBuilder.append(dataCount.getText());
            csvBuilder.append("|");
            csvBuilder.append(dataCount.getCount());
            csvBuilder.append("\n");
        }
        return csvBuilder.toString();
    }


}

