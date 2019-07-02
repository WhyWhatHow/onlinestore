package com.sdut.onlinestore.utils;

import com.sdut.onlinestore.pojo.Category;

import java.util.List;

import java.util.ArrayList;

public class CPUtils {
    //
//    public  static Object getChridens(Integer parentId, ArrayList<T> list){
//        return  null;
//    }
    public List<Category> getChridrens(Integer parentId, List<Category> list) {
        List<Category> arrayList = new ArrayList(100);
        for (Category cat :
                list) {
            if (parentId == cat.getParentid()) {
                arrayList.addAll(getChridrens(cat.getCid(), cat.getChridernList()));

                arrayList.add(cat);
            }

        }
        return arrayList;
    }
}
