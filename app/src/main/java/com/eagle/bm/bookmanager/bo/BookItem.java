package com.eagle.bm.bookmanager.bo;

import com.eagle.bm.bookmanager.common.Utils;

import java.io.File;

/**
 * 图书类
 */
public class BookItem {
    /**
     * 书名
     */
    private String name;
    /**
     * 文件大小
     */
    private String size;

    private boolean isDir;

    private File file;

    /**
     * 文件的绝对路径
     */
    private String absolutePath;

    public BookItem(String name, String absolutePath) {
        this.name = name;
        this.absolutePath = absolutePath;
        this.file = new File(absolutePath);
        this.isDir = this.file.isDirectory();
        this.size = Utils.FormetFileSize(this.file.length());
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
