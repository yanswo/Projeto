package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;

public class RoundedBorder implements Border {
    private int radius; // Raio da borda arredondada

    public RoundedBorder(int radius) {
        this.radius = radius;  // Inicializa o raio da borda arredondada
    }

    @Override
    public Insets getBorderInsets(java.awt.Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);  // Ajuste da borda
    }

    @Override
    public boolean isBorderOpaque() {
        return true;  // A borda será opaca
    }

    @Override
    public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
        // Define a cor da borda
        g.setColor(Color.GRAY);  // Aqui você pode ajustar a cor
        g.fillRoundRect(x, y, width - 1, height - 1, radius, radius);  // Desenha o retângulo com bordas arredondadas
    }
}
