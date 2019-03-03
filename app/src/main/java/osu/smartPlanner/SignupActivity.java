package osu.smartPlanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private String userName;
    private String passWord;
    private EditText mUserNameField;
    private EditText mPassWordField;
    private CardView mConfirmSignup;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userName=new String();passWord=new String();
        mUserNameField=(EditText)findViewById(R.id.user_name_edit);
        mUserNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userName=s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPassWordField=(EditText)findViewById(R.id.pass_word_edit);
        mPassWordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passWord=s.toString();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mConfirmSignup=findViewById(R.id.confirm_sign_up_button);
        mConfirmSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupRequest(userName,passWord);

            }
        });

    }

    public   void SignupRequest(final String accountNumber, final String password) {
        //请求地址
        String url = "http://www.hygg.com.ngrok.io/SmartEventPlanner/SignupServlet";
        String tag = "SignupServlet"+userName;    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);

        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                            String result = jsonObject.getString("Result");  //注④
                            if (result.equals("success")) {  //注⑤
                                //做自己的登录成功操作，如页面跳转
                                Toast.makeText(SignupActivity.this,R.string.signup_sucess,Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                                startActivity(intent);
                            } else {
                                //做自己的登录失败操作，如Toast提示
                                Toast.makeText(SignupActivity.this,R.string.signup_fail,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Toast.makeText(SignupActivity.this,R.string.connection_failed,Toast.LENGTH_SHORT).show();
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userName", userName);  //注⑥
                params.put("passWord", password);
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }



}





