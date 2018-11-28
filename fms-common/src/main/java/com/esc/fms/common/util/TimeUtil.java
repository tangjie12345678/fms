package com.esc.fms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tangjie on 2017/1/3.
 */
public class TimeUtil {

    /**
     *
     * @param strTime
     * @return
     */
    public static Date timeStrToSimpleDate(String strTime) {
        //format :"yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date datetmp = null;
        try {
            datetmp = sf2.parse(strTime);
            datetmp.setTime(( (datetmp.getTime()/1000))*1000);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return datetmp;
    }

}
