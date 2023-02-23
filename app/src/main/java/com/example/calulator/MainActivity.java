package com.example.calulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resulttv,solutiontv;
    MaterialButton buttonC,buttonOpenBracket,buttonCloseBracket;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus, button_equalto;
    MaterialButton button_zero,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton button_AC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulttv = findViewById(R.id.result_tv);
        solutiontv = findViewById(R.id.solution_tv);

        resulttv = findViewById(R.id.result_tv);
        solutiontv = findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonOpenBracket,R.id.button_openbracket);
        assignId(buttonCloseBracket,R.id.button_closebracket);
        assignId(buttonDivide,R.id.button_division);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_add);
        assignId(buttonMinus,R.id.button_sub);
        assignId(button_equalto,R.id.button_equal);
        assignId(button_zero,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(button_AC,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutiontv.getText().toString();

        if(buttonText.equals("AC")){
            solutiontv.setText("");
            resulttv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutiontv.setText(resulttv.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutiontv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")){
            resulttv.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}