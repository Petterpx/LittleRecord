package com.petterp.latte_core.ui.camera;

import android.net.Uri;

import com.petterp.latte_core.delegates.PermissionCheckerDelegate;
import com.petterp.latte_core.util.file.FileUtil;

/**
 * @author Petterp on 2019/6/15
 * Summary:照相机调用类
 * email：1509492795@qq.com
 */
public class LatteCamera {
    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }

}
