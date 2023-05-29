package com.home.fastfoodactivity.ui.listFood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Food;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.ListFoodViewHolder> {

    private List<Food> foods;
    private static ClickItem clickItem;


    public ListFoodAdapter(ClickItem clickItem) {
        this.clickItem = clickItem;
        this.foods = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ListFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFoodViewHolder holder, int position) {
        holder.bind(foods.get(position));
    }

    @Override
    public int getItemCount() {
        return (foods != null && foods.size() >0) ? foods.size() : 0;
    }

    public static class ListFoodViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView image;
        private TextView price;
        private TextView description;

        private Food food;

        public ListFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_food);
            image = itemView.findViewById(R.id.image_food);
            price = itemView.findViewById(R.id.price_food);
            description = itemView.findViewById(R.id.description_food);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickItem != null){
                        clickItem.onItemClicked(food);
                    }
                }
            });

        }

        public void bind(Food food){
            this.food = food;
            if(food.getName().length() > 9){
                name.setText(food.getName().substring(0,9)+"..");
            }else{
                name.setText(food.getName());
            }



            //pegando o preço com apenas 2 casas decimais
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String priceString = decimalFormat.format(food.getPrice());
            price.setText("$"+priceString);

            if(food.getDescription().length()>22){
                description.setText(food.getDescription().substring(0,22)+"...");
            }else{
                description.setText(food.getDescription());
            }

            Picasso.get().load(food.getImage()).into(image);
        }
    }

    public void setFoods(List<Food> foods){
        this.foods = foods;
        notifyDataSetChanged();
    }

    public interface ClickItem{
        void onItemClicked(Food food);
    }
}
