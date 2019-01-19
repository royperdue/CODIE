package com.codie.simulation.ui.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codie.simulation.R;
import com.codie.simulation.exception.UnsupportedModuleException;
import com.codie.simulation.search.adapters.ImageResultArrayAdapter;
import com.codie.simulation.search.helpers.EndlessScrollListener;
import com.codie.simulation.search.models.ImageFilter;
import com.codie.simulation.search.models.ImageResult;
import com.codie.simulation.search.net.SearchClient;
import com.codie.simulation.ui.fragment.base.BaseFragment;
import com.codie.simulation.ui.help.HelpOptionAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import com.codie.simulation.ui.view.graphview.Graph;
import com.codie.simulation.ui.view.graphview.GraphAdapter;
import es.dmoral.toasty.Toasty;


public class SearchFragment extends BaseFragment {
    private static int MAX_PAGE = 10;
    private EditText queryEditText;
    private GridView resultsGridView;
    private AppCompatImageView searchButton;
    private AppCompatImageView uploadButton;
    private ImageView imageView;
    private TextView imageNameTextView;
    private ArrayList<ImageResult> imageResults;
    private ImageResultArrayAdapter imageAdapter;
    private ImageFilter imageFilter = new ImageFilter();
    private SearchClient client;
    private int startPage = 1;
    private String query;

    public SearchFragment() {
        super(R.string.navigation_fragment_gallery, R.layout.fragment_search);

    }

    @Override
    protected void ready(View view) throws UnsupportedModuleException {

    }

    @Override
    protected void setUp(View view) {
        imageView = view.findViewById(R.id.imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        imageNameTextView = view.findViewById(R.id.imageNameTextView);
        queryEditText = view.findViewById(R.id.queryEditText);
        resultsGridView = view.findViewById(R.id.resultsGridView);
        uploadButton = view.findViewById(R.id.uploadButton);
        searchButton = view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(v);
                onImageSearch(1);
            }
        });

        resultsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageResult imageResult = imageResults.get(position);
                displayResult(imageResult.getFullUrl(), imageResult.getTitle());
            }
        });

        resultsGridView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (page <= MAX_PAGE) {
                    onImageSearch((10 * (page - 1)) + 1);
                }
            }
        });

        imageResults = new ArrayList<>();
        imageAdapter = new ImageResultArrayAdapter(owner, imageResults);
        resultsGridView.setAdapter(imageAdapter);
    }

    public void onImageSearch(int start) {

        if (isNetworkAvailable()) {
            client = new SearchClient();
            query = queryEditText.getText().toString();
            startPage = start;
            if (startPage == 1)
                imageAdapter.clear();

            if (!query.equals(""))
                client.getSearch(query, startPage, imageFilter, owner, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    org.json.JSONArray imageJsonResults;
                                    if (response != null) {
                                        imageJsonResults = response.getJSONArray("items");
                                        imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(owner.getApplicationContext(), R.string.invalid_data, Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                super.onFailure(statusCode, headers, responseString, throwable);
                                Toast.makeText(owner.getApplicationContext(), R.string.service_unavailable, Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            else {
                Toast.makeText(owner, R.string.invalid_query, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(owner, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) owner.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void displayResult(String url, String title) {
        Glide.with(owner).load(url).into(imageView);
        imageNameTextView.setText(title);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareImageData();
            }
        });
    }

    private void prepareImageData() {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        showDialog(bytes);
    }

    private void showDialog(byte[] bytes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_enter_image_name, null);
        EditText imageNameEditText = view.findViewById(R.id.imageNameEditText);
        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String imageName = imageNameEditText.getText().toString();

                        if (imageName != null && imageName.length() > 0)
                            onImageUploadRequestListener.onImageUpload(bytes, "training_images", imageName);
                        else
                            Toasty.warning(owner, getString(R.string.enter_an_image_name), Toast.LENGTH_SHORT, true).show();
                    }
                })
                .setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
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
