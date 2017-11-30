package vn.vnpt.ansv.bvbd11.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import vn.vnpt.ansv.bvbd11.common.injection.qualifier.ForApplication;
import vn.vnpt.ansv.bvbd11.utils.BvbdPreference;

/**
 * Created by ANSV on 11/30/2017.
 */
@Singleton
public class PreferenceHelper {

    private static final String PREFERENCE_KEY = "bvbd_pref_file";
    private static final String PREFERENCE_CONTENT = PreferenceHelper.class.getSimpleName() + "preferences";
    private final SharedPreferences sharedPreferences;
    private BvbdPreference bvbdPreference;

    @Inject
    public PreferenceHelper(@ForApplication Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
        this.bvbdPreference = getPreferences();
    }

    public BvbdPreference getPreferences() {
        if (bvbdPreference != null) {
            return bvbdPreference;

        } else {
            String jsonString = sharedPreferences.getString(PREFERENCE_CONTENT, null);
            if (jsonString == null) {
                return new BvbdPreference();
            } else {
                return new Gson().fromJson(jsonString, BvbdPreference.class);
            }
        }
    }

    public void setBvbdPreference(BvbdPreference bvbdPreference) {
        this.bvbdPreference = bvbdPreference;
        sharedPreferences.edit().putString(PREFERENCE_CONTENT, new Gson().toJson(bvbdPreference)).commit();
    }

    public void clear() {
        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().commit();
        }
    }

}
