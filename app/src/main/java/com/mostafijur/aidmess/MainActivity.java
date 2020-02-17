package com.mostafijur.aidmess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView gotoManagerLoginPageTV;
    private EditText memberLoginPhoneET, memberLoginPassET;
    private Button memberLoginBTN;

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
                startActivity(new Intent(MainActivity.this, MemberDashBoardActivity.class));
                finish();
            }
        });
    }
}
