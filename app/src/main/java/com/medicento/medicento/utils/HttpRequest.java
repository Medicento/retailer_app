package com.medicento.medicento.utils;

import android.os.AsyncTask;

import com.medicento.medicento.Config;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created by sid on 6/13/17.
 */

public class HttpRequest {
    private Method method;
    private String url;
    private Map<String,String> params= new HashMap<>();
    private boolean shouldExecuteResponse = true;
    private OkHttpClient client;
    private Map<String,String> headers = new HashMap<>();

    private static ArrayList<HttpRequest> allRequests = new ArrayList<>();
    public static void stopAll(){
        for (HttpRequest request : allRequests){
            request.stop();
        }
        allRequests.clear();
    }

    public HttpRequest(String url){
        this.url= Config.serverLink +url+".php";
        this.method=Method.POST;
        allRequests.add(this);
    }

    public HttpRequest(String url,Method method){
        this.url= Config.serverLink +url+".php";
        this.method=method;
    }

    public HttpRequest(){
        allRequests.add(this);
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url= Config.serverLink +url+".php";
        this.method=Method.POST;
    }

    void stop(){
        shouldExecuteResponse=false;
        if(client!=null) client.cancel("Cancelled");
        allRequests.remove(this);
    }


    public HttpRequest addParam(String key,String value){
        params.put(key,value);
        return this;
    }

    public HttpRequest addHeader(String key,String value){
        this.headers.put(key,value);
        return this;
    }

    public interface OnResponseListener{
        void OnResponse(String response);
    }

    public HttpRequest sendRequest(OnResponseListener onResponseListener){
        (new ApiExecuter(onResponseListener)).execute(false);
        return this;
    }

    private Request buildRequest(){

        HttpUrl.Builder builder  = HttpUrl.parse(url).newBuilder();
        for(Map.Entry<String,String> param : params.entrySet()){
            builder.addQueryParameter(param.getKey(),param.getValue());
        }
        HttpUrl urlwithquery = builder.build();

        Request.Builder requestBuilder = null;
        if(method.equals(Method.GET)) {
            requestBuilder= new Request.Builder()
                    .url(urlwithquery)
                    .get();
        }
        else{
            requestBuilder= new Request.Builder()
                    .url(urlwithquery)
                    .post(RequestBody.create(null,new byte[0]));
        }


        for(Map.Entry<String,String> entry : headers.entrySet()){
            requestBuilder.addHeader(entry.getKey(),entry.getValue());
        }
        return requestBuilder.build();
    }

    private class ApiExecuter extends AsyncTask<Boolean,Void,String> {

        OnResponseListener onResponseListener;
        public ApiExecuter(OnResponseListener onResponseListener){
            this.onResponseListener=onResponseListener;
        }
        @Override
        protected String doInBackground(Boolean... booleans) {
            if(shouldExecuteResponse) {
                client = new OkHttpClient();
                Request request = buildRequest();
                Response response = null;
                try {
                    client.setConnectTimeout(60, TimeUnit.SECONDS);
                    client.setReadTimeout(60, TimeUnit.SECONDS);
                    client.setWriteTimeout(60, TimeUnit.SECONDS);
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response != null && response.isSuccessful()) {
                    try {
                        return response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    return null;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(shouldExecuteResponse) {
                onResponseListener.OnResponse(s);
            }
            allRequests.remove(HttpRequest.this);
            super.onPostExecute(s);
        }
    }

}
