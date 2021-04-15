package com.bootcamp.finalProject.mnemonics;

import java.util.Arrays;
import java.util.List;

public class QueryType {

    /**
     * Finds all parts
     */
    public static final String COMPLETE = "C";
    /**
     * Finds all parts that have been modified since the date
     */
    public static final String PARTIAL = "P";
    /**
     * Finds all the parts that the price was modified from the date
     */
    public static final String VARIATION = "V";

    /**
     * List of all values inside this mnemonic
     */
    public static final List<String> LIST_OF_VALUES = Arrays.asList(COMPLETE, PARTIAL, VARIATION);

}
