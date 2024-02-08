package com.ahmad.socialmedia.fragment_menu;

import android.content.ClipData;
import android.content.ClipboardManager;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmad.socialmedia.R;
import com.ahmad.socialmedia.databinding.FragmentClipboardBinding;


public class ClipBoard extends Fragment implements View.OnClickListener{
    FragmentClipboardBinding clipboardBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        clipboardBinding =FragmentClipboardBinding.inflate(getLayoutInflater());
        WatcherText();
        return clipboardBinding.getRoot();



    }
//listenr btn
    @Override
    public void onClick(View v) {
        if (v.getId()==clipboardBinding.btnCopy.getId()){

            String TextValue=clipboardBinding.TextMultiLine.getText().toString();
            if(!TextValue.isEmpty()){
                clipboardBinding.btnCopy.startTickAnim();
                ClipboardManager clipboardManager= (ClipboardManager) getContext().getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData=android.content.ClipData.newPlainText("Data",TextValue);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getContext(), "copied", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getContext(), "enter data!!!!!!!!", Toast.LENGTH_SHORT).show();
            }


        }
        else if (v.getId()==clipboardBinding.btnremove.getId()){

            String TextValue=clipboardBinding.TextMultiLine.getText().toString();
            if(TextValue.length()>0){
                int Last_index=TextValue.length();
String newText= TextValue.substring(0,Last_index-1);

                clipboardBinding.TextMultiLine.setText(newText);
                }

            }else {
                Toast.makeText(getContext(), "text already empty", Toast.LENGTH_SHORT).show();

            }

        }
//initilize all UI and text watcher
    public void WatcherText() {
        clipboardBinding.btnCopy.setVisibility(View.INVISIBLE);
clipboardBinding.btnCopy.setOnClickListener(this);
clipboardBinding.btnremove.setOnClickListener(this);
clipboardBinding.btnremove.setVisibility(View.INVISIBLE);
        clipboardBinding.TextMultiLine.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
clipboardBinding.btnCopy.setVisibility(View.VISIBLE);
            clipboardBinding.btnremove.setVisibility(View.VISIBLE);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
    }
}