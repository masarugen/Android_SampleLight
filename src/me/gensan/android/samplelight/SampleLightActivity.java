package me.gensan.android.samplelight;

import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class SampleLightActivity extends Activity implements Callback {
	
	private Camera mCamera;
	private SurfaceView mSurfaceview;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // SurfaceViewを設定
        mSurfaceview = new SurfaceView(getApplicationContext());
        SurfaceHolder holder = mSurfaceview.getHolder();
        holder.addCallback(this);
        setContentView(mSurfaceview);
    }

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mCamera = Camera.open();
		Parameters parameters = mCamera.getParameters();
		parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
		mCamera.setParameters(parameters);
		try {
			mCamera.setPreviewDisplay(mSurfaceview.getHolder());
		} catch (IOException e) {
			e.printStackTrace();
		}
		mCamera.startPreview();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mCamera != null) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	}
}