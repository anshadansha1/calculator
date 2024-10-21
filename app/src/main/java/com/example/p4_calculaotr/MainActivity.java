package com.example.p4_calculaotr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public TextView tvDisplay;
    public Button btnClear, btnDivision, btnMultiply,
            btnDel, btn7, btn8, btn9, btnMinus, btn4,
            btn5, btn6, btnPlus, btn1, btn2, btn3,
            btnEquals, btn0, btnDot;
    public String current_input = "";
    public String operator = "";
    public double first_operand = 0.0;
    public double second_operand = 0.0;
    public boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        btnClear = findViewById(R.id.btnClear);
        btnDivision = findViewById(R.id.btnDivision);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDel = findViewById(R.id.btnDel);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnMinus = findViewById(R.id.btnMinus);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnPlus = findViewById(R.id.btnPlus);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnEquals = findViewById(R.id.btnEquals);
        btn0 = findViewById(R.id.btn0);
        btnDot = findViewById(R.id.btnDot);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFunction();
            }
        });

        //set onclicklistener for numbers
        setNumberClickListener(btn0,"0");
        setNumberClickListener(btn1,"1");
        setNumberClickListener(btn2,"2");
        setNumberClickListener(btn3,"3");
        setNumberClickListener(btn4,"4");
        setNumberClickListener(btn5,"5");
        setNumberClickListener(btn6,"6");
        setNumberClickListener(btn7,"7");
        setNumberClickListener(btn8,"8");
        setNumberClickListener(btn9,"9");
        setNumberClickListener(btnDot,".");

        //set onclicklistener for operators
        setOperatorClickListener(btnPlus,"+");
        setOperatorClickListener(btnMinus,"-");
        setOperatorClickListener(btnMultiply,"*");
        setOperatorClickListener(btnDivision,"/");


        //equal button
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!current_input.isEmpty()){
                    second_operand = Double.parseDouble(current_input);
                    performCalculation();

                }
            }
        });

        //del button
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!current_input.isEmpty()){
                    current_input =current_input.substring(0,current_input.length()-1);
                    tvDisplay.setText(current_input);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setNumberClickListener(Button btn,String number){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperatorClicked){
                    current_input = "";
                    isOperatorClicked = false;
                }
                current_input += number;
                tvDisplay.setText(current_input);
            }
        });
    }

    public void setOperatorClickListener(Button btn , String op){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!current_input.isEmpty()){
                    first_operand = Double.parseDouble(current_input);
                    operator = op;
                    isOperatorClicked = true;
                }
            }
        });
    }

    public void performCalculation(){
        double result = 0.0;
        switch(operator){
            case "+":
                result = first_operand + second_operand;
                break;
            case "-":
                result = first_operand - second_operand;
                break;
            case "*":
                result = first_operand * second_operand;
                break;
            case "/":
                if(second_operand != 0){
                    result = first_operand / second_operand;
                }else{
                    tvDisplay.setText("Error");
                    return;
                }
                break;
        }

        tvDisplay.setText(String.valueOf(result));
        current_input = String.valueOf(result);
        first_operand = result;
        second_operand = 0.0;
    }

    public void clearFunction(){
        tvDisplay.setText("0");
        current_input = "";
        operator = "";
        first_operand = 0.0;
        second_operand = 0.0;
    }
}

