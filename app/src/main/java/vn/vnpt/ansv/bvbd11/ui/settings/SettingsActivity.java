package vn.vnpt.ansv.bvbd11.ui.settings;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.johnpersano.supertoasts.SuperToast;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import vn.vnpt.ansv.bvbd11.R;
import vn.vnpt.ansv.bvbd11.common.ui.base.BaseActivity;
import vn.vnpt.ansv.bvbd11.data.PreferenceHelper;
import vn.vnpt.ansv.bvbd11.utils.BvbdPreference;
import vn.vnpt.ansv.bvbd11.utils.toast.BvbdToast;

/**
 * Created by ANSV on 11/30/2017.
 */

public class SettingsActivity extends BaseActivity implements SettingsView {

    public static void launch(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ip_edit_text)
    EditText ip_edit_text;
    @BindView(R.id.port_edit_text)
    EditText port_edit_text;
    @BindView(R.id.checkIPButton)
    Button checkIPButton;
    @BindView(R.id.txtStatus)
    TextView txtStatus;

    @Inject
    PreferenceHelper preferenceManager;

    @Inject
    SettingsPresenter presenter;

    /**
     * life cycle của SettingsActivity: Hàm cho phép load layout từ xml file
     * - Gọi dagger model
     * - bind tới ButterKnife
     * - Khởi tạo toolbar
     * - ẩn keyboard
     * - load nội dung trong Preference ra view
     * - Kiểm tra ip
     * @param savedInstanceState
     * */
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        activityComponent().inject(this);
        ButterKnife.bind(this);
        initToolbar();
        hideKeyboard(ip_edit_text);
        hideKeyboard(port_edit_text);
        loadSharedPreference();
        checkIPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(ip_edit_text);
                hideKeyboard(port_edit_text);
                txtStatus.setVisibility(View.INVISIBLE);
                if (ip_edit_text.length()>=7 && port_edit_text.length()>0) {
                    showLoading();
                    txtStatus.setVisibility(View.INVISIBLE);
                    final String ip = ip_edit_text.getText().toString();
                    final String port = port_edit_text.getText().toString();
                    String urlCheck = "http://" + ip +":"+ port + "/BTSRestWebService/apikey/login?";
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    final StringRequest stringRequest = new StringRequest(Request.Method.GET, urlCheck,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.length() > 5) {
                                        showToast("ip, port khả dụng", SuperToast.Background.GREEN);
                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                saveSharePreference(ip, port);
                                                finish();
                                            }
                                        }, 500);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            hideLoading();
                            showToast("Không khả dụng, thử lại!", SuperToast.Background.RED);
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            return headers;
                        }
                    };
                    queue.add(stringRequest);

                } else {
                    txtStatus.setVisibility(View.VISIBLE);
                    if (ip_edit_text.length() < 7) {
                        txtStatus.setText("trống ip");
                    } else if (port_edit_text.length() < 1) {
                        txtStatus.setText("trống port");
                    }
                }
            }
        });
    }

    @OnClick(R.id.checkIPButton)
    public void onClick() {

    }

    private void updateToast() {

    }

    private ProgressDialog dialog;
    /**
     * Hàm hiển thị dialog để kiểm tra ip
     * */
    public void showLoading() {
        dialog = new ProgressDialog(SettingsActivity.this);
        dialog.setCancelable(false);
        dialog.setMax(100);
        dialog.setMessage("Đang kiểm tra " + ip_edit_text.getText().toString() + "...");
        dialog.setTitle("Thông báo");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
    }

    /**
     * Hàm ẩn dialod nếu dialog đó đang được hiển thị
     * */
    public void hideLoading() {
        if (dialog.isShowing()) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.hide();
                }
            }, 500);
        }
    }

    /**
     * Hàm cho phép ẩn keyboard
     * @param fromView ẩn từ view...
     * */
    private void hideKeyboard(EditText fromView) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(fromView.getWindowToken(), 0);
    }

    /**
     * Hàm load nội dung trong Preference vào view
     * */
    private void loadSharedPreference() {
        BvbdPreference prefs = preferenceManager.getPreferences();
        ip_edit_text.setText(prefs.ip);
        port_edit_text.setText(prefs.port);
    }

    /**
     * Hàm lưu các nội dung vào Preference
     * @param ip ip
     * @param port port
     * */
    private void saveSharePreference(String ip, String port) {
        BvbdPreference prefs = preferenceManager.getPreferences();
        prefs.ip = ip;
        prefs.port = port;
        preferenceManager.setBvbdPreference(prefs);
    }

    /**
     * Hàm khởi tạo toolBar
     * */
    private void initToolbar() {

        if (Build.VERSION.SDK_INT < 23) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.sl_terbium_green));
        } else {
            toolbar.setBackgroundColor(getColor(R.color.sl_terbium_green));
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        toolbar.setLayoutParams(params);
        setTitle("CÀI ĐẶT IP&PORT");
    }

    /**
     * Hàm hiển thị toast
     * @param content nội dung cần hiển thị
     * @param color màu nền cho toast
     * */
    private void showToast(String content, int color) {
        new BvbdToast(this).showToast(content, color);
    }
}
