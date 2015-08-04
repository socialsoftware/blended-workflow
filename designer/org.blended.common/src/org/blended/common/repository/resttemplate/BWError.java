package org.blended.common.repository.resttemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BWError {
    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String type;
    private String value;

    public BWError() {
    }

    public BWError(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getMessage() {
        return "[" + this.type + "] " + this.value;
    }

}
