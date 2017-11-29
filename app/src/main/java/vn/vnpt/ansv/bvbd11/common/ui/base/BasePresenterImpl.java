package vn.vnpt.ansv.bvbd11.common.ui.base;

import vn.vnpt.ansv.bvbd11.common.utils.exceptions.ViewNotAttachedException;

/**
 * Created by ANSV on 11/29/2017.
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T>{

    private T baseView;

    @Override
    public void attachView(T baseView) {
        this.baseView = baseView;
    }

    @Override
    public void detachView() {
        if (baseView != null) {
            baseView = null;
        }
    }

    private boolean isViewAttached() {
        return baseView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new ViewNotAttachedException();
        }
    }

}
