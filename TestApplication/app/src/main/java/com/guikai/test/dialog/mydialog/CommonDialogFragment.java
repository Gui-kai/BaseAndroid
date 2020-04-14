package com.guikai.test.dialog.mydialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guikai.test.R;

/**
 * Description: 真正需要使用的各自弹窗(权限、更新...)
 * Crete by Anding on 2020-03-11
 */
public class CommonDialogFragment extends MyDialogFragment implements View.OnClickListener {
    private static final String TAG = "CommonDialogFragment";
    private static final String KEY_POSITIVE_TEXT_COLOR = "positive_text_color";
    private static final String KEY_TITLE = "title";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TEXT_START = "text_start";
    private static final String KEY_TEXT_END = "text_end";

    @ColorRes
    private int mPositiveTextColorId;
    private String mTitle;
    private CharSequence mMessage;
    private String mTextNegative;
    private String mTextPositive;
    private OnClickListener mOnNegativeClickListener;
    private OnClickListener mOnPositiveClickListener;

    public static void show(Context context, FragmentManager fm, @StringRes int title, @StringRes int message,
                            @StringRes int positive, OnClickListener positiveListener,
                            @StringRes int negative, OnClickListener negativeListener) {
        createCommonBuilder(context, title, message, positive, positiveListener, negative, negativeListener)
                .show(fm, TAG);
    }

    public static Builder createCommonBuilder(Context context, @StringRes int title, @StringRes int message,
                                              @StringRes int positive, OnClickListener positiveListener,
                                              @StringRes int negative, OnClickListener negativeListener) {
        CommonDialogFragment.Builder builder = new CommonDialogFragment.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositive(positive, positiveListener)
                .setNegative(negative, negativeListener);
        return builder;
    }

    @Override
    protected void init(@Nullable Bundle args, @Nullable Bundle savedInstanceState) {
        super.init(args, savedInstanceState);
        if (args != null) {
            mTitle = args.getString(KEY_TITLE);
            mMessage = (CharSequence) args.get(KEY_MESSAGE);
            mTextNegative = args.getString(KEY_TEXT_START, getString(android.R.string.cancel));
            mTextPositive = args.getString(KEY_TEXT_END, getString(android.R.string.yes));
            mPositiveTextColorId = args.getInt(KEY_POSITIVE_TEXT_COLOR, R.color.btn_text_common_color);
        }

        if (mDialogInterface != null) {
            mOnPositiveClickListener = mDialogInterface.getOnPositiveClickListener();
            mOnNegativeClickListener = mDialogInterface.getOnNegativeClickListener();
        }
    }

    @Override
    protected void setUp(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.setUp(rootView, savedInstanceState);
        // dialog的标题 (如果有就显示 没有则不显示标题)
        TextView tvTitle = rootView.findViewById(android.R.id.title);
        if (tvTitle != null) {
            if (!TextUtils.isEmpty(mTitle)) {
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(mTitle);
            } else {
                tvTitle.setVisibility(View.GONE);
            }
        }

        // dialog message 同上
        TextView tvMessage = rootView.findViewById(android.R.id.message);
        if (tvMessage != null && !TextUtils.isEmpty(mMessage)) {
            tvMessage.setText(mMessage);
            if (mMessage instanceof SpannableString) {
                tvMessage.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }

        // negative button
        Button btnNegative = rootView.findViewById(android.R.id.button1);
        if (btnNegative != null) {
            if (!TextUtils.isEmpty(mTextNegative)) {
                btnNegative.setText(mTextNegative);
                btnNegative.setOnClickListener(this);
                btnNegative.setVisibility(View.VISIBLE);
            } else {
                btnNegative.setVisibility(View.GONE);
            }
        }

        // positive button
        Button btnPositive = rootView.findViewById(android.R.id.button3);
        if (btnPositive != null) {
            if (!TextUtils.isEmpty(mTextPositive)) {
                btnPositive.setText(mTextPositive);
                btnPositive.setOnClickListener(this);
                btnPositive.setVisibility(View.VISIBLE);

                //positive btn text color
                if (mPositiveTextColorId != 0) {
                    btnPositive.setTextColor(getResources().getColor(mPositiveTextColorId));
                }

            } else {
                btnPositive.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.button1:
                //处理dialog点击事件 交给子类使用者处理
                if (mOnNegativeClickListener != null) {
                    mOnNegativeClickListener.onClick(this, android.content.DialogInterface.BUTTON_NEGATIVE);
                }
                break;
            case android.R.id.button3:
                //处理dialog点击事件 交给使用者处理
                if (mOnPositiveClickListener != null) {
                    mOnPositiveClickListener.onClick(this, android.content.DialogInterface.BUTTON_POSITIVE);
                }
                break;
            default:
                break;
        }
        dismiss();
    }

    public static class Builder extends MyDialogFragment.Builder {

        public Builder(Context context) {
            super(context);
        }

        public Builder setTitle(@StringRes int titleRes) {
            if (titleRes == 0) {
                return this;
            }

            String title = mContext.getString(titleRes);
            return setTitle(title);
        }

        public Builder setTitle(String title) {
            mArgs.putString(KEY_TITLE, title);
            return this;
        }

        public Builder setMessage(@StringRes int messageRes) {
            if (messageRes == 0) {
                return this;
            }

            String message = mContext.getString(messageRes);
            return setMessage(message);
        }

        public Builder setMessage(String message) {
            mArgs.putString(KEY_MESSAGE, message);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            mArgs.putCharSequence(KEY_MESSAGE, message);
            return this;
        }

        public Builder setNegative(@StringRes int negativeId, @Nullable OnClickListener listener) {
            String negative = negativeId != 0 ? mContext.getString(negativeId) : "";
            return setNegative(negative, listener);
        }

        public Builder setNegative(String negative, @Nullable OnClickListener listener) {
            mArgs.putString(KEY_TEXT_START, negative);
            DialogInterface dialogInterface = getDialogParams();
            dialogInterface.setOnNegativeClickListener(listener);
            saveDialogParams(dialogInterface);
            return this;
        }

        public Builder setPositive(@StringRes int positiveId, @Nullable OnClickListener listener) {
            String positive = positiveId != 0 ? mContext.getString(positiveId) : "";
            return setPositive(positive, listener);
        }

        public Builder setPositive(String positive, @Nullable OnClickListener listener) {
            mArgs.putString(KEY_TEXT_END, positive);
            DialogInterface dialogInterface = getDialogParams();
            dialogInterface.setOnPositiveClickListener(listener);
            saveDialogParams(dialogInterface);
            return this;
        }

        public Builder setPositiveTextColor(@ColorRes int colorId) {
            mArgs.putInt(KEY_POSITIVE_TEXT_COLOR, colorId);
            return this;
        }

        @Override
        public CommonDialogFragment create() {
            CommonDialogFragment dialog = new CommonDialogFragment();
            dialog.setArguments(mArgs);

            return dialog;
        }

        @Override
        public CommonDialogFragment show(FragmentManager fm, String tag) {
            CommonDialogFragment dialog = create();
            dialog.show(fm, tag);
            return dialog;
        }
    }

    // 回调接口
    public interface OnClickListener {
        void onClick(CommonDialogFragment dialog, int which);
    }

}
