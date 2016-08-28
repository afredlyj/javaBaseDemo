package afred.javademo.dozer;

/**
 * Created by winnie on 2015-03-26 .
 */
import java.util.Date;

public class Info {
    private int id;
    private Date createDate;
    private GenderType gender;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public GenderType getGender() {
        return gender;
    }
    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", gender=" + gender +
                '}';
    }
}