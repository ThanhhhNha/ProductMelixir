package com.example.demopro2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;

    public ProductAdapter(Context context, List<Product> products) {
        super(context, 0, products);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layoutitem, parent, false);
        }

        ImageView imgView = convertView.findViewById(R.id.imageView);
        TextView nameView = convertView.findViewById(R.id.nameTextView);
        TextView desView = convertView.findViewById(R.id.descriptionTextView);
        TextView priceView = convertView.findViewById(R.id.priceTextView);

        Product product = getItem(position);

        if (product != null) {
            // Load image from assets
            try {
                // Assuming the image name is stored in the 'img' field of the Product class
                String imageName = "images/" + product.getImagePro();
                InputStream stream = context.getAssets().open(imageName);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                imgView.setImageBitmap(bitmap);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error or set a default image
                imgView.setImageResource(R.drawable.ic_launcher_background);
            }

            nameView.setText(product.getNamePro());
            desView.setText(product.getDecriptionPro());
            priceView.setText(product.getPrice());
        }

        return convertView;
    }
}