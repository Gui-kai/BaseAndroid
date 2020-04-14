package com.guikai.test.dialog.mydialog;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.guikai.test.R;
import com.guikai.test.dialog.bottom.BottomDialog;

/**
 * Description: 使用封装的dialog
 * Crete by Anding on 2020-03-11
 */
public class MyDialogActivity extends AppCompatActivity {

    private DialogFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_dialog);
        findViewById(R.id.common).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeclarationDialog();
            }
        });
        findViewById(R.id.simple_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDialog();
            }
        });
        findViewById(R.id.center_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCenterDialog();
            }
        });
        findViewById(R.id.activity_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomDialog bottomDialog = new BottomDialog();
                bottomDialog.show(getSupportFragmentManager(),"bottom");
            }
        });
    }

    private void showCenterDialog() {
        CommonDialogFragment.Builder builder = new CommonDialogFragment.Builder(this);
        builder.setTitle("用户协议及隐私政策")
                .setNegative(R.string.know, new CommonDialogFragment.OnClickListener() {
                    @Override
                    public void onClick(CommonDialogFragment dialog, int which) {
                        Toast.makeText(getApplicationContext(),"知道了",Toast.LENGTH_SHORT).show();
                    }
                })
                .setMessage("欢迎您使用趣定位APP，在您使用某些特定功能时会读取您的位置信息、设备信息和存储。\n我们非常重视您的隐私保护和个人信息保护。请您认真阅读上述相关信息以及《隐私政策》和《用户协议》。\n如您阅读完毕并同意以上协议的全部内容，请开始使用我们的产品和服务。")
                .setLayoutId(R.layout.center_common_dialog)
                .showBottom(false)
                .setHorizontalMargin(96)
                .show(getSupportFragmentManager(),"center");
    }

    private void showSimpleDialog() {
        CommonDialogFragment.Builder builder = CommonDialogFragment.createCommonBuilder(this,
                R.string.permission_settings_title, R.string.permission_autorun_tips, R.string.go_settings, new CommonDialogFragment.OnClickListener() {
                    @Override
                    public void onClick(CommonDialogFragment dialog, int which) {
                        Toast.makeText(getApplicationContext(),"点击确定",Toast.LENGTH_SHORT).show();
                    }
                }, 0, null);
        builder.setCancelOutside(true);
        builder.setPositiveTextColor(R.color.color_03DD7F);
        builder.show(getSupportFragmentManager(), "auto_run");
    }


    private void showDeclarationDialog() {
        if (fragment == null) {
            CommonDialogFragment.Builder builder = new CommonDialogFragment.Builder(this);
            builder.setTitle(R.string.declare_title);
            builder.setMessage(highlightProtocolPolicy(R.string.declare_message));
            builder.setPositive(R.string.declare_agree, new CommonDialogFragment.OnClickListener() {
                @Override
                public void onClick(CommonDialogFragment dialog, int which) {
                    // 同意后点击后跳转
                    Toast.makeText(getApplicationContext(),"点击确定",Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegative(R.string.exit, new CommonDialogFragment.OnClickListener() {
                @Override
                public void onClick(CommonDialogFragment dialog, int which) {
                    // 拒绝后点击后跳转
                }
            });
            builder.setPositiveTextColor(R.color.color_03DD7F);
            builder.setCancelOutside(true);
            fragment = builder.create();
        }
        fragment.show(getSupportFragmentManager(), "declare");
    }

    // 这是文字超链接
    public SpannableString highlightProtocolPolicy(int messageId) {
        Context context = getApplicationContext();
        Resources res = context.getResources();
        String sentences = res.getString(messageId);
        SpannableString sp = new SpannableString(sentences);
        String privacy = res.getString(R.string.privacy_policy);
        String agreement = res.getString(R.string.use_agreement);

        int privacyStartIndex = sentences.indexOf(privacy);
        int agreementStartIndex = sentences.indexOf(agreement);
        sp.setSpan(new AbstractCustomSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // 点击超链接跳转

            }
        }, privacyStartIndex, privacyStartIndex + privacy.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        sp.setSpan(new AbstractCustomSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // 点击超链接跳转
            }
        }, agreementStartIndex, agreementStartIndex + agreement.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }

    // span样式
    public abstract class AbstractCustomSpan extends ClickableSpan {
        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            // 不显示下划线
            ds.setUnderlineText(false);
        }
    }

}
