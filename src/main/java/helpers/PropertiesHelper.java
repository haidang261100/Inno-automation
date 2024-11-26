package helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {
    private String filePath;


    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    private static String relPropertiesFilePathDefault = "src/test/resources/configs/configs.properties";


    public static Properties loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();
        // Add file
        files.add("src/test/resources/configs.properties");
        files.add("src/test/resources/dataSet/login.json");

        try {
            properties = new Properties();

            for (String f : files) {
                Properties tempProp = new Properties();
                linkFile = SystemsHelper.getCurrentDir() + f;
                file = new FileInputStream(linkFile);
                tempProp.load(file);
                properties.putAll(tempProp);
            }
            return properties;

        } catch (IOException ioe) {
            return new Properties();
        }
    }


    public static String getValue(String key) {
        String keyValue = null;
        try {
            if (file == null) {
                properties = new Properties();
                linkFile = SystemsHelper.getCurrentDir() + relPropertiesFilePathDefault;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            // Lấy giá trị từ file đã Set
            keyValue = properties.getProperty(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return keyValue;
    }


    public static ArrayList<HashMap<String, String>> readJsonData(String filePath) {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            // Parse JSON file
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            // Iterate over the array
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                HashMap<String, String> map = new HashMap<>();

                for (Object key : jsonObject.keySet()) {
                    String keyStr = (String) key;
                    String value = (String) jsonObject.get(keyStr);
                    map.put(keyStr, value);
                }

                dataList.add(map);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return dataList;
    }


}
