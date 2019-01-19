package com.codie.simulation.ui.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.codie.simulation.R;
import com.codie.simulation.exception.UnsupportedModuleException;
import com.codie.simulation.ui.fragment.base.BaseFragment;
import com.codie.simulation.ui.help.HelpOptionAdapter;

import com.codie.simulation.ui.view.graphview.Graph;
import com.codie.simulation.ui.view.graphview.GraphAdapter;


public class LogFragment extends BaseFragment {
    private Switch loggingControl;
    private EditText loggingEditText;
    public LogFragment() {
        super(R.string.navigation_fragment_logging, R.layout.fragment_log);
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
        loggingEditText = view.findViewById(R.id.loggingEditText);
        loggingControl = view.findViewById(R.id.loggingControl);

        loggingControl.setOnCheckedChangeListener((compoundButton, checked) -> {
            if (checked) {

            } else {

            }
        });

    }

    @Override
    protected void fillHelpOptionAdapter(HelpOptionAdapter adapter) {

    }

    private String testDataBase() {
        String results = "";

        return results;
    }
}