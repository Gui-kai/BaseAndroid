package com.guikai.test.dialog.mydialog;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Description:
 * Crete by Anding on 2020-03-13
 */
public final class DialogInterface implements Parcelable, Serializable {

    private CommonDialogFragment.OnClickListener mOnNegativeClickListener;
    private CommonDialogFragment.OnClickListener mOnPositiveClickListener;

    public DialogInterface() {
    }

    protected DialogInterface(Parcel in) {
    }

    public static final Creator<DialogInterface> CREATOR = new Creator<DialogInterface>() {
        @Override
        public DialogInterface createFromParcel(Parcel in) {
            return new DialogInterface(in);
        }

        @Override
        public DialogInterface[] newArray(int size) {
            return new DialogInterface[size];
        }
    };

    public CommonDialogFragment.OnClickListener getOnNegativeClickListener() {
        return mOnNegativeClickListener;
    }

    public void setOnNegativeClickListener(CommonDialogFragment.OnClickListener onNegativeClickListener) {
        mOnNegativeClickListener = onNegativeClickListener;
    }

    public CommonDialogFragment.OnClickListener getOnPositiveClickListener() {
        return mOnPositiveClickListener;
    }

    public void setOnPositiveClickListener(CommonDialogFragment.OnClickListener onPositiveClickListener) {
        mOnPositiveClickListener = onPositiveClickListener;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
