package com.mk.hotfixdemo;

import android.app.Application;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Created by liguoying on 2017/10/12.
 */

public class MyApplication extends Application {

    private String appVersion = "1.0.0";
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize最好放在attachBaseContext最前面，初始化直接在Application类里面，切勿封装到其他类
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Log.e(TAG, "onLoad success: " + mode + " " + code + " " + info + handlePatchVersion);
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            Log.e(TAG, "onLoad CODE_LOAD_RELAUNCH : " + mode + " " + code + " " + info + handlePatchVersion);

                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                        } else {
                            Log.e(TAG, "onLoad: error " + mode + " " + code + " " + info + handlePatchVersion);

                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
// queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
//        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
