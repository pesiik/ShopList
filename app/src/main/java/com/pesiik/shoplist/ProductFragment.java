package com.pesiik.shoplist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pesiik.shoplist.Model.Product;
import com.pesiik.shoplist.Model.ProductManager;

import java.util.UUID;

public class ProductFragment extends Fragment {

    private static final String TAG = "ProductFragment";
    private static final String ARG_PRODUCT_ID = "product_id";

    private Product mProduct;
    private EditText mTitleField;
    private EditText mPriceField;
    private EditText mDescriptionField;
    private Button mSaveChangesButton;
    private Button mCanselButton;


    public static ProductFragment newInstance(UUID productId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT_ID, productId);

        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProduct = new Product();

        Bundle arguments = getArguments();

        if(arguments != null){
            UUID productId = (UUID) arguments.getSerializable(ARG_PRODUCT_ID);
            mProduct = ProductManager.get(getContext()).getProduct(productId);
        }
        else {
            mProduct = new Product();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product, container, false);

        mTitleField = v.findViewById(R.id.product_edit);
        mTitleField.setText(mProduct.getName());

        mPriceField = v.findViewById(R.id.price_edit);

        if(mProduct.getPrice() != null){
            mPriceField.setText(mProduct.getPrice().toString());
        }

        mDescriptionField = v.findViewById(R.id.description_edit);
        mDescriptionField.setText(mProduct.getDescription());

        mSaveChangesButton = v.findViewById(R.id.save_button);
        mSaveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mTitleField.getText().toString().equals("") && !mDescriptionField.getText().toString().equals("")
                && !mPriceField.getText().toString().equals("")){

                    mProduct.setName(mTitleField.getText().toString());
                    mProduct.setPrice(Double.parseDouble(mPriceField.getText().toString()));
                    mProduct.setDescription(mDescriptionField.getText().toString());
                    getActivity().finish();
                }
                else {
                    Toast.makeText(getContext(), R.string.empty_fields_hint, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCanselButton = v.findViewById(R.id.cancel_button);
        mCanselButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return v;
    }
}
