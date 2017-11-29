package vn.vnpt.ansv.bvbd11.common.injection.modules;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

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
    @Singleton
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mActivity;
    }
}

