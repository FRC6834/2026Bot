package frc.robot;

import org.photonvision.*;
public class DriverCamera {
    PhotonCamera camera = new PhotonCamera("photonvision");

    // Driver camera settings
    int index = 1;
    //Set FPS limit to -1 for unlimited FPS
    int FPS = 48;

    boolean toggleDriverMode = true;
    
    public DriverCamera() {
        setFPSLimit(FPS);
        toggleDriverMode(toggleDriverMode);
        setPipelineIndex(index);
    } 
    // Set pipeline index
    public void setPipelineIndex(int index) {
        camera.setPipelineIndex(index);
    }

    // set FPS limit to 60
    public void setFPSLimit(int FPS) {
        camera.setFPSLimit(FPS);
    }

    // Set driver mode off/on
    public void toggleDriverMode(boolean toggleDriverMode) {
        camera.setDriverMode(toggleDriverMode);
    }

   
    

    
    
}