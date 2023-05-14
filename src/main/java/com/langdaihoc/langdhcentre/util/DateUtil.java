package com.langdaihoc.langdhcentre.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.Map.Entry;

//import jp.careco.online.app.arch.util.Date.Jp;
//import jp.careco.online.app.arch.util.Date.YearEraConstant;
//import jp.careco.online.domain.exception.MfrSystemException;
import lombok.extern.slf4j.Slf4j;

/**
 * 日付処理の共通クラス
 * @author DLC
 *
 */
@Slf4j
public class DateUtil {

	private static String JP_DATE_FORMAT_TYPE1 = "JP_DATE_FORMAT_TYPE1";
	private static String JP_DATE_FORMAT_TYPE2 = "JP_DATE_FORMAT_TYPE2";

	private static Integer JP_YEAR_MEIJI = 10;
	private static Integer JP_YEAR_TAISHO = 20;
	private static Integer JP_YEAR_SHOWA = 30;
	private static Integer JP_YEAR_HEISEI = 40;
	private static Integer JP_YEAR_AD_2019 = 50;

	private static final String ERA_TYPE_ID = "AD";
	private static final String ERA_TYPE_NAME = "西暦";

	/**
	 * 現在日時を取得（Date型）
	 *
	 * @return nowDate
	 */
	public static Date now(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.setTimeZone(TimeZone.getDefault());
		simpleDateFormat.format(date);
		return date;
	}

	/**
	 * 現在日時を取得（long型）
	 *
	 * @return nowDate
	 */
	public static long longNow(){
		return now().getTime();
	}

	/**
	 * LocalDateTimeからDateに変換
	 *
	 * @param localDateTime localDateTime
	 * @return LocalDateTimeからDateに変換したDate型
	 */
	public static Date LocalDateTimeToDate(LocalDateTime localDateTime){
		return Date.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant());
	}

	/**
     * タイムスタンプから元号表記の年月日文字列を得る。
     * @param date
     * @param ignoreWithEra 別元号の併記を、定義ファイルの「WITH」によって設定されている場合でも行わない => true
     * @param format
     * @param eraFirstYearUseKanjiFlag
     * @return string
     *
     */


    /**
     * format date string
     * @param format
     * @return array
     */
    private static LinkedHashMap<String, Object> getDateFormatString(String format)
    {
    	LinkedHashMap<String, Object> formatDate = new LinkedHashMap<>();
        if (format.equals(JP_DATE_FORMAT_TYPE1)) {
			formatDate.put("year", "%s");
        	formatDate.put("month", "%02d");
        	formatDate.put("day", "%02d");
		} else if (format.equals(JP_DATE_FORMAT_TYPE2)) {
			formatDate.put("year", "%2s");
        	formatDate.put("month", "%2s");
        	formatDate.put("day", "%2s");
		} else {
			formatDate.put("year", "%s");
        	formatDate.put("month", "%02d");
        	formatDate.put("day", "%02d");
		}
        return formatDate;
    }

    // 併記する元号表記の文字列に付加する開始と終了
    private static String withOpen = "（";
    private static String withClose = "）";



    /**
     * ソートマップに変換
     * @param map
     * @return ソートされたマップ
     */
    @SuppressWarnings("unused")
	private static Map<String, Object> sortByKey(Map<String, Object> map)
    {
        // TreeMap to store values of HashMap
        TreeMap<String, Object> sorted = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sorted.putAll(map);

        return sorted;
    }

    /**
     * 文字は数字か確認
     * @param strNum
     * @return　true:数字
     */
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}