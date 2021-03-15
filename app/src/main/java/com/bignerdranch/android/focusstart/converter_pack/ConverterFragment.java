package com.bignerdranch.android.focusstart.converter_pack;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.focusstart.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.UUID;

public class ConverterFragment extends Fragment {

    private static final String ARG_Name= "Name";
    private static final String ARG_Value= "Value";
    private static final String ARG_Nominal= "Nominal";

    private LinearLayout mConverterContainer;
    private TextView mValuteName;
    private TextView mValuteSumm;
    private EditText mRubCount;
    private String valname;
    private Double value;
    private int nominal;

    public static ConverterFragment newInstance(String valname, Double value, Integer nominal) {
        Bundle args = new Bundle();
        args.putString(ARG_Name, valname);
        args.putDouble(ARG_Value, value);
        args.putInt(ARG_Nominal, nominal);
        ConverterFragment fragment = new ConverterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        valname = getArguments().getString(ARG_Name);
        value = getArguments().getDouble(ARG_Value);
        nominal = getArguments().getInt(ARG_Nominal);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.converter_linear, container,
                false);
        mConverterContainer = view
                .findViewById(R.id.converter_container);
        mValuteName = view
                .findViewById(R.id.valute_code);
        mValuteSumm = view
                .findViewById(R.id.valute_summ);
        mRubCount = view
                .findViewById(R.id.rub_count);
        mRubCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                double qty = 0;
                if (!s.toString().isEmpty()){
                    qty = Double.parseDouble(s.toString());
                }
                double nominaldb = nominal;
                double allUnits = qty/(value/nominaldb);
                DecimalFormat df = new DecimalFormat("#.####");
                df.setRoundingMode(RoundingMode.CEILING);
                mValuteSumm.setText(df.format(allUnits));
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mValuteName.setText(valname);
        return view;
    }
}
