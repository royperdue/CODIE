package com.codie.simulation.interfaces;


import com.codie.simulation.data.model.DataObject;
import com.codie.simulation.data.model.ObjectProperty;

import java.util.List;


public interface OnUpdateMyMemoryListener {
    void onUpdateMyMemory(List<DataObject> dataObjects, List<List<ObjectProperty>> objectProperties);

}
