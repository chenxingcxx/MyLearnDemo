package com.cxx.learndemo.mvvmaccdemo;

import android.os.Bundle;

import com.cxx.learndemo.mvvmaccdemo.databinding.ActivityMainBinding;
import com.cxx.learndemo.mvvmaccdemo.model.UserBean;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserBean userBean = new UserBean("张三zfcdsfsfsasfcsafsafsdf", 25);
        binding.setUser(userBean);
        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        binding.setList(list);
    }
}
