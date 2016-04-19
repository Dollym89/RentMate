package com.example.michal.rentmate.ui.claims;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.model.repositories.ClaimRepository;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Michal on 05/04/2016.
 */
public class ClaimListFragment extends Fragment {


  @Bind(R.id.add_first_crime_layout)
  RelativeLayout addFirstClaimLayout;
  @Bind(R.id.add_first_crime_button)
  FloatingActionButton addClaimButton;
  @Bind(R.id.recyclerView_claim_list)
  RecyclerView claimRecyclerView;


  private ClaimAdapter adapter;
  private ClaimContract.Callbacks callbacks;


  public static ClaimListFragment newInstance() {
    return new ClaimListFragment();

  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    Log.e("FRAGMENT ATTACHED", "FRAGMENT CLAIM LIST ATTACHED");
    callbacks = (ClaimContract.Callbacks) context;
  }

  @Override
  public void onDetach() {
    super.onDetach();
    Log.e("FRAGMENT DETACHED","FRAGMENT CLAIM LIST DETACHED");
    callbacks = null;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.e("FRAGMENT ONCREATE", "FRAGMENT CLAIM LIST ONCREATE");
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_claim_list, container, false);
    setHasOptionsMenu(true);
    ButterKnife.bind(this, view);
    callbacks.setClaimActionBar();

    claimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    updateUi();

    return view;
  }

  public void updateUi() {

    ClaimRepository repository = ClaimRepository.getInstance();
    List<Claim> claimList = repository.getClaimList();

    if (adapter == null) {
      adapter = new ClaimAdapter(claimList);
      claimRecyclerView.setAdapter(adapter);
    }
    else {
      adapter.notifyDataSetChanged();
      claimRecyclerView.setAdapter(adapter);
    }

    if (claimList.size() > 0) {
      addFirstClaimLayout.setVisibility(View.GONE);
    }
    else {
      addFirstClaimLayout.setVisibility(View.VISIBLE);
      addClaimButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          callbacks.onClaimSelected();
        }
      });
    }

  }

  public class ClaimHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @Bind(R.id.list_item_textview_claim_name)
    TextView titleTextView;
    @Bind(R.id.list_item_claim_date)
    TextView dateTextView;
    @Bind(R.id.state_claim_button)
    Button statusButton;


    public ClaimHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      callbacks.onClaimSelected();
    }

    public void bindClaim(Claim claim) {

      int color = setButtonColor(claim.getStatus());
      titleTextView.setText(claim.getTitle());
      dateTextView.setText(claim.getCreatedAt());
      statusButton.setBackgroundColor(getResources().getColor(color));
      statusButton.setText(claim.getStatus());

    }

    public int setButtonColor(String status) {
      int color;

//      TODO set statuses!!!!!!!
      switch (status) {
        case "OPEN":
          color = R.color.colorProblem;
          break;
        case "CLOSED":
          color = R.color.colorOpen;
          break;
        case "PENDING":
          color = R.color.colorOk;
          break;
        default:
          color = R.color.colorOk;
          break;
      }
      return color;
    }
  }

  private class ClaimAdapter extends RecyclerView.Adapter<ClaimHolder> {

    private List<Claim> claimList;

    public ClaimAdapter(List<Claim> claimList) {
      this.claimList = claimList;

    }

    @Override
    public ClaimHolder onCreateViewHolder(ViewGroup parent, int viewType) {

      LayoutInflater inflater = LayoutInflater.from(getActivity());
      View view = inflater.inflate(R.layout.claim_view_in_recyclerview, parent, false);
      return new ClaimHolder(view);
    }

    @Override
    public void onBindViewHolder(ClaimHolder holder, int position) {
      Claim claim = claimList.get(position);
      holder.bindClaim(claim);
    }

    @Override
    public int getItemCount() {
      return claimList.size();
    }
  }


}
