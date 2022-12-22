package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.entities.Message;

import java.util.Date;

public class MessageFactory {

    private MessageFactory() {
        //MessageFactory is a utility class
    }

    public static Message createMessage(Date now, String messageString, Integer studentID, Integer companyID, Integer jobID) {
        Message message = new Message();

        message.setTimeSent(now);
        message.setMessage(messageString);
        message.setStudentID(studentID);
        message.setCompanyID(companyID);
        if(jobID != -1) {
            message.setJobID(jobID);
        }

        return message;
    }
}
