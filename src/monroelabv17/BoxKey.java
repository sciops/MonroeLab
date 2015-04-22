/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monroelabv17;

import com.google.bitcoin.core.ECKey;
import com.google.bitcoin.core.Utils;
import java.io.IOException;
import java.math.BigInteger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author home
 */
public class BoxKey {

    private ECKey key;
    private Seed seed;
    private int denom;
    private long currentBalance;//satoshis
    private long targetBalance;//satoshis

    public BoxKey(Seed seed, long targetBalance) throws IOException {
        this.seed = seed;
        this.targetBalance = targetBalance;
        this.denom = seed.getDenom()[0];
        byte[] digest = DigestUtils.sha256(seed.getSeed());
        //store private key in ECKey object
        BigInteger bigDig = new BigInteger(1, digest);
        ECKey ecKey = new ECKey(bigDig);
        ecKey.setCreationTimeSeconds(60 * Bitwise.getInt(seed.getUtcDiv60(), 0));//set creation time with utc of the timestamp
        this.key = ecKey;
        BCI bci = new BCI();
        currentBalance = bci.addressBalance(B58.encodeMainNetAddr(ecKey.getPubKeyHash()));
    }

    public BoxKey(String seed_s, long targetBalance) throws IOException {
        byte[] seed_b = B58.hexStringToByteArray(seed_s);
        this.denom = seed_b[27];
        this.targetBalance = targetBalance;
        byte[] digest = DigestUtils.sha256(seed_s);
        //store private key in ECKey object
        BigInteger bigDig = new BigInteger(1, digest);
        ECKey ecKey = new ECKey(bigDig);
        ecKey.setCreationTimeSeconds(Bitwise.getInt(seed_b, 28));//set creation time with utc of the timestamp
        this.key = ecKey;
        BCI bci = new BCI();
        currentBalance = bci.addressBalance(B58.encodeMainNetAddr(ecKey.getPubKeyHash()));
        this.seed = new Seed(seed_b);
    }

    public BoxKey(int denom, long unixtime, long targetBalance) throws IOException {
        this.denom = denom;
        this.targetBalance = targetBalance;
        BCI bci = new BCI();
        currentBalance = bci.addressBalance(B58.encodeMainNetAddr(key.getPubKeyHash()));
        //TODO: create seed and hash for key

    }

    public BoxKey(int denom, ECKey key, long targetBalance) throws IOException {
        this.denom = denom;
        this.key = key;
        this.targetBalance = targetBalance;
        BCI bci = new BCI();
        currentBalance = bci.addressBalance(B58.encodeMainNetAddr(key.getPubKeyHash()));
    }

    public BoxKey(int denom, String seed_s, long unixtime, long targetBalance) throws IOException {
        this.denom = denom;
        this.targetBalance = targetBalance;
        byte[] digest = DigestUtils.sha256(seed_s);
        //store private key in ECKey object
        BigInteger bigDig = new BigInteger(1, digest);
        ECKey ecKey = new ECKey(bigDig);
        ecKey.setCreationTimeSeconds(unixtime);//set creation time with utc of the timestamp
        this.key = ecKey;
        BCI bci = new BCI();
        currentBalance = bci.addressBalance(B58.encodeMainNetAddr(ecKey.getPubKeyHash()));
    }

    public String getAddress() {
        return B58.encodeMainNetAddr(key.getPubKeyHash());
    }

    public String getPrivateKey() {
        return B58.encodePrivKey(key.getPrivKeyBytes());
    }

    public ECKey getKey() {
        return key;
    }

    public void setKey(ECKey key) {
        this.key = key;
    }

    public int getDenom() {
        return denom;
    }

    public void setDenom(int denom) {
        this.denom = denom;
    }

    public long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(long currentBalance) {
        this.currentBalance = currentBalance;
    }

    public long getTargetBalance() {
        return targetBalance;
    }

    public void setTargetBalance(long targetBalance) {
        this.targetBalance = targetBalance;
    }

}
