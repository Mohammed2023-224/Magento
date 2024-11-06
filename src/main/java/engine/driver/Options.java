package engine.driver;

import engine.constants.FrameWorkConstants;

import java.util.ArrayList;

public class Options {
    public static ArrayList<String> option() {
        ArrayList<String> options = new ArrayList<>();
        if (FrameWorkConstants.headlessMode.equalsIgnoreCase("true")) {
            options.add("--headless");
        }
        if (FrameWorkConstants.maximized.equalsIgnoreCase("true")) {
            options.add("--start-maximized");
        }
        if (FrameWorkConstants.extensions == "true") {
            options.add("--disable-extensions");
        }
        if (FrameWorkConstants.noSandBox == "true") {
            options.add("--no-sandbox");
        }
        if (FrameWorkConstants.disableDevSHM == "true") {
            options.add("--disable-dev-shm-usage");
        }

        return options;
    }
}
