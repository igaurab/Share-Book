package com.example.sharebook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharebook.Classes.Book;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Book extends AppCompatActivity {
    private ImageButton sell;
    private EditText name,category,description,price;
    private String sname,scategory,sdescription,sprice;
    private ImageView img_book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__book);
        setTheme(R.style.SellItemsTheme);

//        TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbarAddBook);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Sell Book");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.add_book_gradient)));
//        INITIALIZE
        img_book = findViewById(R.id.img_book_sellBook);
        name = findViewById(R.id.book_name);
        category = findViewById(R.id.book_type);
        description = findViewById(R.id.description);
        price = findViewById(R.id.book_price);
        sell = findViewById(R.id.btn_sell);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadBookDetails();

            }
        });

        img_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }

    public void chooseImage() {
//      TODO
    }

    public void uploadBookDetails() {

        FirebaseAuth mAuth;
        String uid;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();
        Book book = new Book();
        Log.d("Help", "uploadBookDetails: "+ uid);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("books");
        String key = reference.push().getKey();

        sname = name.getText().toString().toLowerCase();
        scategory = category.getText().toString().toLowerCase();
        sdescription = description.getText().toString().toLowerCase();
        sprice = price.getText().toString();

        book.setName(sname);
        book.setCategory(scategory);
        book.setDescription(sdescription);
        book.setPrice(sprice);
        book.setUid(uid);
        book.setImg_url("JPT");

        Log.d("Help", "uploadBookDetails: "+ book.getName());
        reference.child(uid).child(key).setValue(book);
        Log.d("Help", "uploadBookDetails: "+ reference);
        Intent intent = new Intent(Add_Book.this, HomeActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Book Added "+ sname+uid,Toast.LENGTH_LONG).show();
    }
}
