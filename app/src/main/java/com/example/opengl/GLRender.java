package com.example.opengl;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraMetadata;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.opengl.util.CameraHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRender implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener,
        Camera.PreviewCallback{
    GLView mView;
    private SurfaceTexture mSurfaceTure;
    private int mTextures[] = new int[1];
    private CameraHelper mCameraHelper;
    GLRender(GLView glView) {
        this.mView = glView;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mCameraHelper = new CameraHelper(Camera.CameraInfo.CAMERA_FACING_BACK);
        //创建OpenGL纹理，并获取纹理ID
        GLES20.glGenTextures(mTextures.length,mTextures,0);
        //通过OpenGL纹理创建SurfaceTexture
        mSurfaceTure = new SurfaceTexture(mTextures[0]);
        mSurfaceTure.setOnFrameAvailableListener(this);
        mCameraHelper.setPreviewCallback(this);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        //当从流中获取到一帧数据时，就会回调该方法
        //手动请求GLSurfaceView 进行渲染，会调用onDrawFrame()
        mView.requestRender();
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

    }
}
