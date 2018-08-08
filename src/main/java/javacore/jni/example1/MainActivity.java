package javacore.jni.example1;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;

//public class MainActivity extends AppCompatActivity {
public class MainActivity  {

//    private static final String TAG = "MainActivity";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
    public static void main(String args []) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        JavaClassDemoWrapper wrapper1 = new JavaClassDemoWrapper();
//        JavaClassDemoWrapper wrapper2 = new JavaClassDemoWrapper();
//        JavaClassDemoWrapper wrapper3 = new JavaClassDemoWrapper();

        wrapper1.setTag("I am wrapper1");
//        wrapper2.setTag("I am wrapper2");
//        wrapper3.setTag("I am wrapper3");

//        Log.i(TAG, "wrapper1 "+wrapper1.getTag());
//        Log.i(TAG, "wrapper2 "+wrapper2.getTag());
//        Log.i(TAG, "wrapper3 "+wrapper3.getTag());
    }


}
