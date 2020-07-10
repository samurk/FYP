package com.example.fabricfox.Buyer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fabricfox.Prevalent.Prevalent;
import com.example.fabricfox.R;
import com.example.fabricfox.Seller.SellerAddNewProductActivity;
import com.example.fabricfox.Seller.SellerHomeActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class RequestItemsActivity extends AppCompatActivity {

    private String Extra, Description, Color, PName, saveCurrentDate, saveCurrentTime;
    private Button AddNewProductBtn;
    private EditText InputProductName, InputProductDescription, InputProductColor, InputProductExtra;
    private ImageView InputProductImage;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductRef, userRef;
    private ProgressDialog loadingBar;
    private String sName, sPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_items);


        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Request Images");
        ProductRef = FirebaseDatabase.getInstance().getReference().child("Requests");
        /*userRef = FirebaseDatabase.getInstance().getReference().child("Users");*/



        AddNewProductBtn = (Button) findViewById(R.id.request_new_product_btn);
        InputProductImage = (ImageView) findViewById(R.id.request_select_product_image);
        InputProductName = (EditText) findViewById(R.id.request_product_name);
        InputProductDescription = (EditText) findViewById(R.id.request_product_description);
        InputProductColor = (EditText) findViewById(R.id.request_product_color);
        InputProductExtra = (EditText) findViewById(R.id.request_product_extra);
        loadingBar = new ProgressDialog(this);

        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenGallery();
            }
        });

        AddNewProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateProductData();
            }
        });

        userRef = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(Prevalent.currentOnlineUser.getPhone());

        userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists())
                        {
                            sName = snapshot.child("name").getValue().toString();
                            sPhone = snapshot.child("phone").getValue().toString();



                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GalleryPick && resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }

    private void ValidateProductData()
    {
        Extra = InputProductExtra.getText().toString();
        Description = InputProductDescription.getText().toString();
        Color = InputProductColor.getText().toString();
        PName = InputProductName.getText().toString();


        if(ImageUri == null)
        {
            Toast.makeText(this, "Product Image is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Extra))
        {
            Toast.makeText(this, "Please write Product Extra.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Please write Product Description.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Color))
        {
            Toast.makeText(this, "Please write Product Price.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(PName))
        {
            Toast.makeText(this, "Please write Product Name.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreProductInformation();
        }

    }

    private void StoreProductInformation()
    {
        loadingBar.setTitle("Add new Product");
        loadingBar.setMessage("Please Wait, While We Are adding the new product.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calender = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM,dd,yyyy");
        saveCurrentDate = currentDate.format(calender.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss,a");
        saveCurrentTime = currentTime.format(calender.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(RequestItemsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(RequestItemsActivity.this, "Product Image Uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if(task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(RequestItemsActivity.this, "Got Image Url successfully.", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveProductInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pid",productRandomKey);
        productMap.put("date",saveCurrentDate);
        productMap.put("time",saveCurrentTime);
        productMap.put("description",Description);
        productMap.put("image",downloadImageUrl);
        productMap.put("pname", PName);
        productMap.put("pcolor", Color);
        productMap.put("pextra", Extra);
        productMap.put("name", sName);
        productMap.put("phone",sPhone);
        productMap.put("requestState", "Not Available");


        ProductRef.child(productRandomKey).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Intent intent = new Intent(RequestItemsActivity.this , HomeActivity.class);
                    startActivity(intent);

                    loadingBar.dismiss();
                    Toast.makeText(RequestItemsActivity.this, "Product is Requested Successfully.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loadingBar.dismiss();
                    String message = task.getException().toString();
                    Toast.makeText(RequestItemsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}