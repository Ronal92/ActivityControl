package com.jinwoo.android.activitycontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.onClick;

public class TransActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etValue;
    Button btnOK;
    TextView tvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);

        etValue = (EditText) findViewById(R.id.etValue);
        btnOK = (Button) findViewById(R.id.btnOK);
        tvValue = (TextView) findViewById(R.id.tvValue);
    }

    public void onClick(View v){
       returnValue();
        // 6. 액티비티를 종료시하여 메인 액티비티를 화면에 나타낸다. (액티비티 스택에서 벗어난다.)
        finish();
    }

    public void onBackPressed(){
        returnValue();
        super.onBackPressed();
    }

    private void returnValue(){
        Intent intent = new Intent();
        // 1. 되돌려 줄 값을 설정
        String str = etValue.getText().toString();
        // 2. 처리상태를 설정
        int statusCode = 1;
        // 3. 되돌려 줄 값이 문제가 있으면 처리상태 변경
        if(str == null || str.equals("")){
            statusCode = 0;
        }
        // 4. 돌려줄값을 intent에 세팅
        intent.putExtra("result", str);
        // 5. setResult 함수로 결과값을 전송
        setResult(statusCode, intent);

    }
}
