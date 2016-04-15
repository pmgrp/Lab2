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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActivityAddOffer extends AppCompatActivity {

    private static final int PICK_IMAGE_ID = 234;
    private Button return_menu;
    private ImageView imagecapturephoto;
    private ImageButton buttoncamera;
    static final int CAM_REQUEST = 1;
    private static final int SELECTED_PICTURE = 1;
    private NumberPicker pickerPrice = null;
    private NumberPicker pickerAvailableQuantity = null;
    private String imagePath = null;
    private Uri tempImageUri = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);
        imagecapturephoto = (ImageView) findViewById(R.id.capturephoto);
        pickerPrice = (NumberPicker) findViewById(R.id.picker_Price);
        pickerPrice.setMaxValue(1000);
        pickerPrice.setMinValue(0);
        pickerPrice.setWrapSelectorWheel(false);

        pickerAvailableQuantity = (NumberPicker) findViewById(R.id.picker_AvailableQuantity);
        pickerAvailableQuantity.setMaxValue(1000);
        pickerAvailableQuantity.setMinValue(0);
        pickerAvailableQuantity.setWrapSelectorWheel(false);


        buttoncamera = (ImageButton) findViewById(R.id.camera);





        buttoncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String saved_offerName = preferences.getString("offerName", null);
        String saved_offerDescription = preferences.getString("offerDescription", null);
        imagePath = preferences.getString("imgPath", null);
        //Bitmap bitmap = imagePicker.loadImageFromStorage(imagePath);
       /* imagecapturephoto.setImageBitmap(bitmap);
        if (savedInstanceState != null) {
            tempImageUri = savedInstanceState.getParcelable("TempUri");
        }
        if (tempImageUri != null) {
            bitmap = imagePicker.getImageResized(this, tempImageUri);
            if (imagecapturephoto != null)
                imagecapturephoto.setImageBitmap(bitmap);
        }*/
        int saved_offerPrice = preferences.getInt("offerPrice", -1);
        int saved_offerAvailableQuantity = preferences.getInt("offerAvailableQuantity", -1);
        String saved_imgPath = preferences.getString("imgPath", null);

        if (saved_imgPath != null){
            ImageView imageView = (ImageView) findViewById(R.id.capturephoto);
            //Bitmap imageBitmap = imagePicker.loadImageFromStorage(saved_imgPath);
            //if(imageView!=null)
                //imageView.setImageBitmap(imageBitmap);
        }
        TextView textView;
        textView = (TextView) findViewById(R.id.editOffer_Name);
        if(textView!=null) {
            textView.setText(saved_offerName);
        }

        textView = (TextView) findViewById(R.id.editOffer_Description);
        if(textView!=null) {
            textView.setText(saved_offerDescription);
        }
        NumberPicker numPicker = (NumberPicker)findViewById(R.id.picker_Price);
        if(numPicker!=null){

        }
        NumberPicker numPicker1 = (NumberPicker)findViewById(R.id.picker_AvailableQuantity);
        if(numPicker1!=null){

        }

        /*
        textView = (TextView) findViewById(R.id.editOffer_Price);
        if(textView!=null) {
            textView.setText(saved_offerPrice);
        }

        textView = (TextView) findViewById(R.id.editOffer_AvailableQuantity);
        if(textView!=null) {
            textView.setText(saved_offerAvailableQuantity);
        }
*/


        /*
        return_menu=(Button)findViewById(R.id.return_menu);
        return_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityAddOffer.this, MainActivity.class));
            }
        });*/
    }


    /*
    private void setupEditButton() {

        Button save = (Button) findViewById(R.id.offerSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText offerName = (EditText) findViewById(R.id.editOffer_Name);
                String valueName = offerName.getText().toString();

                EditText offerDescription = (EditText) findViewById(R.id.editOffer_Description);
                String valueDescription = offerDescription.getText().toString();

                NumberPicker offerPrice = (NumberPicker) findViewById(R.id.picker_Price);
                float valuePrice = pickerPrice.getValue();

                NumberPicker offerAvailableQuantity = (NumberPicker) findViewById(R.id.picker_AvailableQuantity);
                int valueAvailableQuantity= offerAvailableQuantity.getValue();

        // TODO Miss the part of how I can get the image ?

                Intent intent = new Intent(ActivityAddOffer.this, ActivityShowOffers.class);
                intent.putExtra("parameter name",valueName);
                intent.putExtra("parameter description",valueDescription);
                intent.putExtra("parameter price",valuePrice);
                intent.putExtra("parameter quantity",valueAvailableQuantity);
                startActivity(intent);
            }
        });
    }
    */


    public void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAddOffer.this);
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


    private File getFile(){

        File folder = new File("sdcard/camera_app");
        if(!folder.exists()){
            folder.mkdir();
        }
        File image_file = new File(folder,"cam_image.jpg");

        return image_file;
    }

    @Override
    public void onSaveInstanceState(Bundle onSave) {
        super.onSaveInstanceState(onSave);
        onSave.putParcelable("TempUri", tempImageUri);
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


    public void saveData(View view) {
        //save data to shared preferences
        EditText editText = (EditText) findViewById(R.id.editOffer_Name);
        String offerName = editText.getText().toString();
        editText = (EditText) findViewById(R.id.editOffer_Description);
        String offerDescription = editText.getText().toString();
        //int offerAvailableQuantity = (int) findViewById(R.id.picker_AvailableQuantity);
        //int offerPrice = (int) findViewById(R.id.picker_price);
        ImageView imageView = (ImageView) findViewById(R.id.capturephoto);
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        //imagePath = imagePicker.saveToInternalStorage(bitmap,this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("imgPath", imagePath);
        editor.putString("offerName", offerName);
        editor.putString("offerDescription", offerDescription);
        //editor.putString("availableQuantity", offerAvailableQuantity);
        //editor.putString("price", offerPrice);
        editor.commit();

        Intent intent = new Intent(this, ActivityShowOffers.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void returnToOffers (View view) {
        Intent intent = new Intent(ActivityAddOffer.this, ActivityShowOffers.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void selectPhoto(View view) {
        Intent chooseImageIntent = imagePicker.getPickImageIntent(this);
        startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }
}

