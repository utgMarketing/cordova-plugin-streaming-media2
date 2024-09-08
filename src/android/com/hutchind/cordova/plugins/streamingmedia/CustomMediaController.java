package com.hutchind.cordova.plugins.streamingmedia;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.content.res.ColorStateList;
import android.graphics.Color;

import java.lang.reflect.Field;

public class CustomMediaController extends MediaController {

    private Context mContext;

    public CustomMediaController(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void setAnchorView(View view) {
        super.setAnchorView(view);

        // Отримуємо доступ до прогрес-бара
        try {
            Field mProgressField = MediaController.class.getDeclaredField("mProgress");
            mProgressField.setAccessible(true);
            ProgressBar mProgress = (ProgressBar) mProgressField.get(this);

            // Міняємо колір прогрес-бара
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mProgress.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
                mProgress.setThumbTintList(ColorStateList.valueOf(Color.BLUE)); // Міняємо колір thumb (кнопки)
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
