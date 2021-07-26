package com.example.hamiltontevin_ce06.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hamiltontevin_ce06.AddContactListener;
import com.example.hamiltontevin_ce06.Contacts;
import com.example.hamiltontevin_ce06.R;


public class FormFragment extends Fragment implements View.OnClickListener {
    private AddContactListener listener;
    private static Context mContext;
    private Toast mToast = null;

    public FormFragment() {
    }

    public static FormFragment newInstance(Context mainContext) {

        Bundle args = new Bundle();

        if(mainContext != null){
            mContext = mainContext;
        }

        FormFragment fragment = new FormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AddContactListener){
            listener = (AddContactListener)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getView() != null){
            (getView().findViewById(R.id.btn_add_contact)).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if(getView() != null) {
            String fName = ((EditText) getView().findViewById(R.id.et_firstname)).getText().toString();
            String lName = ((EditText) getView().findViewById(R.id.lastName_et)).getText().toString();
            String pNumber = ((EditText) getView().findViewById(R.id.phoneNumber_et)).getText().toString();
            if (!fName.isEmpty() && !lName.isEmpty() && !pNumber.isEmpty()) {
                if(pNumber.length() == 10){

                    if (getFragmentManager() != null) {

                        getFragmentManager().popBackStack();
                        int number = Integer.parseInt(pNumber);
                        Contacts contacts = new Contacts(fName, lName, number);
                        listener.addContact(contacts);
                    }
                }
                else {
                    if(mToast != null){
                        mToast.cancel();
                    }
                    mToast = Toast.makeText(mContext,R.string.wrongMaxNumber,Toast.LENGTH_SHORT);
                    mToast.show();
                }
            }
            else{
                if(mToast != null){
                    mToast.cancel();
                }
                mToast = Toast.makeText(mContext,R.string.wrongInput,Toast.LENGTH_SHORT);
                mToast.show();
            }

        }
    }
}
