package com.anywr.javasprintsecuritytest.Security;

public interface SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="dSgVkYp3s6v9y/B?E(H+MbQeThWmZq4t7w!z%C&F)J@NcRfUjXn2r5u8x/A?D(G-";
    public static final long EXPIRATION=1*24*3600*1000;
    public static final String HEADER_PREFIX="Bearer ";

}
