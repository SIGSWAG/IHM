package com.insa.thibault.ihm.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.RestaurantApplication;
import com.insa.thibault.ihm.adapter.ContactAdapter;
import com.insa.thibault.ihm.adapter.FriendAdapter;
import com.insa.thibault.ihm.adapter.InviteListener;
import com.insa.thibault.ihm.business.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by Thibault on 09/12/2015.
 */
public class FriendsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1 ;
    private static final String INVITATION_FRAGMENT = "invitation_fragment";
    @BindString(R.string.title_friends_fragment)
    protected String title;



    @Inject
    protected User currentUser;


    private List<User> contacts;

    // The column index for the _ID column
    private static final int CONTACT_ID_INDEX = 0;
    // The column index for the LOOKUP_KEY column
    private static final int LOOKUP_KEY_INDEX = 1;
    /*
    * Defines an array that contains column names to move from
    * the Cursor to the ListView.
    */
    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                    ContactsContract.Contacts.DISPLAY_NAME,
    };
    /*
     * Defines an array that contains resource ids for the layout views
     * that get the Cursor column contents. The id is pre-defined in
     * the Android framework, so it is prefaced with "android.R.id"
     */
    private final static int[] TO_IDS = {

            R.id.friend_name

    };
    // Define global mutable variables
    // Define a ListView object
    @Bind(R.id.list_friends)
    ListView mContactsList;
    // Define variables for the contact the user selects
    // The contact's _ID value
    long mContactId;
    // The contact's LOOKUP_KEY
    String mContactKey;
    // A content URI for the selected contact
    Uri mContactUri;
    // An adapter that binds the result Cursor to the ListView
    private ContactAdapter mContactAdapter;


    // Defines the text expression
    @SuppressLint("InlinedApi")
    private static final String SELECTION =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?" :
                    ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
    // Defines a variable for the search string
    private String mSearchString;
    // Defines the array to hold values that replace the ?
    private String[] mSelectionArgs = { mSearchString };

    @SuppressLint("InlinedApi")
    private static final String[] PROJECTION =
            {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    Build.VERSION.SDK_INT
                            >= Build.VERSION_CODES.HONEYCOMB ?
                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                            ContactsContract.Contacts.DISPLAY_NAME,

            };
    private InviteListener mCallback;
    private boolean isInvitationFragment;


    public static FriendsFragment newInstance(Bundle bundle, boolean isInvitationFragment){
        FriendsFragment fragment = new FriendsFragment();

        bundle.putBoolean(INVITATION_FRAGMENT, isInvitationFragment);
        fragment.setArguments(bundle);

        return fragment;

    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((RestaurantApplication) getActivity().getApplication()).getAppComponent().inject(this);



        if(getArguments() != null){

            isInvitationFragment = getArguments().getBoolean(INVITATION_FRAGMENT);

            if(isInvitationFragment) {
                try {
                    mCallback = (InviteListener) getContext();
                } catch (ClassCastException e) {
                    throw new ClassCastException(getContext().toString()
                            + " must implement InviteListener");
                }
            }
        }



        View v = inflater.inflate(R.layout.fragment_friends, container, false);
        ButterKnife.bind(this, v);



        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.
            Log.d("permission","contacts not granted");
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);

            // PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            //}
        } else{

            launchSearch();
        }





        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);


        return v;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
         /*
         * Makes search string into pattern and
         * stores it in the selection array
         */
        mSelectionArgs[0] = "%" + mSearchString + "%";
        // Starts the query
        return new CursorLoader(
                getActivity(),
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Put the result Cursor in the adapter for the ListView
        contacts = new ArrayList<>();
        contacts.addAll(currentUser.getFriends().values());
        while (data.moveToNext()) {
            String name = data.getString(2);
            if(!currentUser.getFriends().containsKey(name)){
                User user = new User(name, "",null, null,
                       null, null,
                       null, null);
                user.setAppUser(false);
                contacts.add(user);

            }
        }
        mContactAdapter.setContacts(contacts);
        mContactAdapter.notifyDataSetChanged();


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Delete the reference to the existing Cursor
        mContactAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchSearch();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    private void launchSearch(){
        // permission was granted, yay! Do the
        // contacts-related task you need to do.
        getLoaderManager().initLoader(0, null, this);
        contacts = new ArrayList<>();
        mContactAdapter =  new ContactAdapter(getActivity(),contacts, isInvitationFragment, mCallback);

        /**new SimpleCursorAdapter(
                getActivity(),
                R.layout.item_friend,
                null,
                FROM_COLUMNS, TO_IDS,
                0);*/
        // Sets the adapter for the ListView
        mContactsList.setAdapter(mContactAdapter);
        mContactsList.setOnItemClickListener(this);
    }


   
}
