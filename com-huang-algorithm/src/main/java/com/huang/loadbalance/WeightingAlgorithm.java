package com.huang.loadbalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by JeffreyHy on 2017/10/30.
 */
public class WeightingAlgorithm {
    private static final Logger logger = LoggerFactory.getLogger(WeightingAlgorithm.class);

    static final String methodA = "methodA";
    static final String methodB = "methodB";
    static HashMap<String, WeightDTO> methodMap = new HashMap<>();

    static {
        WeightDTO dto1 = new WeightDTO();
        dto1.setWeight(100);
        methodMap.put(methodA, dto1);
        WeightDTO dto2 = new WeightDTO();
        dto2.setWeight(0);
        methodMap.put(methodB, dto2);
    }

    static String getMethod() {
        Set<String> keySet = methodMap.keySet();
        List<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);
        return weightChoice(keyList);
    }

    static String weightChoice(List<String> keyList) {
        int minR, maxR = -1;
        for (String key : keyList) {
            WeightDTO dto = methodMap.get(key);
            if (dto.getWeight() == 0) {
                continue;
            }
            minR = maxR + 1;
            maxR = minR + dto.getWeight() - 1;
            dto.setMaxr(maxR);
            dto.setMinR(minR);
        }
        Random random = new Random();
        int index = random.nextInt(maxR + 1);
        for (String key : keyList) {
            WeightDTO dto = methodMap.get(key);
            int min = dto.getMinR();
            int max = dto.getMaxr();
            if (min <= index && index <= max) {
                return key;
            }
        }
        return null;
    }

    static void methodA() {
        System.out.println("call methodA");
    }

    static void methodB() {
        System.out.println("call methodB");
    }

    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i < 100; i++) {
            String key = getMethod();
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
        logger.info("-----------");
    }
}
