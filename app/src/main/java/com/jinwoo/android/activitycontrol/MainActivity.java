package com.jinwoo.android.activitycontrol;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 *  1. 액티비티 라이프 사이클 관리
 *  2.
 */

public class MainActivity extends AppCompatActivity {

    Button common;
    Button transparent1, transparent2;
    Button btnDial, btnSms, btnBrowse;

    EditText edittext;
    EditText editDial, editSms, editBrowse;
    TextView tv1, tv2;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        common = (Button) findViewById(R.id.common);
        transparent1 = (Button) findViewById(R.id.transparent1);
        transparent2 = (Button) findViewById(R.id.transparent2) ;
        btnDial = (Button) findViewById((R.id.btnDial));
        btnBrowse = (Button) findViewById(R.id.btnBrowse);
        btnSms = (Button) findViewById(R.id.btnSms);

        editSms = (EditText) findViewById(R.id.editSms);
        editBrowse = (EditText) findViewById(R.id.editBrowse);
        editDial = (EditText) findViewById(R.id.editDial);
        edittext = (EditText) findViewById(R.id.edittext);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        btnDial.setOnClickListener(clickListener);
        btnSms.setOnClickListener(clickListener);
        btnBrowse.setOnClickListener(clickListener);
        common.setOnClickListener(clickListener);
        transparent1.setOnClickListener(clickListener);
        transparent2.setOnClickListener(clickListener);

    }

    public static final int ONE = 1;
    public static final int TWO = 2;


    View.OnClickListener clickListener = new View.OnClickListener(){
        Intent intent;
        String value;
        public void onClick(View view){
            switch(view.getId()){
                case R.id.common:
                    // # Activity로 값 넘기기
                    // 1. Intent 생성
                    intent = new Intent(MainActivity.this, CommonActivity.class);
                    // 2. putExtra 함수에 전달할 값 설정 (var라는 이름에 값을 넣어서 보낸다.)
                    intent.putExtra("var", edittext.getText().toString());
                    // 3. Extra에 담긴 값을 intent로 전달한다.
                    startActivity(intent);
                    break;

                case R.id.transparent1:
                    // # 호출한 액티비티로부터 값을 돌려 받을 때
                    intent = new Intent(MainActivity.this, TransActivity.class);
                    // 2. putExtra 함수에 전달할 값 설정 (var라는 이름에 값을 넣어서 보낸다.)
                    intent.putExtra("var", edittext.getText().toString());
                    intent.putExtra("var2","33333");
                    // 3.
                    startActivityForResult(intent, ONE); //구분 ONE
                    break;
                case R.id.transparent2:
                    // # 호출한 액티비티로부터 값을 돌려 받을 때
                    intent = new Intent(MainActivity.this, TransActivity.class);
                    // 2. putExtra 함수에 전달할 값 설정 (var라는 이름에 값을 넣어서 보낸다.)
                    intent.putExtra("var", edittext.getText().toString());
                    intent.putExtra("var2","33333");
                    // 3. 액티비티를 호출
                    startActivityForResult(intent, TWO); // 구분 TWO
                    break;

                // 묵시적 인텐트 발생
               case R.id.btnDial:
                    value = editDial.getText().toString();
                    intent  = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + value));
                    startActivity(intent);
                    break;
                case R.id.btnBrowse:
                    value = editBrowse.getText().toString();
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + value));
                    startActivity(intent);
                    break;
                case R.id.btnSms:
                    value = editSms.getText().toString();
                    intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + value));
                    startActivity(intent);
                    break;
            }

        }
    };

    /** startActivityForResult 함수로 호출된 액티비티가 종료되면서 자동으로 호출된다.
     *
     * @param requestCode  : 호출시에 호출한측(Main Activity) 액티비티에서 넘긴 구분값
     * @param resultCode   : 호출된 액티비티의 처리 상태 코드
     * @param intent         : 호출된 액티비티가 돌려주는 데이터
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        // 처리상태 코드 체크
        if(resultCode == 1){

            // 1. 돌려받은 intent를 꺼내고
            Bundle bundle = intent.getExtras();
            String result = bundle.getString("result");

            // 2. 호출한 측 코드를 매칭후 값을 처리
            switch(requestCode){
                case ONE:
                    tv1.setText(result);
                    break;
                case TWO:
                    tv2.setText(result);
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.print("onStart 시작",TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.print("onResume 시작",TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.print("onPause 시작",TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.print("onStop 시작",TAG);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.print("onRestart 시작",TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.print("onDestroy 시작",TAG);
    }

}
