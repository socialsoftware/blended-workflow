package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecVO {
    public String externalId;
    public String specId;
    public String name;
    public String dataSpecId;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataSpecId() {
        return dataSpecId;
    }

    public void setDataSpecId(String dataSpecId) {
        this.dataSpecId = dataSpecId;
    }

    public SpecVO() {
    }

    public SpecVO(String specId, String name) {
        this.specId = specId;
        this.name = name;
    }

}
