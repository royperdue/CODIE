package com.codie.simulation.ui.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.codie.simulation.R;
import com.codie.simulation.exception.UnsupportedModuleException;
import com.codie.simulation.ui.fragment.base.BaseFragment;
import com.codie.simulation.ui.help.HelpOptionAdapter;
import com.codie.simulation.util.Config;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.codie.simulation.ui.view.graphview.Graph;
import com.codie.simulation.ui.view.graphview.GraphAdapter;

import static android.app.Activity.RESULT_OK;


public class InstructFragment extends BaseFragment {
    protected Button buttonOpenGallery;
    protected ImageView imageView;
    private Uri selectedImage;
    private Bitmap selectedBitmap;

    public InstructFragment() {
        super(R.string.navigation_fragment_instruction, R.layout.fragment_instruct);
    }

    @Override
    protected void ready(View view) throws UnsupportedModuleException {

    }

    @Override
    protected void setUp(View view) {
        this.buttonOpenGallery = (Button) view.findViewById(R.id.buttonOpenGallery);
        this.buttonOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, Config.GALLERY_IMAGE_LOADED);
            }
        });

        this.imageView = view.findViewById(R.id.imageView);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.GALLERY_IMAGE_LOADED && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            this.loadImage();
        }
    }

    private void loadImage() {
        try {
            InputStream inputStream = owner.getContentResolver().openInputStream(this.selectedImage);

            selectedBitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(selectedBitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void fillHelpOptionAdapter(HelpOptionAdapter adapter) {

    }

    @Override
    public Graph createGraph(View View) {
        return null;
    }

    @Override
    public void setAlgorithm(GraphAdapter adapter) {

    }
}
