package com.huang.loadbalance;

import java.util.*;

/**
 * 加权选择算法
 * Created by JeffreyHy on 2017/10/30.
 */
public class WeightAlgorithm {
    public static Map<String, Integer> methodMap = new HashMap<>();
    static final String methodA = "mongoDB";
    static final String methodB = "ES";
    private static Integer pos = 0;

    static {
        methodMap.put(methodA, 100);
        methodMap.put(methodB, 0);
    }

    /**
     * 加权轮询
     *
     * @return
     */
    static String weightRoundRobin() {
        List<String> methodList = getMethodList();
        String method;
        synchronized (pos) {
            if (pos >= methodList.size())
                pos = 0;
            method = methodList.get(pos);
            pos++;
        }
        return method;
    }

    /**
     * 加权随机
     *
     * @return
     */
    static String weightRandom() {
        List<String> methodList = getMethodList();
        Random random = new Random();
        int pos = random.nextInt(methodList.size());
        return methodList.get(pos);
    }

    static List<String> getMethodList() {
        Map<String, Integer> tempMap = new HashMap<>();
        tempMap.putAll(methodMap);
        Iterator<String> iterator = tempMap.keySet().iterator();
        List<String> methodList = new ArrayList<>();
        while (iterator.hasNext()) {
            String method = iterator.next();
            int weight = tempMap.get(method);
            for (int i = 0; i < weight; i++)
                methodList.add(method);
        }
        return methodList;
    }

    static void methodA() {
        System.out.println("call mehtodA");
    }

    static void methodB() {
        System.out.println("call mehtodB");
    }

    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i < 100; i++) {
            //String key=weightRoundRobin();
            String key = weightRandom();
            if (key == null) {
                c++;
                System.out.println("no Method");
            }
            if (methodA == key) {
                a++;
                methodA();
            } else {
                b++;
                methodB();
            }
        }
        System.out.println("call MethodA count:" + a + ",call MethodB count:" + b + ",no Method count:" + c);
    }
}
