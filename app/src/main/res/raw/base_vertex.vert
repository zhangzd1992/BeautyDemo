//顶点着色器

//定义变量，用于接收定点坐标，确实绘制的形状
attribute vec4 vPosition;
//接收纹理坐标  接收采样器采样图片的坐标
attribute vec4 vCoord;
//传递给片元着色器
varying vec4 aCoord;
void main() {
    gl_Position = vPosition;
    aCoord = vCoord;
}