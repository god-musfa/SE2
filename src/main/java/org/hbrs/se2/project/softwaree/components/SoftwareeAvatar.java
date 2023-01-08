package org.hbrs.se2.project.softwaree.components;


import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.server.StreamResource;
import org.hbrs.se2.project.softwaree.control.PictureUploadController;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.ProfilePictureService;
import org.hbrs.se2.project.softwaree.views.MainView;

import java.io.IOException;
import java.io.InputStream;


@CssImport("./styles/components/SoftwareeAvatar.css")
public class SoftwareeAvatar extends HorizontalLayout implements SoftwareeAvatarIf{

    // ToDo: Replace avatar image with other image due to copyright reasons.
    private final String DEFAULT_AVATAR_IMAGE = "https://cdn-icons-png.flaticon.com/512/149/149071.png";
    
    private Image avatarImage = new Image();
    private final Label avatarLabel = new Label();
    public final MemoryBuffer uploadBuffer = new MemoryBuffer();
    private final Upload profilePictureUpload = new Upload(uploadBuffer);
    private final Button profilePictureUploadButton = new Button("Bild hochladen...");
    private final Label profilePictureUploadLabel = new Label("Bilddatei hier ablegen");


    public SoftwareeAvatar() {

        // Set up class name for css integration:
        this.setClassName("softwaree_avatar");

        // Set default attributes to components:
        this.avatarImage.setSrc(DEFAULT_AVATAR_IMAGE);
        this.avatarImage.setWidth("4rem");
        this.avatarImage.setHeight("4rem");
        this.setWidth("4rem");
        this.setHeight("4rem");
        this.setMargin(true);
        this.setAlignItems(Alignment.START);

        // Add sub components to avatar object:
        this.add(avatarImage);
        this.add(avatarLabel);

        // Initialize optional upload section:
        profilePictureUpload.setUploadButton(profilePictureUploadButton);
        profilePictureUpload.setDropLabel(profilePictureUploadLabel);
        profilePictureUpload.setDropAllowed(true);
        profilePictureUpload.addFileRejectedListener(event -> {System.out.println("REJECTED" + event.getErrorMessage());});
        //profilePictureUpload.setAcceptedFileTypes("*.jpg", "image/jpeg", "*.png", "image/png", "*.bmp", "image/bmp");
        profilePictureUpload.setMaxFileSize(ProfilePictureService.MAX_FILESIZE);

    }

    public SoftwareeAvatar(String labelText) {
        this();
        this.setLabel(labelText);
    }

    public SoftwareeAvatar(boolean enableChangeOption) {
        this();
        this.setChangeOption(enableChangeOption);
    }

    /**
     * Sets the image path of the avatar image.
     * @param imageData Image base64 data for avatar image file.
     */
    @Override
    public void setImage(String imageData) {
        avatarImage.setSrc(imageData);
        avatarImage.setClassName("softwaree_avatar_image");
    }

    /**
     * Assigns a controller that handles the upload of new profile pictures.
     * @param pictureUploadController Image base64 data for avatar image file.
     * @param targetUser User for which the picture should be saved.
     */
    public void setUploadController(PictureUploadController pictureUploadController, UserDTO targetUser) {
        profilePictureUpload.addSucceededListener(
                event -> {
                    InputStream fileData = uploadBuffer.getInputStream();
                    String fileName = event.getFileName();
                    long contentLength = event.getContentLength();
                    String mimeType = event.getMIMEType();

                    if (ProfilePictureService.checkPicture(fileName, contentLength, mimeType)) {
                        try {
                            System.out.println("Set picture: " + pictureUploadController.setProfilePicture(targetUser, fileData.readAllBytes(), mimeType));
                        } catch (IOException ioex) {
                            System.out.println("Profile Picture upload failed! Message: " + ioex.getMessage());
                        }
                    } else {
                        System.out.println("Invalid file properties for Profile Picture!");
                    }
                }
        );


    }

    /**
     * Sets the text of the optional label.
     * That label will be displayed after the avatar image.
     * @param labelText Text to set the label to.
     */
    @Override
    public void setLabel(String labelText) {
        this.avatarLabel.setText(labelText);
    }

    /**
     * Changes the size of the avatar image.
     * The dimensions are cubic, there is only one value that sets width and height.
     * @param sizeValue Width and height value to set
     */
    @Override
    public void setSize(String sizeValue) {
        this.avatarImage.setWidth(sizeValue);
        this.avatarImage.setHeight(sizeValue);
        this.setHeight(sizeValue);
    }

    /**
     * Enables / disables the functionality to change the profile picture via this SoftwareeAvatar instance
     * @param state Switch to enable or disable the option of changing image
     */
    @Override
    public void setChangeOption(boolean state) {
        if (state) {
            this.add(profilePictureUpload);
        } else {
            this.remove(profilePictureUpload);
        }
    }


}
