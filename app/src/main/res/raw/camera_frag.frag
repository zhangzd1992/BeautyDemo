#extension GL_OES_EGL_image_external : require
//片元着色器
precision mediump float;
//由顶点着色器传递过来的采样坐标点
varying vec2 aCoord;
uniform samplerExternalOES vTexture;


void main()
{
    //    倒 的   正的
    //变量 接收像素值
    // texture2D：采样器 采集 aCoord的像素
    //赋值给 gl_FragColor 就可以了
    gl_FragColor = texture2D(vTexture,aCoord);

}