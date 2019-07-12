package com.example.opengl.filter;

import android.content.Context;

import com.example.opengl.R;

/**
 * @Description:
 * @Author: zhangzd
 * @CreateDate: 2019-07-12 13:59
 */
public class CameraFilter extends AbstrictFilter {
    public CameraFilter(Context context) {
        super(context, R.raw.camera_vertex, R.raw.camera_frag);
    }

    @Override
    protected void initCoordinate() {

    }
}
