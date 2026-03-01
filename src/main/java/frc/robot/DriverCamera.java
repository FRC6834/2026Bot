package frc.robot;

import org.photonvision.*;
public final class DriverCamera {
    PhotonCamera camera1 = new PhotonCamera("Camera 1");
    PhotonCamera camera2 = new PhotonCamera("Camera 2");

    // Driver camera settings

    //Camera 1 pipeline index
    int index1 = 1;

    //Camera 2 pipeline index
    int index2 = 2;

    //Set FPS limit to -1 for unlimited FPS
    int FPS = 48;

    //Toggle driver mode on/off.
    boolean toggleDriverMode = true;



    
    public DriverCamera() {
        setFPSLimit(FPS);
        toggleDriverMode(toggleDriverMode);
        setPipelineIndex(index1);
        setPipelineIndex2(index2);
    } 
    // Set pipeline index
    public void setPipelineIndex(int index1) {
        camera1.setPipelineIndex(index1);
    }
    // Set pipeline index 2
    public void setPipelineIndex2(int index2) {
        camera2.setPipelineIndex(index2);
    }

    // set FPS limit to 60
    public void setFPSLimit(int FPS) {
        camera1.setFPSLimit(FPS);
        camera2.setFPSLimit(FPS);
    }

    // Set driver mode off/on
    public void toggleDriverMode(boolean toggleDriverMode) {
        camera1.setDriverMode(toggleDriverMode);
        camera2.setDriverMode(toggleDriverMode);
    }

   
    

    
    
}