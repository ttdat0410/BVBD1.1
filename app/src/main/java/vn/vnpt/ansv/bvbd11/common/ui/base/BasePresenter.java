package vn.vnpt.ansv.bvbd11.common.ui.base;

/**
 * Created by ANSV on 11/30/2017.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V baseView);
    void detachView();

}
