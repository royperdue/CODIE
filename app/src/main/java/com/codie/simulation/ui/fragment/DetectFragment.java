package com.codie.simulation.ui.fragment;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.util.Pair;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codie.simulation.R;
import com.codie.simulation.data.model.DataObject;
import com.codie.simulation.data.model.ObjectProperty;
import com.codie.simulation.exception.UnsupportedModuleException;
import com.codie.simulation.interfaces.OnImageDownloadRequestListener;
import com.codie.simulation.interfaces.OnSetDetectedItemsListener;
import com.codie.simulation.ui.fragment.base.BaseFragment;
import com.codie.simulation.ui.help.HelpOptionAdapter;
import com.codie.simulation.util.Config;
import com.codie.simulation.util.GlideApp;
import com.codie.simulation.util.Wrapper;
import com.codie.simulation.util.manage.UtilityDialogManager;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.codie.simulation.ui.view.graphview.Graph;
import com.codie.simulation.ui.view.graphview.GraphAdapter;
import es.dmoral.toasty.Toasty;


public class DetectFragment extends BaseFragment implements OnImageDownloadRequestListener, OnSetDetectedItemsListener {
    private List<DataObject> dataObjects = new ArrayList<>();
    private List<List<ObjectProperty>> objectProperties = new ArrayList<>();
    protected ImageView previewImageView;
    protected CheckBox objectCheckBox1;
    protected CheckBox componentObjectCheckBox1;
    protected CheckBox objectCheckBox2;
    protected CheckBox componentObjectCheckBox2;
    protected CheckBox objectCheckBox3;
    protected CheckBox componentObjectCheckBox3;
    protected CheckBox objectCheckBox4;
    protected CheckBox componentObjectCheckBox4;

    protected AppCompatButton downloadButton;
    protected AppCompatButton detectButton;
    protected AppCompatButton saveButton;

    protected TextInputLayout objectPropertyNameTextInputLayout1;
    protected EditText objectPropertyNameEditText1;
    protected TextInputLayout objectPropertyExpectedTypeTextInputLayout1;
    protected EditText objectPropertyExpectedTypeEditText1;
    protected TextInputLayout objectPropertyDescriptionTextInputLayout1;
    protected EditText objectPropertyDescriptionEditText1;

    protected TextInputLayout objectPropertyNameTextInputLayout2;
    protected EditText objectPropertyNameEditText2;
    protected TextInputLayout objectPropertyExpectedTypeTextInputLayout2;
    protected EditText objectPropertyExpectedTypeEditText2;
    protected TextInputLayout objectPropertyDescriptionTextInputLayout2;
    protected EditText objectPropertyDescriptionEditText2;

    protected TextInputLayout objectPropertyNameTextInputLayout3;
    protected EditText objectPropertyNameEditText3;
    protected TextInputLayout objectPropertyExpectedTypeTextInputLayout3;
    protected EditText objectPropertyExpectedTypeEditText3;
    protected TextInputLayout objectPropertyDescriptionTextInputLayout3;
    protected EditText objectPropertyDescriptionEditText3;

    protected TextInputLayout objectPropertyNameTextInputLayout4;
    protected EditText objectPropertyNameEditText4;
    protected TextInputLayout objectPropertyExpectedTypeTextInputLayout4;
    protected EditText objectPropertyExpectedTypeEditText4;
    protected TextInputLayout objectPropertyDescriptionTextInputLayout4;
    protected EditText objectPropertyDescriptionEditText4;

