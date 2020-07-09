package com.example.fabricfox.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fabricfox.R;

public class SellerCategoryActivity extends AppCompatActivity {

    private ImageView tShirts, sportsTShirts, femaleShirts, sweaters;
    private ImageView menPants, menJackets, skirts, tights;
    private ImageView frocks, bottoms, sweatShirts, dresses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_category);

        tShirts= (ImageView) findViewById(R.id.t_shirts);
        sportsTShirts= (ImageView) findViewById(R.id.sports_t_shirts);
        femaleShirts= (ImageView) findViewById(R.id.female_shirts);
        sweaters= (ImageView) findViewById(R.id.sweaters);
        menPants= (ImageView) findViewById(R.id.men_pants);
        menJackets= (ImageView) findViewById(R.id.men_jackets);
        skirts= (ImageView) findViewById(R.id.skirts);
        tights= (ImageView) findViewById(R.id.tights);
        frocks= (ImageView) findViewById(R.id.frocks);
        bottoms= (ImageView) findViewById(R.id.bottoms);
        sweatShirts= (ImageView) findViewById(R.id.sweat_shirts);
        dresses= (ImageView) findViewById(R.id.dresses);



        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "tShirts");
                startActivity(intent);
            }
        });

        sportsTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "sportsTShirts");
                startActivity(intent);
            }
        });

        femaleShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "femaleShirts");
                startActivity(intent);
            }
        });

        sweaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "sweaters");
                startActivity(intent);
            }
        });

        menPants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "menPants");
                startActivity(intent);
            }
        });

        menJackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "menJackets");
                startActivity(intent);
            }
        });

        skirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "skirts");
                startActivity(intent);
            }
        });

        tights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "tights");
                startActivity(intent);
            }
        });

        frocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "frocks");
                startActivity(intent);
            }
        });

        bottoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "bottoms");
                startActivity(intent);
            }
        });

        sweatShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "sweatShirts");
                startActivity(intent);
            }
        });

        dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "dresses");
                startActivity(intent);
            }
        });
    }
}
