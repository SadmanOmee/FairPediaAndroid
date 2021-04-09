package com.example.omeepc.fairpedia;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by OMEE PC on 18/1/2018.
 */

public class MySingleton {
    private  static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mctx;


   public MySingleton(Context context)
    {
        mctx = context;
        requestQueue = getRequestQueue();
    }
    private RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        return requestQueue;
    }
    public static synchronized MySingleton getmInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }
    public<T> void addToRequestQueue(Request<T> request)
    {
        getRequestQueue().add(request);
    }
}
