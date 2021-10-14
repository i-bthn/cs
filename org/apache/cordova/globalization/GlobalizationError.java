package org.apache.cordova.globalization;

import org.json.JSONException;
import org.json.JSONObject;

public class GlobalizationError extends Exception {
    public static final String FORMATTING_ERROR = "FORMATTING_ERROR";
    public static final String PARSING_ERROR = "PARSING_ERROR";
    public static final String PATTERN_ERROR = "PATTERN_ERROR";
    public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    private static final long serialVersionUID = 1;
    int error = 0;

    public GlobalizationError() {
    }

    public GlobalizationError(String str) {
        if (str.equalsIgnoreCase(FORMATTING_ERROR)) {
            this.error = 1;
        } else if (str.equalsIgnoreCase(PARSING_ERROR)) {
            this.error = 2;
        } else if (str.equalsIgnoreCase(PATTERN_ERROR)) {
            this.error = 3;
        }
    }

    public String getErrorString() {
        switch (this.error) {
            case 0:
                return UNKNOWN_ERROR;
            case 1:
                return FORMATTING_ERROR;
            case 2:
                return PARSING_ERROR;
            case 3:
                return PATTERN_ERROR;
            default:
                return "";
        }
    }

    public int getErrorCode() {
        return this.error;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", getErrorCode());
            jSONObject.put("message", getErrorString());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
