package com.codie.simulation.ui.fragment.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.codie.simulation.R;
import com.codie.simulation.exception.UnsupportedModuleException;
import com.codie.simulation.interfaces.OnBeginListener;
import com.codie.simulation.interfaces.OnImageUploadRequestListener;
import com.codie.simulation.interfaces.OnUpdateMyMemoryListener;
import com.codie.simulation.ui.activity.MainActivity;
import com.codie.simulation.ui.help.HelpOptionAdapter;
import com.codie.simulation.util.Config;

import java.util.Locale;

import com.codie.simulation.ui.view.graphview.Graph;
import com.codie.simulation.ui.view.graphview.GraphAdapter;


public abstract class BaseFragment extends Fragment {
    protected boolean ready = false;
    protected final int layoutId;
    protected int resourceId;
    protected static MainActivity owner;
    protected LinearLayout navigation;



    protected OnImageUploadRequestListener onImageUploadRequestListener;
    protected OnUpdateMyMemoryListener onUpdateMyMemoryListener;
    protected OnBeginListener onBeginListener;

    protected abstract void ready(View view) throws UnsupportedModuleException;

    protected abstract void setUp(View view);

    protected abstract void fillHelpOptionAdapter(HelpOptionAdapter adapter);

    public void showHelpDialog() {
        HelpOptionAdapter adapter = new HelpOptionAdapter(getContext(), R.id.config_help_list);
        fillHelpOptionAdapter(adapter);

        if (adapter.getCount() != 0) {
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setPositiveButton(R.string.label_ok, null)
                    .setView(R.layout.layout_config_help)
                    .create();
            dialog.show();
            ((ListView) dialog.findViewById(R.id.config_help_list)).setAdapter(adapter);

            adapter.notifyDataSetChanged();
        } else {
            new AlertDialog.Builder(getActivity())
                    .setPositiveButton(R.string.label_ok, null)
                    .setMessage(R.string.message_no_config)
                    .create().show();
        }
    }

    public BaseFragment(int resourceId, int layoutId) {
        this.resourceId = resourceId;
        this.layoutId = layoutId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        owner = (MainActivity) getActivity();
        if (!(owner instanceof OnImageUploadRequestListener)) {
            throw new ClassCastException(String.format(Locale.US, "%s %s", owner.toString(),
                    "OnImageUploadRequestListener"));
        }

        onImageUploadRequestListener = (OnImageUploadRequestListener) owner;
        onUpdateMyMemoryListener = (OnUpdateMyMemoryListener) owner;
        onBeginListener = (OnBeginListener) owner;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);

        View view = inflater.inflate(layoutId, container, false);

        setUp(view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigation = owner.findViewById(R.id.navigation);
        if (navigation != null) {
            if (navigation.getVisibility() == View.VISIBLE)
                navigation.setVisibility(View.GONE);
        }
        createImageProcessor();
        try {
            ready(view);
        } catch (UnsupportedModuleException e) {
            unsupportedModule();
        }
    }

    public void reconnected() {
    }

    protected void unsupportedModule() {
        new AlertDialog.Builder(getActivity()).setTitle(R.string.title_error)
                .setMessage(String.format("%s %s", getContext().getString(resourceId), getActivity().getString(R.string.error_unsupported_module)))
                .setCancelable(false)
                .setPositiveButton(R.string.label_ok, (dialog, id) -> enableDisableViewGroup((ViewGroup) getView(), false))
                .create()
                .show();
    }

    protected void enableDisableViewGroup(ViewGroup viewGroup, boolean enabled) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(enabled);
            if (view instanceof ViewGroup) {
                enableDisableViewGroup((ViewGroup) view, enabled);
            }
        }
    }

    protected void createImageProcessor() {
        switch (Config.selectedMode) {
            case Config.CLOUD_LABEL_DETECTION:

                break;
            default:
                throw new IllegalStateException("Unknown selectedMode: " + Config.selectedMode);
        }
    }

    public abstract Graph createGraph(View View);

    public abstract void setAlgorithm(GraphAdapter adapter);
}
