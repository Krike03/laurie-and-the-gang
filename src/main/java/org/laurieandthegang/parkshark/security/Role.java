package org.laurieandthegang.parkshark.security;

import com.google.common.collect.Lists;

import java.util.List;

import static org.laurieandthegang.parkshark.security.Feature.*;

public enum Role {
    MANAGER("manager", ADD_PARKING_LOT, ADD_DIVISION, GET_DIVISIONS, GET_MEMBERS, GET_PARKING_LOTS),
    MEMBER("member");

    private final String label;
    private final List<Feature> featureList;

    Role(String label, Feature... featureList) {
        this.label = label;
        this.featureList = Lists.newArrayList(featureList);
    }

    public List<Feature> getFeatures() {
        return featureList;
    }

    public String getLabel() {
        return label;
    }
}
