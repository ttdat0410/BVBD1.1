package vn.vnpt.ansv.bvbd11.common.injection.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import vn.vnpt.ansv.bvbd11.common.injection.qualifier.ForActivity;

/**
 * Created by ANSV on 11/29/2017.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ForActivity
    Context providesContext() {
        return mActivity;
    }
}

