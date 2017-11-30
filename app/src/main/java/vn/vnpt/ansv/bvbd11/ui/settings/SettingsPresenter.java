package vn.vnpt.ansv.bvbd11.ui.settings;

import javax.inject.Inject;

import vn.vnpt.ansv.bvbd11.common.ui.base.BasePresenterImpl;

/**
 * Created by ANSV on 11/30/2017.
 */

public class SettingsPresenter extends BasePresenterImpl<SettingsView> {

    public interface SettingsCallback {
        void callback(SettingsState settingsState);
    }

    public enum SettingsState {
        IP_EMPTY,
        IP_AVAILABLE,
        IP_UNAVAILABLE,
        PORT_EMPTY,
        PORT_AVAILABLE,
        PORT_UNAVAILABLE;
    }

    @Inject
    public SettingsPresenter() {

    }

    public void checkIp(String ipString, SettingsCallback callback) {

    }

    public void checkIp(String ipString) {
        if (ipString.trim().isEmpty()) {

        }
    }

    public void checkPort(String portString) {
        if (portString.trim().isEmpty()) {

        }
    }

    public void checkPort(String portString, SettingsCallback callback) {

    }

}
