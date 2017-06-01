package com.example.lzw.myapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import java.util.List;
import java.util.Set;

/**
 * Created by LZW on 2016/09/05.
 */
public class MainPreferenceActivity extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.headers,target);
    }


    public static class Frag1 extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener,
            SharedPreferences.OnSharedPreferenceChangeListener
    {
        private static EditTextPreference pkgPref;
        private static EditTextPreference emailPref;
        private static ListPreference listPref;
        private static MultiSelectListPreference pizzaPref;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.main);

            listPref=(ListPreference)findPreference("flight_sort_option");
            pkgPref=(EditTextPreference)findPreference("package_name_preference");
            pkgPref.setSummary(pkgPref.getText());
            emailPref=(EditTextPreference)findPreference("alert_email_address");
            emailPref.setSummary(emailPref.getText());
            pizzaPref=(MultiSelectListPreference)findPreference("pizza_toppings");
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            final String key=preference.getKey();
            if("package_name_preference".equals(key))
            {
                pkgPref.setSummary(newValue.toString());
            }else if("alert_email_address".equals(key))
            {
                emailPref.setSummary(newValue.toString());
            }else if("flight_sort_option".equals(key))
            {
                setFlightOptionsSummary(newValue.toString());
            }
            else if ("pizza_toppings".equals(key))
            {
                if(((Set<String>)newValue).size()>4)
                {
                    Toast.makeText(getActivity(),"Too many toppings. No more than 4 please",
                            Toast.LENGTH_LONG).show();
                    return false;
                }
            }

            return true;
        }

        @Override
        public void onResume() {
            super.onResume();
            listPref.setOnPreferenceChangeListener(this);
            setFlightOptionsSummary(null);
            pkgPref.setOnPreferenceChangeListener(this);
            pkgPref.setSummary(pkgPref.getText());
            emailPref.setOnPreferenceChangeListener(this);
            emailPref.setSummary(emailPref.getText());
            pizzaPref.setOnPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();

            listPref.setOnPreferenceChangeListener(null);
            pkgPref.setOnPreferenceChangeListener(null);
            emailPref.setOnPreferenceChangeListener(null);
            pizzaPref.setOnPreferenceChangeListener(null);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if("package_name_preference".equals(s))
            {
                pkgPref.setSummary(pkgPref.getText());
            }
            else if("alert_email_address".equals(s))
            {
                emailPref.setSummary(emailPref.getText());
            }
            else if("flight_sort_option".equals(s))
            {
                setFlightOptionsSummary(listPref.getValue());
            }
        }

        private void setFlightOptionsSummary(String newValue)
        {
            String setTo=newValue;
            if(setTo==null)
                setTo=listPref.getValue();
            String[] optionEntries=this.getResources().getStringArray(R.array.flight_sort_options);

            try
            {
                listPref.setSummary("Currently set to "+optionEntries[listPref.findIndexOfValue(setTo)]);
            }
            catch (Exception e)
            {
                listPref.setSummary("Preference error:unknown value of listPref :"+setTo );
            }
        }
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }

    public static class Frag2 extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.frag2);
        }
    }
}
