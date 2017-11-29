package vn.vnpt.ansv.bvbd11.common.ui.base;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vn.vnpt.ansv.bvbd11.common.app.BvbdApplication;
import vn.vnpt.ansv.bvbd11.common.injection.components.ActivityComponent;
import vn.vnpt.ansv.bvbd11.common.injection.components.DaggerActivityComponent;
import vn.vnpt.ansv.bvbd11.common.injection.modules.ActivityModule;

/**
 * Created by ANSV on 11/29/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent activityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent
                    .builder()
                    .activityModule(new ActivityModule(this))
                    .bvbdComponent(BvbdApplication.get(this).getBvbdComponent())
                    .build();
        }
        return activityComponent;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermisson(String permission) {
        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
                || (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
    }


}
