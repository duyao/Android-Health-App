package example.dy.com.homework.myUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dy on 2016/4/19.
 */
public class StringUtils {
    //        public static final  String IPString  = "172.16.153.14";
//    public static final String IPString = "172.16.120.97";
//    public static final String IPString = "1u4d986139.iask.in:17471";
    public static final String IPString = "1u4d986139.iask.in:24792";


    public static String getPasswordEncryption(String inputStr) {
//        System.out.println("=======加密前的数据:" + inputStr);
        BigInteger bigInteger = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] inputData = inputStr.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("MD5加密后:" + bigInteger.toString(16));
        return bigInteger.toString(16);
    }

    public static String getCurTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String time = sdf.format(new Date());
//        System.out.println(time);
        return time;
    }
}
