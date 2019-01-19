package com.codie.simulation.ui.fragment;


import android.view.View;

import com.codie.simulation.R;
import com.codie.simulation.exception.UnsupportedModuleException;
import com.codie.simulation.ui.fragment.base.BaseFragment;
import com.codie.simulation.ui.help.HelpOptionAdapter;

import com.codie.simulation.ui.view.graphview.Graph;
import com.codie.simulation.ui.view.graphview.GraphAdapter;


public class CommunicateFragment extends BaseFragment {

    public CommunicateFragment() {
        super(R.string.navigation_fragment_communication, R.layout.fragment_communicate);

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

    @Override
    public Graph createGraph(View View) {
        return null;
    }

    @Override
    public void setAlgorithm(GraphAdapter adapter) {

    }
}
