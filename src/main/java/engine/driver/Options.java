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
        if (FrameWorkConstants.extensions.equalsIgnoreCase("true")) {
            options.add("--disable-extensions");
        }
        if (FrameWorkConstants.noSandBox.equalsIgnoreCase("true")) {
            options.add("--no-sandbox");
        }
        if (FrameWorkConstants.disableDevSHM.equalsIgnoreCase("true")) {
            options.add("--disable-dev-shm-usage");
        }
        return options;
    }
}
