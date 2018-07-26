package javacore.jni.example1;

//import android.util.Log;
//
//import static android.content.ContentValues.TAG;

/**
 * Created by lidechen on 6/5/17.
 */

public class JavaClassDemoWrapperV1 {

//    private static final String TAG = "JavaClassDemoWrapper";

    static {
//        System.loadLibrary("demo");
        System.load("");
    }

    private int mCppObjWapper;

    public JavaClassDemoWrapperV1(){
        mCppObjWapper = getCppObjWrapper();
    }

    public void setTag(String tag){
        setTag(tag, mCppObjWapper);
    }

    public String getTag(){
        return getTag(mCppObjWapper);
    }

    @Override
    protected void finalize() throws Throwable {

        try {

//            Log.e(TAG, "finalize()");

            if (mCppObjWapper != 0) {
                release(mCppObjWapper);
                mCppObjWapper = 0;
            }
        }finally {
            super.finalize();
        }
    }

    //获取cpp对象指针
    public native int getCppObjWrapper();

    //调用cpp对象中对应的方法
    public native void setTag(String tag, int cppObjWapper);
    public native String getTag(int cppObjWapper);

    //释放cpp对象
    public native void release(int cppObjWapper);
}
