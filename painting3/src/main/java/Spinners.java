import javax.swing.*;
import java.awt.*;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

public class Spinners extends JFrame {
    public Spinners() {
        initComponents();
    }

    public void initComponents() {
        this.setTitle("Spinners");
        this.setBounds(300, 300, 300, 200);

        String[] miesiace = new DateFormatSymbols().getMonths();
        int[] tab = new int[3];
        tab[0] = 1;
        tab[1] = 2;
        tab[2] = 42;

       /* for (int i = 0; i < miesiace.length; i++)
             System.out.println(miesiace[i]);*/
        MySpinnerListModel modelMiesiecy = new MySpinnerListModel(obetnijArray(miesiace, 0, 11));

        JSpinner spinner = new JSpinner(modelMiesiecy);
        // spinner.setEditor(new JTextArea("lala"));
        SpinnerNumberModel modelLiczb = new SpinnerNumberModel(1, 1, 5, 1);

        JSpinner spinner2 = new JSpinner(modelLiczb);

        Calendar kalendarz = Calendar.getInstance();

        Date poczatkowaWartosc = kalendarz.getTime();
        kalendarz.add(Calendar.DAY_OF_MONTH, -20);
        Date minimalnaWartosc = kalendarz.getTime();
        kalendarz.add(Calendar.DAY_OF_MONTH, 40);
        Date maksymalnaWartosc = kalendarz.getTime();

        SpinnerDateModel modelDat = new SpinnerDateModel(poczatkowaWartosc, minimalnaWartosc, maksymalnaWartosc, Calendar.DAY_OF_MONTH);

        JSpinner spinner3 = new JSpinner(modelDat);

        JPanel panel = new JPanel();
        panel.add(spinner);
        panel.add(spinner2);
        panel.add(spinner3);

        this.getContentPane().add(panel, BorderLayout.NORTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class MySpinnerListModel extends SpinnerListModel {

        public MySpinnerListModel(Object[] values) {
            super(values);
            firstValue = values[0];
            lastValue = values[values.length - 1];
        }

        @Override
        public Object getNextValue() {
            if (super.getNextValue() == null)
                return firstValue;
            else
                return super.getNextValue();
        }

        @Override
        public Object getPreviousValue() {
            if (super.getPreviousValue() == null)
                return lastValue;
            else
                return super.getPreviousValue();
        }

        Object firstValue, lastValue;
    }

    /**
     * obcina tablice od <code>poczatek</code> do <code>koniec</code>
     *
     * @param operowany tablica, która będzie operowana
     * @param poczatek  indeks od kąd zacząć obcinanie
     * @param koniec    indeks gdzie zakończyć obcinanie
     * @return zwraca obcięta tablicę (tmp)
     */
    private Object[] obetnijArray(Object[] operowany, int poczatek, int koniec) {
        koniec = koniec > operowany.length - 1 || koniec < 0 ? operowany.length - 1 : koniec;
        poczatek = poczatek < 0 || poczatek > koniec ? 0 : poczatek;

        Object[] tmp = new Object[koniec + 1 - poczatek];

        for (int i = poczatek, j = 0; i < koniec + 1; i++, j++)
            tmp[j] = operowany[i];

        return tmp;
    }

    public static void main(String[] args) {
        new Spinners().setVisible(true);
    }


}
