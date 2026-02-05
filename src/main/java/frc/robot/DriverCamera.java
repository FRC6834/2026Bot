package frc.robot;

import org.photonvision.*;
import org.photonvision.targeting.PhotonPipelineResult;
public class DriverCamera {
    PhotonCamera camera = new PhotonCamera("photonvision");
    int index = 1;
    public DriverCamera() {
        setFPSLimit();
        toggleDriverMode();
        setPipelineIndex(index);
    } 
    // Set pipeline index
    public void setPipelineIndex(int index) {
        camera.setPipelineIndex(index);
    }

    // set FPS limit to 60
    public void setFPSLimit() {
        camera.setFPSLimit(60);
        /* Set FPS limit to -1 for unlimited FPS
        camera.setFPSLimit(-1);
        */
    }

    // Set driver mode off/on
    public void toggleDriverMode() {
        camera.setDriverMode(true);
    }

   
    

    
    
}