    public DetectFragment() {
        super(R.string.navigation_fragment_detection, R.layout.fragment_detect);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public Graph createGraph(View View) {
        return null;
    }

    @Override
    public void setAlgorithm(GraphAdapter adapter) {

    }

    @Override
    protected void ready(View view) throws UnsupportedModuleException {
        setUp(view);
    }

    @Override
    protected void setUp(View view) {
        previewImageView = view.findViewById(R.id.previewImageView);

        TextView titleTextView = view.findViewById(R.id.title);
        titleTextView.setText("Results");

        TextView messageTextView = view.findViewById(R.id.message);
        messageTextView.setText("Objects detected in image.");

        objectCheckBox1 = view.findViewById(R.id.objectCheckBox1);
        componentObjectCheckBox1 = view.findViewById(R.id.componentObjectCheckBox1);

        objectCheckBox2 = view.findViewById(R.id.objectCheckBox2);
        componentObjectCheckBox2 = view.findViewById(R.id.componentObjectCheckBox2);

        objectCheckBox3 = view.findViewById(R.id.objectCheckBox3);
        componentObjectCheckBox3 = view.findViewById(R.id.componentObjectCheckBox3);

        objectCheckBox4 = view.findViewById(R.id.objectCheckBox4);
        componentObjectCheckBox4 = view.findViewById(R.id.componentObjectCheckBox4);

        downloadButton = view.findViewById(R.id.downloadButton);
        detectButton = view.findViewById(R.id.detectButton);
        saveButton = view.findViewById(R.id.saveButton);

        objectPropertyNameEditText1 = view.findViewById(R.id.objectPropertyNameEditText1);
        objectPropertyNameTextInputLayout1 = view.findViewById(R.id.objectPropertyNameTextInputLayout1);
        objectPropertyExpectedTypeEditText1 = view.findViewById(R.id.objectPropertyExpectedTypeEditText1);
        objectPropertyExpectedTypeTextInputLayout1 = view.findViewById(R.id.objectPropertyExpectedTypeTextInputLayout1);
        objectPropertyDescriptionEditText1 = view.findViewById(R.id.objectPropertyDescriptionEditText1);
        objectPropertyDescriptionTextInputLayout1 = view.findViewById(R.id.objectPropertyDescriptionTextInputLayout1);

        objectPropertyNameEditText2 = view.findViewById(R.id.objectPropertyNameEditText2);
        objectPropertyNameTextInputLayout2 = view.findViewById(R.id.objectPropertyNameTextInputLayout2);
        objectPropertyExpectedTypeEditText2 = view.findViewById(R.id.objectPropertyExpectedTypeEditText2);
        objectPropertyExpectedTypeTextInputLayout2 = view.findViewById(R.id.objectPropertyExpectedTypeTextInputLayout2);
        objectPropertyDescriptionEditText2 = view.findViewById(R.id.objectPropertyDescriptionEditText2);
        objectPropertyDescriptionTextInputLayout2 = view.findViewById(R.id.objectPropertyDescriptionTextInputLayout2);

        objectPropertyNameEditText3 = view.findViewById(R.id.objectPropertyNameEditText3);
        objectPropertyNameTextInputLayout3 = view.findViewById(R.id.objectPropertyNameTextInputLayout3);
        objectPropertyExpectedTypeEditText3 = view.findViewById(R.id.objectPropertyExpectedTypeEditText3);
        objectPropertyExpectedTypeTextInputLayout3 = view.findViewById(R.id.objectPropertyExpectedTypeTextInputLayout3);
        objectPropertyDescriptionEditText3 = view.findViewById(R.id.objectPropertyDescriptionEditText3);
        objectPropertyDescriptionTextInputLayout3 = view.findViewById(R.id.objectPropertyDescriptionTextInputLayout3);

        objectPropertyNameEditText4 = view.findViewById(R.id.objectPropertyNameEditText4);
        objectPropertyNameTextInputLayout4 = view.findViewById(R.id.objectPropertyNameTextInputLayout4);
        objectPropertyExpectedTypeEditText4 = view.findViewById(R.id.objectPropertyExpectedTypeEditText4);
        objectPropertyExpectedTypeTextInputLayout4 = view.findViewById(R.id.objectPropertyExpectedTypeTextInputLayout4);
        objectPropertyDescriptionEditText4 = view.findViewById(R.id.objectPropertyDescriptionEditText4);
        objectPropertyDescriptionTextInputLayout4 = view.findViewById(R.id.objectPropertyDescriptionTextInputLayout4);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBeginListener.onBegin();
            }
        });

        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                identifyImageContent();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUpdateMyMemoryListener.onUpdateMyMemory(dataObjects, objectProperties);

                objectCheckBox1.setChecked(false);
                componentObjectCheckBox1.setChecked(false);
                objectCheckBox2.setChecked(false);
                componentObjectCheckBox2.setChecked(false);
                objectCheckBox3.setChecked(false);
                componentObjectCheckBox3.setChecked(false);
                objectCheckBox4.setChecked(false);
                componentObjectCheckBox4.setChecked(false);

                objectPropertyNameEditText1.setText("");
                objectPropertyExpectedTypeEditText1.setText("");
                objectPropertyDescriptionEditText1.setText("");
                objectPropertyNameEditText2.setText("");
                objectPropertyExpectedTypeEditText2.setText("");
                objectPropertyDescriptionEditText2.setText("");
                objectPropertyNameEditText3.setText("");
                objectPropertyExpectedTypeEditText3.setText("");
                objectPropertyDescriptionEditText3.setText("");
                objectPropertyNameEditText4.setText("");
                objectPropertyExpectedTypeEditText4.setText("");
                objectPropertyDescriptionEditText4.setText("");
            }
        });
    }

    @Override
    protected void fillHelpOptionAdapter(HelpOptionAdapter adapter) {

    }

    @Override
    public void onImageDownload(Bitmap bitmap) {
        previewImageView.setImageBitmap(bitmap);
        UtilityDialogManager.getInstance(owner, null).dismiss();
    }

    private void identifyImageContent() {
        Bitmap bitmapForDetection = null;
        bitmapForDetection = createBitmap(((BitmapDrawable) previewImageView.getDrawable()).getBitmap());

        UtilityDialogManager.getInstance(owner, getString(R.string.message_detecting_objects)).show();

        if (bitmapForDetection != null) {
            //imageProcessor.process(bitmapForDetection);
        } else
            Toasty.error(owner, "bitmapForDetection is null...", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onSetDetectedItems(List<Wrapper> detectedItems) {
       for (int i = 0; i < detectedItems.size(); i++) {
            if (i == 0) {
                DataObject dataObject = new DataObject();
                ObjectProperty objectProperty = new ObjectProperty();
                objectPropertyNameEditText1.setText(String.valueOf(detectedItems.get(i).getConfidenceValue()));
                objectCheckBox1.setText(detectedItems.get(i).getName());
                int finalI3 = i;
                objectCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            dataObject.setName(detectedItems.get(finalI3).getName());
                            dataObject.setConfidenceValue(Float.parseFloat(objectPropertyNameEditText1.getText().toString()));
                            dataObjects.add(dataObject);
                            List<ObjectProperty> objectPropertyList = new ArrayList<>();
                            objectPropertyList.add(objectProperty);
                            objectProperties.add(objectPropertyList);
                        } else {
                            dataObjects.remove(dataObject);
                            objectProperties.get(0).remove(objectProperty);
                        }
                    }
                });

                componentObjectCheckBox1.setText("Is component object?");
                componentObjectCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true)
                            dataObject.setIsComponentObject(true);
                    }
                });

                objectProperty.setName(objectPropertyNameEditText1.getText().toString());
                objectProperty.setExpectedType(objectPropertyExpectedTypeEditText1.getText().toString());
                objectProperty.setDescription(objectPropertyDescriptionEditText1.getText().toString());
            }else if (i == 1) {
                DataObject dataObject = new DataObject();
                ObjectProperty objectProperty = new ObjectProperty();
                objectPropertyNameEditText2.setText(String.valueOf(detectedItems.get(i).getConfidenceValue()));
                objectCheckBox2.setText(detectedItems.get(i).getName());
                int finalI2 = i;
                objectCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            dataObject.setName(detectedItems.get(finalI2).getName());
                            dataObject.setConfidenceValue(Float.parseFloat(objectPropertyNameEditText2.getText().toString()));
                            dataObjects.add(dataObject);
                            List<ObjectProperty> objectPropertyList = new ArrayList<>();
                            objectPropertyList.add(objectProperty);
                            objectProperties.add(objectPropertyList);
                        } else {
                            dataObjects.remove(dataObject);
                            objectProperties.get(1).remove(objectProperty);
                        }
                    }
                });

                componentObjectCheckBox2.setText("Is component object?");
                componentObjectCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            dataObject.setIsComponentObject(true);
                        }
                    }
                });

                objectProperty.setName(objectPropertyNameEditText2.getText().toString());
                objectProperty.setExpectedType(objectPropertyExpectedTypeEditText2.getText().toString());
                objectProperty.setDescription(objectPropertyDescriptionEditText2.getText().toString());
            } else if (i == 2) {
                DataObject dataObject = new DataObject();
                ObjectProperty objectProperty = new ObjectProperty();
                objectPropertyNameEditText3.setText(String.valueOf(detectedItems.get(i).getConfidenceValue()));
                objectCheckBox3.setText(detectedItems.get(i).getName());
                int finalI1 = i;
                objectCheckBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            dataObject.setName(detectedItems.get(finalI1).getName());
                            dataObject.setConfidenceValue(Float.parseFloat(objectPropertyNameEditText3.getText().toString()));
                            dataObjects.add(dataObject);
                            List<ObjectProperty> objectPropertyList = new ArrayList<>();
                            objectPropertyList.add(objectProperty);
                            objectProperties.add(objectPropertyList);
                        } else {
                            dataObjects.remove(dataObject);
                            objectProperties.get(2).remove(objectProperty);
                        }
                    }
                });

                componentObjectCheckBox3.setText("Is component object?");
                componentObjectCheckBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true)
                            dataObject.setIsComponentObject(true);
                    }
                });

                objectProperty.setName(objectPropertyNameEditText3.getText().toString());
                objectProperty.setExpectedType(objectPropertyExpectedTypeEditText3.getText().toString());
                objectProperty.setDescription(objectPropertyDescriptionEditText3.getText().toString());
            }else if (i == 3) {
                DataObject dataObject = new DataObject();
                ObjectProperty objectProperty = new ObjectProperty();
                objectPropertyNameEditText4.setText(String.valueOf(detectedItems.get(i).getConfidenceValue()));
                objectCheckBox4.setText(detectedItems.get(i).getName());
                int finalI = i;
                objectCheckBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            dataObject.setName(detectedItems.get(finalI).getName());
                            dataObject.setConfidenceValue(Float.parseFloat(objectPropertyNameEditText4.getText().toString()));
                            dataObjects.add(dataObject);
                            List<ObjectProperty> objectPropertyList = new ArrayList<>();
                            objectPropertyList.add(objectProperty);
                            objectProperties.add(objectPropertyList);
                        } else {
                            dataObjects.remove(dataObject);
                            objectProperties.get(3).remove(objectProperty);
                        }
                    }
                });

                componentObjectCheckBox4.setText("Is component object?");
                componentObjectCheckBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true)
                            dataObject.setIsComponentObject(true);
                    }
                });

                objectProperty.setName(objectPropertyNameEditText4.getText().toString());
                objectProperty.setExpectedType(objectPropertyExpectedTypeEditText4.getText().toString());
                objectProperty.setDescription(objectPropertyDescriptionEditText4.getText().toString());
            }

            UtilityDialogManager.hideProgressDialog();
        }
    }

    public Bitmap createBitmap(Bitmap imageBitmap) {
        Bitmap bitmapForDetection;
        Pair<Integer, Integer> targetedSize = getTargetedWidthHeight();

        int targetWidth = targetedSize.first;
        int maxHeight = targetedSize.second;

        float scaleFactor =
                Math.max(
                        (float) imageBitmap.getWidth() / (float) targetWidth,
                        (float) imageBitmap.getHeight() / (float) maxHeight);

        Bitmap resizedBitmap =
                Bitmap.createScaledBitmap(
                        imageBitmap,
                        (int) (imageBitmap.getWidth() / scaleFactor),
                        (int) (imageBitmap.getHeight() / scaleFactor),
                        true);

        bitmapForDetection = resizedBitmap;

        return bitmapForDetection;
    }

    public Pair<Integer, Integer> getTargetedWidthHeight() {
        int targetWidth;
        int targetHeight;

        switch (Config.selectedSize) {
            case Config.SIZE_PREVIEW:
                int maxWidthForPortraitMode = getScreenWidth();
                int maxHeightForPortraitMode = getScreenHeight() - 60;
                targetWidth = maxWidthForPortraitMode;
                targetHeight = maxHeightForPortraitMode;
                break;
            default:
                throw new IllegalStateException("Unknown size");
        }

        return new Pair<>(targetWidth, targetHeight);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
