
package com.example.rasik.firebasedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG="Main Activity";
  //  private static MainActivity ins;
    private TextView notificationTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationTextView = (TextView) findViewById(R.id.notify);
        notificationTextView.setText("hai");
      //  Intent intent = getIntent();
//        String message = intent.getStringExtra("message");
        //ins=this;
        getApplicationContext().registerReceiver(new RefreshNotificationDataReceiver(), new IntentFilter("com.example.rasik.firebasedemo.NOTIFICATION"));

    }

 /*   public static MainActivity  getInstace(){
        return ins;
    }*/

/*    public void updateTheTextView(final String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView textV1 = (TextView) findViewById(R.id.notify);
                textV1.setText(t);
            }
        });
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        getApplicationContext().registerReceiver(new RefreshNotificationDataReceiver(), new IntentFilter("com.example.rasik.firebasedemo.NOTIFICATION"));

    }

    public class RefreshNotificationDataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
           // MainActivity  .getInstace().updateTheTextView("String");
            Log.d(TAG,"received");
            String newlyComingStr = intent.getStringExtra("message");
            System.out.println("MainActivity"+newlyComingStr);
            notificationTextView.setText("Message: "+newlyComingStr);
        }
    }

}
