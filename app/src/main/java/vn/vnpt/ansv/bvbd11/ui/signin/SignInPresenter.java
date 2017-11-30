package vn.vnpt.ansv.bvbd11.ui.signin;

import javax.inject.Inject;

import vn.vnpt.ansv.bvbd11.common.ui.base.BasePresenter;

/**
 * Created by ANSV on 11/30/2017.
 */

public class SignInPresenter implements BasePresenter<SignInView> {

    private static final String TAG = SignInPresenter.class.getSimpleName();

    private SignInView signInView;
    @Inject
    public SignInPresenter() {

    }

    @Override
    public void attachView(SignInView baseView) {
        this.signInView = baseView;
    }

    @Override
    public void detachView() {

    }

    /////
    protected void showBottomView(){
        signInView.showBottomView();
    }
}
