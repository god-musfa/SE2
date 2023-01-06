package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.dtos.UserDTO;

public interface PictureUploadController {
    public boolean setProfilePicture(UserDTO targetUser, byte[] imageData, String mimetype);
}
