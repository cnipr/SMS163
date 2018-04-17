package com.sms;

import java.security.MessageDigest;
import java.util.Random;
/**
 * end+1
 * @author weiding
 *
 */
public class CheckSumBuilder {
	private static  Random random = new Random();
	public static void main(String[] args) {
		String appKey = "aa208ab202afb58a6724d6ff13e188c3";
		
		String checksum = getCheckSum();
		System.out.println(checksum);
	}

	private static String getCheckSum() {
		String appSecret = "46c2db6ce68c";
		String nonce = random.nextInt() + "";
		String curTime = System.currentTimeMillis() / 1000 + "";		
		String checksum = getCheckSum(appSecret, nonce, curTime);
		return checksum;
	}
	
    // 计算并获取CheckSum
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    // 计算并获取md5值
    public static String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}