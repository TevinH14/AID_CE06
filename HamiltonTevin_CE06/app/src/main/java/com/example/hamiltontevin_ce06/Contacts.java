package com.example.hamiltontevin_ce06;

import java.io.Serializable;

public class Contacts implements Serializable {
    private final String mFirstName;
    private final String mLastName;
    private final int mPhoneNumber;

    public Contacts(String firstName, String lastName, int phoneNumber) {
        mFirstName = firstName;
        mLastName = lastName;
        mPhoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  mFirstName + " "+mLastName  ;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public int getPhoneNumber() {
        return  mPhoneNumber;
    }
}
