package com.codie.simulation.interfaces;


public interface OnImageUploadRequestListener {
    void onImageUpload(byte[] data, String folderName, String imageName);
}
