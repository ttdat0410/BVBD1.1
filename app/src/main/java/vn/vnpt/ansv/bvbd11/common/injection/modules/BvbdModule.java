package vn.vnpt.ansv.bvbd11.common.injection.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.vnpt.ansv.bvbd11.common.injection.qualifier.ForApplication;

/**
 * Created by ANSV on 11/29/2017.
 */

@Module
public class BvbdModule {

    protected final Application mApplication;

    public BvbdModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
     Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideContext() {
        return mApplication;
    }

}
