package com.example.todoapplication.utility;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* ------------------------------------------------------------- *
 * Utility Class which is initialize during the app created
 * ------------------------------------------------------------- */
public class Utility {

    /* ------------------------------------------------------------- *
     * For Hiding the Keyboard after typing text
     * ------------------------------------------------------------- */
    public static void hideSoftKeyBoard(View view, Context context) {
        if (view != null && context != null) {
            InputMethodManager imm = (InputMethodManager)
                    context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null && imm.isActive())
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /* ------------------------------------------------------------- *
     * For Making your first charter capital in the Title word
     * ------------------------------------------------------------- */
    public static String capitalCharAt(String value, int index) {
        return value.substring(0, index) + Character.toTitleCase(value.charAt(index))
                + value.substring(index + 1);
    }
}
