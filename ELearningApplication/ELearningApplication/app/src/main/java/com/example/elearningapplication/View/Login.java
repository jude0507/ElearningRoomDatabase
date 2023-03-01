package com.example.elearningapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearningapplication.Model.UsersModel;
import com.example.elearningapplication.Quarter.QuarterOne;
import com.example.elearningapplication.R;
import com.example.elearningapplication.ShowPassword;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {


    static final String TAG = "Login";
    static final String MY_NAME = "name";
    static final String MY_USERNAME = "username";
    static final String MY_PASSWORD = "password";

    public static String PREFS_Name = "MyFilePreps";

    public static String Email_Login, Password_Login, Name;
    //public static String uname, upass, name;
    ProgressDialog progressDialog;
   public static String outputname;
    private EditText emailLogin,passwordLogin;
    private Button btnlogin;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("ELearningUsers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin = findViewById(R.id.email);
        passwordLogin = findViewById(R.id.password);
        ShowPassword showPassword = new ShowPassword();
        showPassword.ShowPassword(passwordLogin);

    }

    public void GotoReg(View view) {
        startActivity(new Intent(Login.this, Registration.class));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }

    public void LoginFunction(View view) {
        if (!emailLogin.getText().toString().trim().isEmpty() && !passwordLogin.getText().toString().trim().isEmpty()){
            progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog_account_checking);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            DocumentReference documentReference = db.collection("ELearningUsers").document(emailLogin.getText().toString());
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()){
                         Email_Login = documentSnapshot.getString("username");
                         Password_Login = documentSnapshot.getString("password");
                         Name = documentSnapshot.getString("name");

                        if (emailLogin.getText().toString().equals(Email_Login) && passwordLogin.getText().toString().equals(Password_Login)){
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            collectionReference.whereEqualTo("username", Email_Login).get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            outputname = "";
                                            for (QueryDocumentSnapshot documentSnapshot1 : queryDocumentSnapshots){
                                                UsersModel usersModel = documentSnapshot1.toObject(UsersModel.class);
                                                usersModel.setMyid(documentSnapshot1.getId());
                                                String set_uname = usersModel.getUsername();
                                                String set_name = usersModel.getName();
                                                outputname += set_name;
                                            }
                                            AlertDialog.Builder myBuilder = new AlertDialog.Builder(Login.this);
                                            LayoutInflater myinflater = getLayoutInflater();
                                            View mydialogView = myinflater.inflate(R.layout.greetingdialog,null);
                                            myBuilder.setCancelable(false);
                                            myBuilder.setView(mydialogView);

                                            ImageView imageViewOk = mydialogView.findViewById(R.id.close_dialog);
                                            TextView txtshowname = mydialogView.findViewById(R.id.displayUsername);

                                            final AlertDialog alertDialogmyPicture = myBuilder.create();
                                            alertDialogmyPicture.show();

                                            txtshowname.setText(outputname);

                                            imageViewOk.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    alertDialogmyPicture.dismiss();
                                                    LoginPref();
                                                    startActivity(new Intent(Login.this, QuarterOne.class));
                                                }
                                            });
                                        }
                                    });

                        }



//                        firebaseAuth.signInWithEmailAndPassword(Email_Login, Password_Login)
//                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        if (task.isSuccessful()){
//
//                                            if (firebaseAuth.getCurrentUser().isEmailVerified()){
//                                                if (emailLogin.getText().toString().equals(Email_Login) && passwordLogin.getText().toString().equals(Password_Login)){
//                                                    VerifiedStatus();
//                                                    progressDialog.dismiss();
//                                                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                                                    collectionReference.whereEqualTo("username", Email_Login).get()
//                                                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                                                        @Override
//                                                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                                                            outputname = "";
//                                                                            for (QueryDocumentSnapshot documentSnapshot1 : queryDocumentSnapshots){
//                                                                                UsersModel usersModel = documentSnapshot1.toObject(UsersModel.class);
//                                                                                usersModel.setMyid(documentSnapshot1.getId());
//                                                                                String set_uname = usersModel.getUsername();
//                                                                                String set_name = usersModel.getName();
//
//                                                                                outputname += set_name;
//                                                                            }
//
//                                                                            AlertDialog.Builder myBuilder = new AlertDialog.Builder(Login.this);
//                                                                            LayoutInflater myinflater = getLayoutInflater();
//                                                                            View mydialogView = myinflater.inflate(R.layout.greetingdialog,null);
//                                                                            myBuilder.setCancelable(false);
//                                                                            myBuilder.setView(mydialogView);
//
//                                                                            ImageView imageViewOk = mydialogView.findViewById(R.id.close_dialog);
//                                                                            TextView txtshowname = mydialogView.findViewById(R.id.displayUsername);
//
//                                                                            final AlertDialog alertDialogmyPicture = myBuilder.create();
//                                                                            alertDialogmyPicture.show();
//
//                                                                            txtshowname.setText(outputname);
//
//                                                                            imageViewOk.setOnClickListener(new View.OnClickListener() {
//                                                                                @Override
//                                                                                public void onClick(View v) {
//                                                                                    alertDialogmyPicture.dismiss();
//                                                                                    startActivity(new Intent(Login.this, QuarterOne.class));
//                                                                                }
//                                                                            });
//
//                                                                        }
//                                                                    });
////                                                    startActivity(new Intent(Login.this, QuarterOne.class));
//                                                }else {
//                                                    progressDialog.dismiss();
//                                                    Toast.makeText(Login.this, "Credential not match", Toast.LENGTH_SHORT).show();
//                                                }
//                                            }else{
//                                                progressDialog.dismiss();
//                                                Toast.makeText(Login.this, "Please verify your email", Toast.LENGTH_SHORT).show();
//                                            }
//
//                                        }
//
//                                    }
//                                });
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, "You don't have an account", Toast.LENGTH_LONG).show();
                    }

                }
            });

        }else{
            if (emailLogin.getText().toString().isEmpty() && passwordLogin.getText().toString().isEmpty()){
                emailLogin.setError("Required Field");
                passwordLogin.setError("Required Field");
                Toast.makeText(Login.this, "Username and Password Required", Toast.LENGTH_LONG).show();
            }else if (emailLogin.getText().toString().isEmpty()){
                emailLogin.requestFocus();
                emailLogin.setError("Required Field");
            }else{
                passwordLogin.requestFocus();
                passwordLogin.setError("Required Field");
            }
        }

    }

    public void VerifiedStatus(){
        String Verified = "Verified";
        DocumentReference updateReference = db.collection("ElearningUsers").document(emailLogin.getText().toString());
        updateReference.update("email_status", Verified);
    }

    private void LoginPref(){
        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_Name, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("hasLoggedIn", true);
        editor.commit();

    }
}