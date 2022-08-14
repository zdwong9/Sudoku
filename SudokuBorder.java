import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;

public class SudokuBorder extends AbstractBorder {
    private boolean both;
    private BorderEnum borderEnum;

    public SudokuBorder(BorderEnum... be) {
        if (be.length > 1) {
            both = true;
        } else if (be.length != 0) {
            borderEnum = be[0];
        }
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = null;
        if (g instanceof Graphics2D) {
            g2d = (Graphics2D) g;
            g2d.setColor(Color.gray);
            x = 0;
            y = 0;
            // Top Border
            g2d.draw(new Line2D.Double((double) x, (double) y, (double) width, (double) y));

            // Left Border
            g2d.draw(new Line2D.Double((double) x, (double) y, (double) x, (double) height));
            g2d.setColor(Color.RED);

            if (borderEnum == BorderEnum.RIGHT && both == false) {

                // Right Border
                g2d.draw(new Line2D.Double((double) width - 1, (double) 0, (double) width - 1,
                        (double) height));

            } else if (borderEnum == BorderEnum.BOTTOM && both == false) {
                g2d.draw(new Line2D.Double((double) 0, (double) height - 1, (double) width,
                        (double) height - 1));

            } else if (both == true) {
                g2d.draw(new Line2D.Double((double) 0, (double) height - 1, (double) width,
                        (double) height - 1));
                g2d.draw(new Line2D.Double((double) width - 1, (double) 0, (double) width - 1,
                        (double) height));

            }

        }
    }

}