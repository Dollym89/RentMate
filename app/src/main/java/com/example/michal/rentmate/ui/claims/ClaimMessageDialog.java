package com.example.michal.rentmate.ui.claims;




import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.michal.rentmate.R;


public class ClaimMessageDialog extends DialogFragment {

  public static ClaimMessageDialog newInstance(){
    return new ClaimMessageDialog();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder msgDialog = new AlertDialog.Builder(getActivity());

    LayoutInflater inflater = getActivity().getLayoutInflater();
    msgDialog.setView(inflater.inflate(R.layout.frag_message_dilaog, null))
        .setTitle("NEW MESSAGE")
        .setPositiveButton("CREATE", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {

          }
        })
        .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {

          }
        });




    return msgDialog.create();
  }
}
