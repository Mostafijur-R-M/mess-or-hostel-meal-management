package com.mostafijur.aidmess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagerLoginActivity extends AppCompatActivity {

    private EditText managerLoginPhoneET, managerLoginPassET;
    private Button managerLoginBTN;
    String getPhone = "", getPassword = "";

    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        managerLoginPhoneET = findViewById(R.id.manager_login_phone_et_id);
        managerLoginPassET = findViewById(R.id.manager_login_pass_et_id);
        managerLoginBTN = findViewById(R.id.manager_login_btn_id);

        mRef = FirebaseDatabase.getInstance().getReference().child("Manager");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null){
                    getPhone = dataSnapshot.child("phoneNumber").getValue().toString();
                    getPassword = dataSnapshot.child("password").getValue().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        managerLoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProcessing();
            }
        });

    }

    private void loginProcessing() {

        String phone = managerLoginPhoneET.getText().toString();
        String password = managerLoginPassET.getText().toString();

        Log.e("asdf", "kkkkk"+getPhone);

        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Fill in the blank field", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Fill in the blank field", Toast.LENGTH_SHORT).show();
        }else {
            //allowLoginPage();
            if (phone.equals(getPhone) && password.equals(getPassword)){
                startActivity(new Intent(ManagerLoginActivity.this, ManagerDashBoardActivity.class));
                finish();
            }else {
                Toast.makeText(this, "Wrong phone number or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void allowLoginPage() {

    }
}
