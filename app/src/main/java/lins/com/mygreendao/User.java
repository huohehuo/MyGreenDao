package lins.com.mygreendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by LINS on 2017/3/7.
 * 此处只要加了Entity这些注解，项目中的gen文件夹就会自动生成，并生成相应的类（仅此为自动生成）
 */

@Entity//添加这个注解，用于让GreenDao识别
public class User {
    @Id//用于让GreenDao识别此字段，默认为自增
    private Long id;
    private String name;
    private String sex;
    @Transient//用于识别该字段不添加进数据库
    private int tempUsageCount;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Generated(hash = 1265202627)
    public User(Long id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
    @Generated(hash = 586692638)
    public User() {
    }


}
