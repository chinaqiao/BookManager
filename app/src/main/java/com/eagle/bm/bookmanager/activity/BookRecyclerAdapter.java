package com.eagle.bm.bookmanager.activity;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eagle.bm.bookmanager.R;
import com.eagle.bm.bookmanager.bo.BookItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder>  {
    private final ArrayList<BookItem> sampleData = new ArrayList<>();
    private List<File> bookList;

    private BookOnClickListener clickListener;

    // 用于创建控件
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i)
    {

        // 获得列表项控件（LinearLayer对象）

        // list_basic_item.xml布局文件中只包含一个<LinearLayer>标签，在该标签中包含
        // 了一个<TextView>标签
        //  item是LinearLayout对象
        View item = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.main_book_item, parentViewGroup, false);

        return new ViewHolder(item);

    }
    public void setOnClickListener(BookOnClickListener listenter) {
        this.clickListener = listenter;
    }
    // 为控件设置数据
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position)
    {
        //  获取当前item中显示的数据
        final BookItem rowData = sampleData.get(position);
        //  设置要显示的数据
        viewHolder.textName.setText(rowData.getName());
        if (rowData.isDir()) {
            viewHolder.layout.setBackgroundColor(Color.parseColor("#99D9EA"));
        }else {
            viewHolder.textSize.setText(rowData.getSize());
        }
        viewHolder.itemView.setTag(rowData);
        viewHolder.itemView.setOnClickListener(v -> {
            BookItem book = this.sampleData.get(position);
            System.out.println(book.getName());
            System.out.println(book.getFile().getAbsolutePath());
            if (book.getFile().isDirectory())
                this.clickListener.onClick(book);
        });
    }

    @Override
    public int getItemCount()
    {
        return sampleData.size();
    }
    //  删除指定的Item
    public void removeData(int position)
    {
        sampleData.remove(position);
        //  通知RecyclerView控件某个Item已经被删除
        notifyItemRemoved(position);
    }
    //  删除所有Item
    public void removeAllData()
    {
        sampleData.removeAll(sampleData);
        //  通知RecyclerView控件某个Item已经被删除
        notifyDataSetChanged();
    }
    //  在指定位置添加一个新的Item
    public void addItem(int positionToAdd)
    {
        sampleData.add(positionToAdd,new BookItem("新的列表项", "/sdcard/"));
        //  通知RecyclerView控件插入了某个Item
        notifyItemInserted(positionToAdd);
    }

    public void setBookList(List<File> bookList) {
        this.bookList = bookList;
        this.removeAllData();
        this.transform();
        this.notifyDataSetChanged();
    }

    private void transform() {
        for (File file : this.bookList) {
            sampleData.add(new BookItem(file.getName(), file.getAbsolutePath()));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textName;
        private final TextView textSize;
        private final LinearLayout layout;

        public ViewHolder(View itemView)
        {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.book_item);
            textName = (TextView) itemView.findViewById(R.id.book_name);
            textSize = (TextView) itemView.findViewById(R.id.book_size);
        }
    }

}
