package com.eagle.bm.bookmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.eagle.bm.bookmanager.R;
import com.eagle.bm.bookmanager.search.FileSearch;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BookRecyclerAdapter sampleRecyclerAdapter;

    private FileSearch search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.activity_main_rv);
        // 创建线性布局管理器（默认是垂直方向）
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        sampleRecyclerAdapter = new BookRecyclerAdapter();
        recyclerView.setAdapter(sampleRecyclerAdapter);

        search = new FileSearch("/sdcard/");

        initView();
    }

    private void initView() {

        TextView pathText = findViewById(R.id.activity_main_path);
        pathText.setText(search.getPath());

        sampleRecyclerAdapter.setOnClickListener(b -> {
            List<File> list = search.getFileList(b.getFile());
            sampleRecyclerAdapter.setBookList(list);
            pathText.setText(search.getPath());
        });

        sampleRecyclerAdapter.setBookList(search.getFileList());

        Button addButton = findViewById(R.id.activity_main_add);
        addButton.setOnClickListener(v -> sampleRecyclerAdapter.addItem(0));

        Button backButton = findViewById(R.id.activity_main_back);
        backButton.setOnClickListener(v -> {
            sampleRecyclerAdapter.setBookList(search.back());
            pathText.setText(search.getPath());
        });


    }
}
