package br.com.api.g4.exceptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final int STATUS;
    private final String MESSAGE;
    private List<String> details;

    public ErrorResponse(int STATUS, String MESSAGE) {
        super();
        this.STATUS = STATUS;
        this.MESSAGE = MESSAGE;
    }

    public ErrorResponse(int STATUS, String MESSAGE, List<String> details) {
        super();
        this.STATUS = STATUS;
        this.MESSAGE = MESSAGE;
        this.details = details;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

}
