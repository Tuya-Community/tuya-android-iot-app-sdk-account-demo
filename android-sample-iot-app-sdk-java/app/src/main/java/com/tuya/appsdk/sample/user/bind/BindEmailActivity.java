package com.tuya.appsdk.sample.user.bind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tuya.appsdk.sample.R;
import com.tuya.smart.android.user.api.IRegisterCallback;
import com.tuya.smart.android.user.bean.User;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.IResultCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO feature
 *
 * @author liya <a href="mailto:developer@tuya.com"/>
 * @since 5/15/21 9:16 AM
 */
public class BindEmailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "BindEmailActivity";
    private final String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private final Pattern regex = Pattern.compile(check);
    //mType equals 1 is for register
    private final int mRegisterType = 1;
    private String strAccount;
    private String strCountryCode;
    private EditText etCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_email_layout);
        Toolbar toolbar = findViewById(R.id.bindAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnBindEmail = findViewById(R.id.btnBindEmail);
        Button btnCode = findViewById(R.id.btnCode);
        btnBindEmail.setOnClickListener(this);
        btnCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText etAccount = findViewById(R.id.etAccount);
        strAccount = etAccount.getText().toString();
        EditText etCountryCode = findViewById(R.id.etCountryCode);
        strCountryCode = etCountryCode.getText().toString();
        etCode = findViewById(R.id.etCode);
        String strCode = etCode.getText().toString();

        Matcher matcher = regex.matcher(strAccount);
        boolean isEmail = matcher.matches();

        if (v.getId() == R.id.btnBindEmail) {
            // Bind the Email
            TuyaHomeSdk.getUserInstance().bindEmail(strCountryCode,
                    strAccount,
                    strCode,
                    TuyaHomeSdk.getUserInstance().getUser().getSid(), new IResultCallback() {
                        @Override
                        public void onError(String code, String error) {
                            Toast.makeText(
                                    BindEmailActivity.this,
                                    "Bind error:" + error,
                                    Toast.LENGTH_LONG
                            ).show();
                        }

                        @Override
                        public void onSuccess() {
                            Intent intent = new Intent();
                            intent.putExtra("result", strAccount);
                            BindEmailActivity.this.setResult(RESULT_OK, intent);
                            BindEmailActivity.this.finish();

                        }
                    });

        } else if (v.getId() == R.id.btnCode) {
            // Get verification code
            if (isEmail) {
                TuyaHomeSdk.getUserInstance().sendBindVerifyCodeWithEmail(strCountryCode,
                        strAccount, new IResultCallback() {

                            @Override
                            public void onError(String code, String error) {
                                Toast.makeText(
                                        BindEmailActivity.this,
                                        "getValidateCode error:" + error,
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                            @Override
                            public void onSuccess() {
                                Toast.makeText(
                                        BindEmailActivity.this,
                                        "Got validateCode",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        });

            } else {
                Toast.makeText(
                        BindEmailActivity.this,
                        "Please check your Email format!",
                        Toast.LENGTH_LONG
                ).show();
            }
        }
    }
}
