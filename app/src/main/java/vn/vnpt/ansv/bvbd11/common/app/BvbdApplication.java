package vn.vnpt.ansv.bvbd11.common.app;

import android.app.Application;
import android.content.Context;

import vn.vnpt.ansv.bvbd11.common.injection.components.BvbdComponent;
import vn.vnpt.ansv.bvbd11.common.injection.components.DaggerBvbdComponent;
import vn.vnpt.ansv.bvbd11.common.injection.modules.BvbdModule;

/**
 * Created by ANSV on 11/29/2017.
 */

public class BvbdApplication extends Application{

    private BvbdComponent bvbdComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (bvbdComponent == null) {
            bvbdComponent = DaggerBvbdComponent
                    .builder()
                    .bvbdModule(new BvbdModule(this))
                    .build();
        }
        bvbdComponent.inject(this);

    }

    public static BvbdApplication get(Context context) {
        return (BvbdApplication) context.getApplicationContext();
    }

    public BvbdComponent getBvbdComponent() {
        return bvbdComponent;
    }

    public void setBvbdComponent(BvbdComponent bvbdComponent) {
        this.bvbdComponent = bvbdComponent;
    }
}
