package ink.scotty.cdd.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * (Pet)表实体类
 *
 * @author Scott
 * @since 2020-04-21 22:40:55
 */
@SuppressWarnings("serial")
public class Pet extends Model<Pet> {
    @TableId(type = IdType.AUTO)
    //主键
    private Long id;
    //用户ID
    private Long userId;
    //昵称
    private String nickname;
    //性别（0：公，1：母）
    private Integer gender;
    //品种
    private String species;
    //宠物头像
    private String avatar;
    //介绍
    private String introduction;
    //生日
    private Date birthday;
    //当前体重
    private Double weight;
    //总消费
    private Double totalCost;
    //照片数量
    private Integer photoNumber;
    //日记数量
    private Integer diaryNumber;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getPhotoNumber() {
        return photoNumber;
    }

    public void setPhotoNumber(Integer photoNumber) {
        this.photoNumber = photoNumber;
    }

    public Integer getDiaryNumber() {
        return diaryNumber;
    }

    public void setDiaryNumber(Integer diaryNumber) {
        this.diaryNumber = diaryNumber;
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