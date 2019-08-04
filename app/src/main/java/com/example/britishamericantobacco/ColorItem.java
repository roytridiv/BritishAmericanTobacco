package com.example.britishamericantobacco;

public class ColorItem {

    private  String mColorName;
    private  int mFlagImage;

    public ColorItem(String colorName , int flagImage){

        mColorName = colorName;
        mFlagImage = flagImage;
    }

    public String getmColorName() {
        return mColorName;
    }

    public int getmFlagImage() {
        return mFlagImage;
    }
}
