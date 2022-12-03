package com.example.elearningapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearningapplication.Model.UsersModel;
import com.example.elearningapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
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

    public static String uname, upass, name;
   // public static String outputname = "";
    private EditText usernameLogin,passwordLogin;
    private Button btnlogin;
    private CheckBox show_password;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("ELearningUsers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLogin = findViewById(R.id.my_uname);
        passwordLogin = findViewById(R.id.my_pass);
        btnlogin = findViewById(R.id.btn_login);
        show_password = findViewById(R.id.cb_showpassword);

        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (show_password.isChecked()){
                    //for true visibility
                    passwordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{

                    //for false visibility
                    passwordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

    public void login(View view) {

        if (!usernameLogin.getText().toString().isEmpty() && !passwordLogin.getText().toString().isEmpty()) {
            DocumentReference documentReference = db.collection("ELearningUsers").document(usernameLogin.getText().toString());
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        uname = documentSnapshot.getString(MY_USERNAME).toString();
                        upass = documentSnapshot.getString(MY_PASSWORD).toString();
                        if (usernameLogin.getText().toString().equals(uname) && passwordLogin.getText().toString().equals(upass)) {
                            collectionReference.whereEqualTo("username",uname).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    String outputname = "";
                                    for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                                        UsersModel usersModel = documentSnapshot.toObject(UsersModel.class);
                                        usersModel.setMyid(documentSnapshot.getId());

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
                                            Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                            alertDialogmyPicture.dismiss();
                                            startActivity(new Intent(Login.this, QuarterOne.class));
                                        }
                                    });

                                   // nameofuser.setText(outputname);

//                                    AlertDialog.Builder alBuilder = new AlertDialog.Builder(Login.this);
//                                    String msg = "Welcome \n" + outputname;
//                                    alBuilder.setTitle("Message")
//                                            .setMessage(msg);
//                                    alBuilder.setPositiveButton("Thank you", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.dismiss();
//                                            Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                                            startActivity(new Intent(Login.this, QuarterOne.class));
//
//                                        }
//                                    });
//                                    alBuilder.create().show();
                                }

                            });
                        } else {
                            Toast.makeText(Login.this, "Username and Password not match", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Login.this, "You don't have an account", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            if (usernameLogin.getText().toString().isEmpty() && passwordLogin.getText().toString().isEmpty()){
                usernameLogin.setError("Required Field");
                passwordLogin.setError("Required Field");
                Toast.makeText(Login.this, "Username and Password Required", Toast.LENGTH_LONG).show();
            }else if (usernameLogin.getText().toString().isEmpty()){
                usernameLogin.setError("Required Field");
            }else{
                passwordLogin.setError("Required Field");
            }

        }
    }

    public void GotoReg(View view) {
        startActivity(new Intent(Login.this, Registration.class));
    }
}