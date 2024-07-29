package com.example.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main5 {
    public static void main(String[] args) {
        String s = "Hello World";

        System.out.println(s.matches(".+"));

        s = "12749030AC770";

        System.out.println(isHexadecimal(s));

        s = "123-4568";

        System.out.println(isPostCode(s));

        s = "ij92400038";

        System.out.println(isEmployeeNumber(s));

        s = "10.168.1.0";

        System.out.println(s.matches("^([1-9]?[0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]).([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]).([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]).([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$"));
        System.out.println(isIPAddress(s));

        String name = "Yusei Uchida";

        System.out.println(isName(name));

        String birthday = "1999-09-15";

        System.out.println(isBirthday(birthday));

        s = "uchida_yusei@ims-sol.co.jp";

        Pattern pattern = Pattern.compile("^(?<userPart>[^@]+)@(?<domainPart>.+$)");

        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            String captured0 = matcher.group(0);
            String captured1 = matcher.group("userPart");
            String captured2 = matcher.group(2);

            System.out.println("---------------");
            System.out.println(captured0);
            System.out.println(captured1);
            System.out.println(captured2);
            System.out.println("---------------");
        }
    }

    public static boolean isBirthday(String birthday) {
        return birthday.matches("^(?!0000)[0-9]{4}-(?:(?:(?:0[469]|11)-(?!00)(?:[012][0-9]|30))|(?:(?:0[13578]|10|12)-(?!00)(?:[012][0-9]|3[01]))|(?:02-(?!00)(?:[01][0-9]|2[0-8])))$");
    }

    public static boolean isName(String name) {
        return name.matches("^[A-Z][a-z]{1,} [A-Z][a-z]{1,}$");
    }

    public static boolean isIPAddress(String s) {
        return s.matches("^([1-9]?[0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.([1-9]?[0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}$");
    }

    public static boolean isEmployeeNumber(String s) {
        return s.matches("^[iI][jJ][0-9]{8}$");
    }

    public static boolean isBinaryNumber(String s) {
        return s.matches("[01]{1,}");
    }

    public static boolean isPostCode(String s) {
        return s.matches("^[0-9]{3}-[0-9]{4}$");
    }

    public static boolean isHexadecimal(String s) {
        return s.matches("^[0-9A-Fa-f]{1,}$");
    }

    public static boolean isMoney(String s) {
        return s.matches("^(?:([1-9]?[0-9])|([1-9][0-9]{1,2}))(,[0-9]{3})*$");
    }
}
