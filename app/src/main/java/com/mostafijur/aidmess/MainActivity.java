package com.mostafijur.aidmess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView gotoManagerLoginPageTV;
    private EditText memberLoginPhoneET, memberLoginPassET;
    private Button memberLoginBTN;
    DatabaseReference mRef;
    String getPhone = "", getPassword = "",phone = "", password = "", aaaa = "01927033582";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoManagerLoginPageTV = findViewById(R.id.goto_manager_login_page_tv_id);
        memberLoginPhoneET = findViewById(R.id.member_login_phone_et_id);
        memberLoginPassET = findViewById(R.id.member_login_pass_et_id);
        memberLoginBTN = findViewById(R.id.member_login_btn_id);



        gotoManagerLoginPageTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ManagerLoginActivity.class));
            }
        });
        memberLoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginProcessing();
            }
        });

    }

    private void loginProcessing() {

        phone = memberLoginPhoneET.getText().toString();
        password = memberLoginPassET.getText().toString();



        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Fill in the blank field", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Fill in the blank field", Toast.LENGTH_SHORT).show();
        }else {
            //allowLoginPage();
            mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(phone);
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getValue() != null){
                        getPhone = dataSnapshot.child("phoneNumber").getValue().toString();
                        getPassword = dataSnapshot.child("pass").getValue().toString();
                        Log.e("asdf", "kkkkk"+getPhone);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                    //here you can have your logic to set text to edittext
                    long count = millisUntilFinished / 1000;

                    if (count == 1){
                        if (phone.equals(getPhone) && password.equals(getPassword)){
                            startActivity(new Intent(MainActivity.this, MemberDashBoardActivity.class));
                            finish();
                        }else {
                            Toast.makeText(MainActivity.this, "Wrong phone number or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                public void onFinish() {
                    //mTextField.setText("done!");
                }

            }.start();

        }
    }
}
