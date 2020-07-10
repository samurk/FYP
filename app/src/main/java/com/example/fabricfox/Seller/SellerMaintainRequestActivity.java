package com.example.fabricfox.Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fabricfox.Buyer.HomeActivity;
import com.example.fabricfox.Buyer.ProductDetailsActivity;
import com.example.fabricfox.Model.Products;
import com.example.fabricfox.Model.SellerRequest;
import com.example.fabricfox.Prevalent.Prevalent;
import com.example.fabricfox.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class SellerMaintainRequestActivity extends AppCompatActivity {

    private Button addToDbBtn;
    private ImageView productImage;
    private TextView productColor, productName, productDescription, productExtra;
    private String productID = "";
    private String state = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_maintain_request);

        addToDbBtn = (Button) findViewById(R.id.pd_add_to_database_btn);
        productImage = (ImageView) findViewById(R.id.request_product_image_details);
        productColor = (TextView) findViewById(R.id.request_product_color_details);
        productName = (TextView) findViewById(R.id.request_product_name_details);
        productDescription = (TextView) findViewById(R.id.request_product_description_details);
        productExtra = (TextView) findViewById(R.id.request_product_extra_details);

        productID = getIntent().getStringExtra("pid");

        getProductDetails(productID);

        addToDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(SellerMaintainRequestActivity.this, SellerCategoryActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onStart()
    {
        super.onStart();

    }

    private void getProductDetails(String productID)
    {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        productsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    SellerRequest products = snapshot.getValue(SellerRequest.class);

                    productName.setText(products.getPname());
                    productDescription.setText(products.getDescription());
                    productColor.setText(products.getPcolor());
                    productExtra.setText(products.getPextra());
                    Picasso.get().load(products.getImage()).into(productImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void CheckOrderState()
    {
        DatabaseReference ordersRef;

        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders")
                .child(Prevalent.currentOnlineUser.getPhone());

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                if(snapshot.exists())
                {
                    String shippingState = snapshot.child("state").toString();

                    if(shippingState.equals("shipped"))
                    {
                        state = "Order Shipped";
                    }
                    else if(shippingState.equals("not shipped"))
                    {
                        state = "Order Placed";
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}