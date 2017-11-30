package vn.vnpt.ansv.bvbd11.common.injection.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import vn.vnpt.ansv.bvbd11.common.app.BvbdApplication;
import vn.vnpt.ansv.bvbd11.common.injection.modules.BvbdModule;
import vn.vnpt.ansv.bvbd11.common.injection.qualifier.ForApplication;
import vn.vnpt.ansv.bvbd11.data.PreferenceHelper;

/**
 * Created by ANSV on 11/29/2017.
 */
@Singleton
@Component(modules = BvbdModule.class)
public interface BvbdComponent {

    @ForApplication
    Context context();
    Application application();
    PreferenceHelper preferenceHelper();

    void inject(BvbdApplication o);
}
