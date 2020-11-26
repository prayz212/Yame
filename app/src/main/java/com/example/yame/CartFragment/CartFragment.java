package com.example.yame.CartFragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.CartProductDB;
import com.example.yame.ChangeCurrency;
import com.example.yame.R;
import com.example.yame.network.API;
import com.example.yame.network.GetCartProductResponse;
import com.example.yame.network.ProductDBApi;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment implements CustomClickListener {

    private View view;
    private RecyclerView recyclerView;
    private ProductDBApi api;
    private List<CartProductDB> buyProductList;
    private MyCartAdapter adapter;
    private TextView tvCountItem, tvTotal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cart, container, false);

        initView();

        api = API.getProdcutDBApi();
        getCartProducts();

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
        adapter = new MyCartAdapter(getContext(), buyProductList, R.layout.cart_custom_row_item);
        adapter.setListener(this);

        config();

        //DEPLAY DE CHO ADAPTER XONG
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                calTotalPrice();
            }
        },10);
    }

    private void getCartProducts() {
        //TAM THOI TRUYEN ID USER VO
        Call<GetCartProductResponse> call = api.getCartProduct(1);
        call.enqueue(new Callback<GetCartProductResponse>() {
            @Override
            public void onResponse(Call<GetCartProductResponse> call, Response<GetCartProductResponse> response) {
                if (response.isSuccessful()) {
                    GetCartProductResponse result = response.body();

                    if (result != null && result.status == 200) {
                        buyProductList = result.data;
                        initData();
                    } else if (result != null && result.status == 201) {
                        buyProductList = result.data;
                        initData();
                        Toast.makeText(getContext(), "Bạn chưa chọn sản phẩm nào, nhấn vào trang chủ để lựa sản phẩm.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.e("test", "Server fail: " + result.message);
                    }
                } else {
                    Log.e("test", "response fail: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetCartProductResponse> call, Throwable t) {
                Log.e("test", "failure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void config() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        tvCountItem.setText("Giỏ hàng (" + buyProductList.size() + ")");
    }

    private void calTotalPrice() {
        int total = 0;
        for (CartProductDB buy : buyProductList) {
            total += buy.getTotalPrice();
        }

        tvTotal.setText(ChangeCurrency.formatCurrency(total));
    }

    @Override
    public void onQuantityChange(long id_cart, long id_product, int value) {
        calTotalPrice();
        updateQuanlity(id_cart, id_product, value);
    }

    private void updateQuanlity(long id_cart, long id_product, int value) {
        Call<com.example.yame.network.Response> call = api.updateCartProductQuanlity(id_product, id_cart, value);
        call.enqueue(new Callback<com.example.yame.network.Response>() {
            @Override
            public void onResponse(Call<com.example.yame.network.Response> call, Response<com.example.yame.network.Response> response) {
                if (response.isSuccessful()) {
                    com.example.yame.network.Response result = response.body();

                    if (result != null && result.status == 200) {
                        Log.e("test", "Change success");
                    } else {
                        Log.e("test", "Server fail: " + result.message);
                    }
                } else {
                    Log.e("test", "response fail: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<com.example.yame.network.Response> call, Throwable t) {
                Log.e("test", "failure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int pos = viewHolder.getAdapterPosition();
            CartProductDB item = buyProductList.get(pos);
            int size = buyProductList.size();

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

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        if (size > buyProductList.size()) {
                            deleteCartProduct(item.getId_cart(), item.getId());
                        }
                    }, 2750 + 10);

                    //update total price after change
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

    private void deleteCartProduct(long id_cart, long id_product) {
        Call<com.example.yame.network.Response> call = api.deleteCartProduct(id_product, id_cart);
        call.enqueue(new Callback<com.example.yame.network.Response>() {
            @Override
            public void onResponse(Call<com.example.yame.network.Response> call, Response<com.example.yame.network.Response> response) {
                if (response.isSuccessful()) {
                    com.example.yame.network.Response result = response.body();

                    if (result != null && result.status == 200) {
                        Log.e("test", "Delete success");
                    } else {
                        Log.e("test", "Server fail: " + result.message);
                    }
                } else {
                    Log.e("test", "response fail: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<com.example.yame.network.Response> call, Throwable t) {
                Log.e("test", "failure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
