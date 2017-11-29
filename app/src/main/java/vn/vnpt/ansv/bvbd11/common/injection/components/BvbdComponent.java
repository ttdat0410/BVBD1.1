package vn.vnpt.ansv.bvbd11.common.injection.components;

import dagger.Component;
import vn.vnpt.ansv.bvbd11.common.app.BvbdApplication;
import vn.vnpt.ansv.bvbd11.common.injection.modules.BvbdModule;

/**
 * Created by ANSV on 11/29/2017.
 */

@Component(modules = {BvbdModule.class})
public interface BvbdComponent {
    void inject(BvbdApplication o);
}
