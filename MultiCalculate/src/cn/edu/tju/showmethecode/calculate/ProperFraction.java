package cn.edu.tju.showmethecode.calculate;

public class ProperFraction {

	private int numerator;		//分子
	private int denominator;	//分母
	private boolean isNormal;	//是否是合法分数（分子分母不为0）
	
	public ProperFraction(int numerator, int denominator) {
		if(denominator == 0 || numerator == 0){
			this.isNormal = false;
		}
		this.denominator = denominator;
		this.numerator = numerator;
		
//		if(isNormalFraction())
			shrink();
	}

	/**
	 * 化简，同除最大公约数
	 * @return
	 */
	private ProperFraction shrink() {
		int maxCommonDivisor = getMaxCommonDivisor(this.numerator, this.denominator);
		this.numerator /= maxCommonDivisor;
		this.denominator /= maxCommonDivisor;
		return this;
	}

	/**
	 * 找到最大公约数
	 * @param a
	 * @param b
	 * @return
	 */
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

    /**
     * 判断是否是合法分数
     */
    public boolean isNormalFraction() {
		return this.isNormal;
	}
    
    /**
     * 转换成字符串形式显示
     */
	public String toString() {
		if(this.numerator % this.denominator == 0){
			return String.valueOf(this.numerator / this.denominator);
		}else{
			return this.numerator + "/" + this.denominator;
		}
	}  
	
	/**
	 * 判断是否是一个真正的分数，而不是一个整数
	 */
	public boolean isFraction() {
		if(this.numerator % this.denominator == 0)
			return false;
		else 
			return true;
	}
	
	/**
	 * 判断是否是负数
	 */
	public boolean isNegativeNum() {
		if(doubleValue() >= 0.0)
			return false;
		else 
			return true;
	}
	
	/**
	 * 判断是否超过范围，如果是分数则分子，分母都不能超过100，如果是整数，则不能超过500
	 */
	public boolean isOutOfBound() {
		if(isFraction()) {
			if(this.numerator > 100 || this.denominator > 100)
				return true;
			else 
				return false;
		} else {
			if(doubleValue() > 500)
				return true;
			else 
				return false;
		}
	}
}
