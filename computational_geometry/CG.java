class CG{
    class Point {
        double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }   
    }
    class Vector {
        double x, y;
        Vector(double x, double y) {
            this.x = x;
            this.y = y;
        }
        Vector(Point p1, Point p2) {
            x = p2.x - p1.x;
            y = p2.y - p1.y;
        }
        double getLength() {
            return Math.sqrt(x * x + y * y);
        }
    }
    //get counter-clock wise angle in range [0, 360)
    double getAngle(Vector v1, Vector v2) {
        double cos = (v1.x * v2.x + v1.y * v2.y) / v1.getLength() / v2.getLength();
        cos = cos > 1 ? 1 : cos < -1 ? -1 : cos;
        double angle = Math.toDegrees(Math.acos(cos));
        return v1.x * v2.y - v1.y * v2.x > -1e-9 ? angle : 360.0 - angle;
    }
    Point[] getConvexHull(Point[] ps) {
        if (ps.length <= 2)
            return ps;
        int bp = 0;
        for (int i = 1; i < ps.length; i++)
            if (ps[i].y < ps[bp].y)
                bp = i;
            else if (Math.abs(ps[i].y - ps[bp].y) < 1e-9 && ps[i].x <= ps[bp].x)
                bp = i;
        Point tmp = ps[0];
        ps[0] = ps[bp];
        ps[bp] = tmp;
        Arrays.sort(ps, 1, ps.length, (Point p1, Point p2) -> {
            Vector v0 = new Vector(1.0, 0);
            Vector v1 = new Vector(ps[0], p1);
            Vector v2 = new Vector(ps[0], p2);
            double a1 = getAngle(v0, v1), a2 = getAngle(v0, v2);
            if (Math.abs(a1 - a2) < 1e-9)
                return Double.compare(v1.getLength(), v2.getLength());
            return Double.compare(a1, a2);
        });
        ArrayList<Point> arr = new ArrayList<>();
        arr.add(ps[0]);
        arr.add(ps[1]);
        for (int i = 2; i < ps.length; i++) {
            while (arr.size() >= 2) {
                Point p1 = arr.get(arr.size() - 2), p2 = arr.get(arr.size() - 1), p3 = ps[i];
                Vector v1 = new Vector(p1, p2), v2 = new Vector(p2, p3);
                double angle = getAngle(v1, v2);
                if (angle < 180 + 1e-9) //to ignore in convex hull point, add "&& angle < 1e-5"
                    break;
                arr.remove(arr.size() - 1);
            }
            arr.add(ps[i]);
        }
        //to ignore in convex hull point, comment the next "for" block
        for (int i = 1; i < ps.length - 1; i++) {
            Point p0 = arr.get(0), p1 = arr.get(1), p2 = arr.get(arr.size() - 1), p = ps[i];
            Vector v0 = new Vector(p0, p1), vn = new Vector(p0, p2), v = new Vector(p0, p);
            double a0 = getAngle(v0, v), a = getAngle(vn, v);
            if (Math.abs(a0 - a) > 1e-9 && a < 1e-5)
                arr.add(ps[i]);
        }
        Point[] ret = new Point[arr.size()];
        for (int i = 0; i < arr.size(); i++)
            ret[i] = arr.get(i);
        return ret;
    }
}
