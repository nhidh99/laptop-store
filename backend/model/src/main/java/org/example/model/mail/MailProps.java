package org.example.model.mail;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class MailProps {
    private String from;
    private String to;
    private String name;
    private String subject;
    private String content;
    private Map<String, String> model;
}
