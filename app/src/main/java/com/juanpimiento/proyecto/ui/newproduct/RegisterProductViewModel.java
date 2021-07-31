package com.juanpimiento.proyecto.ui.newproduct;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterProductViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RegisterProductViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
