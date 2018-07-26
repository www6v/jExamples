package javacore.jni.example1;

//import android.util.Log;
//
//import static android.content.ContentValues.TAG;

/**
 * Created by lidechen on 6/5/17.
 */

public class JavaClassDemoWrapper {

//    private static final String TAG = "JavaClassDemoWrapper";

    static {
//        System.loadLibrary("demo");
        System.load("");
    }

    private CppClassWrapper mCppObjWapper;

    public JavaClassDemoWrapper(){
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

//            if (mCppObjWapper != 0) {
                release(mCppObjWapper);
//                mCppObjWapper = 0;
//            }
        }finally {
            super.finalize();
        }
    }

    //获取cpp对象指针
    public native CppClassWrapper getCppObjWrapper();

    //调用cpp对象中对应的方法
    public native void setTag(String tag, CppClassWrapper cppObjWapper);
    public native String getTag(CppClassWrapper cppObjWapper);

    //释放cpp对象
    public native void release(CppClassWrapper cppObjWapper);
}
