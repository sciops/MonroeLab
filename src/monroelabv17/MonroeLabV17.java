/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monroelabv17;

import com.google.bitcoin.core.ECKey;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Stephen R. Williams (c) 2014
 */
public class MonroeLabV17 {

    public static void main(String[] args) throws MalformedURLException, IOException {
        //BCI bci = new BCI();
        //address Group B Strep 1G85cQXETuGeMGvniv5SuMACegCR6L51A5
        //long response = bci.addressBalance("1G85cQXETuGeMGvniv5SuMACegCR6L51A5");
        //long response = bci.walletBalance();
        //String response = bci.payment("15dw3sAZL1kfPAznkVHEvkdte9fJj9w9YX",10000);
        /*sendmany test
         BoxKey bk1 = new BoxKey(1,"ff9A038729F44654F9577C698C25F43BBDDC1BA86354F3F07E90DC134CE17436C2", 100, 10000);//5Jz7f3sH9UQoMLQ37MZ1EyLf7NTnewEfQqasStMgLpLCQ3bmBSh
         BoxKey bk2 = new BoxKey(1,"ff0C85A3C5EC7C01551E571ECCD6DB1644EB7FD852DB7683B136C8C1F043926367", 200, 10000);//5HuoSUJ8b1B5gfpPCMhBpE9DXEz3p8V3vYgVeRodvEjymVZK5hj
         List<BoxKey> bks = new ArrayList();
         bks.add(bk1);
         bks.add(bk2);
         */
        //double response = bci.rateCheck();
        //JOptionPane.showMessageDialog(null, response);
        LabJFrame lbj = new LabJFrame();
        lbj.setVisible(true);
    }
}
