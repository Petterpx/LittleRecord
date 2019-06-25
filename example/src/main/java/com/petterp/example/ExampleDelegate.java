package com.petterp.example;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.net.RestClient;

/**
 * auther: Petterp on 2019/4/17
 * Summary:    //测试Demo
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    //对每一个控件做操作
    @Override
    public void onBindView(Bundle savedInstanceState, View view) {
            testRestClient();
    }
    private void testRestClient(){
        RestClient.builder()
                .url("/API/post2.php")
                .params("data","name")
                .loader(getContext())
                .success(response -> {
                    Log.e("Demo",response);
                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                })
                .failure(() -> {
                    Log.e("Demo","失败");

                })
                .error((code, msg) -> {
                    Log.e("Demo",msg);
                })
                .build()
                .post();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
