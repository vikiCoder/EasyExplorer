package com.rarity.apps.easyexplorer;

import android.content.Context;

import java.io.File;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by Premang on 01-Oct-16.
 */
public class FileSystem {

    private TreeSet<Item> tree;

    public FileSystem(Context context, String path){
        tree = new TreeSet<Item>();

        File file = new File(path);

        if(file.isDirectory()){
            File[] list = file.listFiles();

            for(File f : list)
                if(!f.isHidden())
                    tree.add(new Item(context, f));

        }
    }

    public LinkedList<Item> getItems(){
        LinkedList<Item> list = new LinkedList<Item>();

        for(Item item : tree)
            list.add(0, item);

        return list;
    }

}
