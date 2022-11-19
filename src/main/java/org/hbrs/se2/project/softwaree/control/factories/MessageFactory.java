package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.entities.Message;

import java.util.Date;

public class MessageFactory {

    public static Message createMessage(Date now, String messageString, Integer studentID, Integer companyID, Integer jobID) {
        Message message = new Message();

        message.setTimeSent(now);
        message.setMessage(messageString);
        message.setStudent_id(studentID);
        message.setCompany_id(companyID);
        if(jobID != -1) {
            message.setJob_id(jobID);
        }

        return message;
    }
}
