package com.reactnativenavigation.parse;

import android.graphics.Typeface;
import android.support.annotation.Nullable;

import com.reactnativenavigation.parse.params.Color;
import com.reactnativenavigation.parse.params.Fraction;
import com.reactnativenavigation.parse.params.NullColor;
import com.reactnativenavigation.parse.params.NullFraction;
import com.reactnativenavigation.parse.params.NullText;
import com.reactnativenavigation.parse.params.Text;
import com.reactnativenavigation.parse.parsers.ColorParser;
import com.reactnativenavigation.parse.parsers.FractionParser;
import com.reactnativenavigation.parse.parsers.TextParser;
import com.reactnativenavigation.utils.TypefaceLoader;

import org.json.JSONObject;

public class TitleOptions {

    public static TitleOptions parse(TypefaceLoader typefaceManager, JSONObject json) {
        final TitleOptions options = new TitleOptions();
        if (json == null) {
            return options;
        }

        options.text = TextParser.parse(json, "text");
        options.color = ColorParser.parse(json, "color");
        options.fontSize = FractionParser.parse(json, "fontSize");
        options.fontFamily = typefaceManager.getTypeFace(json.optString("fontFamily", ""));
        options.component = TextParser.parse(json, "component");
        options.alignment = TextParser.parse(json, "alignment");

        return options;
    }

    public Text text = new NullText();
    public Color color = new NullColor();
    public Fraction fontSize = new NullFraction();
    @Nullable public Typeface fontFamily;
    public Text component = new NullText();
    public Text alignment = new NullText();

    void mergeWith(final TitleOptions other) {
        if (other.text.hasValue()) text = other.text;
        if (other.color.hasValue()) color = other.color;
        if (other.fontSize.hasValue()) fontSize = other.fontSize;
        if (other.fontFamily != null) fontFamily = other.fontFamily;
        if (other.component.hasValue()) component = other.component;
        if (other.alignment.hasValue()) alignment = other.alignment;
    }

    void mergeWithDefault(TitleOptions defaultOptions) {
        if (!text.hasValue()) text = defaultOptions.text;
        if (!color.hasValue()) color = defaultOptions.color;
        if (!fontSize.hasValue()) fontSize = defaultOptions.fontSize;
        if (fontFamily == null) fontFamily = defaultOptions.fontFamily;
        if (!component.hasValue()) component = defaultOptions.component;
        if (alignment.hasValue()) alignment = defaultOptions.alignment;
    }
}