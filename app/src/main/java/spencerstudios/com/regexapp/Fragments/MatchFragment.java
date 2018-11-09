package spencerstudios.com.regexapp.Fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spencerstudios.com.regexapp.R;


public class MatchFragment extends Fragment implements TextWatcher {

    EditText etInput, etRegEx;
    TextView tvResult, tvQtyOfMatches;
    int spannable_background_color_one = Color.parseColor("#a5d6a7");
    int spannable_background_color_two = Color.parseColor("#81c784");

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_match, container, false);
        etInput = v.findViewById(R.id.et_input_string);
        etRegEx = v.findViewById(R.id.et_input_regex);
        tvResult = v.findViewById(R.id.tv_result);
        tvQtyOfMatches = v.findViewById(R.id.tv_occurrences);
        etInput.addTextChangedListener(this);
        etRegEx.addTextChangedListener(this);
        return v;
    }

    void match(String string, String regex) {

        if (string.length() > 0 && regex.length() > 0) {
            int occurrences = 0, swap = 0;
            try {
                Spannable span = new SpannableString(string);
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(string);
                while (matcher.find()) {
                    occurrences++;
                    swap++;
                    span.setSpan(new BackgroundColorSpan(swap % 2 == 0 ? spannable_background_color_one : spannable_background_color_two), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    span.setSpan(new StyleSpan(Typeface.BOLD), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                String match = occurrences == 1 ? "match" : "matches";
                tvQtyOfMatches.setText(String.format(Locale.getDefault(), "%d %s", occurrences, match));
                tvResult.setText(span);
            } catch (Exception e) {
                tvResult.setText("");
                tvQtyOfMatches.setText("");
            }
        } else {
            tvResult.setText("");
            tvQtyOfMatches.setText("");
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        match(etInput.getText().toString(), etRegEx.getText().toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}
