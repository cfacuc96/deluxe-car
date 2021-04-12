package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.exceptions.DateEnteredGreaterException;
import com.bootcamp.finalProject.exceptions.IncorrectParamsGivenException;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.exceptions.InvalidDateException;
import com.bootcamp.finalProject.mnemonics.DeliveryStatus;
import com.bootcamp.finalProject.mnemonics.QueryType;
import org.assertj.core.util.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidationController {


    private static final String FIXED_DATE_PATTERN = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";

    public static Date validateDateFormat(String date) throws InternalExceptionHandler {
        if (!Pattern.matches(FIXED_DATE_PATTERN, date)) {
            throw new InvalidDateException();
        }

        Date actualDate = new Date();

        if (!actualDate.after(strToDate(date)))
            throw new DateEnteredGreaterException();

        return strToDate(date);
    }

    public static Date strToDate(String sDate) {
        Date newDate = null;
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            newDate = sdf.parse(sDate);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return newDate;
    }

    /**
     * checks if params received in list endpoint are correct
     * It concentrates on verifying whether or not the date parameter
     * was included or not for COMPLETE type queries
     *
     * @param params a map with the params given by a GetRequest
     * @throws IncorrectParamsGivenException the exception throw if the params are incorrect
     */
    public static void isListEndpointMapValid(Map<String, String> params) throws InternalExceptionHandler {

        if (params.isEmpty())
            throw new IncorrectParamsGivenException("Empty Parameters");

        if (params.get("date") != null &&
                params.get("queryType") != null &&
                params.get("queryType").equals(QueryType.COMPLETE))
            throw new IncorrectParamsGivenException("for a complete search the date is not necessary");

        if (params.get("date") != null && !params.containsKey("queryType"))
            throw new IncorrectParamsGivenException("for a date it is necessary to " +
                    "specify if the query will be of partial type or of variation type");
    }

    /**
     * checks if params received in order endpoint are correct
     * not null, contains dealerNumber, [deliveryStatus is correct], [order is correct]
     *
     * @param params
     * @throws IncorrectParamsGivenException
     */
    public static void isOrdersEndpointMapValid(Map<String, String> params) throws IncorrectParamsGivenException {

        if (params.isEmpty())
            throw new IncorrectParamsGivenException("Empty Parameters");

        if (!params.containsKey("dealerNumber"))
            throw new IncorrectParamsGivenException("dealerNumber is missing from request");

        if (params.containsKey("deliveryStatus")) {
            if (!DeliveryStatus.DELIVERY_STATUS_VALUES.contains(params.get("deliveryStatus"))) {
                throw new IncorrectParamsGivenException("deliveryStatus not in " + DeliveryStatus.DELIVERY_STATUS_VALUES);
            }
        }
    }
}
