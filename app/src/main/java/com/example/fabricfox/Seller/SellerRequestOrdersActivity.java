package com.example.fabricfox.Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fabricfox.Admin.AdminUserProductsActivity;
import com.example.fabricfox.Admin.RetailerNewOrdersActivity;
import com.example.fabricfox.Model.RetailerOrders;
import com.example.fabricfox.Model.SellerRequest;
import com.example.fabricfox.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellerRequestOrdersActivity extends AppCompatActivity {

    private RecyclerView ordersList;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_request_orders);

        ordersRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        ordersList = findViewById(R.id.request_orders_list);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<SellerRequest> options =
                new FirebaseRecyclerOptions.Builder<SellerRequest>()
                        .setQuery(ordersRef, SellerRequest.class)
                        .build();

        FirebaseRecyclerAdapter<SellerRequest, SellerRequestsViewHolder> adapter =
                new FirebaseRecyclerAdapter<SellerRequest, SellerRequestsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull SellerRequestsViewHolder holder, final int position, @NonNull final SellerRequest model)
                    {
                        holder.userName.setText("Name: " + model.getName());
                        holder.userPhoneNumber.setText("Phone: " + model.getPhone());
                        holder.userProductName.setText("Product: " + model.getPname());
                        holder.userDateTime.setText("Date: " + model.getName() + "  " + model.getTime());
                        holder.productRequestState.setText("Request Status: " + model.getRequestState());

                        holder.ShowOrdersBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(SellerRequestOrdersActivity.this, SellerMaintainRequestActivity.class);
                                intent.putExtra("pid", model.getPid());
                                startActivity(intent);

                            }
                        });

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {

                                CharSequence options[] = new CharSequence[]
                                        {
                                                "Yes",
                                                "No"
                                        };

                                AlertDialog.Builder builder = new AlertDialog.Builder(SellerRequestOrdersActivity.this);
                                builder.setTitle("Do you have this product?");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        if (i == 0)
                                        {
                                            String uID = getRef(position).getKey();

                                            RemoverOrder(uID);
                                        }
                                        else
                                        {
                                            finish();
                                        }

                                    }
                                });

                                builder.show();


                            }
                        });

                    }

                    @NonNull
                    @Override
                    public SellerRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_layout, parent, false);
                        return new SellerRequestsViewHolder(view);
                    }
                };

        ordersList.setAdapter(adapter);
        adapter.startListening();
    }




    public static class SellerRequestsViewHolder extends RecyclerView.ViewHolder
    {
        public TextView userName, userPhoneNumber, userProductName, userDateTime, productRequestState;
        private Button ShowOrdersBtn;

        public SellerRequestsViewHolder(@NonNull View itemView)
        {
            super(itemView);

            userName = itemView.findViewById(R.id.request_user_name);
            userPhoneNumber = itemView.findViewById(R.id.request_phone_number);
            userProductName = itemView.findViewById(R.id.request_product_name);
            userDateTime = itemView.findViewById(R.id.request_date_time);
            productRequestState = itemView.findViewById(R.id.request_state);
            ShowOrdersBtn = itemView.findViewById(R.id.show_all_requests_btn);
        }
    }

    private void RemoverOrder(String uID) {

        ordersRef.child(uID).child("state").setValue("Available");
    }
}