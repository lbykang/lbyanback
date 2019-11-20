package com.example.lbyanBack.util;

import java.util.UUID;

public final class SystemUtil {

    private SystemUtil() {
    }

    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        return id.replace("-", "");
    }

    /**
     * 随机生产 factor 位的数字，最大不超过 19位，因为long的最大值为19位
     * @param factor
     * @return
     */
    public static String randomNum(int factor) {
        long longs = new Double((Math.random() + 1) * Math.pow(10, factor - 1)).longValue();
        String account = String.valueOf(longs);
        return account;
    }
}
