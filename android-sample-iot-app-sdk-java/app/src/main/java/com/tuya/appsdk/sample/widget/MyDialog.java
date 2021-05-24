package com.tuya.appsdk.sample.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tuya.appsdk.sample.R;

/**
 * dialog
 *
 * @author liya <a href="mailto:developer@tuya.com"/>
 * @since 5/15/21 11:01 AM
 */
public class MyDialog extends Dialog {
    private TextView mCancelTv;
    private TextView mSureTv;
    private EditText mEtNickName;
    private CancelOnclickListener cancelOnclickListener;
    private OkOnclickListener okOnclickListener;

    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_customs);
        mEtNickName=findViewById(R.id.edit_text_dialog);
        mSureTv=findViewById(R.id.ok);
        mCancelTv=findViewById(R.id.cancel);
        initEvent();

    }

    private void initEvent() {
        mSureTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (okOnclickListener!=null){
                    String nickName = mEtNickName.getText().toString().trim();
                    okOnclickListener.onOkOnclick(nickName);

                }
            }
        });

        mCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener!=null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });
    }


    public interface CancelOnclickListener {
        void onCancelClick();
    }

    public interface OkOnclickListener {
        void onOkOnclick(String nickName);
    }

    public void setCancelOnclickListener(CancelOnclickListener cancelOnclickListener) {

        this.cancelOnclickListener = cancelOnclickListener;
    }
    public void setOkOnclickListener(OkOnclickListener okOnclickListener) {

        this.okOnclickListener = okOnclickListener;
    }


}
