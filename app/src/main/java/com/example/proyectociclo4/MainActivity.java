package com.example.proyectociclo4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView createNewAccount;

    EditText inputEmail, inputPassword;
    Button btnLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    //ImageView btnGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewAccount=findViewById(R.id.createNewAccount);

        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        btnLogin=findViewById(R.id.btnLogin);
        //btnGoogle.findViewById(R.id.btnGoogle);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();

            }
        });
        //btnGoogle.setOnClickListener(new View.OnClickListener() {
         //  public void onClick(View v) {
           //     Intent intent= new Intent(MainActivity.this, HomeActivity.class);
             //   startActivity(intent);
            //}
        //}
        //);

    }

    private void performLogin() {
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();


        if (!email.matches(emailPattern)){

            inputEmail.setError("Ingrese el Email");

        } else if(password.isEmpty() || password.length()<6){
            inputPassword.setError("Ingrese contraseña adecuada");
        }  else{
            progressDialog.setMessage("Por Favor espere Mientras Inicia sesion");
            progressDialog.setTitle("Iniciando sesion");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       progressDialog.dismiss();
                       sendUserToNextActivity();
                       Toast.makeText(MainActivity.this, "logeado Satisfactoriamente", Toast.LENGTH_SHORT).show();

                   }else {
                       progressDialog.dismiss();
                       Toast.makeText(MainActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(MainActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}