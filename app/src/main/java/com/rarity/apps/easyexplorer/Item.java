package com.rarity.apps.easyexplorer;

import android.content.Context;
import android.webkit.MimeTypeMap;

import java.io.File;

/**
 * Created by Premang on 01-Oct-16.
 */
public class Item implements Comparable<Item>{

    private String name, type, path;
    private int clicks;
    private File file;
    private Context context;

    public Item(Context context, File file){
        clicks = DataBase.getClicks(path+"/"+name);
        name = file.getName();
        path = file.getAbsolutePath();
        this.file = file;
        this.context = context;

        if(file.isDirectory()){
            type = null;
        }else {
            MimeTypeMap myMime = MimeTypeMap.getSingleton();

            String ext = "";

            for(int i=name.length()-1; i>=0; i--){
                if(name.charAt(i) == '.')
                    break;
                ext = name.charAt(i) + ext;
            }

            type = myMime.getMimeTypeFromExtension(ext);
        }
    }

    @Override
    public int compareTo(Item o) {
        if(this.clicks > o.clicks)
            return 1;
        else
            return -1;
    }

    public String getName(){
        return name;
    }

    public String getPath(){
        return path;
    }

    public File getFile(){
        return file;
    }

    public String getType(){
        return type;
    }

    public void delete(){
        File f=new File(path);
        f.delete();
    }

    public void rename(String newName){
        file.renameTo(new File(newName));
        this.name = newName;
    }
}
