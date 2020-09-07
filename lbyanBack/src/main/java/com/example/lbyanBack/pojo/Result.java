package com.example.lbyanBack.pojo;

public class Result {

    private String message;

    private Object results;

    private boolean state;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Result(String message, boolean state, Object object) {
        this.message = message;
        this.state = state;
        this.results = object;
    }

    public Result() {
    }
}
