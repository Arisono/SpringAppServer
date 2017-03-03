package org.springframework.samples.mvc.jpa.entity;

public class DownloadRepoMessageEvent {
    public String message;

    public DownloadRepoMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
