package com.lisn.citypicker.adapter;

import com.lisn.citypicker.model.City;

public interface OnPickListener {
    void onPick(int position, City data);
    void onLocate();
    void onCancel();
}
