package com.example.hamiltontevin_ce06.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.hamiltontevin_ce06.AddContactListener;
import com.example.hamiltontevin_ce06.Contacts;
import com.example.hamiltontevin_ce06.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactListFragment extends ListFragment{

    private static final String ARG_DISPLAY_LIST = "ARG_DISPLAY_LIST";
    private static final String ARG_CONTACT_LIST = "ARG_CONTACT_LIST";
    private AddContactListener listener;


    public ContactListFragment() {
    }

    public static ContactListFragment newInstance(ArrayList<String> addDisplayName, ArrayList<Contacts> addContactList) {

        Bundle args = new Bundle();
        args.putStringArrayList(ARG_DISPLAY_LIST,addDisplayName);
        args.putSerializable(ARG_CONTACT_LIST,addContactList);

        ContactListFragment fragment = new ContactListFragment();
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
        return inflater.inflate(R.layout.fragment_contact_list,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null && getView() != null){

            ArrayList<String> people  = getArguments().getStringArrayList(ARG_DISPLAY_LIST);
            if(people != null && getContext() != null){
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_expandable_list_item_1,
                        people
                );
                FloatingActionButton fab = getView().findViewById(R.id.fab1);
                if(fab != null){
                    if(fab.getVisibility() == View.GONE) {
                        fab.setVisibility(View.VISIBLE);
                    }
                }
                setListAdapter(adapter);
            }
        }
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int pos, long id) {
        listener.SelectedNumber(pos);
    }
}
