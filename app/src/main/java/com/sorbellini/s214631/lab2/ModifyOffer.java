package com.sorbellini.s214631.lab2;

import android.app.AlertDialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModifyOffer extends AppCompatActivity {

    private String imagePath = null;
    private NumberPicker pickerPrice = null;
    private NumberPicker pickerAvailableQuantity = null;
    static final int CAM_REQUEST = 1;
    private static final int SELECTED_PICTURE = 1;
    private ImageView imagecapturephoto;
    private ImageButton buttoncamera;


    //Data
    ArrayList<DailyOffer> dailyOffers;
    //index
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_offer);
        //toolbar part
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //here takes data from saved resources and get index from previous activity
        Bundle b = getIntent().getExtras();
        index = b.getInt("index");

        //get data from shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String dataString = preferences.getString("dailyOffers",null);
        Gson gson = new Gson();
        dailyOffers = gson.fromJson(dataString, new TypeToken<List<DailyOffer>>(){}.getType());


        pickerPrice = (NumberPicker) findViewById(R.id.picker_price_modify);
        pickerPrice.setMaxValue(1000);
        pickerPrice.setMinValue(0);
        pickerPrice.setWrapSelectorWheel(false);
        pickerPrice.setValue(10);

        pickerAvailableQuantity = (NumberPicker) findViewById(R.id.picker_availablequantity_modify);
        pickerAvailableQuantity.setMaxValue(1000);
        pickerAvailableQuantity.setMinValue(0);
        pickerAvailableQuantity.setValue(10);
        pickerAvailableQuantity.setWrapSelectorWheel(false);

        buttoncamera = (ImageButton) findViewById(R.id.camera_modify);
        imagecapturephoto = (ImageView) findViewById(R.id.capturephoto_modify);

        buttoncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage2();
            }
        });

        //set Values from retrieved data
        //set image
        ImageView imageView = (ImageView) findViewById(R.id.capturephoto_modify);
        imageView.setImageURI(Uri.parse(dailyOffers.get(index).getPhoto()));
        //set name
        TextView textView = (TextView) findViewById(R.id.offer_name_modify);
        textView.setText(dailyOffers.get(index).getName());
        //set description
        textView = (TextView) findViewById(R.id.editoffer_description_modify);
        textView.setText(dailyOffers.get(index).getDescription());
        //set price
        NumberPicker numPicker = (NumberPicker)findViewById(R.id.picker_price_modify);
        numPicker.setValue(dailyOffers.get(index).getPrice());
        //set availability
        numPicker = (NumberPicker)findViewById(R.id.picker_availablequantity_modify);
        numPicker.setValue(dailyOffers.get(index).getAvailability());



        /*
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String saved_offerName = preferences.getString("offerNameModify", null);
        String saved_offerDescription = preferences.getString("offerDescriptionModify", null);
        int saved_offerPrice = preferences.getInt("offerPriceModify", -1);
        int saved_offerAvailableQuantity = preferences.getInt("offerAvailableQuantityModify", -1);
        String saved_imgPath = preferences.getString("imgPathModify", null);

        if (saved_imgPath != null){
            ImageView imageView = (ImageView) findViewById(R.id.capturephoto_modify);
            Bitmap imageBitmap = imagePicker.loadImageFromStorage(saved_imgPath);
            if(imageView!=null)
                imageView.setImageBitmap(imageBitmap);
        }
        TextView textView;
        textView = (TextView) findViewById(R.id.editOffer_Name_modify);
        if(textView!=null) {
            textView.setText(saved_offerName);
        }

        textView = (TextView) findViewById(R.id.editOffer_Description_modify);
        if(textView!=null) {
            textView.setText(saved_offerDescription);
        }
        NumberPicker numPicker = (NumberPicker)findViewById(R.id.picker_Price_modify);
        if(numPicker!=null){
            textView.setText(String.valueOf(saved_offerPrice));
        }
        NumberPicker numPicker1 = (NumberPicker)findViewById(R.id.picker_AvailableQuantity_modify);
        if(numPicker1!=null){
            textView.setText(String.valueOf(saved_offerAvailableQuantity));
        }

        /*
        textView = (TextView) findViewById(R.id.editOffer_Price_modify);
        if(textView!=null) {
            textView.setText(saved_offerPrice);
        }

        textView = (TextView) findViewById(R.id.editOffer_AvailableQuantity_modify);
        if(textView!=null) {
            textView.setText(saved_offerAvailableQuantity);
        }
        */
    }



    public void backToViewOffer(View view) {
        Intent intent = new Intent(ModifyOffer.this, ActivityDailyOffer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("index", index);
        startActivity(intent);
    }



    public void saveData2(View view) {
        //save data to shared preferences
        EditText editText = (EditText) findViewById(R.id.offer_name_modify);
        String offerName = editText.getText().toString();
        editText = (EditText) findViewById(R.id.editoffer_description_modify);
        String offerDescription = editText.getText().toString();
        NumberPicker offerAvailableQuantity = (NumberPicker) findViewById(R.id.picker_availablequantity_modify);
        NumberPicker offerPrice = (NumberPicker) findViewById(R.id.picker_price_modify);
        int valuePrice=offerPrice.getValue();
        int valueAvailableQuantity=offerAvailableQuantity.getValue();
        ImageView imageView = (ImageView) findViewById(R.id.capturephoto_modify);
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        imagePath = imagePicker.saveToInternalStorage(bitmap,this);

        dailyOffers.get(index).setPhoto(imagePath);
        dailyOffers.get(index).setName(offerName);
        dailyOffers.get(index).setDescription(offerDescription);
        dailyOffers.get(index).setPrice(valuePrice);
        dailyOffers.get(index).setAvailability(valueAvailableQuantity);

        /*
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("imgPathModify", imagePath);
        editor.putString("offerNameModify", offerName);
        editor.putString("offerDescriptionModify", offerDescription);
        editor.putInt("AvailableQuantityModify", valueAvailableQuantity);
        editor.putInt("PriceModify", valuePrice);
        editor.commit();
        */

        Intent intent = new Intent(this, ActivityDailyOffer.class);
        intent.putExtra("index", index);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void selectImage2() {
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ModifyOffer.this);
        builder.setTitle("Offer Photo : ");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAM_REQUEST);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECTED_PICTURE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == CAM_REQUEST){

            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

            File destination = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            FileOutputStream fo;

            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imagecapturephoto.setImageBitmap(thumbnail);

        }
        else if (requestCode == SELECTED_PICTURE) {
            Uri selectedImageUri = data.getData();
            String[] projection = {};
            CursorLoader cursorLoader =  new CursorLoader(this,selectedImageUri, projection, null, null,
                    null);
            Cursor cursor =cursorLoader.loadInBackground();
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            String selectedImagePath = cursor.getString(column_index);
            Bitmap bm;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(selectedImagePath, options);
            final int REQUIRED_SIZE = 200;
            int scale = 1;
            while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                    && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeFile(selectedImagePath, options);
            imagecapturephoto.setImageBitmap(bm);
        }
    }


    //saves all data when another activity is started
    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson2 = new Gson();
        JsonElement element = gson2.toJsonTree(dailyOffers, new TypeToken<List<DailyOffer>>(){}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        editor.putString("dailyOffers", jsonArray.toString());
        editor.commit();
    }
}
