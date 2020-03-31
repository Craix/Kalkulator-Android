package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView output;
    String  s0 ="", s1 ="", s2 ="";
    double te;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.text);
        output.setText(s0+s1+s2);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("K", s0);
        outState.putString("E", s1);
        outState.putString("Y", s2);
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState)
    {
        super.onRestoreInstanceState(saveInstanceState);
        s0 = saveInstanceState.getString("K", " ");
        s1 = saveInstanceState.getString("E", " ");
        s2 = saveInstanceState.getString("Y", " ");

        output.setText(s0+s1+s2);
    }

    public void calculate()
    {
        // store the value in 1st
        if (s1.equals("+"))
            te = (Double.parseDouble(s0) + Double.parseDouble(s2));
        else if (s1.equals("-"))
            te = (Double.parseDouble(s0) - Double.parseDouble(s2));
        else if (s1.equals("/"))
            te = (Double.parseDouble(s0) / Double.parseDouble(s2));
        else if(s1.equals("%"))
            te = (Double.parseDouble(s0) % Double.parseDouble(s2));
        else if(s1.equals("log"))//log
            te = Math.log(Double.parseDouble(s0))/Math.log(Double.parseDouble(s2));
        else if(s1.equals("pow")) //pow
            te = Math.pow(Double.parseDouble(s0),Double.parseDouble(s2));
        else if(s1.equals("sqrt")) //sqrt
            te = Math.pow(Double.parseDouble(s0), 1/Double.parseDouble(s2));
        else
            te = (Double.parseDouble(s0) * Double.parseDouble(s2));
    }

    public void operator(String s)
    {
        {
            // if there was no operand
            if (s1.equals("") || s2.equals("")) {
                s1 = s;
            }
            else {
                calculate();

                // convert it to string
                s0 = Double.toString(te);

                // place the operator
                s1 = s;

                // make the operand blank
                s2 = "";
            }

            // set the value of text
            output.setText(s0 + s1 + s2);
        }
    }

    public void operation(View view)
    {
        switch(view.getId())
        {
            case(R.id.b3x0):{
                //dzilone
                operator("/");
                break;
            }
            case(R.id.b3x1):{
                //razy
                operator("*");
                break;
            }
            case(R.id.b3x2):{
                //odejmij
                operator("-");
                break;
            }
            case(R.id.b3x3):{
                //dodaj
                operator("+");
                break;
            }
            case(R.id.b0x0):{
                //reset
                s0 = s1 = s2 = "";
                output.setText(s0 + s1 + s2);
                break;
            }
            case(R.id.b2x0): {
                //modulo
                operator("%");
                break;
            }
            case(R.id.s1): {
                //sqrt
                operator("sqrt");
                break;
            }
            case(R.id.s2): {
                //pow
                operator("pow");
                break;
            }
            case(R.id.s3): {
                //log
                operator("log");
                break;
            }

            case (R.id.b3x4): {
                //równa się

                calculate();

                output.setText(s0 + s1 + s2 + "=" + te);
                s0 = Double.toString(te);
                s1 = s2 = "";
                break;
            }
            case(R.id.b2x4): {
                if (!s1.equals("")) {
                    s2 = s2 + ".";
                } else {
                    s0 = s0 + ".";
                }

                output.setText(s0+s1+s2);

                //przecinek
                break;

            }
            case(R.id.b1x0): {
                if(!s1.equals(""))
                {
                    s2 = s2.substring(0, s2.length() - 1);
                }
                else {
                    s0 = s0.substring(0, s0.length() - 1);
                }

                output.setText(s0+s1+s2);
            }
        }
    }

    private void symbol(String s)
    {
        if(!s1.equals(""))
        {
            s2 = s2 + s;
        }
        else
        {
            s0 = s0 + s;
        }
    }

    public void numbers(View view)
    {
        switch (view.getId()) {
            case(R.id.b0x4):{
                symbol("0");
                break;
            }
            case (R.id.b0x1): {
                symbol("1");
                break;
            }
            case (R.id.b1x1): {
                symbol("2");
                break;
            }
            case (R.id.b2x1): {
                symbol("3");
                break;
            }
            case (R.id.b0x2): {
                symbol("4");
                break;
            }
            case (R.id.b1x2): {
                symbol("5");
                break;
            }
            case (R.id.b2x2): {
                symbol("6");
                break;
            }
            case(R.id.b0x3):{
                symbol("7");
                break;
            }
            case(R.id.b1x3):{
                symbol("8");
                break;
            }
            case(R.id.b2x3): {
                symbol("9");
                break;
            }
            case(R.id.s4): {
                symbol("3.14159265359");
                break;
            }
            case(R.id.s5): {
                symbol("2.71828182846");
                break;
            }
        }

        output.setText(s0+s1+s2);

    }
}
