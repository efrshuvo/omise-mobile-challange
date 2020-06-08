package com.devlab.tamboon.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Charity {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("logo_url")
    @Expose
    private String logoUrl;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
