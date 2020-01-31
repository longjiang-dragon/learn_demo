package com.hujiang.mytest.fragment.aidlFragment;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * @author yuefeng
 * @version 3.3.1
 * @date 16/1/9
 */
public class Book implements Parcelable {
    private int mBookId;
    private String mBookName;

    public Book(int pBookId, String pBookName) {
        mBookId = pBookId;
        mBookName = pBookName;
    }

    protected Book(Parcel in) {
        mBookId = in.readInt();
        mBookName = in.readString();
    }


    public String getBookName() {
        return mBookName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return mBookId == book.mBookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mBookId);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mBookId);
        dest.writeString(mBookName);
    }

    @Override
    public String toString() {
        return "mBookId===" + mBookId + "   mBookName==" + mBookName;
    }
}
