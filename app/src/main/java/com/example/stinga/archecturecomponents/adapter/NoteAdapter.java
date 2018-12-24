package com.example.stinga.archecturecomponents.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stinga.archecturecomponents.R;
import com.example.stinga.archecturecomponents.entity.Note;


public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    public static final String TAG = NoteAdapter.class.getSimpleName();
    private onItemClickedListener listener;

    public NoteAdapter() {
        super(callback);
    }

    private static DiffUtil.ItemCallback<Note> callback = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
        Note currentNote = getItem(position);
        noteHolder.textViewTitle.setText(currentNote.getTitle());
        noteHolder.textViewDescription.setText(currentNote.getDescription());
        noteHolder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    public Note getElementAt(int adapterPosition) {
        return getItem(adapterPosition);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewDescription;
        public TextView textViewPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_vew_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null || position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    } else {
                        Log.e(TAG, "onClick: something wrong happened!!");
                    }
                }
            });
        }
    }

    public void setOnItemClickedListener(onItemClickedListener listener) {
        this.listener = listener;
    }

    public interface onItemClickedListener {
        void onItemClick(Note note);
    }
}
