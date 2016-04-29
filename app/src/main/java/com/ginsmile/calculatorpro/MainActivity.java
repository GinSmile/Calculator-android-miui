package com.ginsmile.calculatorpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public String expression = "";
    EditText text1;//第一行，用来显示按过等号之后的完整表达式
    EditText text2;//第二行，用来显示表达式和结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化
        text1 = (EditText)findViewById(R.id.text1);
        text2 = (EditText)findViewById(R.id.text2);
        Button[] buttons = new Button[18];
        init(buttons);//初始化按钮

    }

    public String calculate(String expression){

        return "0.00";
    }

    //初始化
    public void init(final Button[] buttons){
        buttons[0] = (Button)findViewById(R.id.zero);
        buttons[1] = (Button)findViewById(R.id.one);
        buttons[2] = (Button)findViewById(R.id.two);
        buttons[3] = (Button)findViewById(R.id.three);
        buttons[4] = (Button)findViewById(R.id.four);
        buttons[5] = (Button)findViewById(R.id.five);
        buttons[6] = (Button)findViewById(R.id.six);
        buttons[7] = (Button)findViewById(R.id.seven);
        buttons[8] = (Button)findViewById(R.id.eight);
        buttons[9] = (Button)findViewById(R.id.nine);

        buttons[10] = (Button)findViewById(R.id.empty);
        buttons[11] = (Button)findViewById(R.id.delete);
        buttons[12] = (Button)findViewById(R.id.divide);
        buttons[13] = (Button)findViewById(R.id.multiple);
        buttons[14] = (Button)findViewById(R.id.minus);
        buttons[15] = (Button)findViewById(R.id.plus);
        buttons[16] = (Button)findViewById(R.id.equal);
        buttons[17] = (Button)findViewById(R.id.dot);


        //添加监听事件
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "0";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "1";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "2";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "3";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "4";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "5";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "6";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "7";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "8";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += "9";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        //empty
        buttons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = "";
                text2.setText("0");
                text1.setText(null);
            }
        });
        //delete
        buttons[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expression.length() < 1){
                    return;
                }
                expression = expression.substring(0,expression.length()-1);
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[12].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[13].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[14].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        buttons[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[15].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        //equal
        buttons[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setText(expression + "=");
                text1.setSelection(expression.length()+1);//在第一行显示计算表达式
                expression = calculate(expression);
                text2.setText(expression);//在第二行显示计算结果
            }
        });
        buttons[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[17].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });





    }



}


