package org.hbrs.se2.project.softwaree.dtos;


import org.hbrs.se2.project.softwaree.entities.Message;
import org.hbrs.se2.project.softwaree.entities.StudentCompanyMessage;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Message} entity
 */
public class MessageDTO implements Serializable {
    private Integer id;
    private Date time_sent;
    private String message;

    public MessageDTO(Integer id, Date time_sent, String message) {
        this.id = id;
        this.time_sent = time_sent;
        this.message = message;
    }

    public MessageDTO(Integer id) {
        this.id = id;
    }

    public MessageDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime_sent() {
        return time_sent;
    }

    public void setTime_sent(Date timeSent) {
        this.time_sent = timeSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}