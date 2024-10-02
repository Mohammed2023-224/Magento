package engine.dataDriven;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;

public class ReadProperties {
    private static String filePath = "src/main/resources/properties/";
    private static String filesEnding = ".properties";
    private static FileReader fr;
    static Properties prop;

    public static Properties readAllFiles() {
        prop = new Properties();
        ArrayList<String> files = new ArrayList<>();
        files.add("paths");
        for (String file : files) {
            try {
                //add each file to the properties
                fr = new FileReader(filePath + file + filesEnding);
                prop.load(fr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

    public static String getProperty(String property) {
        return prop.getProperty(property);
    }

    public static String readCertainProperty(String fileName, String property) {
        prop = new Properties();
        try {
            fr = new FileReader(filePath + fileName + filesEnding);
            prop.load(fr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(property);
    }
}
