package com.example.engineering_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switch_but;
    private TextView text_view;
    private Calculator calc;
    private String input_str;

    private StringBuilder str_math_express = new StringBuilder();
    private Integer regim = 1;

    private LinearLayout LL_1, LL_2, LL_3, LL_4;
    private final Button[] butt_number = new Button[10];                //Кнопки чисел
    private Button plus_minus, Point, skobka_open, skobka_close,        //Кнопки простых операций
            plus, minus, mul, div, del, del_one;

    //Кнопки тригонометрии, возведения в степень, корня и логарифма
    private Button koren_2x, koren_3x, exp_x, x_2, x_3, x_y;                 //Первая панель
    private Button sin_x, cos_x, tan_x, ctg_x, sec_x, cosec_x, log10_x, ln_x;   //Вторая панель
    private Button ITOG;                                                        //Кнопка запуска вычислений

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch_but = findViewById(R.id.switch_but);
        text_view = findViewById(R.id.text);
        calc = new Calculator();

        ElementCreate();
        ButtonSetText();
        SwitchFunc();
        ButtonNumberSetClickListener();
        ButtonOperatorSetClickListener();
        ButtonOperatorInjinerSetClickListener();

        View.OnClickListener ClickITOG = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence cs = text_view.getText();
                input_str = String.valueOf(cs);
                calc.onClickITOG(input_str);
                str_math_express = new StringBuilder(calc.getResult());
                text_view.setText(str_math_express);
            }
        };

        ITOG.setOnClickListener(ClickITOG);
    }

    @SuppressLint("WrongViewCast")
    private void ElementCreate(){
        //Кнопки чисел

        Integer[] butt_mass_id = {
                R.id.Zero, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
                R.id.six, R.id.seven, R.id.eight, R.id.nine
        };
        for(int i = 0; i < 10; i++){
            butt_number[i] = findViewById(butt_mass_id[i]);
        }

        //Кнопки простых операций
        plus_minus = findViewById(R.id.plus_minus);
        Point = findViewById(R.id.Point);
        skobka_open = findViewById(R.id.left);
        skobka_close = findViewById(R.id.right);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        del = findViewById(R.id.ac);
        del_one = findViewById(R.id.del_one);

        /*---------Кнопки тригонометрии, возведения в степень, корня и логарифма---------*/
        //Первая панель
        koren_2x = findViewById(R.id.sqrt_2x);
        koren_3x = findViewById(R.id.sqrt_3x);
        exp_x = findViewById(R.id.exp_x);
        x_2 = findViewById(R.id.x_2);
        x_3 = findViewById(R.id.x_3);
        x_y = findViewById(R.id.x_y);

        //Вторая панель
        sin_x = findViewById(R.id.sin_x);
        cos_x = findViewById(R.id.cos_x);
        tan_x = findViewById(R.id.tan_x);
        ctg_x = findViewById(R.id.ctg_x);
        sec_x = findViewById(R.id.sec_x);
        cosec_x = findViewById(R.id.cosec_x);
        log10_x = findViewById(R.id.log_x_y);
        ln_x = findViewById(R.id.ln_x);

        //Кнопка запуска вычислений
        ITOG = findViewById(R.id.equal);
    }

    private void ButtonSetText(){
        x_2.setText(Html.fromHtml(getString(R.string.x_2)));
        x_3.setText(Html.fromHtml(getString(R.string.x_3)));
        x_y.setText(Html.fromHtml(getString(R.string.x_y)));
        koren_2x.setText(Html.fromHtml(getString(R.string.sqrt_2x)));
        koren_3x.setText(Html.fromHtml(getString(R.string.sqrt_3x)));
        exp_x.setText(Html.fromHtml(getString(R.string.exp_x)));

        sin_x.setText(Html.fromHtml(getString(R.string.sin_x)));
        cos_x.setText(Html.fromHtml(getString(R.string.cos_x)));
        tan_x.setText(Html.fromHtml(getString(R.string.tan_x)));
        ctg_x.setText(Html.fromHtml(getString(R.string.ctg_x)));
        sec_x.setText(Html.fromHtml(getString(R.string.sec_x)));
        cosec_x.setText(Html.fromHtml(getString(R.string.cosec_x)));
        log10_x.setText(Html.fromHtml(getString(R.string.log10_x)));
        ln_x.setText(Html.fromHtml(getString(R.string.ln_x)));
    }

    private void SwitchFunc(){
        LL_1 = findViewById(R.id.LL_1);
        LL_2 = findViewById(R.id.LL_2);

        CompoundButton.OnCheckedChangeListener CheckedSwitch = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Log.d("Switch", "Chek!!!");

                if (regim == 1){
                    LL_1.setVisibility(View.GONE);
                    LL_2.setVisibility(View.VISIBLE);
                    regim = 2;
                }
                else if (regim == 2){
                    LL_1.setVisibility(View.VISIBLE);
                    LL_2.setVisibility(View.GONE);
                    regim = 1;
                }
            }
        };
        switch_but.setOnCheckedChangeListener(CheckedSwitch);
    }

    private void ButtonNumberSetClickListener(){
        View.OnClickListener ClickZero = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("0");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickOne = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("1");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickTwo = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("2");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickThree = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("3");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickfFour = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("4");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickfFive = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("5");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickfSix = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("6");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickfSeven = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("7");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickfEight = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("8");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickfNine = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("9");
                text_view.setText(str_math_express);
            }
        };

        butt_number[0].setOnClickListener(ClickZero);
        butt_number[1].setOnClickListener(ClickOne);
        butt_number[2].setOnClickListener(ClickTwo);
        butt_number[3].setOnClickListener(ClickThree);
        butt_number[4].setOnClickListener(ClickfFour);
        butt_number[5].setOnClickListener(ClickfFive);
        butt_number[6].setOnClickListener(ClickfSix);
        butt_number[7].setOnClickListener(ClickfSeven);
        butt_number[8].setOnClickListener(ClickfEight);
        butt_number[9].setOnClickListener(ClickfNine);
    }

    private void ButtonOperatorSetClickListener(){
        View.OnClickListener ClickPlusMinus = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("±");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickPoint = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append(".");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickSkobkaOpen = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickSkobkaClose = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append(")");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickPlus = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("+");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickMinus = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("-");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickMul = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("*");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickDiv = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("/");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickDel = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.delete(0, str_math_express.length());
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickDelOne = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (str_math_express.length() == 0)
                    text_view.setText("");
                else {
                    str_math_express.deleteCharAt(str_math_express.length() - 1);
                    text_view.setText(str_math_express);
                }
            }
        };

        plus_minus.setOnClickListener(ClickPlusMinus);
        Point.setOnClickListener(ClickPoint);
        skobka_open.setOnClickListener(ClickSkobkaOpen);
        skobka_close.setOnClickListener(ClickSkobkaClose);
        plus.setOnClickListener(ClickPlus);
        minus.setOnClickListener(ClickMinus);
        mul.setOnClickListener(ClickMul);
        div.setOnClickListener(ClickDiv);
        del.setOnClickListener(ClickDel);
        del_one.setOnClickListener(ClickDelOne);

    }

    private void ButtonOperatorInjinerSetClickListener(){
        View.OnClickListener ClickKoren2x = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("sqr2(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickKoren3x = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("sqr3(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickExp = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("exp(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickX2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("^2");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickX3 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("^3");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickXY = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("^");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickSinX = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("sin(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickCosX = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("cos(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickTanX = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("tan(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickCtgX = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("ctg(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickSecX = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("sec(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickCoSecX = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("cosec(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickLogX = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("log(");
                text_view.setText(str_math_express);
            }
        };

        View.OnClickListener ClickLnX = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_math_express.append("ln(");
                text_view.setText(str_math_express);
            }
        };

        koren_2x.setOnClickListener(ClickKoren2x);
        koren_3x.setOnClickListener(ClickKoren3x);
        exp_x.setOnClickListener(ClickExp);
        x_2.setOnClickListener(ClickX2);
        x_3.setOnClickListener(ClickX3);
        x_y.setOnClickListener(ClickXY);
        sin_x.setOnClickListener(ClickSinX);
        cos_x.setOnClickListener(ClickCosX);
        tan_x.setOnClickListener(ClickTanX);
        ctg_x.setOnClickListener(ClickCtgX);
        sec_x.setOnClickListener(ClickSecX);
        cosec_x.setOnClickListener(ClickCoSecX);
        log10_x.setOnClickListener(ClickLogX);
        ln_x.setOnClickListener(ClickLnX);

    }
}