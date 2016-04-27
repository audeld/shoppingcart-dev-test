package ve.com.bybeeconcept.rokk3rstest_audel.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import ve.com.bybeeconcept.rokk3rstest_audel.R;
import ve.com.bybeeconcept.rokk3rstest_audel.adapter.ProductsRecyclerViewAdapter;
import ve.com.bybeeconcept.rokk3rstest_audel.fragments.ShoppingCartDialogFragment;
import ve.com.bybeeconcept.rokk3rstest_audel.internalcomms.BusProvider;
import ve.com.bybeeconcept.rokk3rstest_audel.internalcomms.ItemAddedEvent;
import ve.com.bybeeconcept.rokk3rstest_audel.model.Product;
import ve.com.bybeeconcept.rokk3rstest_audel.model.ShoppingCartProd;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProducts;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProductsRecyclerViewAdapter mAdapter;

    private TextView textViewCartTotal;
    private TextView textViewCartAmount;

    private List<Product> productsList;
    private double totalCart;
    private long cartItemsQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BusProvider.getInstance().register(this);
        makeData();
        initViews();
        init();
        loadData();
        updateViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onCLickOpenFragmentCart(View v){
        FragmentManager manager = getFragmentManager();
        ShoppingCartDialogFragment mDialogFragment = new ShoppingCartDialogFragment();
        mDialogFragment.show(manager, "ShoppingCartDialogFragment");
    }

    private void initViews(){
        recyclerViewProducts = (RecyclerView) findViewById(R.id.recyclerViewMainPage);
        recyclerViewProducts.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProducts.setLayoutManager(mLayoutManager);

        textViewCartTotal = (TextView) findViewById(R.id.textViewAmountTotalInCart);
        textViewCartAmount = (TextView) findViewById(R.id.textViewProceTotalInCart);
    }

    private void init(){
        cartItemsQty = 0;
        totalCart = 0.0;

        productsList = new ArrayList<Product>();
    }

    public void loadData(){
        productsList = Product.listAll(Product.class);
        //TODO take sublists to make 20 by 20 pages
        if(productsList.size()>20) productsList = productsList.subList(0, 20);
    }

    private void updateViews(){
        textViewCartAmount.setText(Long.toString(cartItemsQty) );
        textViewCartTotal.setText(Double.toString(totalCart));

        mAdapter = new ProductsRecyclerViewAdapter(productsList);
        recyclerViewProducts.setAdapter(mAdapter);
    }

    @Subscribe
    public void productItemAddedEvent(ItemAddedEvent event){
        cartItemsQty++;
        totalCart += event.getPrice();
        List<ShoppingCartProd> mProduct = new ArrayList<ShoppingCartProd>();
        mProduct =  ShoppingCartProd.find(ShoppingCartProd.class, "product_id=?", Long.toString(event.getItemId())) ;
        if(mProduct.size() > 0 ){
            mProduct.get(0).delete();
            mProduct.get(0).setCartAmount(mProduct.get(0).getCartAmount() + 1);
            mProduct.get(0).save();
        }else{
            (new ShoppingCartProd(event.getItemId(), 1)).save();
        }
        updateViews();
    }

    private void makeData(){

        for (int i = 0; i<20; i++){
            ( new Product( 100l, Math.random()*100.0, "mItem"+i) ).save();
        }
    }
}
