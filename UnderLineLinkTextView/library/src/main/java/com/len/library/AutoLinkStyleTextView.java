package com.len.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Shaolei on 2016/10/10.
 */
public class AutoLinkStyleTextView extends TextView {

    private final static int DEFAULT_COLOR = Color.parseColor("#f23218");
    private String LINK_TEXT_VALUE = null;
    private int LINK_TEXT_COLOR;
    private int LINK_TEXT_BG_COLOR;
    private int LINK_TEXT_CLICK_BG_COLOR;
    private boolean LINK_TEXT_CLICK_BG_COLOR_AUTO_INVALIDATE;
    private boolean HAS_UNDER_LINE = true;

    private ClickCallBack mClickCallBack;

    public AutoLinkStyleTextView(Context context) {
        this(context, null);
    }

    public AutoLinkStyleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLinkStyleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoLinkStyleTextView, defStyleAttr, 0);
        LINK_TEXT_VALUE = typedArray.getString(R.styleable.AutoLinkStyleTextView_link_text_value);
        LINK_TEXT_COLOR = typedArray.getColor(R.styleable.AutoLinkStyleTextView_link_text_color, DEFAULT_COLOR);
        LINK_TEXT_BG_COLOR = typedArray.getColor(R.styleable.AutoLinkStyleTextView_link_text_bg_color, Color.TRANSPARENT);
        LINK_TEXT_CLICK_BG_COLOR = typedArray.getColor(R.styleable.AutoLinkStyleTextView_link_text_click_bg_color, Color.TRANSPARENT);
        LINK_TEXT_CLICK_BG_COLOR_AUTO_INVALIDATE = typedArray.getBoolean(R.styleable.AutoLinkStyleTextView_link_text_click_bg_auto_invalidate, true);
        HAS_UNDER_LINE = typedArray.getBoolean(R.styleable.AutoLinkStyleTextView_link_has_under_line, HAS_UNDER_LINE);
        addStyle();
    }

    private void addStyle() {
        if (!TextUtils.isEmpty(LINK_TEXT_VALUE)) {
            final String[] values = LINK_TEXT_VALUE.split(",");
            SpannableString spannableString = new SpannableString(getText().toString().trim());
            for (int i = 0; i < values.length; i++) {
                final int position = i;
                spannableString.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        if (mClickCallBack != null) mClickCallBack.onClick(position, values[position]);
                        if (LINK_TEXT_CLICK_BG_COLOR_AUTO_INVALIDATE) widget.invalidate();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.bgColor = LINK_TEXT_BG_COLOR;
                        setHighlightColor(LINK_TEXT_CLICK_BG_COLOR);
                        ds.setColor(LINK_TEXT_COLOR);
                        ds.setUnderlineText(HAS_UNDER_LINE);
                    }
                }, getText().toString().trim().indexOf(values[i]), getText().toString().trim().indexOf(values[i]) + values[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            setText(spannableString);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
    }


    public void setOnClickCallBack(ClickCallBack clickCallBack) {
        this.mClickCallBack = clickCallBack;
    }

    public interface ClickCallBack {
        void onClick(int position, String clickText);
    }
}
