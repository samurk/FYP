package com.example.fabricfox.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fabricfox.Admin.CheckNewProductsActivity;
import com.example.fabricfox.Admin.RetailerNewOrdersActivity;
import com.example.fabricfox.Buyer.HomeActivity;
import com.example.fabricfox.Buyer.MainActivity;
import com.example.fabricfox.R;

public class AdminHomeActivity extends AppCompatActivity {


    private Button LogOutBtn, CheckOrdersBtn, maintainProductsBtn, checkApproveProductsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        LogOutBtn = (Button) findViewById(R.id.admin_logout_btn);
        CheckOrdersBtn = (Button) findViewById(R.id.check_orders_btn);
        maintainProductsBtn = (Button) findViewById(R.id.maintain_btn);

        checkApproveProductsBtn = (Button) findViewById(R.id.check_approve_products_btn);


        maintainProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, HomeActivity.class);
                intent.putExtra("Retailers", "Retailers");
                startActivity(intent);
            }
        });

        LogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });

        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminHomeActivity.this, RetailerNewOrdersActivity.class);
                startActivity(intent);
            }
        });


        checkApproveProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminHomeActivity.this, CheckNewProductsActivity.class);
                startActivity(intent);
            }
        });
    }
}