package com.example.elearningapplication.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elearningapplication.Model.UsersModel;
import com.example.elearningapplication.R;
import com.example.elearningapplication.ShowPassword;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity {

    private EditText etName,etEmail,etPassword, etConfirmPassword;
    private Button btn_register;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.ConfirmPass);

        ShowPassword showPassword = new ShowPassword();
        showPassword.ShowPassword(etPassword);
        showPassword.ShowPassword(etConfirmPassword);

    }

    public void GotoLogin(View view) {
        startActivity(new Intent(Registration.this, Login.class));
    }

    public void RegistrationFunction(View view) {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirm_password = etConfirmPassword.getText().toString();
//Patterns.EMAIL_ADDRESS.matcher(email).matches()
        //&& email.matches("(.*)@depedpasay|.ph")
        if (!name.isEmpty() &&
                !password.isEmpty() && !confirm_password.isEmpty()){
            if (!email.isEmpty() && email.contains("@depedpasay.ph")){
                if (password.length() >= 6 && confirm_password.equals(password)){
                    AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
                    alBuilder.setTitle("Confirmation Message")
                            .setMessage("Please confirm to create your account");
                    alBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ProgressDialog progressDialog = new ProgressDialog(Registration.this);
                            progressDialog.show();
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.setContentView(R.layout.progress_dialog_registration);
                            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                            final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                            firebaseAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()){
                                                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            DocumentReference userReference = firestore.collection("ELearningUsers").document(email);
                                                            userReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.exists()){
                                                                        progressDialog.dismiss();
                                                                        Toast.makeText(Registration.this, "Email has been registered", Toast.LENGTH_LONG).show();
                                                                    }else{

                                                                        UsersModel userModel = new UsersModel();
                                                                        userModel.setName(name);
                                                                        userModel.setUsername(email);
                                                                        userModel.setPassword(password);

                                                                        firestore.collection("ELearningUsers").document(email).set(userModel);
                                                                        Toast.makeText(Registration.this, "User Registered Successfully! Please Verify your email", Toast.LENGTH_SHORT).show();
                                                                        Toast.makeText(Registration.this, "Please check your inbox/spam message", Toast.LENGTH_LONG).show();
                                                                        progressDialog.dismiss();
                                                                        clearField();


                                                                    }
                                                                }
                                                            });
                                                        }
                                                    }
                                                });
                                            }else{
                                                progressDialog.dismiss();
                                                Toast.makeText(Registration.this, "Email has been registered! Try Again", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }
                    });
                    alBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                    alBuilder.show();

                }else{
                    etPassword.setError("Incorrect Password");
                    etConfirmPassword.setError("Incorrect Password");
                }
            }else {
                etEmail.requestFocus();
                etEmail.setError("INVALID EMAIL");
            }

        }else{
            setErrors();
        }

    }

    void setErrors(){
        etName.setError("Required Field");
        etPassword.setError("Required Field");
        etConfirmPassword.setError("Required Field");
    }

    void clearField(){
        etName.setText(null);
        etPassword.setText(null);
        etConfirmPassword.setText(null);
        etEmail.setText(null);
    }
}