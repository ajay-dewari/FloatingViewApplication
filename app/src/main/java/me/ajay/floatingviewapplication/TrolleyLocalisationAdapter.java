package me.ajay.floatingviewapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrolleyLocalisationAdapter extends RecyclerView.Adapter<TrolleyLocalisationAdapter.WagonPositionViewHolder> {

    private List<Integer> wagonPositions = new ArrayList<>(Arrays.asList(
            1, 2, 3, 4, 5, 6, 7, 11, 12, 13, 14, 15, 16, 17
    ));
    private String currentPosition;
    private String nextPosition;
    private boolean nextPositionView;

    @NonNull
    @Override
    public WagonPositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wagon_position_row, parent, false);
        return new WagonPositionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final WagonPositionViewHolder holder, final int position) {
        String wagonValue = wagonPositions.get(position).toString();
        holder.wagonPosition.setText(wagonValue);
        if (wagonValue.equals(currentPosition)) {
            holder.wagonPosition.setBackgroundResource(R.drawable.shape_wagon_selected);
        } else if (wagonValue.equals(nextPosition)) {
            holder.wagonPosition.setBackgroundResource(R.drawable.shape_wagon_selected_light);
        } else {
            holder.wagonPosition.setBackgroundResource(R.drawable.shape_wagon_not_selected);
        }
        holder.wagonPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nextPositionView) {
                    String selectedPosition = holder.wagonPosition.getText().toString();
                    if (selectedPosition.equals(currentPosition)) {
                        Toast.makeText(holder.wagonPosition.getContext(), "Next Position can't be same as current", Toast.LENGTH_LONG).show();
                    } else {
                        nextPosition = selectedPosition;
                    }
                } else {
                    currentPosition = holder.wagonPosition.getText().toString();
                    nextPosition = null;
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return wagonPositions.size();
    }

    public boolean isNextPositionView() {
        return nextPositionView;
    }

    public boolean isCurrentPositionSelected() {
        return null != currentPosition;
    }

    public boolean isNextPositionSelected() {
        return null != nextPosition;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public String getNextPosition() {
        return nextPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setNextPosition(String nextPosition) {
        this.nextPosition = nextPosition;
    }

    public void setNextPositionView(boolean nextPositionView) {
        this.nextPositionView = nextPositionView;
    }

    public static class WagonPositionViewHolder extends RecyclerView.ViewHolder {
        public TextView wagonPosition;

        public WagonPositionViewHolder(View itemView) {
            super(itemView);
            wagonPosition = itemView.findViewById(R.id.wagon_position);
        }
    }

}
