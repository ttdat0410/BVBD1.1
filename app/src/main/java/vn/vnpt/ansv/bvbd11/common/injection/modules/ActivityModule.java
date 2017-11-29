package vn.vnpt.ansv.bvbd11.common.injection.modules;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.vnpt.ansv.bvbd11.common.injection.qualifier.ForActivity;
import vn.vnpt.ansv.bvbd11.common.injection.scope.ActivityScope;

/**
 * Created by ANSV on 11/29/2017.
 */

@Module
@ActivityScope
public class ActivityModule {

    private Activity mActivity;
    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @Singleton
    @ForActivity
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @Singleton
    @ForActivity
    Context providesContexz() {
        return mActivity;
    }
}

