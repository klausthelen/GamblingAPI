package com.example.model;

import java.util.Map;

public class Response {

    private String message;

    private Boolean error;

    private String id;

    private static Response response;

    public static Response getResponse(String message, Boolean error, String id) {

        if (response==null) {

            response=new Response(message,error,id);
        }
        return response;
    }

    private Response(String message, Boolean error, String id){
        this.message = message;
        this.error = error;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Response setResponse(Map<String, Object> result){
        this.setError((boolean) result.get("error"));
        this.setMessage((String) result.get("message"));
        this.setId(result.get("id").toString());
        return this;
    }
}
