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
package com.kevin.delegationadapter.extras.binding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * BindingViewHolder
 *
 * @author zwenkai@foxmail.com, Created on 2018-04-03 17:51:49
 *         Major Function：<b>Binding ViewHolder</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

public final class BindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public BindingViewHolder(View itemView) {
        super(itemView);
    }

    public <T extends ViewDataBinding> T getBinding() {
        return (T) binding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }
}
