package cn.edu.tju.showmethecode.calculate;

public class ProperFraction {

	private int numerator;		//分子
	private int denominator;	//分母
	
	public ProperFraction(int numerator, int denominator) {
		if(denominator == 0){
			throw new IllegalArgumentException("分母为0");
		}
		this.denominator = denominator;
		this.numerator = numerator;
		
		shrink();
	}

	//化简，同除最大公约数
	private ProperFraction shrink() {
		int maxCommonDivisor = getMaxCommonDivisor(this.numerator, this.denominator);
		this.numerator /= maxCommonDivisor;
		this.denominator /= maxCommonDivisor;
		return this;
	}

	private int getMaxCommonDivisor(int a, int b) {
		int mod = a % b;  
		   
        if (mod == 0) {  
            return Math.abs(b);  
        } else {  
            return getMaxCommonDivisor(b, mod);  
        }  
	}
	
	public ProperFraction add(ProperFraction that) {
		return new ProperFraction(this.numerator * that.denominator + this.denominator * that.numerator,  
                this.denominator * that.denominator); 
	}
	
	public ProperFraction minus(ProperFraction that) {  
        return new ProperFraction(this.numerator * that.denominator - this.denominator * that.numerator,  
                this.denominator * that.denominator);  
    }  
    
    public ProperFraction multiply(ProperFraction that) {  
        return new ProperFraction(this.numerator * that.numerator,  
                this.denominator * that.denominator);  
    }  
     
    public ProperFraction devide(ProperFraction that) {  
        return new ProperFraction(this.numerator * that.denominator,  
                this.denominator * that.numerator);  
    }
    
    public double doubleValue() {  
        return (double) numerator / denominator;  
    }

	public String toString() {
		if(Math.abs(this.numerator) < this.denominator){
			return this.numerator + "/" + this.denominator;
		}else if(this.numerator % this.denominator == 0){
			return String.valueOf(this.numerator / this.denominator);
		}else{
			return (this.numerator / this.denominator) + "'" + (Math.abs(this.numerator) % this.denominator) + "/" + this.denominator;
		}
	}  
	
	
}
