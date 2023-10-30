//package com.example.audioandvideocall;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
//
//
//    EditText userIdEditText;
//    Button startBtn;
//    @Override
//    protected void onCreate (Bundle savedInstanceState) (
//        super.onCreate (savedInstanceState);
//        setContentView (R.layout.activity_main);
//        userIdEditText = findViewById(R.id.user_id_edit_text);
//        startBtn = findViewById(R.id.start_btn);
//        startBtn.setOnClickListener((v)->{
//        String userID = userIdEditText.getText(). toString().trim();
//        if (userID. isEmpty ())(
//        return;
package com.example.audioandvideocall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {
    EditText userIdEditText;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //hi

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIdEditText = findViewById(R.id.user_id_edit_text);
        startBtn = findViewById(R.id.start_button);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = userIdEditText.getText().toString().trim();
                if (userID.isEmpty()) {
                    return;
                }

                // Add your logic here to handle the user's input
                //start services
                startService(userID);
                Intent intent = new Intent(MainActivity.this,callactivity.class);
                intent.putExtra("userID",userID);
                startActivity(intent);

            }
        });
    }

    void startService(String userID) {
        Application application = getApplication();
        long appID = 1929740140; // Replace with your actual App ID
        String appSign = "83603323e238167030c4663574ff5b169acdec5749180ef282e88e0ab765c628"; // Replace with your actual App Sign
        String userName = userID; // Replace with the user's name

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit=true;

        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound="zego_uikit_sound_call";
        notificationConfig.channelName= "CallInvitation";
       // notificationConfig.setSound("zego_uikit_sound_call");
        //notificationConfig.setChannelID("CallInvitation");

        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(),appID,appSign,userID,userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}
