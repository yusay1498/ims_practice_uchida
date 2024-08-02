package org.example;

import java.util.Objects;

public class MeiboClass {
    private int number;
    private String name;
    private String mail;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MeiboClass that = (MeiboClass) o;
//        return number == that.number && Objects.equals(name, that.name) && Objects.equals(mail, that.mail);
//    }
//


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeiboClass that = (MeiboClass) o;
        return number == that.number && name.equals(that.name) && mail.equals(that.mail);
    }

    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(number, name, mail);
    //    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + name.hashCode();
        result = 31 * result + mail.hashCode();
        return result;
    }
}
