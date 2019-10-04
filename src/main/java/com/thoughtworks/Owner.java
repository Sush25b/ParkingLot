package com.thoughtworks;

public class Owner {
    public static String getMessage;

    void getNotify(boolean capacityIsfull) {
        if (capacityIsfull) {
            getMessage = "parking lot is full";
        }
    }

    public String getMessage()
    {
        return getMessage;
    }

}
