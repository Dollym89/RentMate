package com.example.michal.rentmate.ui.claims;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ClaimNew extends Fragment {

  @Bind(R.id.claim_title_edit_text) EditText titleEditText;
  @Bind(R.id.claim_detail_description) EditText descriptionEditText;
  @Bind(R.id.create_claim_button) Button saveButton;


  public static ClaimNew newInstance() {
    return new ClaimNew();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_new_claim, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  //  Listeners
  @OnClick(R.id.create_claim_button)
  public void onClaimSaved() {

  }

  private Claim setClaimProp() {
    Claim claim = new Claim();
    String title = String.valueOf(titleEditText.getText());
    String description = String.valueOf(descriptionEditText.getText());
    claim.setTitle(title);
    claim.setDescription(description);
    return claim;
  }

  private void sendResult(int resultCode, Boolean isApartmentAdded) {
    if (getTargetFragment() == null) {
      return;
    }
    Intent intent = new Intent();
    intent.putExtra(Constants.EXTRA_NEW_CLAIM, isApartmentAdded);
    getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
  }
}
