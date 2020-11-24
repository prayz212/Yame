package com.example.yame.Activitys.ProductDetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.yame.R;

import java.util.ArrayList;
import java.util.List;

public class ShowImageActivity extends AppCompatActivity {

    private ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        imageSlider = findViewById(R.id.fullScreenSlider);

        receiveData();
    }

    private void receiveData() {

        Intent intent = getIntent();
        String[] urls = intent.getStringArrayExtra("url");

        showImage(urls);
    }

    private void showImage(String[] urls) {
        List<SlideModel> slideModels = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            slideModels.add(new SlideModel(urls[i], ScaleTypes.FIT));
        }

        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
    }


}