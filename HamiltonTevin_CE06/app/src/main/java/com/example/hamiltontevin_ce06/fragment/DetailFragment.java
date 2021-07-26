package com.example.hamiltontevin_ce06.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hamiltontevin_ce06.Contacts;
import com.example.hamiltontevin_ce06.R;

import java.util.Objects;

public class DetailFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_PERSON_INFO = "ARG_PERSON_INFO";

    public DetailFragment() {
    }

    public static DetailFragment newInstance(Contacts  person) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_PERSON_INFO, person);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getView() != null){
           Contacts person = (Contacts) Objects.requireNonNull(getArguments()).getSerializable(ARG_PERSON_INFO);
            TextView tv_firstName = getView().findViewById(R.id.firstName_display_tv);
            TextView tv_lastName = getView().findViewById(R.id.lastName_display_tv);
            TextView tv_phoneNumber = getView().findViewById(R.id.phoneNumber_display_tv);
            getView().findViewById(R.id.btn_back).setOnClickListener(this);
            if(person != null) {
                tv_firstName.setText(person.getFirstName());
                tv_lastName.setText(person.getLastName());
                String phoneNumberString = Integer.toString(person.getPhoneNumber());
                tv_phoneNumber.setText(phoneNumberString);
            }
        }
    }

    @Override
    public void onClick(View view) {
            Objects.requireNonNull(getFragmentManager()).popBackStack();
    }
}
