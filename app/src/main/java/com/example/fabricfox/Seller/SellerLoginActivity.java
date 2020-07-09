package com.example.fabricfox.Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fabricfox.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SellerLoginActivity extends AppCompatActivity {


    private Button LoginSellerBtn;
    private EditText emailInput, passwordInput;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        mAuth = FirebaseAuth.getInstance();

        LoginSellerBtn=findViewById(R.id.seller_login_btn);

        emailInput = findViewById(R.id.seller_login_email);
        passwordInput = findViewById(R.id.seller_login_password);

        loadingBar = new ProgressDialog(this);


        LoginSellerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginSeller();

            }
        });
    }

    private void loginSeller()
    {
        final String email = emailInput.getText().toString();
        final String password = passwordInput.getText().toString();

        if (!email.equals("") &&!password.equals("")) {

            loadingBar.setTitle(" Seller Account Login");
            loadingBar.setMessage("Please Wait, While We Are Checking Your Credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            /*Toast.makeText(this, "Please Wait, While We Are Checking Your Credentials.", Toast.LENGTH_SHORT).show();*/

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(SellerLoginActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SellerLoginActivity.this, SellerHomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }

                        }
                    });


        }

        else
        {
            Toast.makeText(this, "Please complete the Login form", Toast.LENGTH_SHORT).show();

        }

    }
}