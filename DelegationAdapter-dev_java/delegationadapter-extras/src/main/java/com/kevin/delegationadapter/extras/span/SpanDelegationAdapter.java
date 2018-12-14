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
package com.kevin.delegationadapter.extras.span;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kevin.delegationadapter.AdapterDelegate;
import com.kevin.delegationadapter.DelegationAdapter;

/**
 * SpanDelegationAdapter
 *
 * @author zwenkai@foxmail.com, Created on 2018-06-10 11:08:37
 *         Major Function：<b>DelegationAdapter with span</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

public class SpanDelegationAdapter<VH extends RecyclerView.ViewHolder> extends DelegationAdapter<VH> {

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    AdapterDelegate delegate = mDelegatesManager.getDelegate(getItemViewType(position));
                    if (null != delegate && delegate instanceof SpanAdapterDelegate) {
                        return ((SpanAdapterDelegate) delegate).getSpanSize();
                    } else {
                        return gridLayoutManager.getSpanCount();
                    }
                }
            });
        }
    }
}
