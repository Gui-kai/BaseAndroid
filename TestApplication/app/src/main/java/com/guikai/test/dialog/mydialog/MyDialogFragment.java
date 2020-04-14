package com.guikai.test.dialog.mydialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.guikai.test.R;
import com.guikai.test.utils.DensityUtil;
import com.guikai.test.utils.ScreenUtils;

/**
 * Description: 自定义Dialog的一种方式
 * Crete by Anding on 2020-03-10
 */
public class MyDialogFragment extends DialogFragment {

    // key的默认值
    private static final String TAG = "CustomDialogFragment";
    private static final String KEY_LAYOUT_ID = "layout_id";
    private static final String KEY_SHOW_BOTTOM = "show_bottom";
    private static final String KEY_HORIZONTAL_MARGIN = "horizontal_margin";
    private static final String KEY_WIDTH = "width";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_Y = "y";
    private static final String KEY_BACKGROUND_ID = "background_id";
    private static final String KEY_CANCEL_ON_TOUCH_OUTSIDE = "cancel_outside";
    private static final String KEY_INTERCEPT_BACK = "intercept_back";
    private static final String KEY_DIALOG_PARAMS = "dialog_params";

    // 默认dialog布局
    @LayoutRes
    private static final int DEF_LAYOUT_ID = R.layout.layout_dialog_common;
    private static final boolean DEF_SHOW_BOTTOM = true;

    @DimenRes
    public static final int DEF_HORIZONTAL_MARGIN = R.dimen.dp_16;

    @DimenRes
    public static final int DEF_Y = R.dimen.dp_20;

    // 默认圆角背景
    @DrawableRes
    private static final int DEF_BACKGROUND_ID = R.drawable.bg_dialog;
    private static final boolean DEF_CANCEL_ON_TOUCH_OUTSIDE = false;

    // dialog布局视图id
    @LayoutRes
    private int mLayoutResId;

    // 是否底部弹出
    private boolean mShowBottom;

    // 间距 宽度 高度
    @Dimension
    private int mHorizontalMargin;
    @Dimension
    private int mWidth, mHeight;

    @Dimension
    private int mY;

    // dialog布局视图id
    @DrawableRes
    private int mBackgroundResId;

    // 是否可以点击空白区域关闭dialog
    private boolean mCancelOnTouchOutside;


    private boolean mInterceptBack = false;

    // 若干回调接口 根据自己需求扩展
    // private OnConvertViewListener mOnConvertViewListener;
    protected com.guikai.test.dialog.mydialog.DialogInterface mDialogInterface;

