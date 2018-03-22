package com.huang.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DB水平拆分算法
 * <p>
 * 拆分方案：假设初始拆分为2个库，每个库2张表。选取分库分表的键，分库：key Mod 2,分表：(key Div 2) Mod 2。共计分为4张表
 * 扩容：
 * 同时扩容库和表，如库扩容为4个，每个库的表扩容为4个，扩容后数据迁移：任意一个新库中4张表的数据均来自某一张旧表。
 * 也就是说，任意一张旧表的数据会裂变为同一个库的4张表
 * 缺点：数据迁移量大，可能某张旧表的数据需要迁移到新库中的4张表
 * <p>
 * 库不变，只扩容表，每个库的表扩容为4个，扩容后数据迁移：任意一张旧表裂变为2张，只需要迁移一半的数据
 * 优点：库不变，只需要把一张表的数据一分为2即可，每张表只需要移动一半的数据，数据迁移量小
 * <p>
 * 总结：进行分库分表前，建议评估下未来需要支撑的数据量，再计算需要分库的数量和分表的数量。
 * 建议分表的数量确定之后，就不再改动，后期需要扩容时，只需要扩容表，数据迁移的工作量相对较小。
 *
 * @author JeffreyHy
 * @date Created by  2018/3/22 17:47
 */
public class HorizontalSplit {
    private static Logger logger = LoggerFactory.getLogger(HorizontalSplit.class);
    //初始拆分
    private static final int INIT_SIZE = 2;
    //扩容1倍
    private static final int RESIZE = 2 << 1;
    private static Map<Integer, Map<Integer, List<Integer>>> databases = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            save(i);
        }
        for (Map.Entry<Integer, Map<Integer, List<Integer>>> database : databases.entrySet()) {
            int dbKey = database.getKey();
            for (Map.Entry<Integer, List<Integer>> table : database.getValue().entrySet()) {
                logger.info("database:{},table:{},data:{}", database.getKey(), table.getKey(), table.getValue().stream().map(value -> String.valueOf(value))
                        .collect(Collectors.joining("|")));

            }
        }
        //库和表同时扩容，库扩大1倍，每个库的表扩大1倍
        Map<Integer, Map<Integer, List<Integer>>> resizeByDbAndTableMap = resize(RESIZE, RESIZE);
        for (Map.Entry<Integer, Map<Integer, List<Integer>>> database : resizeByDbAndTableMap.entrySet()) {
            int dbKey = database.getKey();
            for (Map.Entry<Integer, List<Integer>> table : database.getValue().entrySet()) {
                logger.info("resizeByDbAndTable.database:{},table:{},data:{}", database.getKey(), table.getKey(), table.getValue().stream().map(value -> String.valueOf(value))
                        .collect(Collectors.joining("|")));

            }
        }
        //库不变，每个库的表扩大1倍
        Map<Integer, Map<Integer, List<Integer>>> resizeTableMap = resize(INIT_SIZE, RESIZE);
        for (Map.Entry<Integer, Map<Integer, List<Integer>>> database : resizeTableMap.entrySet()) {
            int dbKey = database.getKey();
            for (Map.Entry<Integer, List<Integer>> table : database.getValue().entrySet()) {
                logger.info("resizeTable.database:{},table:{},data:{}", database.getKey(), table.getKey(), table.getValue().stream().map(value -> String.valueOf(value))
                        .collect(Collectors.joining("|")));

            }
        }
    }

    /**
     * 保存
     *
     * @param key
     */
    public static void save(int key) {
        int dbKey = key % INIT_SIZE;
        Map<Integer, List<Integer>> database = databases.get(dbKey);
        if (database == null) {
            database = new HashMap<>();
            databases.put(dbKey, database);
        }
        int tbKey = (key / INIT_SIZE) % INIT_SIZE;
        List<Integer> table = database.get(tbKey);
        if (table == null) {
            table = new ArrayList<>();
            database.put(tbKey, table);
        }
        table.add(key);
    }

    /**
     * 扩容
     *
     * @param initSize
     * @param resize
     * @return
     */
    public static Map<Integer, Map<Integer, List<Integer>>> resize(int initSize, int resize) {
        Map<Integer, Map<Integer, List<Integer>>> resizeDatabases = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, List<Integer>>> database : databases.entrySet()) {
            for (Map.Entry<Integer, List<Integer>> table : database.getValue().entrySet()) {
                for (Integer key : table.getValue()) {
                    Integer dbKey = key % initSize;
                    Map<Integer, List<Integer>> resizeDatabase = resizeDatabases.get(dbKey);
                    if (resizeDatabase == null) {
                        resizeDatabase = new HashMap<>();
                        resizeDatabases.put(dbKey, resizeDatabase);
                    }
                    Integer tbKey = (key / initSize) % resize;
                    List<Integer> resizeTable = resizeDatabase.get(tbKey);
                    if (resizeTable == null) {
                        resizeTable = new ArrayList<>();
                        resizeDatabase.put(tbKey, resizeTable);
                    }
                    resizeTable.add(key);
                }
            }
        }
        return resizeDatabases;
    }
}
