package com.codie.simulation.service;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.codie.simulation.R;
import com.codie.simulation.ui.activity.MainActivity;
import com.codie.simulation.util.Wrapper;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LableDetectionTask extends AsyncTask<Object, Void, List<Wrapper> > {
    private static final String TAG = LableDetectionTask.class.getSimpleName();
    private final WeakReference<MainActivity> mActivityWeakReference;
    private Vision.Images.Annotate mRequest;

    public LableDetectionTask(MainActivity activity, Vision.Images.Annotate annotate) {
        mActivityWeakReference = new WeakReference<>(activity);
        mRequest = annotate;
    }

    @Override
    protected List<Wrapper>  doInBackground(Object... params) {
        try {
            Log.d(TAG, "created Cloud Vision request object, sending request");
            BatchAnnotateImagesResponse response = mRequest.execute();
            return convertResponseToString(response);

        } catch (GoogleJsonResponseException e) {
            Log.d(TAG, "failed to make API request because " + e.getContent());
        } catch (IOException e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
        return null;
    }

    protected void onPostExecute(List<Wrapper>  results) {
        MainActivity activity = mActivityWeakReference.get();
        if (activity != null && !activity.isFinishing()) {
            activity.setDetectionResultItems(results);
        }
    }

    private static List<Wrapper>  convertResponseToString(BatchAnnotateImagesResponse response) {
        List<Wrapper> detectedItems = new ArrayList<>();

        List<EntityAnnotation> labels = response.getResponses().get(0).getLabelAnnotations();
        if (labels != null) {
            for (EntityAnnotation label : labels) {

                detectedItems.add(new Wrapper(label.getDescription(), label.getScore()));
            }
        }

        return detectedItems;
    }
}