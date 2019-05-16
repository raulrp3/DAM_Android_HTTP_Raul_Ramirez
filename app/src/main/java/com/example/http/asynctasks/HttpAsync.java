package com.example.http.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.http.interfaces.AsyncResponse;

public class HttpAsync extends AsyncTask<Object, Void, Object> {

    private String TAG = this.getClass().getSimpleName();
    public AsyncResponse asyncResponse;

    public HttpAsync(AsyncResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object... params) {
        Context context = (Context) params[0];
        String requestType = (String) params[1];
        Object requestParam = params[2];

        switch (requestType){
            case "get": return HttpResponse.getHttpResponse("https://jsonplaceholder.typicode.com/posts");
            case "getparam": return HttpResponse.getParamHttpResponse("https://jsonplaceholder.typicode.com/posts/" + requestParam);
            case "post": return HttpResponse.postHttpResponse("https://jsonplaceholder.typicode.com/posts", (String[]) requestParam);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

        if (result != null){
            asyncResponse.processFinish(result);
        }
    }
}
