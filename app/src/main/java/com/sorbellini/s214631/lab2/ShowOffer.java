package com.sorbellini.s214631.lab2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

public class ShowOffer extends AppCompatActivity {
    ImageButton add_offer;
    Button return_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_offer);
        /*
        return_menu = (Button) findViewById(R.id.return_menu);
        return_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowOffer.this, MainActivity.class));
            }
        });
        Dish dish1 = new Dish();
        Intent intent = getIntent();
        dish1.description = intent.getStringExtra("parameter description");
        dish1.name = intent.getStringExtra("parameter name");

        addOffer();*/

        GridView gridview = (GridView) findViewById(R.id.grid_view);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position == 0) {
                    onClickPopupOffer();
                }
                else {
                    Intent intent = new Intent(ShowOffer.this, ActivityDailyOffer.class);
                    startActivity(intent);
                }
            }
        });

        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position != 0) {
                    onClickPopupOptions();
                }
                return true;
            }
        });

    }

    public void onClickPopupOptions() {
        final CharSequence[] items = { "Delete", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowOffer.this);
        builder.setTitle("Options");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Delete")) {

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onClickPopupOffer() {
        final CharSequence[] items = { "Yes", "No" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowOffer.this);
        builder.setTitle("Do you want to add an offer ?");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Yes")) {
                    startActivity(new Intent(ShowOffer.this, FormularAddingAnOffer.class));
                } else if (items[item].equals("No")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

/*
    private void addOffer() {
        //ImageButton btn = (ImageButton) findViewById(R.id.add_offer);
        //btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onClickPopupOffer();
            }
        });
    }*/
}
