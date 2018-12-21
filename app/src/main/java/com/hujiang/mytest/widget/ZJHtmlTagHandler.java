package com.hujiang.mytest.widget;

import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import org.xml.sax.XMLReader;

/**
 * @author yuefeng
 * @version 1.2.0
 * @date 15/10/10
 */
public class ZJHtmlTagHandler implements Html.TagHandler {
    private int sIndex = 0;
    private int eIndex = 0;
    private String[] mTags;
    private View.OnClickListener mClickListener;

    public ZJHtmlTagHandler(View.OnClickListener pClickListener, String... pTags) {
        this.mTags = pTags;
        this.mClickListener = pClickListener;
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (null == mTags || mTags.length == 0) {
            return;
        }
        if (isContains(tag)) {
            if (opening) {
                sIndex = output.length();
            } else {
                eIndex = output.length();
                output.setSpan(new ZJClickableSpan(tag), sIndex, eIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private boolean isContains(String pTag) {
        for (String _tag : mTags) {
            if (_tag.equals(pTag)) {
                return true;
            }
        }
        return false;
    }


    private class ZJClickableSpan extends ClickableSpan {
        private String mTag;

        public ZJClickableSpan(String pTag) {
            mTag = pTag;
        }

        @Override
        public void onClick(View widget) {
            if (mClickListener != null) {
                widget.setTag(mTag);
                mClickListener.onClick(widget);
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }
}
