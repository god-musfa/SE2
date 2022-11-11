package org.hbrs.se2.project.softwaree.components;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;


@CssImport("./styles/components/SoftwareeAvatar.css")
public class SoftwareeAvatar extends HorizontalLayout implements SoftwareeAvatarIf{

    // ToDo: Replace avatar image with other image due to copyright reasons.
    private final String DEFAULT_AVATAR_IMAGE = "https://cdn-icons-png.flaticon.com/512/149/149071.png";
    
    private final Image avatarImage = new Image();
    private final Label avatarLabel = new Label();
    private final MemoryBuffer uploadBuffer = new MemoryBuffer();
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
        profilePictureUpload.setAcceptedFileTypes("*.jpg, *.png, *.gif, *.bmp");
        profilePictureUpload.setMaxFileSize(1 * 1024^2);
    }

    public SoftwareeAvatar(String labelText) {
        this();
        this.setLabel(labelText);
    }

    public SoftwareeAvatar(boolean enableChangeOption) {
        this();
        this.setChangeOption(true);
    }

    /**
     * Sets the image path of the avatar image.
     * @param imagePath Image path to avatar image file.
     */
    @Override
    public void setImage(String imagePath) {
        this.avatarImage.setSrc(imagePath);
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

    // ToDo: Implement function to set event handler for upload funcionality

}
