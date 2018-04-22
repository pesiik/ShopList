package com.pesiik.shoplist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pesiik.shoplist.Model.Product;
import com.pesiik.shoplist.Model.ProductManager;

import java.util.List;

public class ProductListFragment extends Fragment {
    private RecyclerView mProductRecyclerView;
    private ProductAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false );
        mProductRecyclerView = (RecyclerView) view.findViewById(R.id.product_recycler_view);
        mProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        updateUI();

        return view;
    }

    private void updateUI(){
        ProductManager productManager = ProductManager.get(getContext());
        List<Product> products = productManager.getProducts();
        if(mAdapter == null)
        {
            mAdapter = new ProductAdapter(products);
            mProductRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Product mProduct;

        private TextView mTitleProduct;
        private TextView mPriceTextView;
        private TextView mCountTextView;
        private TextView mDescriptionTextView;

        public ProductHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_product, parent, false));

            itemView.setOnClickListener(this);
            mTitleProduct = (TextView) itemView.findViewById(R.id.product_title);
            mPriceTextView = (TextView) itemView.findViewById(R.id.price_label);
            mCountTextView = (TextView) itemView.findViewById(R.id.count_products);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.description_text_view);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Product product){
            mProduct = product;
            mTitleProduct.setText(mProduct.getName());
            mPriceTextView.setText(mProduct.getPrice().toString());
            int count = mProduct.getCount();
            mCountTextView.setText(String.valueOf(count));
            mDescriptionTextView.setText(mProduct.getDescription());
        }

        @Override
        public void onClick(View v) {
            Intent intent = ProductActivity.newIntent(getContext(), mProduct.getId());
            startActivity(intent);
        }
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductHolder>{
        private List<Product> mProducts;

        public ProductAdapter(List<Product> products){
            mProducts = products;
        }


        @NonNull
        @Override
        public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            return new ProductHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
            Product product = mProducts.get(position);
            holder.bind(product);
        }

        @Override
        public int getItemCount() {
            return mProducts.size();
        }
    }
}
