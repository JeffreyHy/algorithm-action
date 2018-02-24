package com.huang.intersection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 时间取交集算法
 * Created by JeffreyHy on 2018/1/2.
 */
public class TimeIntersectionAlgorithm {
    private static final Logger logger = LoggerFactory.getLogger(TimeIntersectionAlgorithm.class);

    private static final long st1 = 1497358800000L;
    private static final long et1 = 1497359700000L;
    private static final long st2 = 1497360600000L;
    private static final long et2 = 1497362400000L;
    private static final long st3 = 1497358800000L;
    private static final long et3 = 1497364800000L;

    /**
     * HashSet实现时间取交集
     *
     * @return 交集的秒数
     */
    public int calcTimeIntersectionWithHashSet() {
        Set<Long> attendanceTime = new HashSet<Long>(2 << 10);// 初始化容量 2 << 10
        Set<Long> missionTime = new HashSet<Long>(2 << 15);// 初始化容量 2 << 15
        logger.info("st1 time：{},et1 time：{}", new Date(st1), new Date(et1));
        for (long i = st1 / 1000; i < et1 / 1000; i++) {
            attendanceTime.add(i);
        }
        logger.info("st2 time：{},et2 time：{}", new Date(st2), new Date(et2));
        for (long i = st2 / 1000; i < et2 / 1000; i++) {
            attendanceTime.add(i);
        }
        logger.info("st3 time：{},et3 time：{}", new Date(st3), new Date(et3));
        for (long i = st3 / 1000; i < et3 / 1000; i++) {
            missionTime.add(i);
        }
        attendanceTime.retainAll(missionTime);
        return attendanceTime.size();
    }

    /**
     * BitSet实现时间取交集
     *
     * @return 交集的秒数
     */
    public int calcTimeIntersectionWithBitSet() {
        BitSet attendanceTime = new BitSet();
        BitSet missionTime = new BitSet();
        logger.info("st1 time：{},et1 time：{}", new Date(st1), new Date(et1));
        long start = st1 / 1000;
        long end = et1 / 1000;
        int starts = (int) start;
        int ends = (int) end;
        for (int i = starts; i < ends; i++) {
            attendanceTime.set(i);
        }
        logger.info("st2 time：{},et2 time：{}", new Date(st2), new Date(et2));
        start = st2 / 1000;
        end = et2 / 1000;
        starts = (int) start;
        ends = (int) end;
        for (int i = starts; i < ends; i++) {
            attendanceTime.set(i);
        }
        logger.info("st3 time：{},et3 time：{}", new Date(st3), new Date(et3));
        start = st3 / 1000;
        end = et3 / 1000;
        starts = (int) start;
        ends = (int) end;
        for (int i = starts; i < ends; i++) {
            missionTime.set(i);
        }
        attendanceTime.and(missionTime);
        return attendanceTime.cardinality();
    }
}
