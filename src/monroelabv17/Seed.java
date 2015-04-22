/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monroelabv17;

/**
 *
 * @author Stephen R. Williams (c) 2014
 */
public class Seed {
    private byte[] seed;//combined seed array
    //seed components
    private byte[] serialNo;//identity of machine. start with "ff" byte to avoid rounding issues
    private byte[] operatorNo;//operator of machine
    private byte[] gpsHeading;//future implementation
    private byte[] gpsLocX;//future implementation
    private byte[] gpsLocY;//future implementation
    private byte[] cryptoCurrencyType;//BTC
    private byte[] fiatCurrencyType;//USD
    private byte[] denom;//fiat denomination
    private byte[] utcDiv60;//box generation time divided by 60 for one key per minute

    Seed() {
        
    }
    
    Seed(byte[] seed_b) {
        this.seed = seed_b;
        //TODO: set all the components
    }

    public byte[] getSeed() {
        this.seed = new byte[32];
        for (int i = 0; i <= 12; i++) {
            seed[i] = serialNo[i];
        }
        for (int i = 13; i <= 15; i++) {
            seed[i] = operatorNo[i - 13];
        }
        for (int i = 16; i <= 16; i++) {
            seed[i] = gpsHeading[i - 16];
        }
        for (int i = 17; i <= 19; i++) {
            seed[i] = gpsLocX[i - 17];
        }
        for (int i = 20; i <= 22; i++) {
            seed[i] = gpsLocY[i - 20];
        }
        for (int i = 23; i <= 24; i++) {
            seed[i] = cryptoCurrencyType[i - 23];
        }
        for (int i = 25; i <= 26; i++) {
            seed[i] = fiatCurrencyType[i - 25];
        }
        for (int i = 27; i <= 27; i++) {
            seed[i] = denom[i - 27];
        }
        for (int i = 28; i <= 31; i++) {
            seed[i] = utcDiv60[i - 28];
        }
        return this.seed;
    }

    public byte[] getSeed(byte[] serialNo, byte[] operatorNo, byte[] gpsHeading, byte[] gpsLocX, byte[] gpsLocY, byte[] cryptoCurrencyType, byte[] fiatCurrencyType, byte[] denom, byte[] utcDiv60) {
        this.serialNo = serialNo;
        this.operatorNo = operatorNo;
        this.gpsHeading = gpsHeading;
        this.gpsLocX = gpsLocX;
        this.gpsLocY = gpsLocY;
        this.cryptoCurrencyType = cryptoCurrencyType;
        this.fiatCurrencyType = fiatCurrencyType;
        this.denom = denom;
        this.utcDiv60 = utcDiv60;

        for (int i = 0; i <= 12; i++) {
            this.seed[i] = serialNo[i];
        }
        for (int i = 13; i <= 15; i++) {
            this.seed[i] = operatorNo[i - 13];
        }
        for (int i = 16; i <= 16; i++) {
            this.seed[i] = gpsHeading[i - 16];
        }
        for (int i = 17; i <= 19; i++) {
            this.seed[i] = gpsLocX[i - 17];
        }
        for (int i = 20; i <= 22; i++) {
            this.seed[i] = gpsLocY[i - 20];
        }
        for (int i = 23; i <= 24; i++) {
            this.seed[i] = cryptoCurrencyType[i - 23];
        }
        for (int i = 25; i <= 26; i++) {
            this.seed[i] = fiatCurrencyType[i - 25];
        }
        for (int i = 27; i <= 27; i++) {
            this.seed[i] = denom[i - 27];
        }
        for (int i = 28; i <= 31; i++) {
            this.seed[i] = utcDiv60[i - 28];
        }
        return this.seed;
    }

