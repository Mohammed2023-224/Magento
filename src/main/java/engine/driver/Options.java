package engine.driver;

import engine.constants.FrameWorkConstants;

import java.util.ArrayList;

public class Options {
    public static ArrayList<String> option() {
        ArrayList<String> options = new ArrayList<>();
        if (FrameWorkConstants.headlessMode == "true") {
            options.add("--headless");
        }
        if (FrameWorkConstants.maximized == "true") {
            options.add("--start-maximized");
        }
        if (FrameWorkConstants.extensions == "true") {
            options.add("--disable-extensions");
        }

        return options;
    }
}
