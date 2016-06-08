package kr.co.homein.homeinproject.Estimate;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by seoeunbi on 2016. 6. 3..
 */
public class ImageItem implements Parcelable {
    public Uri uri;
    public String path;
    public ImageItem(){}


    public ImageItem(Parcel source){
        uri = source.readParcelable(Uri.class.getClassLoader());
        path = source.readString();
    }


    public static final Creator<ImageItem> CREATOR = new Creator<ImageItem>() {
        @Override
        public ImageItem createFromParcel(Parcel in) {
            return new ImageItem(in);
        }

        @Override
        public ImageItem[] newArray(int size) {
            return new ImageItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(uri, flags);
        dest.writeString(path);
    }
}
