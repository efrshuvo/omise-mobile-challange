package com.devlab.tamboon.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Charities {

    @SerializedName("data")
    @Expose
    private List<Charity> data = null;
    @SerializedName("total")
    @Expose
    private Integer total;

    public List<Charity> getData() {
        return data;
    }

    public void setData(List<Charity> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}