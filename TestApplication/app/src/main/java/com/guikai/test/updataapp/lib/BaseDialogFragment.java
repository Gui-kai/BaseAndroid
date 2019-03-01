package com.guikai.test.updataapp.lib;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.guikai.test.R;

public abstract class BaseDialogFragment extends DialogFragment {

    /**
     * 设置是否可以cancel
     *
     * @return
     */
    protected abstract boolean isCancel();

    /**
     * 获取布局资源文件
     *
     * @return 布局资源文件id值
     */
    @LayoutRes
    public abstract int getLayoutRes();

    /**
     * 绑定
     *
     * @param v view
     */
    public abstract void bindView(View v);

    private static final String TAG = "BaseDialogFragment";
    private static final float DEFAULT_DIM = 0.2f;
    private Dialog dialog;
    private Local local = Local.BOTTOM;

    public enum Local {
        TOP, CENTER, BOTTOM
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (local == Local.BOTTOM) {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
        } else if (local == Local.CENTER || local == Local.TOP) {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CenterDialog);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialog = getDialog();
        if (dialog != null) {
            if (dialog.getWindow() != null) {
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            }
            dialog.setCanceledOnTouchOutside(isCancel());
            dialog.setCancelable(isCancelable());
        }
        View v = inflater.inflate(getLayoutRes(), container, false);
        bindView(v);
        return v;
    }

    /**
     * 开始展示
     */
    @Override
    public void onStart() {
        super.onStart();
        if (dialog != null) {
            dialog = getDialog();
        }
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = getDimAmount();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            if (getHeight() > 0) {
                params.height = getHeight();
            } else {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            params.gravity = Gravity.CENTER;
            window.setAttributes(params);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }

    public int getHeight() {
        return -1;
    }

    public float getDimAmount() {
        return DEFAULT_DIM;
    }

    public String getFragmentTag() {
        return TAG;
    }

    public void show(FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            show(fragmentManager, getFragmentTag());
        } else {
            Log.e("show", "需要设置setFragmentManager");
        }
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * 一定要销毁dialog，设置为null，防止内存泄漏
     */
    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
