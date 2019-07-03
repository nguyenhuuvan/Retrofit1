package com.example.retrofit.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.listener.OnClick;
import com.example.retrofit.listener.OnDelete;
import com.example.retrofit.listener.OnEdit;
import com.example.retrofit.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private List<User> userList;
    private final Context context;
    private final OnClick onClick;
    private final OnEdit onEdit;
    private final OnDelete onDelete;

    public UserAdapter(List<User> userList, Context context, OnClick onClick, OnEdit onEdit, OnDelete onDelete) {
        this.userList = userList;
        this.context = context;
        this.onClick = onClick;
        this.onEdit = onEdit;
        this.onDelete = onDelete;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_user, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        User user = userList.get(position);
        holder.tvEmail.setText(user.getEmail());
        holder.tvID.setText(user.getId()+"");
        holder.tvLastName.setText(user.getLastName());
        holder.tvFirstName.setText(user.getFirstName());

        Glide.with(context).load(user.getAvatar()).into(holder.imgAvatar);
        holder.imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PopupMenu popup = new PopupMenu(context, holder.imgMenu);
                    popup.inflate(R.menu.option_menu);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()) {
                                case R.id.delete:
                                    onDelete.OnDelete(position);
                                    return true;
                                case R.id.update:
                                    onEdit.OnEdit(position);
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popup.show();
                } catch (Exception e) {
                    Log.e("lỗi onclick","lỗi onclick");
                }


            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onClick.OnClick(position);
                } catch (Exception e) {
                    Log.e("lỗi onclick","lỗi onclick");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (userList == null) return 0;
        return userList.size();
    }
    public void changeDataset(List<User> items) {
        this.userList = items;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imgMenu;
        final TextView tvID;
        final TextView tvEmail;
        final TextView tvFirstName;
        final TextView tvLastName;
        final ImageView imgAvatar ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvID);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            imgMenu = itemView.findViewById(R.id.imgMenu);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
        }
    }
}
