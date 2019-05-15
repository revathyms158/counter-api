package au.com.optus.service;

import au.com.optus.model.DataCountResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by revathy.ms on 14/5/19.
 */
public class SearchServiceImpl implements SearchService {

    private static final String FILE_NAME = "dataFile.txt";
    private static Map<String, Integer> textTokenMap;
    private static File file;

    static {
        textTokenMap = new HashMap<String, Integer>();
        ClassLoader classLoader = new SearchServiceImpl().getClass().getClassLoader();
        file = new File(classLoader.getResource(FILE_NAME).getFile());
        retrieveCountMap();
    }

    /**
     *
     * Static method to process the file and populate textTokenMap
     *
     */
    private static void retrieveCountMap() {

        List<String> wordList;

        try {
            //reading the file line by line and extracting words
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
                //Collections.frequency(wordList, key);

            }
        } catch (IOException e) {
            System.out.println("inside getStringTokenMap(): IOException occured " + e.getMessage());
        }
    }

    /**
     * To retrieve the text and count Map
     *
     * @return
     */
    @Override
    public Map getWordCountMap() {
        return textTokenMap;
    }

    /**
     * To retrieve the top number of text and occurrence from sorted map
     *
     * @param sortedMap
     * @param count
     * @return
     */
    @Override
    public String getTopOrderList(Map<String, Integer> sortedMap, int count) {

        int i = 0;
        List<DataCountResult> topOrderList = new ArrayList<DataCountResult>();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
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

