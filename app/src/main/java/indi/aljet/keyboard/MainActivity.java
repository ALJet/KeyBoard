package indi.aljet.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.security.Key;

import indi.aljet.keyboard.widget.Keyboard;
import indi.aljet.keyboard.widget.PayEditText;

public class MainActivity extends AppCompatActivity {

    private static final String[] KEY = new String[]{
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "<<", "0", "完成"
    };

    private PayEditText payEditText;
    private Keyboard keyboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        initView();
        setSubView();
        initEvent();
    }

    private void initView(){
        setContentView(R.layout.activity_main);
        payEditText = (PayEditText)findViewById(R.id
        .PayEditText_pay);
        keyboard = (Keyboard)findViewById(R.id.KeyboardView_pay);
    }


    private void setSubView(){
        //设置键盘
        keyboard.setKeyboardKeys(KEY);
    }

    private void initEvent(){
        keyboard.setOnClickKeyboardListener(new Keyboard.OnClickKeyboardListener() {
            @Override
            public void onKeyClick(int position, String value) {
                if(position < 11 && position != 9){
                    payEditText.add(value);
                }else if(position == 9){
                    payEditText.remove();
                }else if(position == 11){
                    //当点击完成的时候，
                    // 也可以通过payEditText.getText()获取密码，
                    // 此时不应该注册OnInputFinishedListener接口
                    Toast.makeText(getApplication(),
                            "输入的密码是： "+ payEditText.
                    getText().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });


        /**
         * 当输入密码完成时的回调
         *
         */

        payEditText.setOnInputFinishedListener(new PayEditText.OnInputFinishedListener() {
            @Override
            public void onInputFinished(String password) {
                Toast.makeText(getApplication(), "您的密码是：" + password, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
