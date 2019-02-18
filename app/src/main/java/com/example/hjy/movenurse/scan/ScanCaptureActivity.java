package com.example.hjy.movenurse.scan;

import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseActivity;
import com.fy.entity.RxBean;
import com.fy.eventbus.RxBus;
import com.fy.utils.JumpUtils;
import com.fy.utils.T;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 扫描二维码界面
 * Created by fangs on 2017/9/4.
 */
public class ScanCaptureActivity extends BaseActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;
    private CameraManager mCameraManager;

    @BindView(R.id.capture_preview)
    FrameLayout scanPreview;
    @BindView(R.id.capture_container)
    RelativeLayout scanContainer;
    @BindView(R.id.capture_crop_view)
    RelativeLayout scanCropView;
    @BindView(R.id.capture_scan_line)
    ImageView scanLine;

    private Rect mCropRect = null;
    private boolean barcodeScanned = false;
    private boolean previewing = true;
    private ImageScanner mImageScanner = null;

    static {
        System.loadLibrary("iconv");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_scan_capture;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setActTitle(R.string.scanQRcode);
        tvMenu.setVisibility(View.INVISIBLE);

        initViews();
    }

    @OnClick({R.id.tvInIdNum, R.id.capture_restart_scan})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.tvInIdNum://手动输入
                JumpUtils.jump(mContext, ManualInputActivity.class, null);
                finish();
                break;
            case R.id.capture_restart_scan://重置扫描
                releaseScan();
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != mCameraManager){
            mCameraManager.closeDriver();
        }
    }

    private void initViews() {
        mImageScanner = new ImageScanner();
        mImageScanner.setConfig(0, Config.X_DENSITY, 3);
        mImageScanner.setConfig(0, Config.Y_DENSITY, 3);

        autoFocusHandler = new Handler();
        mCameraManager = new CameraManager(this);
        try {
            mCameraManager.openDriver();
        } catch (Exception e) {
            Toast.makeText(getApplication(), "无法获取摄像头权限，请检查是否打开摄像头权限！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // 调整扫描框大小,自适应屏幕
        Display display = this.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) scanCropView
                .getLayoutParams();
        linearParams.height = (int) (width * 0.25);
        linearParams.width = (int) (width * 0.35);

        scanCropView.setLayoutParams(linearParams);

        mCamera = mCameraManager.getCamera();
        mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
        scanPreview.addView(mPreview);

        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.85f);
        animation.setDuration(5000);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.REVERSE);
        scanLine.startAnimation(animation);
    }

    //重新扫描
    private void releaseScan(){
        if (barcodeScanned) {
            scanPreview.removeView(mPreview);

            initViews();
            mCamera.startPreview();
            previewing = true;
            barcodeScanned = false;
        }
    }

    private void releaseCamera() {
        if (mCamera != null) {
            barcodeScanned = true;
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    Camera.PreviewCallback previewCb = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Size size = camera.getParameters().getPreviewSize();

            // 这里需要将获取的data翻转一下，因为相机默认拿的的横屏的数据
            byte[] rotatedData = new byte[data.length];
            for (int y = 0; y < size.height; y++) {
                for (int x = 0; x < size.width; x++)
                    rotatedData[x * size.height + size.height - y - 1] = data[x
                            + y * size.width];
            }

            // 宽高也要调整
            int tmp = size.width;
            size.width = size.height;
            size.height = tmp;

            initCrop();

            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(rotatedData);
            barcode.setCrop(mCropRect.left, mCropRect.top, mCropRect.width(), mCropRect.height());

            int result = mImageScanner.scanImage(barcode);
            String resultStr = null;

            if (result != 0) {
                SymbolSet syms = mImageScanner.getResults();
                for (Symbol sym : syms) {
                    resultStr = sym.getData();
                }
            }

            if (!TextUtils.isEmpty(resultStr)) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();

                releaseCamera();
                barcodeScanned = true;


                if (!TextUtils.isEmpty(resultStr)) {
                    T.showLong(resultStr);//TODO 临时显示 扫描的结果

                    String finalResultStr = resultStr;
                    mContext.runOnUiThread(() -> {
                        RxBus.getInstance().send(new RxBean("SCAN_RESULT#", finalResultStr));
                        JumpUtils.exitActivity(mContext);
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "无效二维码", Toast.LENGTH_SHORT).show();
                    mContext.runOnUiThread(() -> {
                        JumpUtils.jump(mContext, ScanErrorActivity.class, null);
                    });
                }
            }
        }
    };

    // Mimic continuous auto-focusing
    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };

    /**
     * 初始化截取的矩形区域
     */
    private void initCrop() {
        int cameraWidth = mCameraManager.getCameraResolution().y;
        int cameraHeight = mCameraManager.getCameraResolution().x;

        /** 获取布局中扫描框的位置信息 */
        int[] location = new int[2];
        scanCropView.getLocationInWindow(location);

        int cropLeft = location[0];
        int cropTop = location[1] - getStatusBarHeight();

        int cropWidth = scanCropView.getWidth();
        int cropHeight = scanCropView.getHeight();

        /** 获取布局容器的宽高 */
        int containerWidth = scanContainer.getWidth();
        int containerHeight = scanContainer.getHeight();

        /** 计算最终截取的矩形的左上角顶点x坐标 */
        int x = cropLeft * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的左上角顶点y坐标 */
        int y = cropTop * cameraHeight / containerHeight;

        /** 计算最终截取的矩形的宽度 */
        int width = cropWidth * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的高度 */
        int height = cropHeight * cameraHeight / containerHeight;

        /** 生成最终的截取的矩形 */
        mCropRect = new Rect(x, y, width + x, height + y);
    }

    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
