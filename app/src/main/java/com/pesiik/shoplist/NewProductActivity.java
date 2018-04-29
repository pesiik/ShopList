package com.pesiik.shoplist;

import android.support.v4.app.Fragment;

import com.pesiik.shoplist.SingleFragmentActivity;

public class NewProductActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ProductFragment();
    }
}
