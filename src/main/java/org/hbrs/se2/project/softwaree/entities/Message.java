package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Message")
@Table(name = "message", schema = "coll")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time_sent")
    private Date time_sent;

    @Column(name = "message")
    private String message;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeSent() {
        return time_sent;
    }

    public void setTimeSent(Date time_sent) {
        this.time_sent = time_sent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}