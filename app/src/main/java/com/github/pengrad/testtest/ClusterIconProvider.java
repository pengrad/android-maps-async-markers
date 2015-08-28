package com.github.pengrad.testtest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.androidmapsextensions.ClusterOptions;
import com.androidmapsextensions.ClusterOptionsProvider;
import com.androidmapsextensions.Marker;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.List;

/**
 * stas
 * 8/28/15
 */
public class ClusterIconProvider implements ClusterOptionsProvider {

    Resources resources;
    Paint paint;
    Bitmap base;

    public ClusterIconProvider(Resources resources) {
        this.resources = resources;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(15);

        base = BitmapFactory.decodeResource(resources, R.drawable.m1);
    }

    @Override
    public ClusterOptions getClusterOptions(List<Marker> list) {
        Bitmap bitmap = base.copy(Bitmap.Config.ARGB_8888, true);

        Rect bounds = new Rect();
        String text = String.valueOf(list.size());
        paint.getTextBounds(text, 0, text.length(), bounds);
        float x = bitmap.getWidth() / 2.0f;
        float y = (bitmap.getHeight() - bounds.height()) / 2.0f - bounds.top;

        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text, x, y, paint);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bitmap);

        return new ClusterOptions().anchor(0.5f, 0.5f).icon(icon);
    }
}
