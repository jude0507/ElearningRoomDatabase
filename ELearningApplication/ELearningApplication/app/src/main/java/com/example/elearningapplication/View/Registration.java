package com.example.elearningapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elearningapplication.Model.UsersModel;
import com.example.elearningapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity {

    private EditText etname,etusername,etpassword;
    private Button btn_register;
    private CheckBox show_password;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etname = findViewById(R.id.et_name);
        etusername = findViewById(R.id.et_username);
        etpassword = findViewById(R.id.et_password);
        btn_register = findViewById(R.id.btn_register);
        show_password = findViewById(R.id.cb_showpassword);

        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (show_password.isChecked()){
                    //for true visibility
                    etpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{

                    //for false visibility
                    etpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    public void ONCLICKREG(View view) {

        String uname = etusername.getText().toString();
        String name = etname.getText().toString();
        String password = etpassword.getText().toString();

        if (!uname.isEmpty() && !name.isEmpty() && !password.isEmpty()){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait for a moment..........");
            progressDialog.show();
            DocumentReference documentReference = firestore.collection("ELearningUsers").document(uname);
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        Toast.makeText(Registration.this, "Username Registered", Toast.LENGTH_SHORT).show();
                    }else {

                        UsersModel usersModel = new UsersModel();
                        usersModel.setName(name);
                        usersModel.setUsername(uname);
                        usersModel.setPassword(password);
                        firestore.collection("ELearningUsers").document(uname).set(usersModel);
                        progressDialog.dismiss();
                        Toast.makeText(Registration.this, "Registration Successfully", Toast.LENGTH_SHORT).show();

                        etname.setText(null);
                        etusername.setText(null);
                        etpassword.setText(null);

                        startActivity(new Intent(Registration.this, Login.class));
                    }
                }
            });

        }else{
            etname.setError("Required field");
            etusername.setError("Required field");
            etpassword.setError("Required field");
        }
    }

    public void GotoLogin(View view) {
        startActivity(new Intent(Registration.this, Login.class));
    }
}