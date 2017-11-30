package vn.vnpt.ansv.bvbd11.ui.signin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.vnpt.ansv.bvbd11.BuildConfig;
import vn.vnpt.ansv.bvbd11.R;
import vn.vnpt.ansv.bvbd11.common.ui.base.BaseActivity;
import vn.vnpt.ansv.bvbd11.ui.settings.SettingsActivity;
import vn.vnpt.ansv.bvbd11.utils.Utils;

/**
 * Created by ANSV on 11/30/2017.
 */

public class SignInActivity extends BaseActivity implements SignInView {

    private static final String TAG = SignInActivity.class.getSimpleName();
    private static final int REQUEST_CODE_ENABLE_WIFI = 0;
    private static final int animationDuration = 300;
    private final LinearInterpolator linearInterpolator = new LinearInterpolator();

    @BindView(R.id.signintoolbar)
    Toolbar toolbar;
    @BindView(R.id.mm_vnpt_logo)
    ImageView mm_vnpt_logo;
    @BindView(R.id.bottom_panel)
    FrameLayout bottom_panel;
    @BindView(R.id.logo_block)
    LinearLayout logo_block;
    @BindView(R.id.version)
    TextView version;

    @Inject
    SignInPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        activityComponent().inject(this);
        ButterKnife.bind(this);
        presenter.attachView(this);
        checkNetworkAvailable(this);
        initToolbar();
        initItems();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showBottomView() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animateItems();
            }
        }, Utils.TIMER_SHOW_BOTTOM_VIEW);
    }

    @Override
    public void showLoading() {
        Log.i(TAG, "show loading...");
    }

    @Override
    public void hideLoading() {
        Log.i(TAG, "hide loading...");

    }

    private int countClick = 0;
    // OnClick
    @OnClick(R.id.version)
    public void launchSettingsActivity() {
        if (countClick >= 8) {
            countClick = 0;
            SettingsActivity.launch(this);

        } else {
            countClick++;
        }
    }

    // PRIVATE
    private void checkNetworkAvailable(Context context) {
        if (!Utils.isNetworkAvailable(context)) {
            Intent enableNetwork = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
            this.startActivityForResult(enableNetwork, REQUEST_CODE_ENABLE_WIFI);
        }
    }

    private void initToolbar() {
        if (Build.VERSION.SDK_INT < 23) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        } else {
            toolbar.setBackgroundColor(getColor(R.color.transparent));
        }
    }

    private void initItems() {
        toolbar.setVisibility(View.INVISIBLE);
        logo_block.setVisibility(View.INVISIBLE);
        bottom_panel.setVisibility(View.INVISIBLE);
        mm_vnpt_logo.setVisibility(View.VISIBLE);
        presenter.showBottomView();
        version.setText("version " + BuildConfig.VERSION_NAME);
    }

    private void animateItems() {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList<Animator> animatorList = new ArrayList<>();

        animatorList.add(mm_vnpt_logo.getVisibility() == View.VISIBLE ? animateLogo() : null);
        animatorList.add(toolbar.getVisibility() == View.INVISIBLE ? animateToolbar() : null);
        animatorList.add(logo_block.getVisibility() == View.INVISIBLE ? animateLogoBlock() : null);
        animatorList.add(bottom_panel.getVisibility() == View.INVISIBLE ? animateBottomPanel() : null);

        animatorSet.playTogether(animatorList);
        animatorSet.start();
    }

    private Animator animateLogo() {
        ObjectAnimator mmLogoAnimator = ObjectAnimator.ofFloat(mm_vnpt_logo, "alpha", 1.0f, 0.0f);
        mmLogoAnimator.setDuration(animationDuration);
        mmLogoAnimator.setInterpolator(linearInterpolator);
        mmLogoAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mm_vnpt_logo.setVisibility(View.INVISIBLE);
            }
        });
        return mmLogoAnimator;
    }

    private Animator animateToolbar() {
        toolbar.setVisibility(View.VISIBLE);
        ObjectAnimator toolbarAnimator = ObjectAnimator.ofFloat(toolbar, "alpha", 0.0f, 1.0f);
        toolbarAnimator.setDuration(animationDuration);
        toolbarAnimator.setInterpolator(linearInterpolator);
        return toolbarAnimator;
    }

    private Animator animateBottomPanel() {
        bottom_panel.setVisibility(View.VISIBLE);
        ObjectAnimator bottomPanelAnimator = ObjectAnimator.ofFloat(bottom_panel, "translationY", (float) bottom_panel.getHeight(), 0.0f);
        bottomPanelAnimator.setDuration(animationDuration);
        bottomPanelAnimator.setInterpolator(linearInterpolator);
        return bottomPanelAnimator;
    }

    private Animator animateLogoBlock() {
        logo_block.setVisibility(View.VISIBLE);
        ObjectAnimator hospitalLogoAnimator = ObjectAnimator.ofFloat(logo_block, "alpha", 0.0f, 1.0f);
        hospitalLogoAnimator.setDuration(animationDuration);
        hospitalLogoAnimator.setInterpolator(linearInterpolator);
        return hospitalLogoAnimator;
    }

}
