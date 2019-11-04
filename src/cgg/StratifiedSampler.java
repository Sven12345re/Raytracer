package cgg;
import cgtools.*;

import static cgtools.Vector.color;

public class StratifiedSampler implements Sampler {
    private Sampler sampler;
    private int n;

    public StratifiedSampler(Sampler sampler, int n) {
        this.sampler = sampler;
        this.n = (int) Math.ceil(Math.sqrt(n));
    }

    public Color getColor(double x, double y) {
        Color color = color(0,0,0);

        for (int xi = 0; xi < n; xi++) {
            for (int yi = 0; yi < n; yi++) {
                double rx = Math.random();
                double ry = Math.random();
                double xs = x + (xi + rx) / n;
                double ys = y + (yi + ry) / n;
                color = Color.add(color, sampler.getColor(xs, ys));
            }
        }
        return Color.divide(color, n * n);
    }
}
