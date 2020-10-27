package com.trulyfuture.dormaruniversityside.screens.liveSessions;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trulyfuture.dormaruniversityside.R;
import com.trulyfuture.dormaruniversityside.databinding.FragmentLiveSessionsBinding;
import com.trulyfuture.dormaruniversityside.screens.login.LoginViewModel;
import com.trulyfuture.dormaruniversityside.utils.ProgressDialog;
import com.trulyfuture.dormaruniversityside.utils.SharedPreferenceClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class LiveSessionsFragment extends Fragment {
    private FragmentLiveSessionsBinding binding;
    private LiveSessionsViewModel viewModel;
    private int agentId;
    private Calendar mCalendar;
    private SimpleDateFormat mSimpleDateFormat;
    private SimpleDateFormat mSimpleTimeFormat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveSessionsBinding.inflate(getLayoutInflater());
        SharedPreferenceClass sharedPreferenceClass = new SharedPreferenceClass(getContext(), SharedPreferenceClass.UserDetails);
        agentId = sharedPreferenceClass.getInteger("userId");

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LiveSessionsViewModel.class);

        setupViews();
    }


    private void setupViews() {

        mSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        mSimpleTimeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());

        binding.sessionDate.setInputType(InputType.TYPE_NULL);
        binding.sessionDate.setOnClickListener(v -> {
            mCalendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, dayOfMonth) -> {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                binding.sessionDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
            };

            new DatePickerDialog(getContext(), dateSetListener,
                    mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH))
                    .show();
        });

        binding.sessionTime.setInputType(InputType.TYPE_NULL);
        binding.sessionTime.setOnClickListener(v -> {
            mCalendar = Calendar.getInstance();

            TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hourOfDay, minute) -> {
                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mCalendar.set(Calendar.MINUTE, minute);

                binding.sessionTime.setText(mSimpleTimeFormat.format(mCalendar.getTime()));
            };

            new TimePickerDialog(getContext(), timeSetListener,
                    mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), false).show();
        });

        binding.createSessionBtn.setOnClickListener(v -> {

            //TODO token session empty for now
            if (!isFieldEmpty()) {
                ProgressDialog.showLoader(getActivity());

                HashMap<String, Object> sessionMap = new HashMap<>();
                sessionMap.put("sessionName", binding.sessionName.getText().toString());

                String dateTime = binding.sessionDate.getText().toString() +" "+ binding.sessionTime.getText().toString();
                sessionMap.put("dateTime", dateTime);
                sessionMap.put("agentId", agentId);
                sessionMap.put("tokenSession", "");

                viewModel.addSessions(sessionMap).observe(getViewLifecycleOwner(), dormArResults -> {
                    ProgressDialog.hideLoader();

                    if (dormArResults.getResults().getCode() == 1) {
                        Toast.makeText(getContext(), "Session added sucessfully", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getContext(), dormArResults.getResults().getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private boolean isFieldEmpty() {

        if (TextUtils.isEmpty(binding.sessionName.getText())
                || TextUtils.isEmpty(binding.sessionDate.getText())
                || TextUtils.isEmpty(binding.sessionTime.getText())) {
            Toast.makeText(getContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

}