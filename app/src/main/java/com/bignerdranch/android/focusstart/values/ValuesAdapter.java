package com.bignerdranch.android.focusstart.values;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.focusstart.R;
import com.bignerdranch.android.focusstart.converter_pack.ConverterFragment;
import com.bignerdranch.android.focusstart.data.Currency;

import java.util.List;

public class ValuesAdapter extends RecyclerView.Adapter<ValuesAdapter.ValuesHolder> {

    private List<Currency> mCurrencies;

    @NonNull
    @Override
    public ValuesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency_holder,
                parent, false);

        return new ValuesHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ValuesHolder holder, int position) {
        Currency currency = mCurrencies.get(position);
        holder.bind(currency);
    }

    @Override
    public int getItemCount() {
        return mCurrencies.size();
    }

    public void updateUI(List<Currency> mCurrencies){
        this.mCurrencies = mCurrencies;
        notifyDataSetChanged();
    }

    public class ValuesHolder extends RecyclerView.ViewHolder {

        private TextView mCurrencyName;
        private TextView mCurrencyCharCode;
        private TextView getmCurrencyNominal;
        private TextView getmCurrencyValue;
        private View mHolderView;

        public ValuesHolder(@NonNull View itemView) {
            super(itemView);
            mCurrencyName = itemView.findViewById(R.id.valute_name);
            mCurrencyCharCode = itemView.findViewById(R.id.valute_char_code);
            getmCurrencyNominal = itemView.findViewById(R.id.nominal);
            getmCurrencyValue = itemView.findViewById(R.id.value);
            mHolderView = itemView.findViewById(R.id.holder_container);
        }

        public void bind(Currency currency) {
            mCurrencyName.setText(currency.getName());
            mCurrencyCharCode.setText(currency.getCharCode());
            getmCurrencyNominal.setText(currency.getNominal().toString());
            getmCurrencyValue.setText(currency.getValue().toString());
            mHolderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = ConverterFragment.newInstance(currency.getName(), currency.getValue(), currency.getNominal());
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, myFragment).addToBackStack(null).commit();
                }
            });
        }

    }


}
