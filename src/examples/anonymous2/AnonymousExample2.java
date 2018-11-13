package examples.anonymous2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnonymousExample2 {

    public static void main(String[] args) {
        new AnonymousExample2();
    }

    public AnonymousExample2() {
        JButton button = new JButton("change color");
        // 1. - anonymous class, ActionListener is interface
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("action 1");
            }
        });
        // 2. - same as 1., using lambda expression
        button.addActionListener(e -> System.out.println("action 2"));
        // 3.
        button.addActionListener(new MyActionListener());

        // run
        button.doClick();
    }

    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("action 3,4");
        }
    }

}
