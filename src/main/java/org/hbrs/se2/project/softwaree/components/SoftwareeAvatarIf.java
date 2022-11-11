package org.hbrs.se2.project.softwaree.components;

/**
 * Interface for Custom Avatar Component
 *
 * This component displays an avatar image with optional label or upload option.
 */

public interface SoftwareeAvatarIf {
    // Sets the image for the avatar
    public void setImage(String imagePath);

    // Sets the optional label beneath the avatar (right side)
    public void setLabel(String labelText);

    // Set the cubic size of the avatar image to a given value
    public void setSize(String sizeValue);

    // Enables or disables the option to change the profile picture
    public void setChangeOption(boolean state);
}
