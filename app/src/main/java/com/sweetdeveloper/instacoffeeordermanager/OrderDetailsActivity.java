package com.sweetdeveloper.instacoffeeordermanager;

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
import android.widget.TextView;

import com.sweetdeveloper.instacoffeeordermanager.models.OrderItem;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    OrderDetailsRecyclerViewAdapter adapter;
    Parcelable listState;
    ArrayList<OrderItem> orderItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            orderItems = bundle.getParcelableArrayList("orders");

        }

        recyclerView = findViewById(R.id.order_details_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new OrderDetailsRecyclerViewAdapter(orderItems);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (savedInstanceState != null) {
            layoutManager.onRestoreInstanceState(listState);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
