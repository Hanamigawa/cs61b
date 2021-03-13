public class testPlanet {
    public static void main (String[] args) {
        Planet globe1 = new Planet(1, -2, 3, -5, 50, null);
        Planet globe2 = new Planet(-1, -3, 2, 4, 40, null);
        Planet[] allPlanets = new Planet[]{globe1, globe2};
        System.out.println("Test Planet: ");
        System.out.println("globe1's x and y forces are "+
                globe1.calcNetForceExertedByX(allPlanets)+" and "+
                globe1.calcNetForceExertedByY(allPlanets));

        System.out.println("globe2's x and y forces are "+
                globe2.calcForceExertedByX(globe1)+" and "+
                globe2.calcForceExertedByY(globe1));
    }
}