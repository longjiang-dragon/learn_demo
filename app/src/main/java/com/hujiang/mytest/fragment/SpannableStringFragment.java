package com.hujiang.mytest.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hujiang.mytest.fragment.aidlFragment.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 16/3/24
 */
public class SpannableStringFragment extends Fragment {
    @Bind(R.id.tv_content)
    public TextView mTextViewContext;
    @Bind(R.id.tv_content_two)
    public TextView mTextViewContextTwo;
    private static final String TEST_STR = "字体测试字体大小一半两倍前景色背景色正常粗体斜体粗斜体下划线删除线x1x2电话邮件网站短信彩信地图X轴综合点击事件";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.spannable_string_layout, container, false);
        ButterKnife.bind(this, _view);
        return _view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SpannableString _spannableString = new SpannableString(TEST_STR);
        setSpanStyle(_spannableString);
        mTextViewContext.setText(_spannableString);
        mTextViewContext.setMovementMethod(LinkMovementMethod.getInstance());
        testText();
    }

    /**
     * 从Html.java中可以看出，a标签一定会转化成URLSpan。且我们无法重写他的方法。。但他有提供一个clearSpans方法
     */
    private void testText() {
        String tempStr = "<h1>Header</h1><h2>Subheader</h2><a href=\"http://www.baidu.com\">baidu</a> 呵呵哒 <a href=\"http://www.163.com\">wangyi</a> <a href=\"http://www.baidu.com\">baidu</a> <p>Some <em>text</em></p><img src='http://blogs.babble.com/famecrawler/files/2010/11/mickey_mouse-1097.jpg' width=70 height=100 />";
        mTextViewContextTwo.setText(Html.fromHtml(tempStr));
        mTextViewContextTwo.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence charSequence = mTextViewContextTwo.getText();
        if (charSequence instanceof Spannable) {
            int end = charSequence.length();
            Spannable sp = (Spannable) mTextViewContextTwo.getText();
            // 得到其中所有的关键字的数组
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            // 其中关键字的样式
            SpannableStringBuilder style = new SpannableStringBuilder(charSequence);
            // 清楚掉所有的关键字标志
            style.clearSpans();

            for (URLSpan url : urls) {
                // 将关键字数组中的文字添加到新生成的style中去
                MyURLSpan myClickableSpan = new MyURLSpan(url.getURL());
                style.setSpan(myClickableSpan, sp.getSpanStart(url),
                        sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            // 将新生成的放置到TextView上
            mTextViewContextTwo.setText(style);
        }


    }


    private static class MyURLSpan extends URLSpan {

        public MyURLSpan(String url) {
            super(url);
        }

        @Override
        public void onClick(View widget) {
            System.out.println(getURL());
        }
    }


    private void setSpanStyle(SpannableString pSpannableString) {
        pSpannableString.setSpan(new TypefaceSpan("monospace"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pSpannableString.setSpan(new TypefaceSpan("serif"), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置字体大小(像素)
        pSpannableString.setSpan(new AbsoluteSizeSpan(20), 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //单位 dip
        pSpannableString.setSpan(new AbsoluteSizeSpan(20, true), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        pSpannableString.setSpan(new RelativeSizeSpan(0.5f), 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pSpannableString.setSpan(new RelativeSizeSpan(2.0f), 10, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //前景色
        pSpannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 12, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pSpannableString.setSpan(new BackgroundColorSpan(Color.CYAN), 15, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //字体设置 正常
        pSpannableString.setSpan(new StyleSpan(Typeface.NORMAL), 18, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //粗体
        pSpannableString.setSpan(new StyleSpan(Typeface.BOLD), 20, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //斜体
        pSpannableString.setSpan(new StyleSpan(Typeface.ITALIC), 22, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //粗斜体
        pSpannableString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 24, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        pSpannableString.setSpan(new UnderlineSpan(), 27, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pSpannableString.setSpan(new StrikethroughSpan(), 30, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //上下标
        pSpannableString.setSpan(new SubscriptSpan(), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pSpannableString.setSpan(new SuperscriptSpan(), 35, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置电话
        pSpannableString.setSpan(new URLSpan("tel:99999"), 37, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //是不是可以通过scheme来作跳转呢
        pSpannableString.setSpan(new URLSpan("mailto:webmaster@google.com"), 39, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pSpannableString.setSpan(new URLSpan("http://www.baidu.com"), 41, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pSpannableString.setSpan(new URLSpan("sms:110"), 43, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pSpannableString.setSpan(new URLSpan("mms:112"), 45, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pSpannableString.setSpan(new URLSpan("geo:38.899533,-77.036476"), 47, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //字体x轴放大两倍
        pSpannableString.setSpan(new ScaleXSpan(2.0f), 49, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pSpannableString.setSpan(new BulletSpan(android.text.style.BulletSpan.STANDARD_GAP_WIDTH, Color.GREEN), 0, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //点击事件
        pSpannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getActivity(), "ClickableSpan", Toast.LENGTH_LONG).show();
            }
        }, 53, 57, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
