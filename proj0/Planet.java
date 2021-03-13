import java.lang.Math;

public class Planet{
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	public static final double G=6.67e-11;
	
	public Planet(double xP, double yP, double xV, double yV, 
			double m, String img){
		this.xxPos=xP;
		this.yyPos=yP;
		this.xxVel=xV;
		this.yyVel=yV;
		this.mass=m;
		this.imgFileName=img;
	}
	
	public Planet(Planet p) {
		this.xxPos=p.xxPos;
		this.yyPos=p.yyPos;
		this.xxVel=p.xxVel;
		this.yyVel=p.yyVel;
		this.mass=p.mass;
		this.imgFileName=p.imgFileName;
	}

	public double calcDistance(Planet p) {
		return Math.sqrt(Math.pow(this.xxPos-p.xxPos,2)+Math.pow(this.yyPos-p.yyPos,2));
	}

	public double calcForceExertedBy(Planet p){
		return G*this.mass*p.mass/Math.pow(this.calcDistance(p),2);
	}

	public double calcForceExertedByX (Planet p) {
		double all=this.calcForceExertedBy(p);
		return all * (p.xxPos - this.xxPos)/this.calcDistance(p);
	}


	public double calcForceExertedByY (Planet p) {
		double all=this.calcForceExertedBy(p);
		return all * (p.yyPos - this.yyPos)/this.calcDistance(p);
	}

	public double calcNetForceExertedByX (Planet [] allPlanets){
		double sum=0;
		for(Planet x:allPlanets){
			if(this==x){
				continue;
			}
			sum+=this.calcForceExertedByX(x);
		}
		return sum;
	}

	public double calcNetForceExertedByY (Planet [] allPlanets){
		double sum=0;
		for(Planet x:allPlanets){
			if(this==x) continue;
			sum+=this.calcForceExertedByY(x);
		}
		return sum;
	}

	public void update (double dt, double xForce, double yForce){
		double aX = xForce / this.mass;
		double aY = yForce / this.mass;
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		this.xxPos += dt * xxVel;
		this.yyPos += dt * yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos,imgFileName);
	}

}