package vn.vnpt.ansv.bvbd11.common.injection.components;

import dagger.Component;
import vn.vnpt.ansv.bvbd11.common.injection.modules.ActivityModule;
import vn.vnpt.ansv.bvbd11.common.injection.scope.ActivityScope;
import vn.vnpt.ansv.bvbd11.ui.settings.SettingsActivity;
import vn.vnpt.ansv.bvbd11.ui.signin.SignInActivity;

/**
 * Created by ANSV on 11/29/2017.
 */
@ActivityScope
@Component(dependencies = BvbdComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SignInActivity o);
    void inject(SettingsActivity o);
}
