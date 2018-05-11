package com.eagle.bm.bookmanager.activity;

import com.eagle.bm.bookmanager.bo.BookItem;

@FunctionalInterface
public interface BookOnClickListener {
    void onClick(BookItem book);
}
