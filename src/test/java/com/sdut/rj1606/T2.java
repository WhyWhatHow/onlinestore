package com.sdut.rj1606;

import com.sun.tools.javac.util.List;

import java.util.ArrayList;

class Location {
    private int id; //id
    private String name; //名称
    private int parentId; //父id

    //为了组合多维集合添加的属性
    private ArrayList<Location> list;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", list=" + list +
                '}';
    }

    public Location(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public ArrayList<Location> getList() {
        return list;
    }

    public void setList(ArrayList<Location> list) {
        this.list = list;
    }

    //GetSet...
}

/**
 * @Author whywhathow
 * TODO: 递归实现分类, 与bfs 实现分类
 * 前端:
 * 后端:
 * @Param
 * @return
 **/
public class T2 {

    public static void main(String[] args) {

        for (Location location : data) {
            System.err.println(location);

        }

        System.out.println("===============");
        //多维
        ArrayList<Location> list2 = getChildsManyGroup(data, 0);
        for (Location l :
                list2) {
            System.out.println("---" + l);

        }
        //        for(Location l : list2){
//            System.out.println(l.getName());
//            for(Location ll : l.getList()){
//                System.out.println("\t"+ll.getName());
//                for(Location lll : ll.getList()){
//                    System.out.println("\t\t"+lll.getName());
//                    for(Location lllL : lll.getList()){
//                        System.out.println("\t\t\t"+lllL.getName());
//
//                    }
//                }
//            }
//        }
    }

    //查询数据库表中所有数据,(这里模拟一个表的所有数据)
    static ArrayList<Location> data = new ArrayList<Location>();

    static {
        //new Location(编号, "名称", 父编号);
        Location l = new Location(1, "编程语言", 0);
        Location l1 = new Location(2, "数据库", 0);
        Location l2 = new Location(3, "Java", 1);
        Location l3 = new Location(4, ".NET", 1);
        Location l4 = new Location(5, "java EE", 3);
        Location l5 = new Location(6, "java SE", 3);
        Location l6 = new Location(7, "java ME", 3);
        Location l7 = new Location(8, "asp.NET", 4);
        Location l8 = new Location(9, "ado.NET", 4);
        Location l9 = new Location(10, "MySQL", 2);
        Location l10 = new Location(11, "Oracle", 2);
        Location l11 = new Location(12, "hibernate", 5);
        Location l12 = new Location(13, "hibernate 3.5", 12);
        Location l13 = new Location(14, "hibernate 4.2", 12);
        data.add(l);
        data.add(l1);
        data.add(l2);
        data.add(l3);
        data.add(l9);
        data.add(l10);
        data.add(l4);
        data.add(l5);
        data.add(l6);
        data.add(l7);
        data.add(l8);
        data.add(l11);
        data.add(l12);
        data.add(l13);
    }

    /**
     * 根据id 获取所有父级目录 一维
     * 用途（首页 > 编程语言 > JAVA >  hibernate > hibernate入门教程）类似这样的一个系统当前位置   传入位置获取到所有父级
     *
     * @param list
     * @param pid
     * @return
     */
    public static ArrayList<Location> getParents(List<Location> list, int pid) {
        ArrayList<Location> arr = new ArrayList<Location>();
        for (Location location : list) {
            if (pid == location.getId()) {
                arr.addAll(getParents(list, location.getParentId()));
                arr.add(location);
                break;
            }
        }
        return arr;
    }

    /**
     * 根据id获取所有子集分类(一维List集合)
     * 1 11 111
     *
     * @param list
     * @param pid
     * @return
     */
    public static ArrayList<Location> getChilds(ArrayList<Location> list, int pid) {
        ArrayList<Location> arr = new ArrayList<Location>();
        for (Location location : list) {
            if (pid == location.getParentId()) {
                arr.addAll(getChilds(list, location.getId()));
                arr.add(location);

            }
        }
        return arr;
    }

    /**
     * 根据id返回所有子集分类,(多维List集合,List中含有子集List)
     * <p>
     * 1
     * 11
     * 111
     * 2
     * 22
     * 222
     *
     * @param list
     * @param pid
     * @return
     */
    public static ArrayList<Location> getChildsManyGroup(ArrayList<Location> list, int pid) {
        ArrayList<Location> arr = new ArrayList<Location>();
        for (Location location : list) {
            if (pid == location.getParentId()) {
                location.setList(getChildsManyGroup(list, location.getId()));
                arr.add(location);
            }
        }
        return arr;
    }

    /**
     * 组合为一维集合
     *
     * @param list
     * @param pid
     * @return
     */
    public static ArrayList<Location> pushOneGroup(List<Location> list, int pid) {
        ArrayList<Location> arr = new ArrayList<Location>();
        for (Location location : list) {
            if (pid == location.getParentId()) {
                arr.add(location);
                arr.addAll(pushOneGroup(list, location.getId()));
            }
        }
        return arr;
    }

    /**
     * 组合为多维集合
     * 用途：系统首页的导航菜单，常见的2-3级通过下面的方法压栈成多维集合在供前台显示
     *
     * @param list 要便利的集合数据
     * @param pid  父id
     * @return
     */
    public static ArrayList<Location> pushManyGroup(List<Location> list, int pid) {
        ArrayList<Location> arr = new ArrayList<Location>();
        for (Location location : list) {
            if (pid == location.getParentId()) {
                location.setList(pushManyGroup(list, location.getId()));
                arr.add(location);
            }
        }
        return arr;
    }

}