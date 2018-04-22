package com.davidnyangi.ccbrt;

/**
 * Created by Doorway on 4/22/2018.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_USERID = "userID";
    public static final String KEY_COUNCIL = "council";
    public static final String KEY_REGION = "region";
    public static final String KEY_DOB = "dob";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_WARD = "ward";
    public static final String KEY_MOBILE = "phonenumber";
    public static final String KEY_REFCOMPLETED = "refcompleted";
    public static final String KEY_REFSUBMITTED = "refsubmitted";
    public static final String KEY_REFNOTCOMPLETED = "refnotcompleted";
    public static final String KEY_USERTYPE = "usertype";
    public static final String KEY_SPECIALROLES = "specialRoles";
    public static final String KEY_OVCMANAGED = "ovcmanaged";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String Fname,String special, String email,String Lname, String username,String userid, String council,String region, String dob,String ward, String mobile,String completed, String notcompleted,String usertype, String submitted, String ovc){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, Fname);
        editor.putString(KEY_SPECIALROLES, special);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_LNAME, Lname);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_USERID, userid);
        editor.putString(KEY_COUNCIL, council);
        editor.putString(KEY_REGION, region);
        editor.putString(KEY_WARD, ward);
        editor.putString(KEY_DOB, dob);
        editor.putString(KEY_OVCMANAGED, ovc);
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(KEY_REFCOMPLETED, completed);
        editor.putString(KEY_REFNOTCOMPLETED, notcompleted);
        editor.putString(KEY_USERTYPE, usertype);
        editor.putString(KEY_REFSUBMITTED, submitted);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_SPECIALROLES, pref.getString(KEY_SPECIALROLES, null));
        user.put(KEY_LNAME, pref.getString(KEY_LNAME, null));
        user.put(KEY_USERID, pref.getString(KEY_USERID, null));
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        user.put(KEY_USERTYPE, pref.getString(KEY_USERTYPE, null));
        user.put(KEY_REFCOMPLETED, pref.getString(KEY_REFCOMPLETED, null));
        user.put(KEY_REFNOTCOMPLETED, pref.getString(KEY_REFNOTCOMPLETED, null));
        user.put(KEY_REFSUBMITTED, pref.getString(KEY_REFSUBMITTED, null));
        user.put(KEY_REGION, pref.getString(KEY_REGION, null));
        user.put(KEY_WARD, pref.getString(KEY_WARD, null));
        user.put(KEY_DOB, pref.getString(KEY_DOB, null));
        user.put(KEY_COUNCIL, pref.getString(KEY_COUNCIL, null));
        user.put(KEY_MOBILE, pref.getString(KEY_MOBILE, null));
        user.put(KEY_OVCMANAGED, pref.getString(KEY_OVCMANAGED, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}