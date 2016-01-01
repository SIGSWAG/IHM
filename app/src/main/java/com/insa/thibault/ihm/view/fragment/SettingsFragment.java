package com.insa.thibault.ihm.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import com.insa.thibault.ihm.R;

import butterknife.BindString;
import butterknife.ButterKnife;

public class SettingsFragment extends PreferenceFragmentCompat {

    @BindString(R.string.title_settings_fragment)
    protected String title;

    protected SharedPreferences.OnSharedPreferenceChangeListener listener;

    public static SettingsFragment newInstance(Bundle bundle){
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(bundle);

        return fragment;

    }
        /* autre part...
         SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
         String strUserName = SP.getString("username", "NA");
         boolean bAppUpdates = SP.getBoolean("applicationUpdates",false);
         String downloadType = SP.getString("downloadType","1");
         */

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
                EditTextPreference editTextPref = (EditTextPreference) findPreference("welcome_message");
                editTextPref.setSummary("Define the Welcome message to be shown. Actual is : " + sp.getString("welcome_message", "Empty"));
            }
        };
        prefs.registerOnSharedPreferenceChangeListener(listener);

        setPreferencesFromResource(R.xml.preferences, rootKey);
        SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
        EditTextPreference editTextPref = (EditTextPreference) findPreference("welcome_message");
        editTextPref
                .setSummary("Define the Welcome message to be shown. Actual is : " + sp.getString("welcome_message", "Empty"));
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View v = inflater.inflate(R.layout.fragment_settings, container, false);
//        //ButterKnife.bind(this, v);
//
//        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
//        return v;
//
//    }
}
