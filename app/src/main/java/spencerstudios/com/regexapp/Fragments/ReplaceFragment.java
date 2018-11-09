package spencerstudios.com.regexapp.Fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import spencerstudios.com.regexapp.R;

public class ReplaceFragment extends Fragment implements TextWatcher{

    private EditText etInput, etRegEx, etReplacement;
    private TextView tvResult;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_replace, container, false);

        etInput = v.findViewById(R.id.et_input_string);
        etRegEx = v.findViewById(R.id.et_input_regex);
        etReplacement = v.findViewById(R.id.et_input_replacement);
        tvResult = v.findViewById(R.id.tv_result);

        ImageView ivShare = v.findViewById(R.id.iv_share);
        ImageView ivCopy = v.findViewById(R.id.iv_copy);

        ivCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = tvResult.getText().toString();
                if (str.length() > 0) {
                    copyResult(str);
                }
            }
        });

        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = tvResult.getText().toString();
                if (str.length() > 0) {
                    shareResult(str);
                }
            }
        });

        etInput.addTextChangedListener(this);
        etRegEx.addTextChangedListener(this);
        etReplacement.addTextChangedListener(this);

        return v;
    }

    private void replace(String... strings) {
        if (strings[0].length() > 0 && strings[1].length() > 0 && strings[2].length() > 0) {
            try {
                String refactoredString = strings[0].replaceAll(strings[1], strings[2]);
                tvResult.setText(refactoredString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void shareResult(String shareBody) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "RegEx");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via..."));
    }

    void copyResult(String text) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("copied text", text);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getActivity(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        replace(etInput.getText().toString(), etRegEx.getText().toString(), etReplacement.getText().toString());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}
