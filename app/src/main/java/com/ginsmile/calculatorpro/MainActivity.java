package com.ginsmile.calculatorpro;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private String expression = "";
    private boolean last_equal = false;//上一次的按键是否为等号
    private EditText text1;//第一行，用来显示按过等号之后的完整表达式
    private EditText text2;//第二行，用来显示表达式和结果
    private static boolean isSimple = true; //当前是否是简易计算器

    private View board;
    private View board2;

    private int screen_width;
    private int screen_height;

    private LinearLayout display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //为了得到用户区域的高度，重写onWindowFocusChanged
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            System.out.println("second");
            StringBuilder sb = new StringBuilder();
            Dimension dimen1 = getAreaOne(this);
            Dimension dimen2 = getAreaTwo(this);
            Dimension dimen3 = getAreaThree(this);
            Log.v("one=>","Area one : \n\tWidth: "+dimen1.mWidth + ";\tHeight: "+dimen1.mHeight);
            Log.v("two=>","\nArea two: \n\tWidth: "+dimen2.mWidth + ";\tHeight: "+dimen2.mHeight);
            Log.v("three","\nArea three: \n\tWidth: "+dimen3.mWidth + ";\tHeight: "+dimen3.mHeight);


            screen_width = dimen3.mWidth;
            screen_height = dimen3.mHeight;

            init();
        }
    }

    //初始化
    private void init(){
        //初始化change按钮和显示区域
        //设置这一区域的高度始终为用户区域高度的三分之一
        display  = (LinearLayout)findViewById(R.id.display);
        android.view.ViewGroup.LayoutParams lp =display.getLayoutParams();
        lp.height=screen_height/3;
        text1 = (EditText)findViewById(R.id.text1);
        text2 = (EditText)findViewById(R.id.text2);
        Button change_btn = (Button)findViewById(R.id.change);
        change_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //更换键盘
                if(isSimple == true){
                    //缩放动画效果
                    board.setVisibility(View.GONE);
                    board2.setVisibility(View.VISIBLE);
                    ScaleAnimation sa = new ScaleAnimation(1.2f,1f,1.2f,1f,
                            Animation.RELATIVE_TO_SELF,1f,
                            Animation.RELATIVE_TO_SELF,1f);
                    sa.setDuration(300);
                    board2.startAnimation(sa);
                    isSimple = false;
                }else{
                    ScaleAnimation sa = new ScaleAnimation(1f,1.2f,1f,1.2f,
                            Animation.RELATIVE_TO_SELF,1f,
                            Animation.RELATIVE_TO_SELF,1f);
                    sa.setDuration(300);
                    board2.startAnimation(sa);

                    board2.setVisibility(View.GONE);
                    board.setVisibility(View.VISIBLE);
                    isSimple = true;
                }
            }
        });


        //初始化计算器键盘
        Button[] buttons = new Button[18];
        Button[] buttons2 = new Button[30];
        initSimpleBoard(buttons);//初始化简易计算器键盘
        initScienceBoard(buttons2);//初始化科学计算器键盘
        board = (View)findViewById(R.id.board);
        board2 = (View)findViewById(R.id.board2);
        board2.setVisibility(View.GONE);

    }

    //初始化简易计算器键盘
    private void initSimpleBoard(final Button[] buttons){
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


        initCommonBtns(buttons);


        int btn_width = screen_width/4;
        int btn_height = (screen_height - screen_height/3)/5;//tablelayout为屏幕的2/3大，一共5行
        for(int i= 0; i < 18; i++){
            buttons[i].setWidth(btn_width);
            buttons[i].setHeight(btn_height);
        }

        buttons[0].setWidth(btn_width*2);
        buttons[16].setHeight(btn_height*2);
    }


    //初始化科学计算器键盘
    private void initScienceBoard(final Button[] buttons){
        buttons[0] = (Button)findViewById(R.id.zero2);
        buttons[1] = (Button)findViewById(R.id.one2);
        buttons[2] = (Button)findViewById(R.id.two2);
        buttons[3] = (Button)findViewById(R.id.three2);
        buttons[4] = (Button)findViewById(R.id.four2);
        buttons[5] = (Button)findViewById(R.id.five2);
        buttons[6] = (Button)findViewById(R.id.six2);
        buttons[7] = (Button)findViewById(R.id.seven2);
        buttons[8] = (Button)findViewById(R.id.eight2);
        buttons[9] = (Button)findViewById(R.id.nine2);

        buttons[10] = (Button)findViewById(R.id.empty2);
        buttons[11] = (Button)findViewById(R.id.delete2);
        buttons[12] = (Button)findViewById(R.id.divide2);
        buttons[13] = (Button)findViewById(R.id.multiple2);
        buttons[14] = (Button)findViewById(R.id.minus2);
        buttons[15] = (Button)findViewById(R.id.plus2);
        buttons[16] = (Button)findViewById(R.id.equal2);
        buttons[17] = (Button)findViewById(R.id.dot2);

        initCommonBtns(buttons);


        //初始化剩余的12个按钮
        buttons[18] = (Button)findViewById(R.id.sin);
        buttons[19] = (Button)findViewById(R.id.cos);
        buttons[20] = (Button)findViewById(R.id.tan);
        buttons[21] = (Button)findViewById(R.id.ln);
        buttons[22] = (Button)findViewById(R.id.log);

        buttons[23] = (Button)findViewById(R.id.factorial);
        buttons[24] = (Button)findViewById(R.id.power);
        buttons[25] = (Button)findViewById(R.id.sqrt);
        buttons[26] = (Button)findViewById(R.id.pi);
        buttons[27] = (Button)findViewById(R.id.left_parentheses);
        buttons[28] = (Button)findViewById(R.id.right_parentheses);
        buttons[29] = (Button)findViewById(R.id.e);

        //让每个按钮的高度为tablelayout的1/6
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setHeight(screen_height*2/3/6);
        }


    }


    //初始化简易计算器，科学计算器相同的18个按钮
    private void initCommonBtns(final Button[] buttons){
        //添加监听事件
        //数字0～9
        for(int i = 0; i < 10; i++){
            final int m = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(last_equal){
                        expression = "";//这次按的数字，如果上次按了等号，则清空表达式
                        last_equal = false;
                    }
                    expression += buttons[m].getText();
                    text2.setText(expression);
                    text2.setSelection(expression.length());
                }
            });
        }
        //empty
        buttons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = "";
                text2.setText("0");
                text1.setText(null);
                last_equal = false;
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
                last_equal = false;
            }
        });
        //divide
        buttons[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[12].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        //multiple
        buttons[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[13].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        //minus
        buttons[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[14].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        //plus
        buttons[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[15].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        //equal
        buttons[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(last_equal) return;//如果上次还是按的等号，那么什么也不做

                //小小的动画效果
                AnimationSet animSet = new AnimationSet(true);
                TranslateAnimation ta = new TranslateAnimation(0,0,0,-100);
                ta.setDuration(80);
                AlphaAnimation aa = new AlphaAnimation(1f, 0f);
                aa.setDuration(75);
                animSet.addAnimation(ta);
                animSet.addAnimation(aa);
                text2.startAnimation(animSet);
                animSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //动画结束之后计算
                        text1.setText(expression + "=");
                        text1.setSelection(expression.length()+1);//在第一行显示计算表达式
                        try{
                            expression = Calculate.calculate(expression);
                            text2.setText(expression);//在第二行显示计算结果
                        }catch(Exception exception){
                            text2.setText("表达式错误!");//在第二行显示计算结果
                            expression = "";
                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });


                // 为下一次按计算器键盘做准备。
                // 如果下次按的是数字，那么清空第二行重新输入第一个数。
                // 如果是非数字，那就当这次的结果是输入的第一个数，直接参与运算。
                last_equal = true;

            }


        });
        buttons[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[17].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
    }



    //屏幕高度
    private Dimension getAreaOne(Activity activity){
        Dimension dimen = new Dimension();
        Display disp = activity.getWindowManager().getDefaultDisplay();
        Point outP = new Point();
        disp.getSize(outP);
        dimen.mWidth = outP.x ;
        dimen.mHeight = outP.y;
        return dimen;
    }
    //不算状态栏的高度
    private Dimension getAreaTwo(Activity activity){
        Dimension dimen = new Dimension();
        Rect outRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        System.out.println("top:"+outRect.top +" ; left: "+outRect.left) ;
        dimen.mWidth = outRect.width() ;
        dimen.mHeight = outRect.height();
        return dimen;
    }
    //不算状态栏，标题栏的高度
    private Dimension getAreaThree(Activity activity){
        Dimension dimen = new Dimension();
        // 用户绘制区域
        Rect outRect = new Rect();
        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
        dimen.mWidth = outRect.width() ;
        dimen.mHeight = outRect.height();
        // end
        return dimen;
    }
    private class Dimension {
        public int mWidth ;
        public int mHeight ;
        public Dimension(){}
    }



}


