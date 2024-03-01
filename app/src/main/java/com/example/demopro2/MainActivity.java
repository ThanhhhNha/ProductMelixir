package com.example.demopro2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int DATABASE_VERSION = 2;

    public String DATABASE_NAME = "DatabaseDoAn";
    public String DB_SUFFIX_PATH = "/databases/";
    public static SQLiteDatabase database = null;
    ListView list;

    Button btnOpen;
    ArrayAdapter<Product> adapterProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy();
        addControls();
        loadData();

    }

    private void addControls() {
        list = findViewById(R.id.list);
        adapterProduct = new ProductAdapter(this, new ArrayList<>());
        list.setAdapter(adapterProduct);

    }

    private void xulyCapnhat() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = adapterProduct.getItem(position);


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }





    private void loadData() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM Products", null);
        adapterProduct.clear();
        while (cursor.moveToNext()) {
            Integer idpro  = cursor.getInt(0);
            String namepro = cursor.getString(1);
            String despro = cursor.getString(2);
            String price = cursor.getString(3);
            String img = cursor.getString(4);

            Product product = new Product(idpro, namepro, despro, price, img);
            adapterProduct.add(product);
        }
        cursor.close();
    }



    public String getDatabasePath(){
        return getApplicationInfo().dataDir+DB_SUFFIX_PATH+DATABASE_NAME;
    }

    private void processCopy() {
        try{
            File file = getDatabasePath(DATABASE_NAME);
            if(!file.exists()){
                copyDatabaseFromAsset();
                Toast.makeText(this, "Copy Database Successful", Toast.LENGTH_SHORT).show();

            }
        }
        catch (Exception ex){
            Toast.makeText(this, "Copy Database Fail", Toast.LENGTH_SHORT).show();
        }
    }

    private void copyDatabaseFromAsset() {
        try{
            InputStream inputStream = getAssets().open(DATABASE_NAME);
            String outputFileName = getDatabasePath();
            File file = new File(getApplication().getDataDir()+DB_SUFFIX_PATH);
            if(!file.exists())
                file.mkdir();
            OutputStream outputStream = new FileOutputStream(outputFileName);
            byte[]buffer = new byte[1024];
            int lenght;
            while ((lenght=inputStream.read(buffer))>0)outputStream.write(buffer,0,lenght);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        catch (Exception exception){
            Log.e("Error", exception.toString());
        }
    }
}