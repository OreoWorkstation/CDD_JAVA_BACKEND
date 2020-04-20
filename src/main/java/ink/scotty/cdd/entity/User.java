package ink.scotty.cdd.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * (User)表实体类
 *
 * @author Scott
 * @since 2020-04-20 18:51:49
 */
@SuppressWarnings("serial")
public class User extends Model<User> {
    @TableId(type = IdType.AUTO)
    //主键
    private Long id;
    //账号
    private String account;
    //密码
    private String password;
    //昵称
    private String nickname;
    //性别（0：男性，1：女性）
    private Integer gender;
    //头像
    private String avatar;
    //个人介绍
    private String introduction;
    //邮箱
    private String email;
    //手机号
    private String phone;
    //地址
    private String address;
    //生日
    private Date birthday;
    //宠物数量
    private Integer petNumber;
    //瞬间数量
    private Integer instantNumber;
    //关注数量
    private Integer followNumber;
    //粉丝数量
    private Integer fansNumber;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getPetNumber() {
        return petNumber;
    }

    public void setPetNumber(Integer petNumber) {
        this.petNumber = petNumber;
    }

    public Integer getInstantNumber() {
        return instantNumber;
    }

    public void setInstantNumber(Integer instantNumber) {
        this.instantNumber = instantNumber;
    }

    public Integer getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(Integer followNumber) {
        this.followNumber = followNumber;
    }

    public Integer getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    
}