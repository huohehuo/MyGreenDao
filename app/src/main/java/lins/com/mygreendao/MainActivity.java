package lins.com.mygreendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lins.com.mygreendao.gen.UserDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mNameET;
    private Button mAddBtn;
    private ListView mUserLV;

    private UserAdapter mUserAdapter;
    private List<User> mUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initView() {
        mNameET = (EditText) findViewById(R.id.et_name);
        mAddBtn = (Button) findViewById(R.id.btn_add);
        mUserLV = (ListView) findViewById(R.id.lv_user);

        mAddBtn.setOnClickListener(this);
    }

    private void initData() {
        mUserList = GreenDaoManager.getInstance().getSession().getUserDao().queryBuilder().build().list();
        mUserAdapter = new UserAdapter(this, mUserList);
        mUserLV.setAdapter(mUserAdapter);
        mUserLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               User use= (User) adapterView.getItemAtPosition(i);
                deleteUser(use.getId());
            }
        });
    }
    /**
     * 本地数据里添加一个User
     * @param id  id
     * @param name  名字
     */
    private void insertUser(Long id, String name) {
        UserDao userDao = GreenDaoManager.getInstance().getSession().getUserDao();
        User user = new User(id, name,"aa");
        userDao.insert(user);
        //mNameET.setText("");

        mUserList.clear();
        mUserList.addAll(userDao.queryBuilder().build().list());
        mUserAdapter.notifyDataSetChanged();
    }
    /**
     * 根据名字更新某条数据的名字
     * @param prevName  原名字
     * @param newName  新名字
     */
    private void updateUser(String prevName,String newName){
        User findUser = GreenDaoManager.getInstance().getSession().getUserDao().queryBuilder()
                .where(UserDao.Properties.Name.eq(prevName)).build().unique();
        if(findUser != null) {
            findUser.setName(newName);
            GreenDaoManager.getInstance().getSession().getUserDao().update(findUser);
            Toast.makeText(MyApplication.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MyApplication.getContext(), "用户不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 根据id删除某用户(根据id查找到用户，并通过id删除)
     * @param id
     */
    private void deleteUser(long id){
        UserDao userDao = GreenDaoManager.getInstance().getSession().getUserDao();
        User findUser = userDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).build().unique();
        if(findUser != null){
            userDao.deleteByKey(findUser.getId());
            mUserList.clear();
            mUserList.addAll(userDao.queryBuilder().build().list());
            mUserAdapter.notifyDataSetChanged();

        }
    }



    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.btn_add:
                Toast.makeText(MainActivity.this, "sss", Toast.LENGTH_SHORT).show();
                insertUser(null, mNameET.getText().toString());
                break;
        }
    }
}

