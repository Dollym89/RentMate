package com.example.michal.rentmate.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontSetter {

  private Context context;
  private static FontSetter oneInstance = null;

  private FontSetter(Context context) {
    this.context = context.getApplicationContext();
  }

  public static FontSetter getInstance(Context context) {
    if (oneInstance == null) {
      oneInstance = new FontSetter(context);
    }
    return oneInstance;
  }

  //set the different type of
  public static void setFancyText(Context context, String fontName,TextView text) {
    Typeface fancyOne = Typeface.createFromAsset(context.getAssets(), fontName);
    text.setTypeface(fancyOne);
  }
}
