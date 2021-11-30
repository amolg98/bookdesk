package com.amolgupta.bookdesk.payload.response;

public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    
}
