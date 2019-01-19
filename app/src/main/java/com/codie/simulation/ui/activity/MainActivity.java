package com.codie.simulation.ui.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codie.simulation.MyApplication;
import com.codie.simulation.R;
import com.codie.simulation.data.location.LocationData;
import com.codie.simulation.data.location.church.SaintJosephParish;
import com.codie.simulation.data.location.library.ArmsLibrary;
import com.codie.simulation.data.location.monument.BridgeOfFlowers;
import com.codie.simulation.data.location.pub.VFW;
import com.codie.simulation.data.location.restaurant.BlueRockRestaurant;
import com.codie.simulation.data.location.restaurant.BucklandPizza;
import com.codie.simulation.data.location.restaurant.ShelburneFallsCoffeeRoasters;
import com.codie.simulation.data.location.school.BucklandShelburneRegionalElementary;
import com.codie.simulation.data.location.school.GreenfieldCommunityCollege;
import com.codie.simulation.data.location.store.AgwayFarmAndGarden;
import com.codie.simulation.data.location.store.BucklandNeighbors;
import com.codie.simulation.data.model.Action;
import com.codie.simulation.data.model.ActionDao;
import com.codie.simulation.data.model.DataObject;
import com.codie.simulation.data.model.DataObjectDao;
import com.codie.simulation.data.model.DateTime;
import com.codie.simulation.data.model.DateTimeDao;
import com.codie.simulation.data.model.Function;
import com.codie.simulation.data.model.FunctionDao;
import com.codie.simulation.data.model.Location;
import com.codie.simulation.data.model.LocationDao;
import com.codie.simulation.data.model.MyMemory;
import com.codie.simulation.data.model.MyMemoryDao;
import com.codie.simulation.data.model.ObjectProperty;
import com.codie.simulation.data.model.ObjectPropertyDao;
import com.codie.simulation.data.model.Parameter;
import com.codie.simulation.data.model.ParameterDao;
import com.codie.simulation.data.model.Pointer;
import com.codie.simulation.data.model.PointerDao;
import com.codie.simulation.data.model.TimeInterval;
import com.codie.simulation.data.model.TimeIntervalDao;
import com.codie.simulation.interfaces.OnBeginListener;
import com.codie.simulation.interfaces.OnImageDownloadRequestListener;
import com.codie.simulation.interfaces.OnImageUploadRequestListener;
import com.codie.simulation.interfaces.OnSetDetectedItemsListener;
import com.codie.simulation.interfaces.OnUpdateMyMemoryListener;
import com.codie.simulation.service.DownloadService;
import com.codie.simulation.service.LableDetectionTask;
import com.codie.simulation.service.UploadService;
import com.codie.simulation.ui.fragment.CommunicateFragment;
import com.codie.simulation.ui.fragment.DetectFragment;
import com.codie.simulation.ui.fragment.HomeFragment;
import com.codie.simulation.ui.fragment.InstructFragment;
import com.codie.simulation.ui.fragment.LogFragment;
import com.codie.simulation.ui.fragment.SearchFragment;
import com.codie.simulation.ui.fragment.SettingsFragment;
import com.codie.simulation.ui.fragment.base.BaseFragment;
import com.codie.simulation.util.Config;
import com.codie.simulation.util.PackageManagerUtils;
import com.codie.simulation.util.Wrapper;
import com.codie.simulation.util.manage.UtilityDialogManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import es.dmoral.toasty.Toasty;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnUpdateMyMemoryListener, OnImageUploadRequestListener, OnBeginListener {
    private static final String TAG = "Storage#MainActivity";

    private Fragment currentFragment = null;
    private OnImageDownloadRequestListener onImageDownloadRequestListener;
    private OnSetDetectedItemsListener onSetDetectedItemsListener;

    private BroadcastReceiver mBroadcastReceiver;
    private FirebaseAuth mAuth;

    private static LocationData locationData;

    private boolean loggedIn = false;
    private Uri mDownloadUrl = null;
    private Uri mFileUri = null;


    private final static Map<Integer, Class<? extends BaseFragment>> FRAGMENT_CLASSES;

    static {
        Map<Integer, Class<? extends BaseFragment>> tempMap = new LinkedHashMap<>();
        tempMap.put(R.id.nav_home, HomeFragment.class);
        tempMap.put(R.id.nav_communication, CommunicateFragment.class);
        tempMap.put(R.id.nav_detection, DetectFragment.class);
        tempMap.put(R.id.nav_instruction, InstructFragment.class);
        tempMap.put(R.id.nav_search, SearchFragment.class);
        tempMap.put(R.id.nav_logging, LogFragment.class);
        tempMap.put(R.id.nav_settings, SettingsFragment.class);
        FRAGMENT_CLASSES = Collections.unmodifiableMap(tempMap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive:" + intent);
                UtilityDialogManager.getInstance(MainActivity.this, null).dismiss();

                switch (intent.getAction()) {
                    case DownloadService.DOWNLOAD_COMPLETED:
                        break;
                    case DownloadService.DOWNLOAD_ERROR:
                        UtilityDialogManager.getInstance(MainActivity.this, "Download error").show();
                        break;
                    case UploadService.UPLOAD_COMPLETED:
                        Toasty.success(MainActivity.this, "Image upload complete!", Toast.LENGTH_SHORT, true).show();
                    case UploadService.UPLOAD_ERROR:
                        onUploadResultIntent(intent);
                        break;
                }
            }
        };


        setupViews(savedInstanceState);
    }

    private void setupViews(Bundle savedInstanceState) {
        checkLocationPermission();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> ((BaseFragment) currentFragment).showHelpDialog());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_home));
        } else {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, Config.FRAGMENT_KEY);
        }

        if (savedInstanceState != null) {
            mFileUri = savedInstanceState.getParcelable(Config.KEY_FILE_URI);
            mDownloadUrl = savedInstanceState.getParcelable(Config.KEY_DOWNLOAD_URL);
        }

        onNewIntent(getIntent());
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.hasExtra(UploadService.EXTRA_DOWNLOAD_URL)) {
            onUploadResultIntent(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(mBroadcastReceiver, DownloadService.getIntentFilter());
        manager.registerReceiver(mBroadcastReceiver, UploadService.getIntentFilter());
    }

    @Override
    public void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Config.KEY_FILE_URI, mFileUri);
        outState.putParcelable(Config.KEY_DOWNLOAD_URL, mDownloadUrl);

        if (currentFragment != null) {
            getSupportFragmentManager().putFragment(outState, Config.FRAGMENT_KEY, currentFragment);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            transaction.detach(currentFragment);
        }

        String fragmentTag = FRAGMENT_CLASSES.get(id).getCanonicalName();
        currentFragment = fragmentManager.findFragmentByTag(fragmentTag);

        if (currentFragment == null) {
            try {
                currentFragment = FRAGMENT_CLASSES.get(id).getConstructor().newInstance();

                if (fragmentTag.equals("com.codie.simulation.ui.fragment.DetectFragment")) {
                    onImageDownloadRequestListener = (OnImageDownloadRequestListener) currentFragment;
                    onSetDetectedItemsListener = (OnSetDetectedItemsListener) currentFragment;
                }
            } catch (Exception e) {
                throw new RuntimeException("Cannot instantiate fragment", e);
            }

            transaction.add(R.id.container, currentFragment, fragmentTag);
        }

        transaction.attach(currentFragment).commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Drawable loginIcon = getResources().getDrawable(R.drawable.ic_lock_outline_white_24dp);
        loginIcon.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        MenuItem menuItemLogin = menu.findItem(R.id.action_login);
        menuItemLogin.setIcon(loginIcon);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable loginIcon = getResources().getDrawable(R.drawable.ic_lock_outline_white_24dp);
        loginIcon.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        Drawable logoutIcon = getResources().getDrawable(R.drawable.ic_lock_open_white_24dp);
        logoutIcon.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        item.setIcon(this.loggedIn ? loginIcon : logoutIcon);
        item.setTitle(this.loggedIn ? R.string.log_in : R.string.log_out);
        if (!loggedIn) {
            signInAnonymously();
        } else if (loggedIn) {
            signOutAnonymously();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void signInAnonymously() {
        UtilityDialogManager.getInstance(MainActivity.this, getString(R.string.progress_auth)).show();
        mAuth.signInAnonymously()
                .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d(TAG, "signInAnonymously:SUCCESS");
                        Toasty.success(MainActivity.this, "Successful login!", Toast.LENGTH_SHORT, true).show();
                        loggedIn = true;
                        UtilityDialogManager.getInstance(MainActivity.this, null).dismiss();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e(TAG, "signInAnonymously:FAILURE", exception);
                        Toasty.error(MainActivity.this, "Login error...", Toast.LENGTH_SHORT, true).show();
                        UtilityDialogManager.getInstance(MainActivity.this, null).dismiss();
                    }
                });
    }

    private void signOutAnonymously() {
        FirebaseAuth.getInstance().signOut();
        loggedIn = false;
    }

    private void onUploadResultIntent(Intent intent) {
        mDownloadUrl = intent.getParcelableExtra(UploadService.EXTRA_DOWNLOAD_URL);
        mFileUri = intent.getParcelableExtra(UploadService.EXTRA_FILE_URI);
    }

    private int generateIndex() {
        return new Random().nextInt(10);
    }

    private LocationData initializeData(int index) {
        if (index == 0)
            return new BlueRockRestaurant();
        else if (index == 1)
            return new BucklandPizza();
        else if (index == 2)
            return new ShelburneFallsCoffeeRoasters();
        else if (index == 3)
            return new SaintJosephParish();
        else if (index == 4)
            return new ArmsLibrary();
        else if (index == 5)
            return new BridgeOfFlowers();
        else if (index == 6)
            return new VFW();
        else if (index == 7)
            return new BucklandShelburneRegionalElementary();
        else if (index == 8)
            return new GreenfieldCommunityCollege();
        else if (index == 9)
            return new AgwayFarmAndGarden();
        else
            return new BucklandNeighbors();
    }

    @TargetApi(23)
    private boolean checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.title_request_permission);
            builder.setMessage(R.string.permission_read_external_storage);
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(dialog -> requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Config.PERMISSION_REQUEST_READ_STORAGE));
            builder.show();
            return false;
        }
        return true;
    }

    @Override
    public void onUpdateMyMemory(List<DataObject> dataObjects, List<List<ObjectProperty>> objectProperties) {
        MyMemory myMemory = updateMyMemory(dataObjects, objectProperties);

        if (myMemory != null)
            Toasty.success(MainActivity.this, "Successful memory update!", Toast.LENGTH_SHORT, true).show();
    }

    private MyMemory updateMyMemory(List<DataObject> dataObjects, List<List<ObjectProperty>> objectProperties) {
        List<Long> dataObjectIdList = new ArrayList<>();
        List<Long> locationIdList = new ArrayList<>();
        List<Long> dateTimeIdList = new ArrayList<>();
        List<Long> pointerIdList = new ArrayList<>();
        List<Long> actionIdList = new ArrayList<>();
        List<Long> functionIdList = new ArrayList<>();
        List<Long> parameterIdList = new ArrayList<>();
        List<Long> timeIntervalIdList = new ArrayList<>();
        MyMemory myMemory = null;

        for (int i = 0; i < dataObjects.size(); i++) {
            DataObject dataObject = null;
            if (dataObjects.get(i).getObjectId() == null) {
                // actionId~, action~, pointerId~, pointer~, list pointers, list objectProperties~, list locations.
                dataObject = getDataObject(createDataObject(dataObjects.get(i)));
                dataObjectIdList.add(dataObject.getObjectId());
                Log.d("CODIE", "Inserted new dataObject-1, ID: " + dataObject.getObjectId());
            } else {
                dataObject = MyApplication.getDaoSession().getDataObjectDao().queryBuilder()
                        .where(DataObjectDao.Properties.ObjectId.eq(dataObjects.get(i).getObjectId()))
                        .list().get(0);
                dataObjectIdList.add(dataObject.getObjectId());
                dataObject.setNumberTimesEncountered(dataObject.getNumberTimesEncountered() + 1);
                Log.d("CODIE", "Inserted new dataObject-2, ID: " + dataObject.getObjectId());
            }

            // objectId~, dataObject~, timeIntervalId~, timeInterval~, list dataObjects~.
            Pointer pointer = getPointer(createPointer());
            pointer.setObjectId(dataObject.getObjectId());
            pointer.setCurrentDataObject(dataObject);
            pointerIdList.add(pointer.getPointerId());
            Log.d("CODIE", "Inserted new pointer, ID: " + pointer.getPointerId());
            MyApplication.getDaoSession().getPointerDao().update(pointer);

            // obj~, objId~, list dateTimes~.
            Location location = getLocation(createLocation());
            populateLocation(location);
            location.setObjectId(dataObject.getObjectId());
            location.setCurrentDataObject(dataObject);
            locationIdList.add(location.getLocationId());
            Log.d("CODIE", "Inserted new location, ID: " + location.getLocationId());
            MyApplication.getDaoSession().getLocationDao().update(location);

            // locationId~, location~, list actions~.
            DateTime dateTime = getDateTime(createDateTime());
            populateDateTime(dateTime);
            dateTime.setLocationId(location.getLocationId());
            dateTime.setLocation(location);
            dateTimeIdList.add(dateTime.getDateTimeId());
            Log.d("CODIE", "Inserted new location, ID: " + location.getLocationId());
            MyApplication.getDaoSession().getDateTimeDao().update(dateTime);

            // dateTimeId~, dateTime~, timeIntervalId~, timeInterval~, list dataObjects~, list functions~.
            Action action = getAction(createAction());
            action.setDateTimeId(dateTime.getDateTimeId());
            action.setDateTime(dateTime);
            actionIdList.add(action.getActionId());
            Log.d("CODIE", "Inserted new action, ID: " + action.getActionId());
            MyApplication.getDaoSession().getActionDao().update(action);

            // actionId~, action~, list parameters~.
            Function function = getFunction(createFunction());
            function.setActionId(action.getActionId());
            function.setAction(action);
            functionIdList.add(function.getFunctionId());
            Log.d("CODIE", "Inserted new function, ID: " + function.getFunctionId());
            MyApplication.getDaoSession().getFunctionDao().update(function);

            // functionId~, function~.
            Parameter parameter = getParameter(createParameter());
            parameter.setFunctionId(function.getFunctionId());
            parameter.setFunction(function);
            parameterIdList.add(parameter.getParameterId());
            Log.d("CODIE", "Inserted new parameter, ID: " + parameter.getParameterId());
            MyApplication.getDaoSession().getParameterDao().update(parameter);

            dataObject.setActionId(action.getActionId());
            dataObject.setAction(action);
            dataObject.setPointerId(pointer.getPointerId());
            dataObject.setPointer(pointer);

            updateListObjectPropertiesDataObject(objectProperties.get(i), dataObject);

            MyApplication.getDaoSession().getDataObjectDao().update(dataObject);
        }

        // list pointers~, list actions~.
        TimeInterval timeInterval = getTimeInterval(createTimeInterval());

        for (int i = 0; i < pointerIdList.size(); i++) {
            updateListDataObjectsPointer(dataObjectIdList, pointerIdList.get(i));
        }

        for (int i = 0; i < actionIdList.size(); i++) {
            updateListDataObjectsAction(dataObjectIdList, actionIdList.get(i));
        }

        for (int i = 0; i < locationIdList.size(); i++) {
            updateListDateTimesLocation(dateTimeIdList, locationIdList.get(i));
        }

        for (int i = 0; i < dateTimeIdList.size(); i++) {
            updateListActionsDateTime(actionIdList, dateTimeIdList.get(i));
        }

        for (int i = 0; i < actionIdList.size(); i++) {
            updateListFunctionsAction(functionIdList, actionIdList.get(i));
        }

        for (int i = 0; i < functionIdList.size(); i++) {
            updateListParametersFunction(parameterIdList, functionIdList.get(i));
        }

        for (int i = 0; i < timeIntervalIdList.size(); i++) {
            updateListPointerListActionForTimeInterval(pointerIdList, actionIdList, dateTimeIdList.get(i), timeInterval);
        }

        for (int i = 0; i < dataObjectIdList.size(); i++) {
            updateListPointersDataObject(pointerIdList, dataObjectIdList.get(i));
        }

        for (int i = 0; i < dataObjectIdList.size(); i++) {
            updateListLocationsDataObject(locationIdList, dataObjectIdList.get(i));
        }

        List<MyMemory> myMemories = MyApplication.getDaoSession().getMyMemoryDao().queryBuilder().list();

        if (myMemories.size() > 0)
            myMemory = myMemories.get(0);
        else
            myMemory = getMyMemory(createMyMemory());

        if (myMemory != null) {
            updateListTimeIntervalsMyMemory(timeInterval, myMemory);
        }

        return myMemory;
    }

    //===============================================

    private long createDataObject(DataObject dataObject) {
        MyApplication.getDaoSession().getDataObjectDao().insert(dataObject);
        long dataObjectId = dataObject.getObjectId();

        return dataObjectId;
    }

    private DataObject getDataObject(long dataObjectId) {
        return MyApplication.getDaoSession().getDataObjectDao().queryBuilder()
                .where(DataObjectDao.Properties.ObjectId.eq(dataObjectId))
                .list().get(0);
    }

    private long createPointer() {
        Pointer pointer = new Pointer();
        pointer.setAssociatedObjectRatingValue(new Long(1));

        MyApplication.getDaoSession().getPointerDao().insert(pointer);
        long pointerId = pointer.getPointerId();

        return pointerId;
    }

    private Pointer getPointer(long pointerId) {
        return MyApplication.getDaoSession().getPointerDao().queryBuilder()
                .where(PointerDao.Properties.PointerId.eq(pointerId))
                .list().get(0);
    }

    private long createLocation() {
        Location location = new Location();
        MyApplication.getDaoSession().getLocationDao().insert(location);
        long locationId = location.getLocationId();

        return locationId;
    }

    private Location getLocation(long locationId) {
        return MyApplication.getDaoSession().getLocationDao().queryBuilder()
                .where(LocationDao.Properties.LocationId.eq(locationId))
                .list().get(0);
    }

    private long createAction() {
        Action action = new Action();

        MyApplication.getDaoSession().getActionDao().insert(action);
        long actionId = action.getActionId();

        return actionId;
    }

    private Action getAction(long actionId) {
        return MyApplication.getDaoSession().getActionDao().queryBuilder()
                .where(ActionDao.Properties.ActionId.eq(actionId))
                .list().get(0);
    }

    private long createDateTime() {
        DateTime dateTime = new DateTime();
        MyApplication.getDaoSession().getDateTimeDao().insert(dateTime);
        long dateTimeId = dateTime.getDateTimeId();

        return dateTimeId;
    }

    private DateTime getDateTime(long dateTimeId) {
        return MyApplication.getDaoSession().getDateTimeDao().queryBuilder()
                .where(DateTimeDao.Properties.DateTimeId.eq(dateTimeId))
                .list().get(0);
    }

    private long createFunction() {
        Function function = new Function();
        MyApplication.getDaoSession().getFunctionDao().insert(function);
        long functionId = function.getFunctionId();

        return functionId;
    }

    private Function getFunction(long functionId) {
        return MyApplication.getDaoSession().getFunctionDao().queryBuilder()
                .where(FunctionDao.Properties.FunctionId.eq(functionId))
                .list().get(0);
    }

    private long createParameter() {
        Parameter parameter = new Parameter();
        MyApplication.getDaoSession().getParameterDao().insert(parameter);
        long parameterId = parameter.getParameterId();

        return parameterId;
    }

    private Parameter getParameter(long parameterId) {
        return MyApplication.getDaoSession().getParameterDao().queryBuilder()
                .where(ParameterDao.Properties.ParameterId.eq(parameterId))
                .list().get(0);
    }

    private long createTimeInterval() {
        TimeInterval timeInterval = new TimeInterval();
        MyApplication.getDaoSession().getTimeIntervalDao().insert(timeInterval);
        long timeIntervalId = timeInterval.getTimeIntervalId();

        return timeIntervalId;
    }

    private TimeInterval getTimeInterval(long timeIntervalId) {
        return MyApplication.getDaoSession().getTimeIntervalDao().queryBuilder()
                .where(TimeIntervalDao.Properties.TimeIntervalId.eq(timeIntervalId))
                .list().get(0);
    }

    private long createMyMemory() {
        return MyApplication.getDaoSession().getMyMemoryDao().insert(new MyMemory());
    }

    private MyMemory getMyMemory(long myMemoryId) {
        return MyApplication.getDaoSession().getMyMemoryDao().queryBuilder()
                .where(MyMemoryDao.Properties.MyMemoryId.eq(myMemoryId))
                .list().get(0);
    }

    private MyMemory getMyMemory() {
        return MyApplication.getDaoSession().getMyMemoryDao().queryBuilder().list().get(0);
    }

    //===========================================

    private void updateListObjectPropertiesDataObject(List<ObjectProperty> objectProperties, DataObject dataObject) {
        List<ObjectProperty> objectPropertyList = dataObject.getObjectProperties();

        for (int i = 0; i < objectProperties.size(); i++) {
            ObjectProperty objectProperty = objectProperties.get(i);
            objectProperty.setObjectId(dataObject.getObjectId());
            objectProperty.setCurrentDataObject(dataObject);

            if (objectProperty.getObjectPropertyId() == null)
                MyApplication.getDaoSession().getObjectPropertyDao().insert(objectProperty);
            else
                MyApplication.getDaoSession().getObjectPropertyDao().update(objectProperty);

            objectPropertyList.add(objectProperty);
        }

        dataObject.resetObjectProperties();
    }

    private void updateListPointersDataObject(List<Long> pointerIds, Long dataObjectId) {
        DataObject dataObject = getDataObject(dataObjectId);
        List<Pointer> pointerList = dataObject.getPointers();

        for (int i = 0; i < pointerIds.size(); i++) {
            Pointer pointer = getPointer(pointerIds.get(i));
            pointer.setObjectId(dataObjectId);
            pointer.setCurrentDataObject(dataObject);

            if (pointer.getPointerId() == null)
                MyApplication.getDaoSession().getPointerDao().insert(pointer);
            else
                MyApplication.getDaoSession().getPointerDao().update(pointer);

            pointerList.add(pointer);
        }

        dataObject.resetPointers();
    }

    private void updateListLocationsDataObject(List<Long> locationIds, Long dataObjectId) {
        DataObject dataObject = getDataObject(dataObjectId);
        List<Location> locationList = dataObject.getLocations();

        for (int i = 0; i < locationIds.size(); i++) {
            Location location = getLocation(locationIds.get(i));
            location.setObjectId(dataObjectId);
            location.setCurrentDataObject(dataObject);

            if (location.getLocationId() == null)
                MyApplication.getDaoSession().getLocationDao().insert(location);
            else
                MyApplication.getDaoSession().getLocationDao().update(location);

            locationList.add(location);
        }

        dataObject.resetLocations();
    }

    private void updateListDataObjectsPointer(List<Long> dataObjectIds, Long pointerId) {
        Pointer pointer = getPointer(pointerId);
        List<DataObject> dataObjectList = pointer.getDataObjects();

        for (int i = 0; i < dataObjectIds.size(); i++) {
            DataObject dataObject = getDataObject(dataObjectIds.get(i));
            dataObject.setPointerId(pointerId);
            dataObject.setPointer(pointer);

            if (dataObject.getObjectId() == null)
                MyApplication.getDaoSession().getDataObjectDao().insert(dataObject);
            else
                MyApplication.getDaoSession().getDataObjectDao().update(dataObject);

            dataObjectList.add(dataObject);
        }

        pointer.resetDataObjects();
    }

    private void updateListDateTimesLocation(List<Long> dateTimeIds, Long locationId) {
        Location location = getLocation(locationId);
        List<DateTime> dateTimeList = location.getDateTimes();

        for (int i = 0; i < dateTimeIds.size(); i++) {
            DateTime dateTime = getDateTime(dateTimeIds.get(i));
            dateTime.setLocationId(locationId);
            dateTime.setLocation(location);

            if (dateTime.getDateTimeId() == null)
                MyApplication.getDaoSession().getDateTimeDao().insert(dateTime);
            else
                MyApplication.getDaoSession().getDateTimeDao().update(dateTime);

            dateTimeList.add(dateTime);
        }

        location.resetDateTimes();
    }

    private void updateListActionsDateTime(List<Long> actionIds, Long dateTimeId) {
        DateTime dateTime = getDateTime(dateTimeId);
        List<Action> actionList = dateTime.getActions();

        for (int i = 0; i < actionIds.size(); i++) {
            Action action = getAction(actionIds.get(i));
            action.setDateTimeId(dateTimeId);
            action.setDateTime(dateTime);

            if (action.getActionId() == null)
                MyApplication.getDaoSession().getActionDao().insert(action);
            else
                MyApplication.getDaoSession().getActionDao().update(action);

            actionList.add(action);
        }

        dateTime.resetActions();
    }

    private void updateListDataObjectsAction(List<Long> dataObjectIds, Long actionId) {
        Action action = getAction(actionId);
        List<DataObject> dataObjectList = action.getDataObjects();

        for (int i = 0; i < dataObjectIds.size(); i++) {
            DataObject dataObject = getDataObject(dataObjectIds.get(i));
            dataObject.setActionId(actionId);
            dataObject.setAction(action);

            if (dataObject.getObjectId() == null)
                MyApplication.getDaoSession().getDataObjectDao().insert(dataObject);
            else
                MyApplication.getDaoSession().getDataObjectDao().update(dataObject);

            dataObjectList.add(dataObject);
        }

        action.resetDataObjects();
    }

    private void updateListFunctionsAction(List<Long> functionIds, Long actionId) {
        Action action = getAction(actionId);
        List<Function> functionList = action.getFunctions();

        for (int i = 0; i < functionIds.size(); i++) {
            Function function = getFunction(functionIds.get(i));
            function.setActionId(actionId);
            function.setAction(action);

            if (function.getFunctionId() == null)
                MyApplication.getDaoSession().getFunctionDao().insert(function);
            else
                MyApplication.getDaoSession().getFunctionDao().update(function);

            functionList.add(function);
        }

        action.resetFunctions();
    }

    private void updateListParametersFunction(List<Long> parameterIds, Long functionId) {
        Function function = getFunction(functionId);
        List<Parameter> parameterList = function.getParameters();

        for (int i = 0; i < parameterIds.size(); i++) {
            Parameter parameter = getParameter(parameterIds.get(i));
            parameter.setFunctionId(functionId);
            parameter.setFunction(function);

            if (parameter.getParameterId() == null)
                MyApplication.getDaoSession().getParameterDao().insert(parameter);
            else
                MyApplication.getDaoSession().getParameterDao().update(parameter);

            parameterList.add(parameter);
        }

        function.resetParameters();
    }

    private void updateListPointerListActionForTimeInterval(List<Long> pointerIds, List<Long> actionIds, Long dateTimeId, TimeInterval timeInterval) {
        List<Pointer> pointerList = timeInterval.getPointers();
        List<Action> actionList = timeInterval.getActions();

        for (int i = 0; i < pointerIds.size(); i++) {
            Pointer pointer = getPointer(pointerIds.get(i));
            pointer.setTimeIntervalId(timeInterval.getTimeIntervalId());
            pointer.setTimeInterval(timeInterval);

            if (pointer.getPointerId() == null)
                MyApplication.getDaoSession().getPointerDao().insert(pointer);
            else
                MyApplication.getDaoSession().getPointerDao().update(pointer);

            pointerList.add(pointer);
        }

        timeInterval.resetPointers();

        for (int i = 0; i < actionIds.size(); i++) {
            Action action = getAction(actionIds.get(i));
            action.setTimeIntervalId(timeInterval.getTimeIntervalId());
            action.setTimeInterval(timeInterval);

            if (action.getActionId() == null)
                MyApplication.getDaoSession().getActionDao().insert(action);
            else
                MyApplication.getDaoSession().getActionDao().update(action);

            actionList.add(action);
        }

        timeInterval.resetActions();

        if (timeInterval.getDateTime() != null) {
            timeInterval.setDateTimeId(dateTimeId);
            timeInterval.setDateTime(getDateTime(dateTimeId));

            if (timeInterval.getTimeIntervalId() == null)
                MyApplication.getDaoSession().getTimeIntervalDao().insert(timeInterval);
            else
                MyApplication.getDaoSession().getTimeIntervalDao().update(timeInterval);
        }
    }

    private void updateListTimeIntervalsMyMemory(TimeInterval timeInterval, MyMemory myMemory) {
        List<TimeInterval> timeIntervalList = myMemory.getTimeIntervals();

        if (timeInterval.getTimeIntervalId() == null)
            MyApplication.getDaoSession().getTimeIntervalDao().insert(timeInterval);
        else
            MyApplication.getDaoSession().getTimeIntervalDao().update(timeInterval);

        timeIntervalList.add(timeInterval);

        myMemory.resetTimeIntervals();
    }

    //==============================================================

    private void populateLocation(Location location) {
        location.setCreationTime(getTime());
        location.setLocationType(locationData.getLocationType());
        location.setLocationName(locationData.getLocationName());
        location.setDescription(locationData.getDescription());
        location.setLatitude(locationData.getLatitude());
        location.setLongitude(locationData.getLongitude());
        location.setPhoneNumber(locationData.getPhoneNumber());
        location.setLocationBuildingNumber(locationData.getLocationBuildingNumber());
        location.setLocationStreet(locationData.getLocationStreet());
        location.setLocationState(locationData.getLocationState());
        location.setLocationTown(locationData.getLocationTown());
        location.setLocationZipCode(locationData.getLocationZipCode());
        location.setLocationCountry(locationData.getLocationCountry());
        location.setRatingValue(new Long(1));

        MyApplication.getDaoSession().getLocationDao().update(location);
    }

    private void populateDateTime(DateTime dateTime) {
        dateTime.setTime(getTime());
        dateTime.setDay(getDay());
        dateTime.setMonth(getMonth());
        dateTime.setYear(getYear());

        MyApplication.getDaoSession().getDateTimeDao().update(dateTime);
    }

    //=================================================================

    private void clearSession() {
        MyApplication.getDaoSession().clear();
    }

    private String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    private String getDay() {
        return String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    private String getMonth() {
        return String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
    }

    private String getYear() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR) % 100);
    }

    //==============================================================

    @Override
    public void onImageUpload(byte[] bytes, String folderName, String imageName) {
        startService(new Intent(this, UploadService.class)
                .putExtra(UploadService.FOLDER_NAME, folderName)
                .putExtra(UploadService.IMAGE_NAME, imageName)
                .putExtra(UploadService.IMAGE_BYTE_ARRAY, bytes)
                .setAction(UploadService.ACTION_UPLOAD));
        UtilityDialogManager.showProgressDialog(getString(R.string.progress_uploading));
    }

    private Vision.Images.Annotate prepareAnnotationRequest(Bitmap bitmap) throws IOException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        VisionRequestInitializer requestInitializer =
                new VisionRequestInitializer(Config.CLOUD_VISION_API_KEY) {
                    /**
                     * We override this so we can inject important identifying fields into the HTTP
                     * headers. This enables use of a restricted cloud platform API key.
                     */
                    @Override
                    protected void initializeVisionRequest(VisionRequest<?> visionRequest)
                            throws IOException {
                        super.initializeVisionRequest(visionRequest);

                        String packageName = getPackageName();
                        visionRequest.getRequestHeaders().set(Config.ANDROID_PACKAGE_HEADER, packageName);

                        String sig = PackageManagerUtils.getSignature(getPackageManager(), packageName);

                        visionRequest.getRequestHeaders().set(Config.ANDROID_CERT_HEADER, sig);
                    }
                };

        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        builder.setVisionRequestInitializer(requestInitializer);

        Vision vision = builder.build();

        BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                new BatchAnnotateImagesRequest();
        batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

            // Add the image
            Image base64EncodedImage = new Image();
            // Convert the bitmap to a JPEG
            // Just in case it's a format that Android understands but Cloud Vision
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Base64 encode the JPEG
            base64EncodedImage.encodeContent(imageBytes);
            annotateImageRequest.setImage(base64EncodedImage);

            // add the features we want
            annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                Feature labelDetection = new Feature();
                labelDetection.setType("LABEL_DETECTION");
                labelDetection.setMaxResults(Config.MAX_LABEL_RESULTS);
                add(labelDetection);
            }});

            // Add the list of one thing to the request
            add(annotateImageRequest);
        }});

        Vision.Images.Annotate annotateRequest =
                vision.images().annotate(batchAnnotateImagesRequest);
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotateRequest.setDisableGZipContent(true);
        Log.d(TAG, "created Cloud Vision request object, sending request");

        return annotateRequest;
    }

    private void callCloudVision(final Bitmap bitmap) {
        try {
            AsyncTask<Object, Void, List<Wrapper>> labelDetectionTask = new LableDetectionTask(this, prepareAnnotationRequest(bitmap));
            labelDetectionTask.execute();
        } catch (IOException e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
    }

    public void setDetectionResultItems(List<Wrapper> detectedItems) {
        onSetDetectedItemsListener.onSetDetectedItems(detectedItems);
    }

    @Override
    public void onBegin() {
        if (loggedIn == true) {
            locationData = initializeData(generateIndex());
            FirebaseStorage storage = FirebaseStorage.getInstance();

            StorageReference storageRef = storage.getReference();
            storageRef.child(locationData.getRandomLocationImage()).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    callCloudVision(bitmap);
                    onImageDownloadRequestListener.onImageDownload(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            });

            UtilityDialogManager.getInstance(MainActivity.this, getString(R.string.progress_downloading)).show();
        } else
            Toasty.warning(MainActivity.this, "You need to login...", Toast.LENGTH_SHORT, true).show();
    }
}
