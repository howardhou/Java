package com.example.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestOgnl {

    public static void main(String[] args) {

        //创建一个Ognl上下文对象
        // Ognl 3.2.10 之后, 要这样实例化： 解决：MemberAccess implementation must be provided! 问题
        // https://blog.csdn.net/LX928525166/article/details/82699572
        OgnlContext context = new OgnlContext(null,null,new DefaultMemberAccess(true));

        //放入数据
        Map map = new HashMap();
        map.put("who", "Who am i?");
        context.put("who", "Who am i dd?");

        User user = new User();
        user.setId(19612);
        user.setName("sakura");

        User user1 = new User();
        user1.setId(1);
        user1.setName("firer");
        User user2 = new User();
        user2.setId(2);
        user2.setName("firer2");
        List users = new ArrayList();
        users.add(user1);
        users.add(user2);
        users.add(user);
        Department dep = new Department();
        dep.setUsers(users);
        dep.setName("dep");
        dep.setId(11);

        //获取数据（map）
        String value = (String)map.get("who");
        System.out.println("map.get(\"who\") : " + value);

        Object s = context.get("user");
        System.out.println("context.get(\"user\") : " + s);

        try {
            Object root = Ognl.getRoot(context);
            System.out.println("root : " + root);

            String who1 = (String)Ognl.getValue("who", context, map);
            System.out.println("who1 : " + who1);

            // # 号 表示 访问非根对象的属性
            String who2 = (String)Ognl.getValue("#who", context, "");
            System.out.println("who2 : " + who2);

            Object whoExp = Ognl.parseExpression("#who");
            String who3 = (String)Ognl.getValue(whoExp, context, 0);
            System.out.println("who3 : " + who3);

            String name1 = (String)Ognl.getValue("name", context, user);
            System.out.println("name1 : " + name1);

            // 语法不正确
//            String name2 = (String)Ognl.getValue("#user.name",context, user);
//            System.out.println("name2 : " + name2);

            //who1 who2 who3 返回同样的值， whoExp 重复使用可以提高效率

            Object o = Ognl.getValue("users[1].name", context, dep);
            System.out.println("name3 : " + o);

            List names = (List)Ognl.getValue("users.{name}", context, dep);
            System.out.println("names : " + names);

            // # 号用于过滤和投影（projecting）集合
            List ids = (List)Ognl.getValue("users.{? #this.id > 1}",context, dep);
            System.out.println("ids : " + ids);

        } catch (OgnlException e) {
            //error handling
        }

    }

}
