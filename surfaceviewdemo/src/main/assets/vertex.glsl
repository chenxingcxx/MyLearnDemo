uniform mat4 uMVPMatrix;  //顶点最终变换矩阵
attribute vec3 aPostion;  //顶点坐标值（x,y,z）
attribute vec4 aColor;  //顶点的颜色（R,G,B,A）
varying vec4 vColor;   //传递给片段着色器的颜色值,varying声明的变量都是要传递给fragment的

void main(){
    //gl_Position是glsl的内置变量，记录顶点最终的位置 。
    //vec4(aPostion,1)为是矩阵想成匹配
    gl_Position = uMVPMatrix * vec4(aPostion,1);
    vColor = aColor;//将顶点颜色值传递给fragment
}