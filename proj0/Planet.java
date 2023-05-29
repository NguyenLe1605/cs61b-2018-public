public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet P) {
        xxPos = P.xxPos;
        yyPos = P.yyPos;
        xxVel = P.xxVel;
        yyVel = P.yyVel;
        mass = P.mass;
        imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet other) {
        double dx = xxPos - other.xxPos;
        double dy = yyPos - other.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet other) {
        double distance = calcDistance(other);
        double distanceSquare = distance * distance;
        return (G * mass * other.mass) / distanceSquare;
    }

    public double calcForceExertedByX(Planet other) {
        double dx = other.xxPos - xxPos;
        double force = calcForceExertedBy(other);
        double r = calcDistance(other);
        return force * dx / r;
    }

    public double calcForceExertedByY(Planet other) {
        double dy = other.yyPos - yyPos;
        double force = calcForceExertedBy(other);
        double r = calcDistance(other);
        return force * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double result = 0;
        for (Planet planet: planets) {
            if (!this.equals(planet)) {
                result += calcForceExertedByX(planet);
            }
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double result = 0;
        for (Planet planet: planets) {
            if (!this.equals(planet)) {
                result += calcForceExertedByY(planet);
            }
        }
        return result;
    }

    public void update(double dt, double fX, double fY) {
        double aX= fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        String imgToDraw = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgToDraw);
    }
}