    public byte[] getSeed(String serialNo_s, String operatorNo_s, String gpsHeading_s, String gpsLocX_s, String gpsLocY_s, int cryptoCurrencyType_int, int fiatCurrencyType_int, int denom_int, long utcDiv60_L) {
        this.serialNo = B58.hexStringToByteArray(serialNo_s);
        this.operatorNo = B58.hexStringToByteArray(operatorNo_s);
        this.gpsHeading = B58.hexStringToByteArray(gpsHeading_s);
        this.gpsLocX = B58.hexStringToByteArray(gpsLocX_s);
        this.gpsLocY = B58.hexStringToByteArray(gpsLocY_s);
        byte[] temp = null;
        temp[0] = (byte) cryptoCurrencyType_int;
        this.cryptoCurrencyType = temp;
        temp[0] = (byte) fiatCurrencyType_int;
        this.fiatCurrencyType = temp;
        temp[0] = (byte) denom_int;
        this.denom = temp;
        temp = B58.toByteArray(utcDiv60_L);
        this.utcDiv60 = temp;

        for (int i = 0; i <= 12; i++) {
            this.seed[i] = this.serialNo[i];
        }
        for (int i = 13; i <= 15; i++) {
            this.seed[i] = this.operatorNo[i - 13];
        }
        for (int i = 16; i <= 16; i++) {
            this.seed[i] = this.gpsHeading[i - 16];
        }
        for (int i = 17; i <= 19; i++) {
            this.seed[i] = this.gpsLocX[i - 17];
        }
        for (int i = 20; i <= 22; i++) {
            this.seed[i] = this.gpsLocY[i - 20];
        }
        for (int i = 23; i <= 24; i++) {
            this.seed[i] = this.cryptoCurrencyType[i - 23];
        }
        for (int i = 25; i <= 26; i++) {
            this.seed[i] = this.fiatCurrencyType[i - 25];
        }
        for (int i = 27; i <= 27; i++) {
            this.seed[i] = this.denom[i - 27];
        }
        for (int i = 28; i <= 31; i++) {
            this.seed[i] = this.utcDiv60[i - 28];
        }
        return this.seed;
    }

    public byte[] getSerialNo() {
        return serialNo;
    }

    public byte[] getOperatorNo() {
        return operatorNo;
    }

    public byte[] getGpsHeading() {
        return gpsHeading;
    }

    public byte[] getGpsLocX() {
        return gpsLocX;
    }

    public byte[] getGpsLocY() {
        return gpsLocY;
    }

    public byte[] getCryptoCurrencyType() {
        return cryptoCurrencyType;
    }

    public byte[] getFiatCurrencyType() {
        return fiatCurrencyType;
    }

    public byte[] getDenom() {
        return denom;
    }

    public byte[] getUtcDiv60() {
        return utcDiv60;
    }

    public void setSerialNo(byte[] serialNo) {
        this.serialNo = serialNo;
    }

    public void setOperatorNo(byte[] operatorNo) {
        this.operatorNo = operatorNo;
    }

    public void setGpsHeading(byte[] gpsHeading) {
        this.gpsHeading = gpsHeading;
    }

    public void setGpsLocX(byte[] gpsLocX) {
        this.gpsLocX = gpsLocX;
    }

    public void setGpsLocY(byte[] gpsLocY) {
        this.gpsLocY = gpsLocY;
    }

    public void setCryptoCurrencyType(byte[] cryptoCurrencyType) {
        this.cryptoCurrencyType = cryptoCurrencyType;
    }

    public void setFiatCurrencyType(byte[] fiatCurrencyType) {
        this.fiatCurrencyType = fiatCurrencyType;
    }

    public void setDenom(byte[] denom) {
        this.denom = denom;
    }

    public void setDenom(byte denom) {
        byte[] temp = new byte[1];
        temp[0] = denom;
        this.denom = temp;
    }

    public void setUtcDiv60(byte[] utcDiv60) {
        this.utcDiv60 = utcDiv60;
    }

    public void setUtcDiv60(long utvDiv60) {
        byte[] temp = new byte[4];
        Bitwise.putInt((int) utvDiv60, temp, 0);
        this.utcDiv60 = temp;
    }

}
