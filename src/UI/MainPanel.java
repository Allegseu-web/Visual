package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements ActionListener,
        MouseListener, MouseMotionListener, KeyListener {
    int xpanel = 5000, ypanel = 5000;
    int Size = 25;
    int xwidth = xpanel / Size, yheight = ypanel / Size;
    boolean start = true;
    int[][] life = new int[xwidth][yheight];
    int[][] beforelife = new int[xwidth][yheight];
    Timer Tiempo;

    public MainPanel(){
        setLayout(null);
        setPreferredSize(new Dimension(xpanel, ypanel));
        setBackground(new Color(23,32,42));
        Tiempo = new Timer(100, this);
        Tiempo.start();
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(27,38,49));
        grid(g);
        /*Respaw(); ToDo hacer que exista una opcion de iniciar aleatorio*/
        Display(g);
    }

    public void grid(Graphics t){
        for (int i = 0; i < (xpanel / Size); i++){
            t.drawLine(0, i * Size, xpanel, i * Size);
            t.drawLine(i * Size, 0, i * Size, ypanel);
        }
    }

    public void Respaw(){
        if (start){
            for (int x = 0; x < xwidth; x++){
                for (int y = 0; y < yheight; y++){
                    if ((int) (Math.random() * 5) == 0){
                        beforelife[x][y] = 1;
                    }
                }
            }
            start = false;
        }
    }

    private void Display(Graphics o){
        o.setColor(new Color(17,122,101));
        CopiarArreglo();

        for (int x = 0; x < xwidth; x++){
            for (int y = 0; y < yheight; y++){
                if (beforelife[x][y] == 1) {
                    o.fillRect(x * Size+1, y * Size+1, Size-1, Size-1);
                }
            }
        }
    }

    private int Check(int x, int y){
        int esVivo = 0;

        esVivo += life[(x + xwidth - 1) % xwidth][(y + yheight - 1) % yheight];
        esVivo += life[(x + xwidth) % xwidth][(y + yheight - 1) % yheight];

        esVivo += life[(x + xwidth + 1) % xwidth][(y + yheight - 1) % yheight];
        esVivo += life[(x + xwidth - 1) % xwidth][(y + yheight) % xwidth];

        esVivo += life[(x + xwidth + 1) % xwidth][(y + yheight) % yheight];
        esVivo += life[(x + xwidth - 1) % xwidth][(y + yheight + 1) % yheight];

        esVivo += life[(x + xwidth) % xwidth][(y + yheight + 1) % yheight];
        esVivo += life[(x + xwidth + 1) % xwidth][(y + yheight + 1) % yheight];

        return esVivo;
    }

    private void CopiarArreglo(){
        for (int x = 0; x < xwidth; x++) {
            for (int y = 0; y < yheight; y++) { life[x][y] = beforelife[x][y]; }
        }
    }

    private void Limpiar(){
        for (int x = 0; x < xwidth; x++) {
            for (int y = 0; y < yheight; y++) { beforelife[x][y] = 0; }
        }
        start = true;
    }

    public void actionPerformed(ActionEvent e){
        int alive;
        for (int x = 0; x < xwidth; x++){
            for (int y = 0; y < yheight; y++){
                alive = Check(x,y);

                if (alive == 3){
                    beforelife[x][y] = 1;
                }
                else if (alive == 2 && life[x][y] == 1){
                    beforelife[x][y] = 1;
                }
                else {
                    beforelife[x][y] = 0;
                }
            }
        }
        repaint();
    }

    public void mouseDragged(MouseEvent D){
        start = false;
        int x = D.getX()/Size;
        int y = D.getY()/Size;

        if (life[x][y] == 0){ beforelife[x][y] = 1; }
        repaint();
    }

    public void mouseMoved(MouseEvent M){ }

    public void mouseClicked(MouseEvent C){ }

    public void mousePressed(MouseEvent P){ Tiempo.stop(); }

    public void mouseReleased(MouseEvent R){ Tiempo.start(); }

    public void mouseEntered(MouseEvent E){ }

    public void mouseExited(MouseEvent Ex){ }

    public void keyPressed(KeyEvent Pre){
        int Code = Pre.getKeyCode();
        if (Code == KeyEvent.VK_R){ Respaw(); }
        if (Code == KeyEvent.VK_SPACE){ Tiempo.stop(); }
        if (Code == KeyEvent.VK_C){ Limpiar(); }
        repaint();
    }

    public void keyTyped(KeyEvent T){

    }

    public void keyReleased(KeyEvent Re){
        int Code = Re.getKeyCode();
        if (Code == KeyEvent.VK_SPACE){ Tiempo.start(); }
    }
}
