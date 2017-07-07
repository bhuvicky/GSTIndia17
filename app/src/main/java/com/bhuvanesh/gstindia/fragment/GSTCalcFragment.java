package com.bhuvanesh.gstindia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Karthikeyan on 02-07-2017.
 */

public class GSTCalcFragment extends BaseFragment {

    private RadioGroup gstRadioGroup;
    private Button addGSTButton,removeGSTButton;
    private TextView netValueTextView,gstValueTextView,totalValueTextView;
    private TextInputEditText amountEditText;
    public static GSTCalcFragment newInstance(){
        return new GSTCalcFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frament_gst_calculator,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseActivity)getActivity()).setBackEnabled(true);
        ((BaseActivity)getActivity()).setTitle("GST Calculator");
        setHasOptionsMenu(true);
        AdView adView=view.findViewById(R.id.adview);
        AdRequest adRequest=new AdRequest.Builder()
                .addTestDevice("D7485D34081F44384E25018239E6810B")
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        gstRadioGroup=view.findViewById(R.id.radio_button_grp_gst);
        addGSTButton=view.findViewById(R.id.button_add_gst);
        removeGSTButton=view.findViewById(R.id.button_remove_gst);
        netValueTextView=view.findViewById(R.id.textview_net_value);
        gstValueTextView=view.findViewById(R.id.textview_gst_value);
        totalValueTextView=view.findViewById(R.id.textview_total_value);
        amountEditText=view.findViewById(R.id.editText_amount);
        addGSTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(amountEditText.getWindowToken(), 0);
                if(amountEditText.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Nothing entered",Toast.LENGTH_SHORT).show();
                }else {
                    double amt=Double.parseDouble(amountEditText.getText().toString());
                    if(amt<9999999999f) {
                        netValueTextView.setText(String.format("₹%.2f", amt));
                        double gst = amt / 100;
                        switch (gstRadioGroup.getCheckedRadioButtonId()) {
                            case R.id.radio_button_0:
                                gst = 0;
                                break;
                            case R.id.radio_button_5:
                                gst *= 5;
                                break;
                            case R.id.radio_button_12:
                                gst *= 12;
                                break;
                            case R.id.radio_button_18:
                                gst *= 18;
                                break;
                            case R.id.radio_button_28:
                                gst *= 28;
                                break;
                        }

                        gstValueTextView.setText(String.format("₹%.2f", gst));
                        totalValueTextView.setText(String.format("₹%.2f", (amt + gst)));
                    }else{
                        Toast.makeText(getActivity(),"Wrong Input",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        removeGSTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(amountEditText.getWindowToken(), 0);
                if(amountEditText.getText().equals("")){
                    Toast.makeText(getActivity(),"Nothing entered",Toast.LENGTH_SHORT).show();
                }else {
                    double amt=Double.parseDouble(amountEditText.getText().toString());
                    if(amt<9999999999f){
                        netValueTextView.setText(String.format("₹%.2f", amt));
                        double gst=amt/100;
                        switch (gstRadioGroup.getCheckedRadioButtonId()){
                            case R.id.radio_button_0:
                                gst=0;
                                break;
                             case R.id.radio_button_5:
                                gst*=5;
                                break;
                            case R.id.radio_button_12:
                                gst*=12;
                                break;
                            case R.id.radio_button_18:
                                gst*=18;
                                break;
                            case R.id.radio_button_28:
                                gst*=28;
                                break;
                         }
                        gstValueTextView.setText(String.format("₹%.2f",gst));
                        totalValueTextView.setText(String.format("₹%.2f",(amt-gst)));
                    }
                    else{
                        Toast.makeText(getActivity(),"Wrong Input",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        amountEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (view == amountEditText) {
                    if (hasFocus) {
                        // Open keyboard
                        ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(amountEditText, InputMethodManager.SHOW_FORCED);
                    } else {
                        // Close keyboard
                        ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(amountEditText.getWindowToken(), 0);
                    }
                }
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                pop();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
