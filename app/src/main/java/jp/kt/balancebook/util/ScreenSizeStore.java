package jp.kt.balancebook.util;

public class ScreenSizeStore {

    private static int mLayoutWidth;
    private static int mLayoutHeight;

    public static int getLayoutWidth() {
        return mLayoutWidth;
    }

    public static int getLayoutHeight() {
        return mLayoutHeight;
    }

    public static void setLayoutWidth(int layoutWidth) {
        mLayoutWidth = layoutWidth;
    }

    public static void setLayoutHeight(int layoutHeight) {
        mLayoutHeight = layoutHeight;
    }
}
