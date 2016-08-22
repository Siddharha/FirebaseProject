package in.bluehorsre.firebaseproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.bluehorsre.firebaseproject.R;
import in.bluehorsre.firebaseproject.bean.MsgObj;

/**
 * Created by BLUEHORSE 123 on 8/22/2016.
 */
public class MyListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MsgObj> arrayList;
    private LayoutInflater inflater;

    public MyListAdapter(Context context, ArrayList<MsgObj> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public MsgObj getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = convertView;
        if(rootView==null)
        {
            rootView = inflater.inflate(R.layout.list_layout,parent,false);

            ItemHolder itemHolder = new ItemHolder();
            itemHolder.tvName = (TextView)rootView.findViewById(R.id.tvName);
            itemHolder.tvMsg = (TextView) rootView.findViewById(R.id.tvMsg);

            rootView.setTag(itemHolder);
        }

        ItemHolder myItemHolder = (ItemHolder)rootView.getTag();
        myItemHolder.tvName.setText(arrayList.get(position).getName());
        myItemHolder.tvMsg.setText(arrayList.get(position).getMsg());

        return rootView;
    }

    private class ItemHolder
    {
        TextView tvName,tvMsg;
    }
}
