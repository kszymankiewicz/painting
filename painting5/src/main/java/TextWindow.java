import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextWindow extends JFrame {
    public TextWindow() {
        initComponents();
    }

    public void initComponents() {
        this.setTitle("Przeszukiwacz Tekstu");
        this.setBounds(300, 300, 300, 200);

        panelSzukania.add(znajdz);
        panelSzukania.add(szukany);
        panelSzukania.add(etykietaI);
        panelSzukania.add(zamien);
        panelSzukania.add(etykietaNa);
        panelSzukania.add(nowyTekst);

        //obszarTekstowy.selectAll(); - zaznacza cały tekst
        //obszarTekstowy.select(0, 2);// - zaznacza obszar, podany od kąd do kąd
        //obszarTekstowy.replaceSelection("lala"); - zamienić obszar, który jest zaznaczony
        //obszarTekstowy.replaceRange("lala", 0, 3); - range z ang. zasięg, podmieniamy na string z danego obszaru
        //obszarTekstowy.insert("jakis tam", 18);
        //obszarTekstowy.append("dolaczone do konca");
        //obszarTekstowy.select(obszarTekstowy.getText().indexOf("test"), obszarTekstowy.getText().indexOf("test")+"test".length());

        znajdz.addActionListener(new znajdowanieHandler());
        zamien.addActionListener(new zamienianieHandler());
        this.getContentPane().add(obszarPrzewijania, BorderLayout.NORTH);
        this.getContentPane().add(panelSzukania, BorderLayout.CENTER);

        this.setDefaultCloseOperation(3);
    }

    private class znajdowanieHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            poczatekSzukanego = obszarTekstowy.getText().indexOf(szukany.getText(), poczatekSzukanego + szukany.getText().length());

            System.out.println(poczatekSzukanego);
            if (poczatekSzukanego == -1)
                poczatekSzukanego = obszarTekstowy.getText().indexOf(szukany.getText());


            if (poczatekSzukanego >= 0 && szukany.getText().length() > 0) {
                obszarTekstowy.requestFocus();
                obszarTekstowy.select(poczatekSzukanego, poczatekSzukanego + szukany.getText().length());
            }


        }

        private int poczatekSzukanego = 0;
    }


    private class zamienianieHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (obszarTekstowy.getSelectionStart() != obszarTekstowy.getSelectionEnd()) {
                zamienTekst();

            } else {
                znajdz.doClick(0);
                if (obszarTekstowy.getSelectionStart() != obszarTekstowy.getSelectionEnd())
                    zamienTekst();
            }
        }

        private void zamienTekst() {
            obszarTekstowy.requestFocus();
            int opcja = JOptionPane.showConfirmDialog(rootPane, "Czy chcesz zamienić \"" + szukany.getText() + "\" na \"" + nowyTekst.getText() + "\"", "Uwaga, nastąpi zmiana!", JOptionPane.YES_NO_OPTION);
            if (opcja == 0)
                obszarTekstowy.replaceRange(nowyTekst.getText(), obszarTekstowy.getSelectionStart(), obszarTekstowy.getSelectionEnd());
        }
    }

    private JTextArea obszarTekstowy = new JTextArea("To jest testowy tekst o testowym charakterze ;)", 7, 10);
    private JScrollPane obszarPrzewijania = new JScrollPane(obszarTekstowy);

    private JPanel panelSzukania = new JPanel();
    private JButton znajdz = new JButton("Znajdź");
    private JLabel etykietaI = new JLabel("i");
    private JButton zamien = new JButton("Zamień");
    private JLabel etykietaNa = new JLabel("na");

    private JTextField szukany = new JTextField(4);
    private JTextField nowyTekst = new JTextField(4);

    public static void main(String[] args) {
        new TextWindow().setVisible(true);
    }

}
