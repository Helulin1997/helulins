package com.example.yuekaole;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        String   baseUrl="http://www.93.gov.cn/93app/data.do?channelId=0&startNum=1";
        private PullToRefreshListView plv;
        private  MAdapter  adapter;
        private ImageLoader  instance;
        private   JsonDao  jsonDao;
        List<Prodct.DataBean> list=new     ArrayList<>();
        private   int   pager=0;
        @SuppressLint("HandlerLeak")
        Handler    handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what==0){
                    String ss=(String)msg.obj;
                    jsonDao.insertData(baseUrl+pager,ss);
                    Gson gson = new Gson();
                    Prodct prodct = gson.fromJson(ss, Prodct.class);
                    List<Prodct.DataBean> data = prodct.getData();
                    list.addAll(data);
                    adapter.notifyDataSetChanged();
                    plv.onRefreshComplete();
                }



            }
        };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plv = (PullToRefreshListView) findViewById(R.id.plv);
        plv.setMode(PullToRefreshBase.Mode.BOTH);
         instance = ImageLoader.getInstance();
        getNetDate(0);
        adapter=new  MAdapter();
        plv.setAdapter(adapter);
        jsonDao=new   JsonDao(MainActivity.this);


        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                   list.clear();
                   getNetDate(0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

                   pager++;
                  getNetDate(pager);
            }
        });
    }

    public void getNetDate(int pager) {

            final String urlString=baseUrl+pager;
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    String netJson = NetUtil.getNetJson(urlString);
                    Message message = new Message();
                    message.what=0;
                    message.obj=netJson;
                    handler.sendMessage(message);

                }
            }.start();

    }

    private class MAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
                ViewHandler   handler=null;
                if (view==null){
                   handler=new   ViewHandler();
                    view = View.inflate(MainActivity.this, R.layout.teme, null);
                    handler.image = view.findViewById(R.id.image);
                    handler.tv = view.findViewById(R.id.tv);
                    view.setTag(handler);
                }else {

                    handler =(ViewHandler) view.getTag();

                }
            DisplayImageOptions option = ImageLoaderUtils.getDisplayImageOption();

            instance.displayImage((String) list.get(i).getIMAGEURL(),handler.image,option);
               handler.tv.setText(list.get(i).getTITLE());

            return view;
        }
    }

    private class ViewHandler {

       ImageView  image;
       TextView  tv;

    }
}
