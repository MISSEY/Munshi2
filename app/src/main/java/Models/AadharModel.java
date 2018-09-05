package Models;

/**
 * Created by Saurabh on 08-05-2018.
 */

public class AadharModel {
    public String mobile_no;
    public String name;
    public String dob;

    public AadharModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public AadharModel(String mobile_no, String name,String dob) {
        this.mobile_no = mobile_no;
        this.name = name;
        this.dob = dob;
    }
}
