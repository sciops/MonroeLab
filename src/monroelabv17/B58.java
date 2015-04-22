/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monroelabv17;

import com.google.bitcoin.core.Base58;
import java.math.BigInteger;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author home
 */
public class B58 {

    public static void main(String[] args) {

        byte[] test = hexStringToByteArray("E9873D79C6D87DC0FB6A5778633389F4453213303DA61F20BD67FC233AA33262");
        //JOptionPane.showMessageDialog(null, encode(test));
        System.out.println("Result: " + encodePrivKey(test));
    }

    public static String encodePrivKey(byte[] toEncode) {
        byte[] step1 = new byte[33];
        //step1[0] = 0;//mainnet
        //step1[0] = 111;//testnet
        step1[0] = -128;

        for (int i = 1; i <= 32; i++) {
            step1[i] = toEncode[i - 1];
        }

        byte[] step2 = new byte[4];
        byte[] step2a = DigestUtils.sha256(DigestUtils.sha256(step1));
        step2[0] = step2a[0];
        step2[1] = step2a[1];
        step2[2] = step2a[2];
        step2[3] = step2a[3];

        byte[] step3 = new byte[37];
        for (int i = 0; i <= 32; i++) {
            step3[i] = step1[i];
        }
        step3[33] = step2[0];
        step3[34] = step2[1];
        step3[35] = step2[2];
        step3[36] = step2[3];

        String output = Base58.encode(step3);
        return output;
    }

    public static String encodeMainNetAddr(byte[] toEncode) {
        byte[] step1 = new byte[21];
        step1[0] = 0;//mainnet
        //step1[0] = 111;//testnet

        for (int i = 1; i <= 20; i++) {
            step1[i] = toEncode[i - 1];
        }

        byte[] step2 = new byte[4];
        byte[] step2a = DigestUtils.sha256(DigestUtils.sha256(step1));
        step2[0] = step2a[0];
        step2[1] = step2a[1];
        step2[2] = step2a[2];
        step2[3] = step2a[3];

        byte[] step3 = new byte[25];
        for (int i = 0; i <= 20; i++) {
            step3[i] = step1[i];
        }
        step3[21] = step2[0];
        step3[22] = step2[1];
        step3[23] = step2[2];
        step3[24] = step2[3];

        String output = Base58.encode(step3);
        return output;
    }

    public static String encodeTestNetAddr(byte[] toEncode) {
        byte[] step1 = new byte[21];
        //step1[0] = 0;//mainnet
        step1[0] = 111;//testnet

        for (int i = 1; i <= 20; i++) {
            step1[i] = toEncode[i - 1];
        }

        byte[] step2 = new byte[4];
        byte[] step2a = DigestUtils.sha256(DigestUtils.sha256(step1));

        step2[0] = step2a[0];
        step2[1] = step2a[1];
        step2[2] = step2a[2];
        step2[3] = step2a[3];

        byte[] step3 = new byte[25];
        for (int i = 0; i <= 20; i++) {
            step3[i] = step1[i];
        }
        step3[21] = step2[0];
        step3[22] = step2[1];
        step3[23] = step2[2];
        step3[24] = step2[3];

        String output = Base58.encode(step3);
        /*
         BigInteger biggy = new BigInteger(step3);
         byte[] fiftyEight_ba = {58};
         BigInteger fiftyEight = new BigInteger(fiftyEight_ba);
         String alphabet = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
         String output = "1";
         int r=0;
         while (biggy.signum()==1){
         r = (biggy.mod(fiftyEight)).intValue();
         biggy = biggy.divide(fiftyEight);
         output += alphabet.charAt(r);
         }
         */

        return output;
    }

    public static byte[] toByteArray(long value) {
        byte[] ret = new byte[4];
        ret[3] = (byte) (value >> (0 * 8) & 0xFF);
        ret[2] = (byte) (value >> (1 * 8) & 0xFF);
        ret[1] = (byte) (value >> (2 * 8) & 0xFF);
        ret[0] = (byte) (value >> (3 * 8) & 0xFF);
        return ret;
    }

    public static String convertToHexString(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
