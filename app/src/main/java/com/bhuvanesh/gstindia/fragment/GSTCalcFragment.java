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
import com.bhuvanesh.gstindia.activity.GstActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Karthikeyan on 02-07-2017.
 */

public class GSTCalcFragment extends BaseFragment {

    private RadioGroup gstRadioGroup;
    private Button addGSTButton,removeGSTButton;
    private TextView netValueTextView,gstValueTextView,totalValueTextView;
    private TextInputEditText amountEditText;
    private InterstitialAd mInterstitialAd;

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
        ((BaseActivity)getActivity()).setTitle(getString(R.string.calculator));
        setHasOptionsMenu(true);
        AdView adView= (AdView) view.findViewById(R.id.adview);
        adView.loadAd(getAdRequest());
        mInterstitialAd=getInterstitialAdInstance(getContext());
        mInterstitialAd.loadAd(getAdRequest());

        gstRadioGroup= (RadioGroup) view.findViewById(R.id.radio_button_grp_gst);
        addGSTButton= (Button) view.findViewById(R.id.button_add_gst);
        removeGSTButton= (Button) view.findViewById(R.id.button_remove_gst);
        netValueTextView= (TextView) view.findViewById(R.id.textview_net_value);
        gstValueTextView= (TextView) view.findViewById(R.id.textview_gst_value);
        totalValueTextView= (TextView) view.findViewById(R.id.textview_total_value);
        amountEditText= (TextInputEditText) view.findViewById(R.id.editText_amount);
        addGSTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(amountEditText.getWindowToken(), 0);
                if(amountEditText.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Nothing entered",Toast.LENGTH_SHORT).show();
                }else {
                    double amt=0.0;
                   try {

                    amt=Double.parseDouble(amountEditText.getText().toString());
                       }catch (NumberFormatException exception){

                   }

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
                    double amt=0.0;
                    try {

                        amt=Double.parseDouble(amountEditText.getText().toString());
                    }catch (NumberFormatException exception){
                         Toast.makeText(getContext(),"Invalid Amount",Toast.LENGTH_SHORT).show();
                    }
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
                if(mInterstitialAd.isLoaded())mInterstitialAd.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onBackPress() {
        super.onBackPress();
        if(mInterstitialAd.isLoaded())mInterstitialAd.show();

    }
}
