package com.codie.simulation.service;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.codie.simulation.R;
import com.codie.simulation.ui.activity.MainActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadService extends BaseTaskService {

    private static final String TAG = "UploadService";
    public static final String ACTION_UPLOAD = "action_upload";
    public static final String UPLOAD_COMPLETED = "upload_completed";
    public static final String UPLOAD_ERROR = "upload_error";
    public static final String EXTRA_FILE_URI = "extra_file_uri";
    public static final String EXTRA_DOWNLOAD_URL = "extra_download_url";
    public static final String IMAGE_BYTE_ARRAY = "image_byte_array";
    public static final String FOLDER_NAME = "**folder_name**";
    public static final String IMAGE_NAME = "**image_name**";

    private StorageReference mStorageRef;

    @Override
    public void onCreate() {
        super.onCreate();

        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        StringBuffer stringBuffer = new StringBuffer();

        if (ACTION_UPLOAD.equals(intent.getAction())) {
            String folderName = intent.getStringExtra(FOLDER_NAME);
            String imageName = intent.getStringExtra(IMAGE_NAME);

            imageName = imageName.replaceAll(" ", "_");
            stringBuffer.append(imageName).append(".jpg");
            imageName = stringBuffer.toString();
            byte[] bytes = intent.getByteArrayExtra(IMAGE_BYTE_ARRAY);

            uploadFromByteArray(bytes, folderName, imageName);
        }

        return START_REDELIVER_INTENT;
    }

    private void uploadFromByteArray(final byte[] bytes, String folderName, String imageName) {
        taskStarted();
        showProgressNotification(getString(R.string.progress_uploading), 0, 0);

        final StorageReference photoRef = mStorageRef.child(folderName)
                .child(imageName);

        // Upload file to Firebase Storage
        Log.d(TAG, "uploadFromUri:dst:" + photoRef.getPath());
        photoRef.putBytes(bytes).
                addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        showProgressNotification(getString(R.string.progress_uploading),
                                taskSnapshot.getBytesTransferred(),
                                taskSnapshot.getTotalByteCount());
                    }
                })
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        Log.d(TAG, "uploadFromUri: upload success");

                        return photoRef.getDownloadUrl();
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(@NonNull Uri downloadUri) {
                        Log.d(TAG, "uploadFromUri: getDownloadUri success");

                        broadcastUploadFinished(downloadUri, imageName);
                        showUploadFinishedNotification(downloadUri, imageName);
                        taskCompleted();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.w(TAG, "uploadFromUri:onFailure", exception);

                        broadcastUploadFinished(null, imageName);
                        showUploadFinishedNotification(null, imageName);
                        taskCompleted();
                    }
                });
    }

    /**
     * Broadcast finished upload (success or failure).
     * @return true if a running receiver received the broadcast.
     */
    private boolean broadcastUploadFinished(@Nullable Uri downloadUrl, @Nullable String imageName) {
        boolean success = downloadUrl != null;

        String action = success ? UPLOAD_COMPLETED : UPLOAD_ERROR;

        Intent broadcast = new Intent(action)
                .putExtra(EXTRA_DOWNLOAD_URL, downloadUrl)
                .putExtra(IMAGE_NAME, imageName);
        return LocalBroadcastManager.getInstance(getApplicationContext())
                .sendBroadcast(broadcast);
    }

    private void showUploadFinishedNotification(@Nullable Uri downloadUrl, @Nullable String imageName) {
        dismissProgressNotification();

        Intent intent = new Intent(this, MainActivity.class)
                .putExtra(EXTRA_DOWNLOAD_URL, downloadUrl)
                .putExtra(IMAGE_NAME, imageName)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        boolean success = downloadUrl != null;
        String caption = success ? getString(R.string.upload_success) : getString(R.string.upload_failure);
        showFinishedNotification(caption, intent, success);
    }

    public static IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_COMPLETED);
        filter.addAction(UPLOAD_ERROR);

        return filter;
    }

}
