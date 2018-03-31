package com.sweetdeveloper.instacoffeeordermanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sweetdeveloper.instacoffeeordermanager.models.OrderItem;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    TextView totalTextView;
    Button confirmButton;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    OrderDetailsRecyclerViewAdapter adapter;
    Parcelable listState;
    ArrayList<OrderItem> orderItems = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseDatabase = FirebaseDatabase.getInstance();

        confirmButton = findViewById(R.id.confirm_button);
        totalTextView = findViewById(R.id.total_text_view);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            orderItems = bundle.getParcelableArrayList("orders");
            totalTextView.setText(bundle.getString("total"));

        }
        databaseReference = firebaseDatabase.getReference("pending_orders").child(bundle.getString("key"));
        recyclerView = findViewById(R.id.order_details_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new OrderDetailsRecyclerViewAdapter(orderItems);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (savedInstanceState != null) {
            layoutManager.onRestoreInstanceState(listState);
        }
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), getString(R.string.order_confirmed), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), OrdersActivity.class));
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (layoutManager != null) {
            listState = layoutManager.onSaveInstanceState();
            outState.putParcelable("state", listState);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable("state");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (layoutManager != null) {
            if (listState != null) {
                layoutManager.onRestoreInstanceState(listState);
            }
        }
    }

    class OrderDetailsRecyclerViewAdapter extends RecyclerView.Adapter<OrderDetailsRecyclerViewAdapter.ViewHolder> {

        ArrayList<OrderItem> items = new ArrayList<>();

        OrderDetailsRecyclerViewAdapter(ArrayList<OrderItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public OrderDetailsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_card, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull OrderDetailsRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.itemName.setText(items.get(position).getItemName());
            holder.itemPrice.setText(items.get(position).getPrice());
            holder.itemQuantity.setText(items.get(position).getQuantity());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView itemName;
            TextView itemQuantity;
            TextView itemPrice;

            public ViewHolder(View itemView) {
                super(itemView);

                itemName = itemView.findViewById(R.id.cart_item_name_text_view);
                itemQuantity = itemView.findViewById(R.id.cart_item_quantity_text_view);
                itemPrice = itemView.findViewById(R.id.cart_item_price_text_view);
            }
        }
    }


}
