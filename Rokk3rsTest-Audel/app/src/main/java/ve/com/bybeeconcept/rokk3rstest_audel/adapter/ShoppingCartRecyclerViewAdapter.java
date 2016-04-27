package ve.com.bybeeconcept.rokk3rstest_audel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ve.com.bybeeconcept.rokk3rstest_audel.R;
import ve.com.bybeeconcept.rokk3rstest_audel.internalcomms.BusProvider;
import ve.com.bybeeconcept.rokk3rstest_audel.internalcomms.ItemAddedEvent;
import ve.com.bybeeconcept.rokk3rstest_audel.model.Product;

/**
 * Created by audel on 2/25/16.
 */
public class ShoppingCartRecyclerViewAdapter extends RecyclerView.Adapter<ShoppingCartRecyclerViewAdapter.ViewHolder>{

    private List<Product> dataset;

    public ShoppingCartRecyclerViewAdapter(List<Product> dataset) {
        this.dataset = dataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public TextView txtPrice;
        public TextView txtAmount;
        public Button btnBuyItem;

        public ViewHolder(View v) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.textViewProductName);
            txtPrice = (TextView) v.findViewById(R.id.textViewProductPrice);
            txtAmount = (TextView) v.findViewById(R.id.textViewProductAmount);
            btnBuyItem = (Button) v.findViewById(R.id.buttonAddToCart);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /*holder.txtUserWithLoggedAction.setText(dataSet.get(position).getLoggedUserNameForAction());
        holder.txtLoggedAction.setText(dataSet.get(position).getLoggedAction());*/

        holder.txtName.setText(dataset.get(position).getName());
        holder.txtPrice.setText(Double.toString(dataset.get(position).getPrice()) );
        holder.txtAmount.setText(Long.toString( dataset.get(position).getStock()) );

        holder.btnBuyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusProvider.getInstance().post(new ItemAddedEvent(dataset.get(position).getId(),
                        dataset.get(position).getPrice()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
