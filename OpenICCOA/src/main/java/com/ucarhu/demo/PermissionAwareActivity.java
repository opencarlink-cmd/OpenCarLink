package com.ucarhu.demo;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.Arrays;

/**
 * 带运行时权限检查的 Activity 基类。
 *
 * 子类只需要声明必需权限，并实现“权限通过/拒绝”两个回调；这里统一处理申请、
 * 结果回收和重复检查，避免主界面掺入权限模板代码。
 */
public abstract class PermissionAwareActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 239;

    private String[] pendingPermissions = new String[0];

    private boolean arePermissionsGranted(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(this, str) != 0) {
                Log.d(getClass().getSimpleName(), "checkSelfPermissions: " + str + " does not granted");
                return false;
            }
        }
        return true;
    }

    protected abstract String[] requiredPermissions();

    protected abstract void onRequiredPermissionsDenied();

    protected abstract void onRequiredPermissionsGranted();

    @Override
    protected void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        String[] requiredPermissions = requiredPermissions();
        this.pendingPermissions = requiredPermissions;
        if (arePermissionsGranted(requiredPermissions)) {
            onRequiredPermissionsGranted();
        } else {
            ActivityCompat.requestPermissions(this, this.pendingPermissions, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public final void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        Log.d(getClass().getSimpleName(), "onRequestPermissionsResult: " + Arrays.toString(iArr));
        if (arePermissionsGranted(this.pendingPermissions)) {
            onRequiredPermissionsGranted();
        } else {
            onRequiredPermissionsDenied();
        }
    }
}
