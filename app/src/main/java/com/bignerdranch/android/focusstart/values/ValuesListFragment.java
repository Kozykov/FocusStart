package com.bignerdranch.android.focusstart.values;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.focusstart.R;
import com.bignerdranch.android.focusstart.allvalues.GeneralInfo;
import com.bignerdranch.android.focusstart.data.Currency;
import com.bignerdranch.android.focusstart.retrofit.RetrofitClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValuesListFragment extends Fragment {

    private List<Currency> mCurrencies = new ArrayList<>();

    ValuesAdapter valuesAdapter = new ValuesAdapter();

    RetrofitClass request = new RetrofitClass();

    private RecyclerView mValueRecyclerView;

    private Timer mTimer;
    private MyTimerTask mMyTimerTask;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.valute_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_value:
                serverRequest();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        serverRequest();
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.recycler, container,
                false);
            mValueRecyclerView = (RecyclerView) view
                    .findViewById(R.id.values_recycler_view);
        mValueRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));
        mValueRecyclerView.setAdapter(valuesAdapter);
        valuesAdapter.updateUI(mCurrencies);
        myTimer();
        return view;
    }

    public void serverRequest(){
        request.getApiService().loadRepo().enqueue(new Callback<GeneralInfo>() {
            @Override
            public void onResponse(Call<GeneralInfo> call, Response<GeneralInfo> response) {
                if (response.isSuccessful()) {
                    mCurrencies.add(response.body().getValute().getAMD());
                    mCurrencies.add(response.body().getValute().getAUD());
                    mCurrencies.add(response.body().getValute().getAZN());
                    mCurrencies.add(response.body().getValute().getBGN());
                    mCurrencies.add(response.body().getValute().getBRL());
                    mCurrencies.add(response.body().getValute().getBYN());
                    mCurrencies.add(response.body().getValute().getCAD());
                    mCurrencies.add(response.body().getValute().getCHF());
                    mCurrencies.add(response.body().getValute().getCNY());
                    mCurrencies.add(response.body().getValute().getCZK());
                    mCurrencies.add(response.body().getValute().getDKK());
                    mCurrencies.add(response.body().getValute().getEUR());
                    mCurrencies.add(response.body().getValute().getGBP());
                    mCurrencies.add(response.body().getValute().getHKD());
                    mCurrencies.add(response.body().getValute().getHUF());
                    mCurrencies.add(response.body().getValute().getINR());
                    mCurrencies.add(response.body().getValute().getJPY());
                    mCurrencies.add(response.body().getValute().getKGS());
                    mCurrencies.add(response.body().getValute().getKRW());
                    mCurrencies.add(response.body().getValute().getKZT());
                    mCurrencies.add(response.body().getValute().getMDL());
                    mCurrencies.add(response.body().getValute().getNOK());
                    mCurrencies.add(response.body().getValute().getPLN());
                    mCurrencies.add(response.body().getValute().getRON());
                    mCurrencies.add(response.body().getValute().getSEK());
                    mCurrencies.add(response.body().getValute().getSGD());
                    mCurrencies.add(response.body().getValute().getTJS());
                    mCurrencies.add(response.body().getValute().getTMT());
                    mCurrencies.add(response.body().getValute().getTRY());
                    mCurrencies.add(response.body().getValute().getUAH());
                    mCurrencies.add(response.body().getValute().getUSD());
                    mCurrencies.add(response.body().getValute().getUZS());
                    mCurrencies.add(response.body().getValute().getXDR());
                    mCurrencies.add(response.body().getValute().getZAR());
                }
                valuesAdapter.updateUI(mCurrencies);
            }

            @Override
            public void onFailure(Call<GeneralInfo> call, Throwable t) {

            }
        });
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    serverRequest();
                }
            });
        }
    }

    public void myTimer(){
        if (mTimer != null) {
            mTimer.cancel();
        }
        mTimer = new Timer();
        mMyTimerTask = new MyTimerTask();

        mTimer.schedule(mMyTimerTask, 1000, 50000);
        }
}
