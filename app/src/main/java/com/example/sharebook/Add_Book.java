package com.example.sharebook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Add_Book extends AppCompatActivity {
    private ImageButton sell;
    private EditText name,category,description,price;
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
        initialize();

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadBookDetails();
                Intent intent = new Intent(Add_Book.this, HomeActivity.class);
                startActivity(intent);
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
//      TODO
    }

    public void initialize() {


        //Initialization
        img_book = findViewById(R.id.img_book_sellBook);
        name = findViewById(R.id.book_name);
        category = findViewById(R.id.book_type);
        description = findViewById(R.id.description);
        price = findViewById(R.id.book_price);
        sell = findViewById(R.id.btn_sell);

    }
}
