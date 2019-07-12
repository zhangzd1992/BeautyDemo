package com.example.opengl;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraMetadata;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.opengl.filter.CameraFilter;
import com.example.opengl.filter.ScreenFilter;
import com.example.opengl.util.CameraHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRender implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener{
    GLView mView;
    private SurfaceTexture mSurfaceTure;
    private int mTextures[] = new int[1];
    private CameraHelper mCameraHelper;
    private float[] mtx = new float[16];
    private CameraFilter mCameraFilter;
    private ScreenFilter mScreenFilter;
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
        mSurfaceTure.getTransformMatrix(mtx);
        mCameraFilter = new CameraFilter(mView.getContext());
        mScreenFilter = new ScreenFilter(mView.getContext());

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }


    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        //当从流中获取到一帧数据时，就会回调该方法
        //手动请求GLSurfaceView 进行渲染，会调用onDrawFrame()
        mView.requestRender();
    }


    @Override
    public void onDrawFrame(GL10 gl) {

        //
        GLES20.glClearColor(0,0,0,0);
        //开始清理工作
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        //调用updateTexImage，引起onFrameAvailable回调
        mSurfaceTure.updateTexImage();
        int textureId = mCameraFilter.onDrawFrame(mTextures[0]);
        //讲最终的特效显示在surfaceview
        mScreenFilter.onDrawFrame(textureId);
    }


}
