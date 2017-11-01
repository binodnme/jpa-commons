package com.github.binodnme.pojo;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 10/31/17.
 */
public class FilterUnit {

    private String key;

    private String operator;

    private String value;

    public FilterUnit(String key, String value) {
        this.key = key;
        this.value = "eq";
        this.value = value;
    }

    public FilterUnit(String key, String operator, String value) {
        this.key = key;
        this.operator = operator;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
