package vn.vnpt.ansv.bvbd11.common.injection.components;

import dagger.Component;
import vn.vnpt.ansv.bvbd11.common.injection.modules.ActivityModule;

/**
 * Created by ANSV on 11/29/2017.
 */

@Component(dependencies = {BvbdComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {
}
