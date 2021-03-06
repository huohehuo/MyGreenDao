package lins.com.mygreendao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LINS on 2017/3/7.
 */

public class UserAdapter extends BaseAdapter {
    private List<User> mUserList;
    private Context mContext;

    public UserAdapter(Context mContext, List<User> mUserList) {
        this.mUserList = mUserList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mUserList == null ? 0 : mUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_user, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_sex = (TextView) convertView.findViewById(R.id.tv_sex);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        User user = mUserList.get(position);
        viewHolder.tv_id.setText(String.valueOf(user.getId()));
        viewHolder.tv_name.setText(user.getName());
        //viewHolder.tv_sex.setText(user.getName());
        return convertView;
    }

    class ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_sex;
    }
}
