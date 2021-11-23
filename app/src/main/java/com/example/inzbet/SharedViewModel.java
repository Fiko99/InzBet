package com.example.inzbet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Float> number = new MutableLiveData<>();

    public void setNumber(Float input) {
        number.setValue(input);
    }

    public LiveData<Float> getNumber() {
        return number;
    }
}
