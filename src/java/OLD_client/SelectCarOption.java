/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OLD_client;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import OLD_model.Automobile;
import OLD_model.OptionSet;

/**
 *
 * @author keerthanathangaraju
 */
public class SelectCarOption {
    
    public void display(Automobile auto) {
        
        
        JTextField model = new JTextField(auto.getName());
        JTextField make = new JTextField(auto.getMake());
        JTextField basePrice = new JTextField(auto.getBasePrice()+"");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Model"));
        panel.add(model);
        panel.add(new JLabel("Make"));
        panel.add(make);
        panel.add(new JLabel("Base Price"));
        panel.add(basePrice);
        HashMap<String,String[]> opset = auto.getOptionsAsStringArray();
        Iterator<String> it = opset.keySet().iterator();
        
        while(it.hasNext()){
            String name = it.next();
             panel.add(new JLabel(name));
             JComboBox combo = new JComboBox(opset.get(name));
             panel.add(combo);

        }
        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//        if (result == JOptionPane.OK_OPTION) {
//            System.out.println(combo.getSelectedItem()
//                    + " " + field1.getText()
//                    + " " + field2.getText());
//        } else {
//            System.out.println("Cancelled");
//        }
    }
}
