package cs499android.com.a2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cs499android.com.a2.Model.Friend;

/**
 * Created by Richard on 4/11/17.
 */

public class ListFriendsFragment extends Fragment {

    private String[] names = {"Marie Curie", "Thomas Edison", "Albert Einstein", "Michael Faraday", "Galileo Galilei",
            "Stephen Hawking", "Johannes Kepler", "Issac Newton"};

    private int[] image = {R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7};

    private ArrayList<Friend> mFriends;
    private RecyclerView mFriendRecyclerView;
    private FriendsAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mFriends = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Friend f = new Friend();
            f.setName(names[i]);
            f.setImageId(image[i]);
            mFriends.add(f);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_friends, container, false);
        mFriendRecyclerView = (RecyclerView) view
                .findViewById(R.id.friend_recycler_view);
        mFriendRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mFriendRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI(){
        mAdapter = new FriendsAdapter(mFriends);
        mFriendRecyclerView.setAdapter(mAdapter);
    }

    private class FriendHolder extends RecyclerView.ViewHolder {
        private Friend mFriend;
        public ImageView mImageView;
        public TextView mNameTextView;

        public FriendHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mNameTextView = (TextView) itemView.findViewById(R.id.textview_name);
//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getActivity(),
//                                mFriend.getName() + " clicked!", Toast.LENGTH_SHORT)
//                                .show();
//                    }
//                });
        }

        public void bindData(Friend f) {
            mFriend = f;
            mImageView.setImageResource(f.getImageId());
            mNameTextView.setText(f.getName());
        }

    }

    private class FriendsAdapter extends RecyclerView.Adapter<FriendHolder>{
        private ArrayList<Friend> mFriends;
        public FriendsAdapter(ArrayList<Friend> Scientists){
            mFriends = Scientists;
        }
        @Override
        public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item,parent,false);
            return new FriendHolder(view);
        }
        @Override
        public void onBindViewHolder(FriendHolder holder, int position) {
            Friend f = mFriends.get(position);
            holder.bindData(f);

        }
        @Override
        public int getItemCount() {
            return mFriends.size();
        }
    }

}
