package com.guikai.test.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

import com.guikai.test.accessibility.utils.AccessibilityLog;
import com.guikai.test.accessibility.utils.AccessibilityOperator;

public class AccessibilitySampleService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        AccessibilityLog.printLog("辅助模式已启动 onServiceConnected");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityLog.printLog("辅助模式已启动 onAccessibilityEvent");

        // 此方法是在主线程中回调过来的，所以消息是阻塞执行的
        // 获取包名
        String pkgName = event.getPackageName().toString();
        int eventType = event.getEventType();
        AccessibilityOperator.getInstance().updateEvent(this, event);

        AccessibilityLog.printLog("eventType: " + eventType + " pkgName: " + pkgName);
        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                break;
        }
    }

    @Override
    public void onInterrupt() {

    }
}
