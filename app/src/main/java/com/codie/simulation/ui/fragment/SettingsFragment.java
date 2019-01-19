package com.codie.simulation.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codie.simulation.R;
import com.codie.simulation.exception.UnsupportedModuleException;
import com.codie.simulation.ui.fragment.base.BaseFragment;
import com.codie.simulation.ui.help.HelpOptionAdapter;

import com.codie.simulation.ui.view.graphview.Graph;
import com.codie.simulation.ui.view.graphview.GraphAdapter;


public class SettingsFragment extends BaseFragment {
    public SettingsFragment() {
        super(R.string.navigation_fragment_settings, R.layout.fragment_settings);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_settings, container, false);
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

    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    protected void fillHelpOptionAdapter(HelpOptionAdapter adapter) {

    }
}
