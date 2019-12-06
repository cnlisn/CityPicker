package com.lisn.citypicker.adapter;

import com.lisn.citypicker.model.City;

public interface InnerListener {
    void dismiss(int position, City data);
    void locate();
}
