package vn.vnpt.ansv.bvbd11.utils;

/**
 * Created by ANSV on 11/29/2017.
 */

public class BvbdPreference {

    public String ip;
    public String port;
    public String username;
    public String password;
    public String apikey;
    public String userid;
    public String roleId;

    public BvbdPreference() {

    }

    @Override
    public String toString() {
        return String.format(
                "ip: %s, port: %s, username: 5s, password: %s, apikey: %s, userid: %s, roleid: %s",
                ip, port, username, password, apikey, userid, roleId
        );
    }
}
