package Test;

import MyTool.P;

public class dummy {
	private String kk;
	public dummy(String kk) {
		this.kk=kk;
//		kk.toString()
	}
	
	private void ss() {
		P.p(this.kk);
	}
	
	public void vvv() {
		dummy w = new dummy("A");
		w.ss();
		this.ss();
		
	}
	
	public String toString() {
		return this.kk;
	}
	
	public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof dummy) {
            dummy a = (dummy)anObject;
            if (this.kk.equals(a.kk)) {
            	return true;
            }
        }
        return false;
	}
}
            	
            	

