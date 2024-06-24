package com.tourism.util.other;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ERole {
    CUSTOMER, EMPLOYEE, OWNER, ADMINISTRATOR;

    public static List<String> getListERole(){
        return Arrays.stream(ERole.values()).map(Enum::name).collect(Collectors.toList());
    }
}
