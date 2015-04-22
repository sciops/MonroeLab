/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monroelabv17;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.bitcoin.core.ECKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author home
 */
public class BCI {

    private String identifier;
    private String password;
    private String secondPassword;

    public BCI() {
        this.identifier = "1887271c-ed5d-4c31-971e-aefcb8d83762";
        this.password = "adafretvert";
        this.secondPassword = "september";
    }

    public BCI(String identifier, String password, String secondPassword) {
        this.identifier = identifier;
        this.password = password;
        this.secondPassword = secondPassword;
    }

    public long addressBalance(String address) throws IOException {
        /* Getting the balance of an address
         Retrieve the balance of a bitcoin address. Querying the balance of an address by label is depreciated.
         https://blockchain.info/merchant/$guid/address_balance?password=$main_password&address=$address&confirmations=$confirmations
         */
        String uri = "https://blockchain.info/merchant/" + identifier + "/address_balance?password=" + password + "&address=" + address + "&confirmations=1";
        String result = bciCall(uri);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jn = mapper.readTree(result);
        JsonNode balanceNode = jn.path("balance");
        long balance = balanceNode.asLong();
        return balance;
    }

    public long walletBalance() throws IOException {
        /* Fetching the wallet balance
         Fetch the balance of a wallet. This should be used as an estimate only and will include unconfirmed transactions and possibly double spends.
         https://blockchain.info/merchant/$guid/balance?password=$main_password
         */
        String uri = "https://blockchain.info/merchant/" + identifier + "/balance?password=" + password;
        String result = bciCall(uri);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jn = mapper.readTree(result);
        JsonNode balanceNode = jn.path("balance");
        long balance = balanceNode.asLong();
        return balance;
    }

    public String listAddresses() throws IOException {
        /* Listing Addresses
         List all active addresses in a wallet. Also includes a 0 confirmation balance which should be used as an estimate only and will include unconfirmed transactions and possibly double spends.
         https://blockchain.info/merchant/$guid/list?password=$main_password
         */
        String uri = "https://blockchain.info/merchant/" + identifier + "/list?password=" + password;
        String result = bciCall(uri);
        return result;
    }

    public String newAddress() throws IOException {
        /* Generating a new address
         https://blockchain.info/merchant/$guid/new_address?password=$main_password&second_password=$second_password&label=$label
         */
        String uri = "https://blockchain.info/merchant/" + identifier + "/new_address?password=" + password + "&second_password=" + secondPassword;
        String result = bciCall(uri);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jn = mapper.readTree(result);
        JsonNode addressNode = jn.path("address");
        String address = addressNode.asText();
        return address;
    }

    public String payment(String address, long amount) throws IOException {
        /*
         Making Outgoing Payments
         Send bitcoin from your wallet to another bitcoin address. All transactions include a 0.0001 BTC miners fee.
         https://blockchain.info/merchant/$guid/payment?password=$main_password&second_password=$second_password&to=$address&amount=$amount&from=$from&shared=$shared&fee=$fee&note=$note
         */
        String uri
                = "https://blockchain.info/merchant/" + identifier
                + "/payment?password=" + password
                + "&second_password=" + secondPassword
                + "&to=" + address
                + "&amount=" + amount
                + "&note=" + "http://monroebox.wordpress.com/";
        String result = bciCall(uri);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jn = mapper.readTree(result);
        JsonNode messageNode = jn.path("message");
        String message = messageNode.asText();
        return message;
    }

    public String payment(BoxKey bk) throws IOException {
        /*
         Making Outgoing Payments
         Send bitcoin from your wallet to another bitcoin address. All transactions include a 0.0001 BTC miners fee.
         https://blockchain.info/merchant/$guid/payment?password=$main_password&second_password=$second_password&to=$address&amount=$amount&from=$from&shared=$shared&fee=$fee&note=$note
         */
        ECKey key = bk.getKey();
        String address = B58.encodeMainNetAddr(key.getPubKeyHash());
        long amount = bk.getTargetBalance() - bk.getCurrentBalance();
        String uri
                = "https://blockchain.info/merchant/" + identifier
                + "/payment?password=" + password
                + "&second_password=" + secondPassword
                + "&to=" + address
                + "&amount=" + amount
                + "&note=" + "http://monroebox.wordpress.com/";
        String result = bciCall(uri);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jn = mapper.readTree(result);
        JsonNode messageNode = jn.path("message");
        String message = messageNode.asText();
        return message;
    }

    public String sendmany(List<BoxKey> bks) throws IOException {
        /*
         Send Many Transactions
         Send a transaction to multiple recipients in the same transaction.
         https://blockchain.info/merchant/$guid/sendmany?password=$main_password&second_password=$second_password&recipients=$recipients&shared=$shared&fee=$fee
         */
        String uri
                = "https://blockchain.info/merchant/" + identifier
                + "/sendmany?password=" + password
                + "&second_password=" + secondPassword
                + "&recipients={";
        boolean firstOne = true;
        for (BoxKey bk : bks) {
            if (firstOne == false) {
                uri += ",";
            } else {
                firstOne = false;
            }
            String address = B58.encodeMainNetAddr(bk.getKey().getPubKeyHash());
            long amount = bk.getTargetBalance() - bk.getCurrentBalance();
            uri += "\"" + address + "\":" + amount;
        }
        uri += "}&note=" + "http://monroebox.wordpress.com/";
        System.out.println("uri: " + uri);
        String result = bciCall(uri);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jn = mapper.readTree(result);
        JsonNode messageNode = jn.path("message");
        String message = messageNode.asText();
        return message;
    }

    public String sendmany(List<String> addresses, long amount) throws IOException {
        /*
         Send Many Transactions
         Send a transaction to multiple recipients in the same transaction.
         https://blockchain.info/merchant/$guid/sendmany?password=$main_password&second_password=$second_password&recipients=$recipients&shared=$shared&fee=$fee
         */
        String uri
                = "https://blockchain.info/merchant/" + identifier
                + "/sendmany?password=" + password
                + "&second_password=" + secondPassword
                + "&recipients={";
        boolean firstOne = true;
        for (String address : addresses) {
            if (firstOne == false) {
                uri += ",";
            } else {
                firstOne = false;
            }
            uri += "\"" + address + "\":" + amount;
        }
        uri += "}&note=" + "http://monroebox.wordpress.com/";
        System.out.println("uri: " + uri);
        String result = bciCall(uri);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jn = mapper.readTree(result);
        JsonNode messageNode = jn.path("message");
        String message = messageNode.asText();
        return message;
    }
    
    public double rateCheck() throws IOException {
        //https://blockchain.info/ticker
        //https://blockchain.info/q/24hrprice
        String result = bciCall("https://blockchain.info/q/24hrprice");
        double rate = Double.parseDouble(result);
        return rate;
    }

    public String bciCall(String request) throws MalformedURLException, IOException {
        URL url = new URL(request);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        InputStream ins = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in = new BufferedReader(isr);
        String inputLine, response = null;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response = inputLine;
        }
        in.close();
        return response;
    }

}
