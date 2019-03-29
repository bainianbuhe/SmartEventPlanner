package osu.smartPlanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;


class sortEventComp implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        if (Integer.valueOf(e1.getPriority()) < Integer.valueOf(e2.getPriority())) return 1;
        else return -1;
    }
}

public class EventListFragment extends Fragment {
    private RecyclerView mRequestRecyclerView;
    private EventAdapter mAdapter;
    private  String userName;
    private String sortType;
    public void setUserName(String username) {
        userName = username;
    }
    public void setSort(String type) {sortType = type;}
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_event_list,container,false);
        mRequestRecyclerView=(RecyclerView)view.findViewById(R.id.request_recycler_view);
        mRequestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;

    }

    public void updateUI()

    {
        SeeRequestsRequest(new VolleyCallback() {
            @Override
            public void onSuccess(ArrayList<Event> result) {
                if (sortType.equals("priority")) Collections.sort(result, new sortEventComp());
                ArrayList<Event> events = result;
//                Log.e("TAG3","updateuilength"+events.size());
                mAdapter=new EventAdapter(events);
                mRequestRecyclerView.setAdapter(mAdapter);
            }
        });

    }
    private class RequestHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private Event mEvent;
        private TextView mTitleTextView;
        private TextView mDescriptionTextView;
        private TextView mTimeTextView;
        private TextView mLocationTextView;
        private TextView mPriorityTextView;
        private TextView mContactsTextView;
        public RequestHolder(LayoutInflater inflater,ViewGroup parent)
        {
            super(inflater.inflate(R.layout.list_item_event,parent,false));
            mTitleTextView=(TextView) itemView.findViewById(R.id.title_text);
            mDescriptionTextView=(TextView) itemView.findViewById(R.id.description_text);
            mTimeTextView=(TextView) itemView.findViewById(R.id.time_text);
            mLocationTextView=(TextView) itemView.findViewById(R.id.location_text);
            mPriorityTextView=(TextView) itemView.findViewById(R.id.priority_text);
            mContactsTextView=(TextView) itemView.findViewById(R.id.contacts_text);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view)
        {
            String title = mEvent.getTitle();
            String description = mEvent.getDescription();
            String time = mEvent.getTime();
            String location = mEvent.getLocation();
            String contacts = mEvent.getContacts();
            String priority = mEvent.getPriority();
            Intent intent = new Intent(view.getContext(), EventActivity.class);
            intent.putExtra("USERNAME", userName);
            intent.putExtra("TITLE", title);
            intent.putExtra("DESCRIPTION", description);
            intent.putExtra("TIME", time);
            intent.putExtra("LOCATION", location);
            intent.putExtra("CONTACTS", contacts);
            intent.putExtra("PRIORITY", priority);
            intent.putExtra("UPDATE", true);
            view.getContext().startActivity(intent);
            //Intent intent =new Intent(getActivity().getApplicationContext(),RequestDetailActivity.class);
            //intent.putExtra(EXTRA_DRIVER_USERNAME,driverUserName);
            //intent.putExtra(EXTRA_PASSENGER_USERNAME,mEvent.getUserName());
           // startActivity(intent);
        }
        public void bind(Event event)
        {
            mEvent=event;
            mTitleTextView.setText(mEvent.getTitle());
            mDescriptionTextView.setText("Description:"+mEvent.getDescription());
            mTimeTextView.setText("Time:"+mEvent.getTime());
            mContactsTextView.setText("Contacts:"+mEvent.getContacts());
            mPriorityTextView.setText("Priority:"+mEvent.getPriority());
            mLocationTextView.setText("Location:"+mEvent.getLocation());

        }
    }
    private class EventAdapter extends RecyclerView.Adapter<RequestHolder>
    {
        private ArrayList<Event> mEvents;
        public EventAdapter(ArrayList<Event> events)
        {mEvents=events;}
        @Override
        public RequestHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            return new RequestHolder(layoutInflater,parent);
        }
        @Override
        public void onBindViewHolder(RequestHolder holder,int position)
        {
            Event carliftRequest=mEvents.get(position);
            holder.bind(carliftRequest);
        }
        @Override
        public int getItemCount()
        {return mEvents.size();}
    }

    public void SeeRequestsRequest(final VolleyCallback callback) {
        //请求地址
        final ArrayList<Event> eventobjects=new ArrayList<>();
        String url = "http://www.hygg.com.ngrok.io/SmartEventPlanner/QueryEventsServlet";
        String tag = "SeeRequestServlet"+userName;    //注②
        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                            JSONArray events = jsonObject.getJSONArray("Events");
                            for (int i = 0; i < events.length(); i++) {
                                JSONObject event = events.getJSONObject(i);    //注②
                                String title=event.getString("Title");
                                String time=event.getString("Time");
                                String description=event.getString("Description");
                                String priority=event.getString("Priority");
                                String contacts=event.getString("Contacts");
                                String location=event.getString("Location");
                                Event eventobject=new Event();
                                eventobject.setUserName(userName);
                                eventobject.setTitle(title);
                                eventobject.setTime(time);
                                eventobject.setDescription(description);
                                eventobject.setPriority(priority);
                                eventobject.setContacts(contacts);
                                eventobject.setLocation(location);
                                eventobjects.add(eventobject);

                            }
                            //Log.e("TAG3","inseelength"+eventobjects.size());
                            callback.onSuccess(eventobjects);

                        } catch (JSONException e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Toast.makeText(getActivity(),R.string.connection_failed,Toast.LENGTH_SHORT).show();
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error!=null){
                    if(error instanceof TimeoutError){
                        Toast.makeText(getActivity(),"网络请求超时，请重试！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof ServerError) {
                        Toast.makeText(getActivity(),"服务器异常",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof NetworkError) {
                        Toast.makeText(getActivity(),"请检查网络",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(error instanceof ParseError) {
                        Toast.makeText(getActivity(),"数据格式错误",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("TAG", error.getMessage(), error);
            }

        }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> params = new HashMap<>();
                params.put("userName", userName);
                return params;
            }
        };



        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }
    private interface VolleyCallback{
        void onSuccess(ArrayList<Event> result);
    }



}




