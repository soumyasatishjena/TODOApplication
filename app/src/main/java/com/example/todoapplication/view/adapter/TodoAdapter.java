package com.example.todoapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplication.R;
import com.example.todoapplication.databinding.AdapterTodoBinding;
import com.example.todoapplication.pojo.TodoData;

import java.util.ArrayList;

/* ------------------------------------------------------------- *
 * Recycler View Adapter for showing the data
 * ------------------------------------------------------------- */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    /* ------------------------------------------------------------- *
     * Variable Declaration
     * ------------------------------------------------------------- */
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<TodoData> todoDataList;

    /* ------------------------------------------------------------- *
     * Constructor for Recycler Adapter
     * ------------------------------------------------------------- */
    public TodoAdapter(Context context, ArrayList<TodoData> todoDataList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.todoDataList = todoDataList;
    }

    /* ------------------------------------------------------------- *
     * Initialize the Xml for Recycler Adapter
     * ------------------------------------------------------------- */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.adapter_todo,
                parent, false));
    }


    /* ------------------------------------------------------------- *
     * Mapping all the the view to the action on the run time
     * ------------------------------------------------------------- */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setTodoData(todoDataList.get(position));
        if (todoDataList.get(position).getIsSelected() == 0) {
            holder.binding.imageSelected.setImageDrawable(context.getResources()
                    .getDrawable(R.drawable.ic_check_box));
            holder.binding.cardTodo.setCardBackgroundColor(context.getResources()
                    .getColor(R.color.colorWhite));
        } else {
            holder.binding.imageSelected.setImageDrawable(context.getResources()
                    .getDrawable(R.drawable.ic_check_box_selected));
            holder.binding.cardTodo.setCardBackgroundColor(context.getResources()
                    .getColor(R.color.greyed));
        }
    }

    /* ------------------------------------------------------------- *
     * Displaying the number of item required for the recycler view
     * for showing the recycler adapter
     * ------------------------------------------------------------- */
    @Override
    public int getItemCount() {
        return todoDataList.size();
    }

    /* ------------------------------------------------------------- *
     * ViewHolder Class for making the recycler view
     * ------------------------------------------------------------- */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AdapterTodoBinding binding;

        public ViewHolder(@NonNull AdapterTodoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.imageSelected.setOnClickListener(this);
        }

        /* ------------------------------------------------------------- *
         * OnClick Listener for Adapter for performing the operation
         * required
         * ------------------------------------------------------------- */
        @Override
        public void onClick(View view) {
            int index = getAdapterPosition();
            int len = todoDataList.size();
            if (len > 1) {
                if (todoDataList.get(index).getIsSelected() == 0) {
                    todoDataList.add(len, todoDataList.get(index));
                    todoDataList.remove(index);
                    todoDataList.get(len - 1).setIsSelected(1);
                } else {
                    todoDataList.add(0, todoDataList.get(index));
                    todoDataList.remove(index + 1);
                    todoDataList.get(0).setIsSelected(0);
                }
            } else {
                if (todoDataList.get(index).getIsSelected() == 0)
                    todoDataList.get(index).setIsSelected(1);
                else
                    todoDataList.get(index).setIsSelected(0);
            }
            notifyDataSetChanged();
        }
    }
}