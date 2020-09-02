package UI;
import javax.swing.*;

public class VentanaMain extends JFrame{
    public JScrollPane Movimiento;
    public VentanaMain(){
        Movimiento = new JScrollPane();
        Movimiento.setSize(1300,600);
        Movimiento.setViewportView(new MainPanel());
        setTitle("Juego de la vida - John Horton Conway");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1300,600);
        setLocationRelativeTo(null);
        setResizable(true);
        add(Movimiento);
    }
}
