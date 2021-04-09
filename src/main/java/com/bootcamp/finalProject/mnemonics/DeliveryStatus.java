package com.bootcamp.finalProject.mnemonics;

import java.util.Arrays;
import java.util.List;

public class DeliveryStatus {

    public static final String PENDING = "P";
    public static final String DELAYED = "D";
    public static final String FINISHED = "F";
    public static final String CANCELED = "C";
    public static final List<String> DELIVERY_STATUS_VALUES = Arrays.asList(PENDING, DELAYED, FINISHED, CANCELED);

}
