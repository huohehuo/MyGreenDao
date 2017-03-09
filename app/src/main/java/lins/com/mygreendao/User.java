package lins.com.mygreendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by LINS on 2017/3/7.
 */

@Entity//添加这个注解，用于让GreenDao识别
public class User {
    @Id//用于让GreenDao识别此字段，默认为自增
    private Long id;
    private String name;
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
    @Generated(hash = 873297011)
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 586692638)
    public User() {
    }


}
