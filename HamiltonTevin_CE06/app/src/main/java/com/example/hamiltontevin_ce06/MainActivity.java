package com.example.hamiltontevin_ce06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.example.hamiltontevin_ce06.fragment.ContactListFragment;
import com.example.hamiltontevin_ce06.fragment.DetailFragment;
import com.example.hamiltontevin_ce06.fragment.FormFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddContactListener {

    private final ArrayList<String> displayContactsList = new ArrayList<>();
    private final ArrayList<Contacts> mainContactList = new ArrayList<>();
    FloatingActionButton fab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.animator.slide_in_left,0,0,R.animator.slide_out_right)
                        .replace(R.id.ContactLists_FrameLayout, FormFragment.newInstance(MainActivity.this))
                        .addToBackStack(null)
                        .commit();

                fab.setVisibility(View.GONE);
            }
        });
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int backStack = getSupportFragmentManager().getBackStackEntryCount();
                if(fab.getVisibility() == View.GONE && backStack == 0) {
                    fab.setVisibility(View.VISIBLE);
                }
            }
        });

        getSupportFragmentManager().beginTransaction().add(R.id.ContactLists_FrameLayout,
                ContactListFragment.newInstance(displayContactsList,mainContactList)).commit();
    }

    @Override
    public void addContact(Contacts person) {
        displayContactsList.add(person.toString());
        mainContactList.add(person);
    }

    @Override
    public void SelectedNumber(int index) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_left,0,0,R.animator.slide_out_right)
                .replace(R.id.ContactLists_FrameLayout, DetailFragment.newInstance(mainContactList.get(index)))
                .addToBackStack(null)
                .commit();
    }
}
