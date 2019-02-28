package com.wisermark.userinput;

//hi
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity  {

    int quantity =0;
    TextView quantityTV;
    TextView priceTV;
    ImageView coffeeIV;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantityTV =findViewById(R.id.quantity_text_view);
        priceTV =findViewById(R.id.price_text_view);
        coffeeIV =findViewById(R.id.img);


        coffeeIV.setOnTouchListener(new com.wisermark.userinput.OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                //Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();

                String s =coffeeIV.getTag().toString();
                if(s.equals("cappuccino") || s.equals("2131099732")){
                    coffeeIV.setImageResource((R.drawable.espresso));
                    coffeeIV.setTag(R.drawable.espresso);
                    s =coffeeIV.getTag().toString();
                    Log.v("IV tag espresso:", s);
                } else if(s.equals("2131099733")){
                    coffeeIV.setImageResource((R.drawable.macchiato));
                    coffeeIV.setTag(R.drawable.macchiato);
                    Log.v("IV tag macchiato:", coffeeIV.getTag().toString());
                } else if(s.equals("2131099736")){
                    coffeeIV.setImageResource((R.drawable.cappuccino));
                    coffeeIV.setTag(R.drawable.cappuccino);
                    Log.v("IV tag cappuccino:", coffeeIV.getTag().toString());
                }

            }

            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });

    }



    public void submitOrder(View view){
        display(quantity);
        displayPrice(0);
        /* open new page */

        Intent i =new Intent(this, order.class);
        startActivity(i);

        /* send email */
        /*Intent emailIntent =new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Request for coffee");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Number of coffee: "+quantity);
        startActivity(emailIntent); //to check if there is an email app if(sendIntent.resolveActivity(getPackageManager()) !=null)
        */
    }

    private void display(int q){

        quantityTV.setText("" + q);
    }

    private void displayPrice(int p){
        priceTV.setText(NumberFormat.getCurrencyInstance().format(p));
    }

    public void increase(View view){
        quantity++;
        display(quantity);
    }

    public void decrease(View view){
        quantity--;
        if(quantity >=0) {
            display(quantity);
        }
    }
}
