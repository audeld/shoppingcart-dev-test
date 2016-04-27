package ve.com.bybeeconcept.rokk3rstest_audel.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import ve.com.bybeeconcept.rokk3rstest_audel.R;
import ve.com.bybeeconcept.rokk3rstest_audel.adapter.ProductsRecyclerViewAdapter;

/**
 * Created by audel on 2/25/16.
 */
public class ShoppingCartDialogFragment extends DialogFragment{

    private RecyclerView recyclerViewProducts;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProductsRecyclerViewAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_shopping_cart, container);

        //AHORA PODEMOS QUITARLE EL TITULO A LA VENTANA DE DIALOGO PORQUE SE PROBO EL PUNTO
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        //here we put al our code neded for the view
        initViews(view);

        return view;
    }

    private void initViews(View v){
        recyclerViewProducts = (RecyclerView) v.findViewById(R.id.recyclerViewShoppingCart);
        recyclerViewProducts.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewProducts.setLayoutManager(mLayoutManager);
    }
}
