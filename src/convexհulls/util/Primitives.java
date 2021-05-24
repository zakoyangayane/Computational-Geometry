package convexհulls.util;

import java.awt.*;

public class Primitives {

    public static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

        if (val == 0)
            return 0;

        return (val > 0) ? 1 : 2;
    }
}
