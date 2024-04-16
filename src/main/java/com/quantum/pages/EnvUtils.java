package com.quantum.pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.Properties;

public class EnvUtils {
    static Properties props;
    private static JSONObject scenarioData;

    static {
        props = new Properties();
        try {
            props.load(new FileReader("src\\main\\resources\\properties\\configure.properties"));
            switchEnvironment(props.getProperty("Environment"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void switchEnvironment(String environment) {
        try {
            switch (environment) {
                case "stage":
                    scenarioData = (JSONObject) new JSONParser().parse(new FileReader("src\\main\\resources\\data\\stage.json"));
                    break;
                case "test":
                    scenarioData = (JSONObject) new JSONParser().parse(new FileReader("src\\main\\resources\\data\\test.json"));
                    break;
                case "prod":
                    scenarioData = (JSONObject) new JSONParser().parse(new FileReader("prod.json"));
                    break;
                default:
                    throw new Exception("Invalid environment");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return props.getProperty(props.getProperty("Environment"));
    }

    public static JSONObject getScenario(String scenarioName) {
        return (JSONObject) scenarioData.get(scenarioName);
    }
}
