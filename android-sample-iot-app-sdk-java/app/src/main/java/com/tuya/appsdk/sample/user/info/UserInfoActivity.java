/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2021 Tuya Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NO
 */

package com.tuya.appsdk.sample.user.info;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tuya.appsdk.sample.R;
import com.tuya.appsdk.sample.resource.HomeModel;
import com.tuya.appsdk.sample.user.bind.BindEmailActivity;
import com.tuya.appsdk.sample.user.main.UserFuncActivity;
import com.tuya.appsdk.sample.widget.MyDialog;
import com.tuya.smart.android.user.api.ILogoutCallback;
import com.tuya.smart.android.user.api.IReNickNameCallback;
import com.tuya.smart.android.user.bean.User;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.IResultCallback;

/**
 * User Info Example
 *
 * @author chuanfeng <a href="mailto:developer@tuya.com"/>
 * @since 2021/05/17
 */
public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvName;
    private LinearLayout llNickName;
    private MyDialog myDialog;
    private TextView tvEmail;
    private String result;
    private Button logoutButton;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_info);

        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());


        User user = TuyaHomeSdk.getUserInstance().getUser();
        logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(this);
        cancelButton=findViewById(R.id.deactivate);
        cancelButton.setOnClickListener(this);

        tvName = findViewById(R.id.tvName);
        if (user != null && !TextUtils.isEmpty(user.getNickName())) {
            tvName.setText(user.getNickName());
        }
        tvName.setOnClickListener(this);
        llNickName = findViewById(R.id.ll_nick_name);
        llNickName.setOnClickListener(this);
        TextView tvPhone = findViewById(R.id.tvPhone);
        if (user != null && !TextUtils.isEmpty(user.getMobile())) {
            tvPhone.setText(user.getMobile());
        }
        tvEmail = findViewById(R.id.tvEmail);
        if (!TextUtils.isEmpty(user.getEmail())) {
            tvEmail.setText(user.getEmail());
        } else {
            tvEmail.setOnClickListener(this);
        }

        TextView tvCountryCode = findViewById(R.id.tvCountryCode);
        tvCountryCode.setText(user.getPhoneCode());

    }

    /**
     * set the nick name
     */
    private void showCustomizeDialog() {
        myDialog = new MyDialog(this, R.style.MyDialog);
        myDialog.setOkOnclickListener(nickName -> {
            TuyaHomeSdk.getUserInstance().updateNickName(nickName,
                    new IReNickNameCallback() {
                        @Override
                        public void onSuccess() {
                            runOnUiThread(() -> tvName.setText(nickName));
                        }

                        @Override
                        public void onError(String code, String error) {

                        }
                    });
            myDialog.dismiss();


        });
        myDialog.setCancelOnclickListener(() -> myDialog.dismiss());
        myDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                result = data.getExtras().getString("result");
                tvEmail.setText(result);
            }

        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvEmail:
                //Bind the email
                Intent intent = new Intent(v.getContext(), BindEmailActivity.class);
                startActivityForResult(intent, 1001);
                break;
            case R.id.tvName:
                //Click to set the nick name
                showCustomizeDialog();
                break;
            case R.id.logout:
                //Account logout
                TuyaHomeSdk.getUserInstance().logout(new ILogoutCallback() {
                    @Override
                    public void onSuccess() {
                        // Clear cache
                        HomeModel.INSTANCE.clear(UserInfoActivity.this);

                        // Navigate to User Func Navigation Page
                        Intent intent = new Intent(UserInfoActivity.this, UserFuncActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String errorCode, String errorMsg) {
                    }
                });
                break;
            case R.id.deactivate:
                //Cancel account
                TuyaHomeSdk.getUserInstance().cancelAccount(new IResultCallback() {
                    @Override
                    public void onError(String code, String error) {
                        Toast.makeText(v.getContext(),
                                "error" + error,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess() {
                        Toast.makeText(v.getContext(),
                                "deactivate success",
                                Toast.LENGTH_SHORT).show();
                        // Clear cache
                        HomeModel.INSTANCE.clear(v.getContext());

                        // Navigate to User Func Navigation Page
                        Intent intent = new Intent(v.getContext(), UserFuncActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
                break;
        }
    }
}
