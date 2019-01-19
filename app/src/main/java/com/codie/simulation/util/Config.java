package com.codie.simulation.util;


import android.graphics.Bitmap;

import com.codie.simulation.BuildConfig;


public class Config {
    public static final String CLOUD_LABEL_DETECTION = "Cloud Label";
    public static final String KEY_FILE_URI = "key_file_uri";
    public static final String KEY_DOWNLOAD_URL = "key_download_url";
    public static final int PERMISSION_REQUEST_READ_STORAGE = 2;
    public static final String FRAGMENT_KEY = "com.codie.simulation.ui.activity.FRAGMENT_KEY";
    public static String selectedMode = CLOUD_LABEL_DETECTION;

    public final static int GALLERY_IMAGE_LOADED = 1001;
    public static Bitmap selectedImageBitmap;

    public static final String SIZE_PREVIEW = "w:max";
    public static String selectedSize = SIZE_PREVIEW;

    public static final String ACTION_IDS = "action_ids**";
    public static final String DATA_OBJECT_IDS = "data_object_ids**";
    public static final String DATE_TIME_IDS = "date_time_ids**";
    public static final String FUNCTION_IDS = "function_ids**";
    public static final String LOCATION_IDS = "location_ids**";
    public static final String OBJECT_PROPERTY_IDS = "object_property_ids**";
    public static final String PARAMETER_IDS = "parameter_ids**";
    public static final String POINTER_IDS = "pointer_ids**";
    public static final String TIME_INTERVAL_ID = "time_interval_id**";


    public static final String CLOUD_VISION_API_KEY = "";
    public static final String FILE_NAME = "temp.jpg";
    public static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    public static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    public static final int MAX_LABEL_RESULTS = 10;
    public static final int MAX_DIMENSION = 1200;

}
