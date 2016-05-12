package com.example.michal.rentmate.ui.claims;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.model.pojo.Message;
import com.example.michal.rentmate.model.pojo.User;
import com.example.michal.rentmate.model.repositories.ClaimRepository;
import com.example.michal.rentmate.model.repositories.UserRepository;
import com.example.michal.rentmate.networking.RentMateApi;
import com.example.michal.rentmate.util.Constants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClaimMessageFragment extends Fragment {

  @Bind(R.id.add_message_button) FloatingActionButton addMsgButton;
  @Bind(R.id.recyclerView_message_list) RecyclerView recyclerView;

  private MsgAdapter adapter;
  private RentMateApi service;
  private User user;
  private List<Message> msgList;
  private String claimId;


  public static ClaimMessageFragment newInstance() {
    return new ClaimMessageFragment();
  }

  public static ClaimMessageFragment newInstance(String claimID) {
    Bundle arg = new Bundle();
    arg.putSerializable(Constants.ARG_CLAIM_MESSAGE_ID, claimID);

    ClaimMessageFragment messageFragment = new ClaimMessageFragment();
    messageFragment.setArguments(arg);
    return messageFragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    claimId = (String) getArguments().getSerializable(Constants.ARG_CLAIM_MESSAGE_ID);
    user = UserRepository.getInstance().getUser();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_claim_messsage, container, false);
    ButterKnife.bind(this, view);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    updateUI();
    return view;
  }

//  Listeners
  @OnClick(R.id.add_message_button)
  public void newMessage(){

  }

  private void updateUI() {
    ClaimRepository claimRepo = ClaimRepository.getInstance();
    Claim claim = claimRepo.getClaim(claimId);
    msgList = claim.getMessages();

    if (adapter == null) {
      adapter = new MsgAdapter(msgList);
      recyclerView.setAdapter(adapter);
    } else {
      adapter.notifyDataSetChanged();
      recyclerView.setAdapter(adapter);
    }
  }

  public class MsgHolder extends RecyclerView.ViewHolder {
    public Message message;
    @Bind(R.id.message_text_view) TextView messageTextView;
    @Bind(R.id.card_view_tenant) CardView cardView;

    public MsgHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void onBind(Message message) {
      this.message = message;
      CardView.LayoutParams params = new CardView.LayoutParams(
          CardView.LayoutParams.MATCH_PARENT,
          CardView.LayoutParams.WRAP_CONTENT
      );
      params.setMargins(0,0,70,0);
      messageTextView.setText(message.getMessage());
      if (message.getPostedBy().getGroupId().equals("tenant")) {
        cardView.setCardBackgroundColor(getResources().getColor(R.color.material_green_300));
        cardView.setLayoutParams(params);
        messageTextView.setTextColor(getResources().getColor(R.color.colorWhite));
      } else {
        cardView.setCardBackgroundColor(getResources().getColor(R.color.colorCard));
      }
    }
  }

  public class MsgAdapter extends RecyclerView.Adapter<MsgHolder> {
    private List<Message> messageList;

    public MsgAdapter(List<Message> messageList) {
      this.messageList = messageList;
    }

    @Override
    public MsgHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(getActivity());
      View view = inflater.inflate(R.layout.message_view_in_recyclerview, parent, false);
      return new MsgHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgHolder holder, int position) {
      Message msg = messageList.get(position);
      holder.onBind(msg);
    }

    @Override
    public int getItemCount() {
      return messageList.size();
    }
  }
}
