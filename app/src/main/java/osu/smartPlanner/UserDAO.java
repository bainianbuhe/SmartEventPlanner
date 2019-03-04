package osu.smartPlanner;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    static public   void addEvent(final Event event, final Context context) {
        //请求地址
        String url = "http://www.hygg.com.ngrok.io/SmartEventPlanner/AddEventServlet";
        String tag = "AddEventServlet"+event.getUserName();    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(context);

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
                                Toast.makeText(context,"Success adding a event",Toast.LENGTH_LONG).show();

                            } else {
                                //做自己的登录失败操作，如Toast提示
                                Toast.makeText(context,"Failed in adding a event",Toast.LENGTH_LONG).show();
                                Log.e("TAG","result is"+result);
                            }
                        } catch (JSONException e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Toast.makeText(context,R.string.connection_failed,Toast.LENGTH_SHORT).show();
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error!=null){
                    if(error instanceof TimeoutError){
                        Toast.makeText(context,"网络请求超时，请重试！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof ServerError) {
                        Toast.makeText(context,"服务器异常",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof NetworkError) {
                        Toast.makeText(context,"请检查网络",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof ParseError) {
                        Toast.makeText(context,"数据格式错误",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {
               // Log.e("tag","username in addrequest is "+userName);
                Map<String, String> params = new HashMap<>();
                params.put("userName", event.getUserName());
                params.put("contacts",  event.getContacts());
                params.put("description",event.getDescription());
                params.put("location",  event.getLocation());
                params.put("priority",event.getPriority());
                params.put("time",  event.getTime());
                params.put("title", event.getTitle());
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }
    static public   void updateEvent(final String originalUserName, final String originalTitle, final Event event, final Context context) {
        //请求地址
        String url = "http://www.hygg.com.ngrok.io/SmartEventPlanner/UpdateEventServlet";
        String tag = "AddEventServlet"+event.getUserName();    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(context);

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
                                Toast.makeText(context,"Success adding a event",Toast.LENGTH_LONG).show();

                            } else {
                                //做自己的登录失败操作，如Toast提示
                                Toast.makeText(context,"Failed in adding a event",Toast.LENGTH_LONG).show();
                                Log.e("TAG","result is"+result);
                            }
                        } catch (JSONException e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Toast.makeText(context,R.string.connection_failed,Toast.LENGTH_SHORT).show();
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error!=null){
                    if(error instanceof TimeoutError){
                        Toast.makeText(context,"网络请求超时，请重试！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof ServerError) {
                        Toast.makeText(context,"服务器异常",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof NetworkError) {
                        Toast.makeText(context,"请检查网络",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof ParseError) {
                        Toast.makeText(context,"数据格式错误",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {
                // Log.e("tag","username in addrequest is "+userName);
                Map<String, String> params = new HashMap<>();
                params.put("originalUserName", originalUserName);
                params.put("originalTitle",  originalTitle);
                params.put("userName", event.getUserName());
                params.put("contacts",  event.getContacts());
                params.put("description",event.getDescription());
                params.put("location",  event.getLocation());
                params.put("priority",event.getPriority());
                params.put("time",  event.getTime());
                params.put("title", event.getTitle());
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }
    static public   void deleteEvent(final String originalUserName, final String originalTitle, final Context context) {
        //请求地址
        String url = "http://www.hygg.com.ngrok.io/SmartEventPlanner/DeleteEventServlet";
        String tag = "AddEventServlet"+originalUserName;    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(context);

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
                                Toast.makeText(context,"Success adding a event",Toast.LENGTH_LONG).show();

                            } else {
                                //做自己的登录失败操作，如Toast提示
                                Toast.makeText(context,"Failed in adding a event",Toast.LENGTH_LONG).show();
                                Log.e("TAG","result is"+result);
                            }
                        } catch (JSONException e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Toast.makeText(context,R.string.connection_failed,Toast.LENGTH_SHORT).show();
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error!=null){
                    if(error instanceof TimeoutError){
                        Toast.makeText(context,"网络请求超时，请重试！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof ServerError) {
                        Toast.makeText(context,"服务器异常",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof NetworkError) {
                        Toast.makeText(context,"请检查网络",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof ParseError) {
                        Toast.makeText(context,"数据格式错误",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {
                // Log.e("tag","username in addrequest is "+userName);
                Map<String, String> params = new HashMap<>();
                params.put("originalUserName", originalUserName);
                params.put("originalTitle",  originalTitle);
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }


}
