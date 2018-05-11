package com.eagle.bm.bookmanager.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 文件系统
 * TODO 缓存 收藏
 */
public class FileSearch {
    /**
     * 当前路径
     */
    private String path;
    /**
     * 当前文件
     */
    private File file;

    public FileSearch(String path) {
        this.path = path;
        this.file = new File(path);
    }

    public List<File> getFileList(){
        return getFileList(this.file);
    }
    public List<File> getFileList(String dir){
        return getFileList(new File(dir));
    }
    public List<File> getFileList(File dir){
        path = dir.getPath();
        file = new File(path);
        ArrayList<File> list = new ArrayList<>();
        File[] arr = dir.listFiles();
        for (File f : arr) {
            // System.out.println(f.getName());
            list.add(f);
        }
        return list;
    }

    /**
     * 返回上一级
     * @return
     */
    public List<File> back() {
        return getFileList(this.file.getParentFile());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
