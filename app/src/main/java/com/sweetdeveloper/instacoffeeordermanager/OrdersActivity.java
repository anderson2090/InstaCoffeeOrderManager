package com.sweetdeveloper.instacoffeeordermanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OrdersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    TextView userNameTextView;
    TextView userEmailTextView;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PendingOrdersRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        recyclerView = findViewById(R.id.pending_orders_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PendingOrdersRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        userNameTextView = headerView.findViewById(R.id.header_user_name_text_view);
        userEmailTextView = headerView.findViewById(R.id.header_user_email_text_view);
        if (firebaseUser != null) {
            userNameTextView.setText(firebaseUser.getDisplayName());
            userEmailTextView.setText(firebaseUser.getEmail());

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.orders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pending_orders) {
            // Handle the camera action
        } else if (id == R.id.nav_signout) {
            firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class PendingOrdersRecyclerViewAdapter extends RecyclerView.Adapter<PendingOrdersRecyclerViewAdapter.ViewHolder> {

        String[] items = {"One", "Two", "Three"};

        @NonNull
        @Override
        public PendingOrdersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pending_order_item_card, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull PendingOrdersRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.userNameTextView.setText(items[position]);
        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {


            TextView userNameTextView;
            TextView userEmailTextView;
            TextView userPhoneTextView;
            TextView userAddressTextView;
            TextView orderTimeTextView;

            public ViewHolder(View itemView) {
                super(itemView);

                userNameTextView = itemView.findViewById(R.id.po_user_name_text_view);
                userEmailTextView = itemView.findViewById(R.id.po_user_email_text_view);
                userPhoneTextView = itemView.findViewById(R.id.po_user_phone_text_view);
                userAddressTextView = itemView.findViewById(R.id.po_user_address_text_view);
                orderTimeTextView = itemView.findViewById(R.id.po_order_time_text_view);
            }
        }
    }
}
