package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.MacAddress;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private EditText userID, pwd;
    private String userinfo, userpwd;
    private RadioGroup radioGroup;
    private Context context;
    private WifiConfiguration wifiConfiguration;
    private DevicePolicyManager dpm;
    private ComponentName chuan;
    private String sex;
    private static final String TAG = "MainActivity";
    private TelephonyManager telephonyManager;

    //    private SubscriptionManager sm = SubscriptionManager.from(context);
    private SubscriptionInfo sb;


    @SuppressLint("AssertionSideEffect")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SubscriptionManager sm = SubscriptionManager.from(this);

        TelephonyManager tele = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
//        Log.e("elog","macaddress");
//        MacAddress macaddress = wifiConfiguration.getRandomizedMacAddress();
//        String wifimacaddress = dpm.getWifiMacAddress(chuan);
//        Log.e("elog","wifimacaddress" + wifimacaddress);
//        Log.e("elog","macaddress" + macaddress);
//        String serialNumber = Build.getSerial();
//        Log.e("elog","serial" + serialNumber);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
        try {
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
            @SuppressLint("MissingPermission") int a = sm.getActiveSubscriptionInfoCount();
            int b = sm.getActiveSubscriptionInfoCountMax();
            Log.e("elog","count是" + a);
            Log.e("elog","卡槽数量是" + b);
//            String icc = ConmonUtils.getIccid();
//            Log.e("elog","iccid是" + icc);
            @SuppressLint("MissingPermission") List<SubscriptionInfo> sis = sm.getActiveSubscriptionInfoList();
            Log.e("elog","当前list内容个数" + sis.size());
            if (sis.size() >= 1) {
                Log.e("elog","dididi");
                SubscriptionInfo si1 = sis.get(0);
                String iccId1 = si1.getIccId();
                Log.e("elog","here"+iccId1);
                String phoneNum1 = si1.getNumber();
            }
            if (sis.size() >= 2) {
                SubscriptionInfo si2 = sis.get(1);
                String iccId2 = si2.getIccId();
                Log.e("elog","here"+iccId2);
                String phoneNum2 = si2.getNumber();
            }
            Log.e("elog","hereaaaaaa");
            @SuppressLint("MissingPermission") int count = sm.getActiveSubscriptionInfoCount();
            int max = sm.getActiveSubscriptionInfoCountMax();
        } catch (Exception e) {
            Log.e("elog", "HERE:" + e.getClass());
            String ex = e.toString();
        }

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

////        isub
////                geticcid
        try {

//            String DEID = telephonyManager.getDeviceId();
//              String meid = telephonyManager.getImei();
            String meid = telephonyManager.getMeid();

//                geticcid
//                String meid = telephonyManager.getI();
//              String SSN = telephonyManager.getSimSerialNumber();
//              String SBID = telephonyManager.getSubscriberId();
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
             Log.e("mylog", "getmeid: " + meid);
        }catch (Exception e) {
//            assert e instanceof SecurityException :"An exception of the wrong type was thrown";
            Log.e("elog", "HERE:" + e.getClass());
            String ex = e.toString();
            Log.e("elog", "HERE:" + ex);
        }

        userID = findViewById(R.id.userID);
        pwd = findViewById(R.id.pwd);
        radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            //group就是radioGroup本身，checkedid接受的是用户选择的那个按钮id
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.nan){
                    sex = "male";
                } else if (checkedId == R.id.nv){
                    sex ="female";
                }
            }
        });

    }


    public void myclick(View view){
        userinfo = userID.getText().toString();
        userpwd = pwd.getText().toString();
        Toast.makeText(MainActivity.this, "user id is " + userinfo + " user password is " + userpwd + " sex is " + sex,Toast.LENGTH_SHORT).show();
    }
}