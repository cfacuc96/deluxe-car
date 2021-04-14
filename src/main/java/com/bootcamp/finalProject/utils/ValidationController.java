package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.DiscountRateDTO;
import com.bootcamp.finalProject.exceptions.DateEnteredGreaterException;
import com.bootcamp.finalProject.exceptions.IncorrectParamsGivenException;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.exceptions.InvalidDateException;
import com.bootcamp.finalProject.mnemonics.DeliveryStatus;
import com.bootcamp.finalProject.mnemonics.ExceptionMessage;
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

        if (params.get("order") != null && !params.get("order").equals("")) {
            try {
                Integer.parseInt(params.get("order"));

            } catch (Exception e) {
                throw new IncorrectParamsGivenException(ExceptionMessage.NOT_A_NUMBER);
            }
        }

        if (params.isEmpty())
            throw new IncorrectParamsGivenException(ExceptionMessage.EMPTY_PARAMS);

        if (params.get("date") == null && params.get("queryType") == null)
            throw new IncorrectParamsGivenException("date and query type must not be null");

        if (params.get("queryType") != null) {
            params.put("queryType", params.get("queryType").replaceAll(" ", ""));
        }

        if (params.get("queryType") == null && params.get("date") != null)
            throw new IncorrectParamsGivenException(ExceptionMessage.QUERY_TYPE_IS_NECESSARY);

        if (params.get("queryType") != null && (params.get("queryType").equals(QueryType.PARTIAL)
                || params.get("queryType").equals(QueryType.VARIATION))
                && (params.get("date") == null || params.get("date").equals("")))
            throw new IncorrectParamsGivenException(ExceptionMessage.DATE_IS_NECESSARY);
    }

    /**
     * checks if params received in order endpoint are correct
     * not null, contains dealerNumber, [deliveryStatus is correct], [order is correct]
     *
     * @param params
     * @throws IncorrectParamsGivenException
     */
    public static void isOrdersEndpointMapValid(Map<String, String> params) throws IncorrectParamsGivenException {

        if (params.get("order") != null && !params.get("order").equals("")) {
            try {
                Integer.parseInt(params.get("order"));
            } catch (Exception e) {
                throw new IncorrectParamsGivenException(ExceptionMessage.NOT_A_NUMBER);
            }
        }

        if (params.isEmpty())
            throw new IncorrectParamsGivenException(ExceptionMessage.EMPTY_PARAMS);

        if (!params.containsKey("dealerNumber"))
            throw new IncorrectParamsGivenException(ExceptionMessage.MISSING_DEALER_NUMBER);

        if (params.containsKey("deliveryStatus")) {
            if (!DeliveryStatus.DELIVERY_STATUS_VALUES.contains(params.get("deliveryStatus"))) {
                throw new IncorrectParamsGivenException(ExceptionMessage.NOT_CONTAINS_DELIVERY_STATUS + DeliveryStatus.DELIVERY_STATUS_VALUES);
            }
        }
    }

    public static void validateOrderStatus(String orderStatus) throws IncorrectParamsGivenException {

        if (!DeliveryStatus.DELIVERY_STATUS_VALUES.contains(orderStatus)) {
            throw new IncorrectParamsGivenException(ExceptionMessage.WRONG_STATUS_ORDER);
        }
    }

    public static void validateSubsidiaryStockParams(Map<String, String> params) throws InternalExceptionHandler {
        if (params.isEmpty())
            throw new IncorrectParamsGivenException(ExceptionMessage.EMPTY_PARAMS);

        if (params.get("dealerNumber") == null || params.get("dealerNumber").equals("")) {
            throw new IncorrectParamsGivenException(ExceptionMessage.MISSING_DEALER_NUMBER);
        }
        try {
            Long.valueOf(params.get("dealerNumber"));
        } catch (Exception e) {
            throw new IncorrectParamsGivenException(ExceptionMessage.NOT_A_NUMBER);
        }
    }

    /**
     * verification of the information received for the creation of a DiscountRate in the database
     * Id being null and the description and the discount are not null
     *
     * @param discountRateDTO
     * @throws IncorrectParamsGivenException
     */
    public static void validateDiscountRateDTOParams(DiscountRateDTO discountRateDTO) throws IncorrectParamsGivenException {

        if (discountRateDTO.getIdDiscountRate() != null)
            throw new IncorrectParamsGivenException("The ID is not required");

        if (discountRateDTO.getDescription().equals(null) || discountRateDTO.getDescription().equals("")
                || discountRateDTO.getDiscount().equals(null) || discountRateDTO.getDiscount().equals(""))
            throw new IncorrectParamsGivenException("The information required for a discountRate is incomplete");
    }

    public static Long validateProviderId(String id) throws IncorrectParamsGivenException {
        if (id != null) {
            try {
                long idParam = Long.parseLong(id);
                if (idParam > 0)
                    return idParam;
                else
                    throw new IncorrectParamsGivenException(ExceptionMessage.LOWER_THAN_ZERO);

            } catch (Exception e) {
                throw new IncorrectParamsGivenException(ExceptionMessage.NOT_A_NUMBER);
            }
        }
        throw new IncorrectParamsGivenException(ExceptionMessage.ID_NOT_FOUND);
    }
}
