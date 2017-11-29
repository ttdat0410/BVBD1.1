package vn.vnpt.ansv.bvbd11.utils;

/**
 * Created by ANSV on 11/29/2017.
 */

public class BvbdPreference {

    private String ip;
    private String port;
    private String username;
    private String password;
    private String apikey;
    private String userid;
    private String roleId;

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
