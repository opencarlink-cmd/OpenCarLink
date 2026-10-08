package com.ucar.vehiclesdk.camera;

import android.util.Range;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.NonNull;
import com.ucar.vehiclesdk.UCarCommon;
import com.ucarhu.demo.vehicle.camera.CameraProvider;

public interface AbstractCamera {
    void changeConfiguration(@NonNull Surface surface, Size size, Range<Integer> range);

    void close();

    UCarCommon.CameraInfo getInfo() throws Exception;

    void open(@NonNull CameraProvider.e eVar);

    void startPreview(@NonNull Surface surface, @NonNull Range<Integer> range);

    void takePicture();
}
