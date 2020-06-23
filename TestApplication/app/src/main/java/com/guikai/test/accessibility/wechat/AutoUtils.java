package com.guikai.test.accessibility.wechat;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 界面—自动化 工具类
 * Crete by Anding on 2020/6/19
 */
public class AutoUtils {

    int mStep;

    //======================================================================================
    //==================================== Open WeChat =====================================
    //======================================================================================
    /* 第一种方式 */
    public static void startWeChat(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        start_wechat_package(context, intent);
    }

    public static void start_wechat_package(Context context, Intent intent) {
        if (isWeChatAvilible(context)) {
            return;
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static boolean isWeChatAvilible(Context context) {
        List<PackageInfo> pinfo = context.getPackageManager().getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                if (((PackageInfo) pinfo.get(i)).packageName.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /* 第二种方式 */
    public static boolean startWeChat2(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            context.startActivity(packageManager.getLaunchIntentForPackage(packageName)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //======================================================================================
    //=========================== Accessibility Method =====================================
    //======================================================================================

    /* 查找内容为text的第一个Node 并点击 */
    public boolean findTextAndClick(AccessibilityNodeInfo node, int eType, String text, int now) {
        if (node != null && eType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            List<AccessibilityNodeInfo> ls = node.findAccessibilityNodeInfosByText(text);
            for (AccessibilityNodeInfo info : ls) {
                CharSequence cs = info.getText();
                if (cs != null && TextUtils.equals(cs.toString(), text)) {
                    clickParent(info);
                    sleep(1000);
                    mStep = now;
                    return true;
                }
            }
        }
        return false;
    }

    /* 查找内容为text的所有Node 并返回列表 */
    public List<AccessibilityNodeInfo> findText(AccessibilityNodeInfo node, int eType, String text) {
        List<AccessibilityNodeInfo> result = new ArrayList<>();
        if (node != null && eType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            List<AccessibilityNodeInfo> ls = node.findAccessibilityNodeInfosByText(text);
            for (AccessibilityNodeInfo info : ls) {
                CharSequence cs = info.getText();
                if (cs != null && TextUtils.equals(cs.toString(), text)) {
                    result.add(info);
                }
            }
        }
        return result;
    }

    /* (递归所有子view) 根据全局查找内容为text的所有Node 并返回列表 */
    public List<AccessibilityNodeInfo> findByText(String txt, AccessibilityNodeInfo node) {
        List<AccessibilityNodeInfo> ls = new ArrayList<>();
        searchNodeByText(txt, node, ls);
        return ls;
    }

    /* 同上(递归所有子view) 根据全局查找内容为text的所有Node 并返回列表 */
    private void searchNodeByText(String txt, AccessibilityNodeInfo node, @NonNull List<AccessibilityNodeInfo> ls) {
        if (node == null) return;
        CharSequence cs = node.getText();
        if (cs != null && cs.toString().equals(txt)) {
            ls.add(node);
        }
        for (int i = 0, size = node.getChildCount(); i < size; i++) {
            searchNodeByText(txt, node.getChild(i), ls);
        }
    }

    /* (递归所有子view) 根据全局查找内容包含text的所有Node 并返回列表 */
    protected @NonNull
    List<AccessibilityNodeInfo> findByTextContain(String txt, AccessibilityNodeInfo node) {
        List<AccessibilityNodeInfo> ls = new ArrayList<>();
        searchNodeByTextContain(txt, node, ls);
        return ls;
    }

    /* 同上(递归所有子view) 根据全局查找内容包含text的所有Node 并返回列表 */
    private void searchNodeByTextContain(String txt, AccessibilityNodeInfo node,
                                         @NonNull List<AccessibilityNodeInfo> ls) {
        if (node == null) return;
        CharSequence cs = node.getText();
        if (cs != null && cs.toString().contains(txt)) {
            ls.add(node);
        }
        for (int i = 0, size = node.getChildCount(); i < size; i++) {
            searchNodeByTextContain(txt, node.getChild(i), ls);
        }
    }

    /* 查找内容包含text的所有Node 并点击 */
    protected boolean findTextContainAndClick(AccessibilityNodeInfo node, int eType, String text, int now) {
        if (node != null && eType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            List<AccessibilityNodeInfo> ls = node.findAccessibilityNodeInfosByText(text);
            for (AccessibilityNodeInfo info : ls) {
                CharSequence cs = info.getText();
                if (cs != null && cs.toString().contains(text)) {
                    clickParent(info);
                    sleep(1000);
                    mStep = now;
                    return true;
                }
            }
        }
        return false;
    }

    /* 查找内容包含text的所有Node 并返回列表 */
    protected @NonNull
    List<AccessibilityNodeInfo> findTextContain(AccessibilityNodeInfo node, int eType,
                                                String text) {
        List<AccessibilityNodeInfo> result = new ArrayList<>();

        if (node != null && eType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            List<AccessibilityNodeInfo> ls = node.findAccessibilityNodeInfosByText(text);
            for (AccessibilityNodeInfo info : ls) {

                CharSequence cs = info.getText();
                if (cs != null && cs.toString().contains(text)) {
                    result.add(info);
                }
            }
        }
        return result;
    }

    /* 查找所有View类型(TextView、ImageView)的节点 并返回列表 */
    protected @NonNull
    List<AccessibilityNodeInfo> findClass(AccessibilityNodeInfo node, int eType,
                                          String clsName) {
        List<AccessibilityNodeInfo> list = new ArrayList<>();
        if (node != null && eType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            for (int i = 0, size = node.getChildCount(); i < size; i++) {

                AccessibilityNodeInfo child = node.getChild(i);
                if (child != null) {
                    CharSequence cs = child.getClassName();
                    if (cs != null && TextUtils.equals(cs.toString(), clsName)) {
                        list.add(node.getChild(i));
                    } else {
                        if (child.getChildCount() > 0) {
                            List<AccessibilityNodeInfo> tmp = findClass(child, eType, clsName);
                            list.addAll(tmp);
                        }
                    }
                }
            }
        }
        return list;
    }

    /*  递归打印当前整个View视图的结构Log */
    private void logViewHierarchy(AccessibilityNodeInfo nodeInfo, final int depth) {
        if (nodeInfo == null) return;
        String spacerString = "";
        for (int i = 0; i < depth; ++i) {
            spacerString += '-';
        }
        //Log the info you care about here... I choice classname and view resource name, because they are simple, but interesting.
        Log.d("TAG", spacerString + nodeInfo.getClassName() + " "
                + nodeInfo.getViewIdResourceName());
        for (int i = 0, size = nodeInfo.getChildCount(); i < size; ++i) {
            logViewHierarchy(nodeInfo.getChild(i), depth + 1);
        }
    }

    /* 点击指定id (递归)*/
    protected void clickId(AccessibilityNodeInfo node, int eType, int now, String id, int compareEtype) {
        if (node != null && eType == compareEtype) {
            logViewHierarchy(node, 0);
            List<AccessibilityNodeInfo> ls = node.findAccessibilityNodeInfosByViewId(id);
            if (!ls.isEmpty()) {
                clickParent((ls.get(ls.size() - 1)));
                mStep = now;
            }
        }
    }

    /* 公共基础点击VIEW_ID (若不可点击 递归父节点) */
    protected boolean clickParent(AccessibilityNodeInfo node) {
        if (node != null) {
            if (node.isClickable()) {
                if (node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    return true;
                }
                return false;
            } else {
                return clickParent(node.getParent());
            }
        }
        return false;
    }

    /* 点击Node (不递归) */
    protected boolean clickNode(AccessibilityNodeInfo node) {
        if (node != null) {
            if (node.isClickable()) {
                if (node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /* 查找(Description描述内容)为text的Node并点击 */
    public boolean findContentDescriptionAndClick(AccessibilityNodeInfo node, int eType, String text, int now) {
        if (node != null && eType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            List<AccessibilityNodeInfo> ls = node.findAccessibilityNodeInfosByText(text);
            for (AccessibilityNodeInfo info : ls) {
                CharSequence cs = info.getContentDescription();
                if (cs != null) {
                    if (TextUtils.equals(cs.toString(), text)) {
                        clickParent(info);
                        sleep(1000);
                        mStep = now;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* 查找(Description描述内容)为text的Node并长按 */
    public boolean findContentDescriptionAndLongClick(AccessibilityNodeInfo node, int eType, String text, int now) {
        if (node != null && eType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            List<AccessibilityNodeInfo> ls = node.findAccessibilityNodeInfosByText(text);
            for (AccessibilityNodeInfo info : ls) {
                CharSequence cs = info.getContentDescription();
                if (cs != null) {
                    if (TextUtils.equals(cs.toString(), text)) {
                        longClickParent(info);
                        sleep(1000);
                        mStep = now;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* 查找所有(Description描述内容)为text的Node 并返回列表 */
    public List<AccessibilityNodeInfo> findContentDescription(AccessibilityNodeInfo node, int eType, String text) {
        List<AccessibilityNodeInfo> result = new ArrayList<>();
        if (node != null && eType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            List<AccessibilityNodeInfo> ls = node.findAccessibilityNodeInfosByText(text);
            for (AccessibilityNodeInfo info : ls) {
                CharSequence cs = info.getContentDescription();
                if (cs != null) {
                    if (TextUtils.equals(cs.toString(), text)) {
                        sleep(1000);
                        result.add(info);
                    }
                }
            }
        }
        return result;
    }

    /* 查找所有(Description描述内容包含)text的Node的列表并返回 */
    public List<AccessibilityNodeInfo> findContentDescriptionContain(AccessibilityNodeInfo node, int eType, String text) {
        List<AccessibilityNodeInfo> result = new ArrayList<>();
        if (node != null && eType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            List<AccessibilityNodeInfo> ls = node.findAccessibilityNodeInfosByText(text);
            for (AccessibilityNodeInfo info : ls) {
                CharSequence cs = info.getContentDescription();
                if (cs != null) {
                    String t = cs.toString();
                    if (t.contains(text)) {
                        result.add(info);
                    }
                }
            }
        }
        return result;
    }

    /* 查找所有(Description描述内容包含)text的Node 并点击 */
    public boolean findContentDescriptionContainAndClick(AccessibilityNodeInfo node, int eType, String text, int now) {
        if (node != null && eType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            List<AccessibilityNodeInfo> ls = node.findAccessibilityNodeInfosByText(text);
            for (AccessibilityNodeInfo info : ls) {
                CharSequence cs = info.getContentDescription();
                if (cs != null) {
                    String t = cs.toString();
                    if (t.contains(text)) {
                        clickParent(info);
                        sleep(1000);
                        mStep = now;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* 公共基础长按Node (若不可长按 递归父节点) */
    protected boolean longClickParent(AccessibilityNodeInfo node) {
        if (node != null) {
            if (node.isClickable()) {
                if (node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
                    return true;
                }
                return false;
            } else {
                return longClickNode(node.getParent());
            }
        }
        return false;
    }

    /* 公共基础长按Node (无递归) */
    protected boolean longClickNode(AccessibilityNodeInfo node) {
        if (node != null) {
            if (node.isClickable()) {
                if (node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /* 给Node设置文本 (无递归) */
    protected void setText(String text, AccessibilityNodeInfo node) {
        if (node == null) return;
        Bundle arguments = new Bundle();
        arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
        node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
    }

    /* 基础Node滑动事件 */
    protected boolean scroll(AccessibilityNodeInfo node, int action, boolean isChildEffective) {
        if (node == null) return false;
        if (node.isScrollable()) {
            node.performAction(action);
            return true;
        }
        if (!isChildEffective) return false;
        for (int i = 0, size = node.getChildCount(); i < size; i++) {
            if (scroll(node.getChild(i), action, true)) {
                return true;
            }
        }
        return false;
    }

    /* Node上滑滚动事件 */
    protected boolean scrollForward(AccessibilityNodeInfo node, boolean isChildEffective) {
        return scroll(node, AccessibilityNodeInfo.ACTION_SCROLL_FORWARD, isChildEffective);
    }

    /* Node下滑滚动事件 */
    protected boolean scrollBackward(AccessibilityNodeInfo node, boolean isChildEffective) {
        return scroll(node, AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD, isChildEffective);
    }

    /* Node向下滚动事件 */
    protected boolean scrollDown(AccessibilityNodeInfo node, boolean isChildEffective) {
        return scroll(node, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN.getId(),
                isChildEffective);
    }

    /* Node向上滚动事件 */
    protected boolean scrollUp(AccessibilityNodeInfo node, boolean isChildEffective) {
        return scroll(node, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP.getId(),
                isChildEffective);
    }

    /* 基础 Node向上滚动事件执行一次 */
    protected boolean scrollAction(AccessibilityNodeInfo node) {
        if (node == null) return false;
        if (node.getChildCount() == 0) return false;
        for (int i = 0, size = node.getChildCount(); i < size; i++) {
            if (node.isScrollable()) {
                node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                sleep(5000);
                node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                return true;
            } else {
                if (scrollAction(node.getChild(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Node向上滚动事件执行一次 */
    protected boolean scrollOnce(AccessibilityNodeInfo node) {
        if (node == null) return false;
        if (node.getChildCount() == 0) return false;
        for (int i = 0, size = node.getChildCount(); i < size; i++) {
            if (node.isScrollable()) {
                node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                return true;
            } else {
                if (scrollAction(node.getChild(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* 延时事件 */
    public static boolean sleep(long ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* 点击Back键 */
    public boolean back(AccessibilityService service) {
        return performGlobalAction(service, AccessibilityService.GLOBAL_ACTION_BACK);
    }

    /* 点击Home键 */
    public boolean home(AccessibilityService service) {
        return performGlobalAction(service,AccessibilityService.GLOBAL_ACTION_HOME);
    }

    private boolean performGlobalAction(AccessibilityService service, int globalAction) {
        if (service == null)
            return false;
        return service.performGlobalAction(globalAction);
    }

}
