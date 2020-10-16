package com.example.yame.CartFragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.ChangeCurrency;
import com.example.yame.R;
import com.example.yame.Store;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CartFragment extends Fragment implements CustomClickListener {

    private View view;
    private RecyclerView recyclerView;
    private List<BuyProduct> buyProductList;
    private MyCartAdapter adapter;
    private TextView tvCountItem, tvTotal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cart, container, false);

        initView();
        initData();
        config();
        handlerEvents();

        return view;
    }

    private void handlerEvents() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initView() {
        tvCountItem = view.findViewById(R.id.tvCountItem);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvTotal = view.findViewById(R.id.tvTotalPrice);
    }

    private void initData() {
        buyProductList = new ArrayList<>();

        //create list of image product
        List<Integer> imgs = new ArrayList<>();
        imgs.add(R.raw.cart_demo);
        imgs.add(R.raw.cart_demo);
        imgs.add(R.raw.cart_demo);
        imgs.add(R.raw.cart_demo);

        //create list of store have this product
        List<Store> storeList = new ArrayList<>();
        storeList.add(new Store(12340, "This is store name", "123 Duong 3/2 P.12 Q.10 TP.HCM"));
        storeList.add(new Store(12340, "This is store name", "123 Duong 3/2 P.12 Q.10 TP.HCM"));
        storeList.add(new Store(12340, "This is store name", "123 Duong 3/2 P.12 Q.10 TP.HCM"));
        storeList.add(new Store(12340, "This is store name", "123 Duong 3/2 P.12 Q.10 TP.HCM"));


        for (int i = 0; i < 15; i++) {
            buyProductList.add(new BuyProduct(imgs, "T-shirt " + i, i, 100000, 1, "This is product detail", "This is instruction paragraph", storeList));
        }

        adapter = new MyCartAdapter(getContext(), buyProductList, R.layout.cart_custom_row_item);
        adapter.setListener(this);
    }

    private void config() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        tvCountItem.setText("Giỏ hàng (" + buyProductList.size() + ")");
        calTotalPrice();
    }

    private void calTotalPrice() {
        int total = 0;
        for (BuyProduct i : buyProductList) {
            total += i.getTotalPrice();
        }

        tvTotal.setText(ChangeCurrency.formatCurrency(total));
    }

    @Override
    public void onQuantityChange() {
        calTotalPrice();
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int pos = viewHolder.getAdapterPosition();
            BuyProduct item = buyProductList.get(pos);

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    //Delete item while swiped
                    buyProductList.remove(pos);
                    adapter.notifyItemRemoved(pos);

                    //Snackbar to notify user item removed and let them undo if necessary
                    Snackbar.make(recyclerView, "Đã xóa sản phẩm khỏi giỏ hàng", Snackbar.LENGTH_LONG)
                            .setAction("Hoàn tác", v -> {
                               buyProductList.add(pos, item);
                               adapter.notifyItemInserted(pos);
                            }).show();

                    //update total price
                    calTotalPrice();
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(getContext(), c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .addSwipeLeftLabel("Xóa")
                    .setSwipeLeftLabelTextSize(TypedValue.COMPLEX_UNIT_SP, 20)
                    .setSwipeLeftLabelColor(Color.WHITE)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}
