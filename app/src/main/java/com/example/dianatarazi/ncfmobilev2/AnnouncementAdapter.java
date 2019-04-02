package com.example.dianatarazi.ncfmobilev2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementHolder> {
    private List<Announcement> announcements = new ArrayList<>();
    private OnAnnouncementClickListener mOnAnnouncementClickListener;

    @NonNull
    @Override
    public AnnouncementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.announcement_item, parent, false);
        return new AnnouncementHolder(itemView, mOnAnnouncementClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementHolder holder, int position) {
        if (!announcements.isEmpty()) {
            Announcement currentAnnouncement = announcements.get(position);
            holder.textViewTitle.setText(currentAnnouncement.getTitle().get("rendered"));
            holder.textViewDate.setText(currentAnnouncement.getDate());
            holder.textViewContent.setText(currentAnnouncement.getContent().get("rendered"));
        }
    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
        notifyDataSetChanged();
    }

    class AnnouncementHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewContent;
        OnAnnouncementClickListener onAnnouncementClickListener;

        public AnnouncementHolder(@NonNull View itemView, OnAnnouncementClickListener onAnnouncementClickListener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewContent = itemView.findViewById(R.id.text_view_description);
            this.onAnnouncementClickListener = onAnnouncementClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onAnnouncementClickListener.onAnnouncementClick(getAdapterPosition());
        }
    }

    public interface OnAnnouncementClickListener {
        void onAnnouncementClick(int position);

    }

    public void setOnAnnouncementClickListener(OnAnnouncementClickListener listener) {
        mOnAnnouncementClickListener = listener;
    }
}