    // private ResumeCallback mResumeCallback = new ResumeCallback();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过Fragment的Bundle传值进来
        init(getArguments(), savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(mLayoutResId, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(mCancelOnTouchOutside);
        // 是否拦截back事件
        if (mInterceptBack) {
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && event.getAction() == KeyEvent.ACTION_UP) {
                        return true;
                    }
                    return false;
                }
            });
        }
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    protected void setUp(@NonNull View rootView, @Nullable Bundle savedInstanceState) {

    }

    protected void init(Bundle arguments, Bundle savedInstanceState) {
        setStyle(STYLE_NO_TITLE, 0);
        if (arguments != null) {
            mLayoutResId = arguments.getInt(KEY_LAYOUT_ID, DEF_LAYOUT_ID);
            mShowBottom = arguments.getBoolean(KEY_SHOW_BOTTOM, DEF_SHOW_BOTTOM);
            mHorizontalMargin = arguments.getInt(KEY_HORIZONTAL_MARGIN, getResources().getDimensionPixelSize(DEF_HORIZONTAL_MARGIN));
            mWidth = arguments.getInt(KEY_WIDTH, 0);
            mHeight = arguments.getInt(KEY_HEIGHT, 0);
            mY = arguments.getInt(KEY_Y, getResources().getDimensionPixelSize(DEF_Y));
            mBackgroundResId = arguments.getInt(KEY_BACKGROUND_ID, DEF_BACKGROUND_ID);
            mCancelOnTouchOutside = arguments.getBoolean(KEY_CANCEL_ON_TOUCH_OUTSIDE, DEF_CANCEL_ON_TOUCH_OUTSIDE);
            mDialogInterface = (com.guikai.test.dialog.mydialog.DialogInterface) arguments.getSerializable(KEY_DIALOG_PARAMS);
            mInterceptBack = arguments.getBoolean(KEY_INTERCEPT_BACK, false);
        }
    }

    private void initParams() {
        Window window;
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        WindowManager.LayoutParams lp = window.getAttributes();
        // dialog弹出位置
        if (mShowBottom) {
            lp.gravity = Gravity.BOTTOM;
        } else {
            lp.gravity = Gravity.CENTER;
        }

        // dialog宽度设置
        if (mWidth == 0) {
            lp.width = ScreenUtils.getScreenWidth(getContext()) - 2 * mHorizontalMargin;
        } else {
            lp.width = DensityUtil.dip2px(getContext(), mWidth);
        }

        // dialog 高度设置
        if (mHeight == 0) {
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        } else {
            lp.height = DensityUtil.dip2px(getContext(), mHeight);
        }

        // dialog 的
        if (mY != 0) {
            lp.y = mY;
        }

        // background
        if (mBackgroundResId != 0) {
            window.setBackgroundDrawableResource(mBackgroundResId);
        }

        window.setAttributes(lp);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
//        try {
//            Field mDismissed = this.getClass().getSuperclass().getDeclaredField("mDismissed");
//            Field mShownByMe = this.getClass().getSuperclass().getDeclaredField("mShownByMe");
//            mDismissed.setAccessible(true);
//            mShownByMe.setAccessible(true);
//            mDismissed.setBoolean(this, false);
//            mShownByMe.setBoolean(this, true);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        FragmentTransaction transaction = manager.beginTransaction();
        Fragment prevFragment = manager.findFragmentByTag(tag);
        if (prevFragment != null) {
            transaction.remove(prevFragment);
        }
        transaction.add(this, tag);
        transaction.commitAllowingStateLoss();
    }

    // 使用建造者模式 方便设置变量创建
    public static class Builder {
        protected final Context mContext;
        protected final Bundle mArgs = new Bundle();

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setLayoutId(@LayoutRes int layoutId) {
            mArgs.putInt(KEY_LAYOUT_ID, layoutId);
            return this;
        }

        public Builder showBottom(boolean bottom) {
            mArgs.putBoolean(KEY_SHOW_BOTTOM, bottom);
            return this;
        }

        public Builder setHorizontalMargin(@Dimension int margin) {
            mArgs.putInt(KEY_HORIZONTAL_MARGIN, margin);
            return this;
        }

        public Builder setWidth(@Dimension int width) {
            mArgs.putInt(KEY_WIDTH, width);
            return this;
        }

        public Builder setHeigh(@Dimension int height) {
            mArgs.putInt(KEY_HEIGHT, height);
            return this;
        }

        public Builder setY(@Dimension int y) {
            mArgs.putInt(KEY_Y, y);
            return this;
        }

        public Builder setBackgroundRes(@DrawableRes int backgroundRes) {
            mArgs.putInt(KEY_BACKGROUND_ID, backgroundRes);
            return this;
        }

        public Builder setCancelOutside(boolean cancelOutside) {
            mArgs.putBoolean(KEY_CANCEL_ON_TOUCH_OUTSIDE, cancelOutside);
            return this;
        }

        public Builder setInterceptBack(boolean interceptBack) {
            mArgs.putBoolean(KEY_INTERCEPT_BACK, interceptBack);
            return this;
        }

        public MyDialogFragment create() {
            MyDialogFragment fragment = new MyDialogFragment();
            fragment.setArguments(mArgs);
            return fragment;
        }

        public MyDialogFragment show(FragmentManager fm, String tag) {
            MyDialogFragment fragment = create();
            fragment.show(fm, tag);
            return fragment;
        }

        protected com.guikai.test.dialog.mydialog.DialogInterface getDialogParams() {
            com.guikai.test.dialog.mydialog.DialogInterface dialogInterface = mArgs.getParcelable(KEY_DIALOG_PARAMS);
            if (dialogInterface == null) {
                dialogInterface = new com.guikai.test.dialog.mydialog.DialogInterface();
            }

            return dialogInterface;
        }

        protected void saveDialogParams(com.guikai.test.dialog.mydialog.DialogInterface dialogInterface) {
            mArgs.putParcelable(KEY_DIALOG_PARAMS, dialogInterface);
        }
    }

}
