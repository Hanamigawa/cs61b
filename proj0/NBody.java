public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int howMany = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet [] readPlanets(String fileName){
        In in = new In(fileName);
        int howMany = in.readInt();
        Planet [] planets = new Planet[howMany];
        double radius = in.readDouble();
        int index = 0;
//
//        System.out.println("No of planet: "+ howMany + "   "+ radius);
        while (index<howMany) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xV = in.readDouble(), yV = in.readDouble();
            double mass = in.readDouble();
            String gif = in.readString();
//            System.out.println(" "+xPos + yPos + xV + yV + mass + gif);
            planets[index] = new Planet(xPos, yPos, xV, yV, mass, gif);
            index++;
        }
        return planets;
    }

    public static void main(String [] args){    //taking three args: T dt filename
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args [1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0 , "images/starfield.jpg");

        for(Planet x : planets){
            x.draw();
        }

        StdDraw.enableDoubleBuffering();

        double time = 0;

        while(time <= T){
            double[] xForces = new double[planets.length];  // xForces received on each planet
            double[] yForces = new double[planets.length];

            for(int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for(int i = 0; i < planets.length; i++){        //update location velocity of each planet
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");      //draw background and then each planet
            for(Planet x : planets){
                x.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%f.2e\n", radius);
        for(Planet x: planets){
            StdOut.printf("%.4e %.4e %.4e %.4e %.4e %12s\n", x.xxPos, x.yyPos, x.xxVel, x.yyVel, x.mass, x.imgFileName);
        }
    }
}