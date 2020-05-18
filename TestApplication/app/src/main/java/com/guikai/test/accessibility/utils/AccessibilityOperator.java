package com.guikai.test.accessibility.utils;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class AccessibilityOperator {

    private Context mContext;
    private static AccessibilityOperator mInstance = new AccessibilityOperator();
    private AccessibilityEvent mAccessibilityEvent;
    private AccessibilityService mAccessibilityService;

    private AccessibilityOperator() {
    }

    public static AccessibilityOperator getInstance() {
        return mInstance;
    }

    public void init(Context context) {
        mContext = context;
    }

    // ASB监听到的所有事件 更新到这里
    public void updateEvent(AccessibilityService service, AccessibilityEvent event) {
        if (service != null && mAccessibilityService == null) {
            mAccessibilityService = service;
        }
        if (event != null) {
            mAccessibilityEvent = event;
        }
    }

    // 判断当前辅助模式是否开启
    public boolean isServiceRunning() {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Short.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : services) {
            if (info.service.getClassName().equals(mContext.getPackageName() + ".AccessibilitySampleService")) {
                return true;
            }
        }
        return false;
    }

    // 返回当前界面所发生的事件
    private AccessibilityNodeInfo getRootNodeInfo() {
        AccessibilityEvent curEvent = mAccessibilityEvent;
        AccessibilityNodeInfo nodeInfo = null;
        // 建议使用getRootInActiveWindow，这样不依赖当前的事件类型
        if (mAccessibilityService != null) {
            nodeInfo = mAccessibilityService.getRootInActiveWindow();
            AccessibilityLog.printLog("nodeInfo: " + nodeInfo);
        }
        // 下面这个必须依赖当前的AccessibilityEvent
//            nodeInfo = curEvent.getSource();
        return nodeInfo;
    }

    /**
     * 根据View的ID搜索符合条件的节点,精确搜索方式;
     * 这个只适用于自己写的界面，因为ID可能重复
     * api要求18及以上
     *
     * @param viewId
     * @return 是否点击成功
     */
    public boolean clickById(String viewId) {
        return performClick(findNodesById(viewId));
    }

    // 模拟点击
    private boolean performClick(List<AccessibilityNodeInfo> nodeInfos) {
        if (nodeInfos != null && !nodeInfos.isEmpty()) {
            AccessibilityNodeInfo node;
            for (int i = 0; i < nodeInfos.size(); i++) {
                node = nodeInfos.get(i);
                // 获得点击View的类型
                AccessibilityLog.printLog("View类型：" + node.getClassName());
                // 进行模拟点击
                if (node.isEnabled()) {
                    return node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
        return false;
    }

    /**
     * 根据View的ID搜索符合条件的节点,精确搜索方式 可能搜索所有的;
     * 这个只适用于自己写的界面，因为ID可能重复
     * api要求18及以上
     *
     * @param viewId
     */
    public List<AccessibilityNodeInfo> findNodesById(String viewId) {
        AccessibilityNodeInfo nodeInfo = getRootNodeInfo();
        if (nodeInfo != null) {
            return nodeInfo.findAccessibilityNodeInfosByViewId(viewId);
        }
        return null;
    }


    // 模拟点击back键
    public boolean clickBackKey() {
        return performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }

    private boolean performGlobalAction(int action) {
        return mAccessibilityService.performGlobalAction(action);
    }

}
