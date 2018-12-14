/*
 * Copyright (c) 2018 Kevin zhou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kevin.delegationadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * AdapterDelegatesManager
 *
 * @author zwenkai@foxmail.com, Created on 2018-04-10 23:24:58
 *         Major Function：<b>This class is the element that ties {@link RecyclerView.Adapter}
 *         together with {@link AdapterDelegate}.
 *         <p>
 *         So you have to add / register your {@link AdapterDelegate}s to this manager by calling
 *         {@link #addDelegate(AdapterDelegate, String)}
 *         /b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

public class AdapterDelegatesManager<VH extends RecyclerView.ViewHolder> {

    private SparseArray<String> mDataTypeWithTags = new SparseArray<>();
    private SparseArrayCompat<AdapterDelegate<Object, VH>> mDelegates = new SparseArrayCompat();
    protected AdapterDelegate mFallbackDelegate;

    /**
     * Add a Delegate
     *
     * @param delegate
     */
    public AdapterDelegatesManager addDelegate(AdapterDelegate<Object, VH> delegate, String tag) {
        Type superclass = delegate.getClass().getGenericSuperclass();
        try {
            Class<?> clazz = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
            String typeWithTag = getTypeWithTag(clazz, tag);

            int viewType = mDelegates.size();
            // Save the delegate to the collection;
            mDelegates.put(viewType, delegate);
            // Save the index of the delegate to the collection;
            mDataTypeWithTags.put(viewType, typeWithTag);
        } catch (Exception e) {
            // Has no generics or generics not correct.
            throw new IllegalArgumentException(
                    String.format("Please set the correct generic parameters on %s.", delegate.getClass().getName()));
        }
        return this;
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<Object, VH> delegate = getDelegate(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }

        VH vh = delegate.onCreateViewHolder(parent);
        if (vh == null) {
            throw new NullPointerException("ViewHolder returned from AdapterDelegate "
                    + delegate
                    + " for ViewType ="
                    + viewType
                    + " is null!");
        }
        return vh;
    }

    public void onBindViewHolder(VH holder, int position, Object item) {
        int viewType = holder.getItemViewType();
        AdapterDelegate<Object, VH> delegate = getDelegate(viewType);
        if (delegate == null) {
            throw new NullPointerException("No delegate found for item at position = "
                    + position
                    + " for viewType = "
                    + viewType);
        }
        delegate.onBindViewHolder(holder, position, item);
    }

    public void onBindViewHolder(VH holder, int position, List payloads, Object item) {
        int viewType = holder.getItemViewType();
        AdapterDelegate<Object, VH> delegate = getDelegate(viewType);
        if (delegate == null) {
            throw new NullPointerException("No delegate found for item at position = "
                    + position
                    + " for viewType = "
                    + viewType);
        }
        delegate.onBindViewHolder(holder, position, payloads, item);
    }

    /**
     * Return the view type of the item.
     *
     * @param item
     * @param position
     * @return
     */
    public int getItemViewType(@NonNull Object item, int position) {
        if (item == null) {
            throw new NullPointerException("Item data source is null.");
        }

        Class clazz = getTargetClass(item);
        String tag = getTargetTag(item);

        String typeWithTag = getTypeWithTag(clazz, tag);
        ArrayList<Integer> indexList = indexesOfValue(mDataTypeWithTags, typeWithTag);
        if (indexList.size() > 0) {
            for (Integer index : indexList) {
                AdapterDelegate<Object, VH> delegate = mDelegates.valueAt(index);
                if (null != delegate
                        && delegate.getTag().equals(tag)
                        && delegate.isForViewType(item, position)) {
                    return index;
                }
            }
        }

        // If has not add the AdapterDelegate for data type, returns the largest viewType + 1.
        if (mFallbackDelegate != null) {
            return mDelegates.size();
        }

        throw new NullPointerException("No AdapterDelegate added that matches position="
                + position + " item=" + item + " in data source.");
    }

    public void onViewRecycled(VH holder) {
        AdapterDelegate<Object, VH> delegate = getDelegate(holder.getItemViewType());
        if (delegate != null) {
            delegate.onViewRecycled(holder);
        }
    }

    public boolean onFailedToRecycleView(VH holder) {
        AdapterDelegate<Object, VH> delegate = getDelegate(holder.getItemViewType());
        if (delegate != null) {
            return delegate.onFailedToRecycleView(holder);
        }
        return false;
    }

    public void onViewAttachedToWindow(VH holder) {
        AdapterDelegate<Object, VH> delegate = getDelegate(holder.getItemViewType());
        if (delegate != null) {
            delegate.onViewAttachedToWindow(holder);
        }
    }

    public void onViewDetachedFromWindow(VH holder) {
        AdapterDelegate<Object, VH> delegate = getDelegate(holder.getItemViewType());
        if (delegate != null) {
            delegate.onViewDetachedFromWindow(holder);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        for (int i = 0; i < mDelegates.size(); i++) {
            AdapterDelegate<Object, VH> delegate = mDelegates.get(mDelegates.keyAt(i));
            delegate.onAttachedToRecyclerView(recyclerView);
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        for (int i = 0; i < mDelegates.size(); i++) {
            AdapterDelegate<Object, VH> delegate = mDelegates.get(mDelegates.keyAt(i));
            delegate.onDetachedFromRecyclerView(recyclerView);
        }
    }

    public AdapterDelegatesManager setFallbackDelegate(AdapterDelegate fallbackDelegate) {
        this.mFallbackDelegate = fallbackDelegate;
        return this;
    }

    /**
     * Get the fallback delegate
     *
     * @return The fallback delegate or <code>null</code> if no fallback delegate has been set
     * @see #setFallbackDelegate(AdapterDelegate)
     */
    @Nullable
    public AdapterDelegate getFallbackDelegate() {
        return mFallbackDelegate;
    }


    public AdapterDelegate<Object, VH> getDelegate(int viewType) {
        return mDelegates.get(viewType, mFallbackDelegate);
    }

    /**
     * Returns the class name with tag;
     *
     * @param clazz
     * @param tag
     * @return
     */
    private String getTypeWithTag(Class clazz, String tag) {
        if (tag.length() == 0) {
            return clazz.getName();
        } else {
            return clazz.getName() + ":" + tag;
        }
    }

    /**
     * Returns the target class name
     *
     * @return
     */
    private Class getTargetClass(Object data) {
        return data instanceof ItemData ? ((ItemData) data).getData().getClass() : data.getClass();
    }

    /**
     * Returns the target tag
     *
     * @param data
     * @return
     */
    private String getTargetTag(Object data) {
        return data instanceof ItemData ? ((ItemData) data).getTag() : AdapterDelegate.DEFAULT_TAG;
    }

    /**
     * Returns all indexes for the specified value
     *
     * @param array
     * @param value
     * @return
     */
    private ArrayList<Integer> indexesOfValue(SparseArray<String> array, String value) {
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            if (value.equals(array.valueAt(i))) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}
