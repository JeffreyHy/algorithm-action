package com.huang.loadbalance;

import java.io.Serializable;

/**
 * Created by JeffreyHy on 2017/10/30.
 */
public class WeightDTO implements Serializable {
    private int weight;
    private int minR;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMinR() {
        return minR;
    }

    public void setMinR(int minR) {
        this.minR = minR;
    }

    public int getMaxr() {
        return maxr;
    }

    public void setMaxr(int maxr) {
        this.maxr = maxr;
    }

    private int maxr;
}
