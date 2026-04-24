package com.back;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private final String actionName;
    private final Map<String, String> params = new HashMap<>();

    public Rq(String cmd) {
        String[] parts = cmd.split("\\?", 2);
        actionName = parts[0];

        if (parts.length > 1) {
            for (String pair : parts[1].split("&")) {
                String[] kv = pair.split("=", 2);
                if (kv.length == 2) {
                    params.put(kv[0], kv[1]);
                }
            }
        }
    }

    public String getParam(String name, String defaultValue) {
        return params.getOrDefault(name, defaultValue);
    }

    public int getParamAsInt(String name, int defaultValue) {
        String value = getParam(name, null);
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public String getActionName() {
        return actionName;
    }
}
