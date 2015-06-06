
public class Autocorrelate {
	public static double[] compute(byte[] x){ //compute autocorrelation and return computed array
		int N=x.length;
		double out[] = new double[N/2];
		int i;
		int j;
		for(i=0;i<N/2;i++)//simple discrete signal autocorrelation
		{
			out[i]=0;
			for(j=0;j<N-i;j++)
			{
				out[i]+=x[j]*x[j+i];
			}
			out[i]/=(N-i);
			
		}
		
		return(out);
	}
	
	public static void computeShow(byte[] x){ //compute autocorrelation and display the computed array (no return, just used for debug)
		int N=x.length;
		double out[] = new double[N/2];
		int i;
		int j;
		for(i=0;i<N/2;i++)//simple discrete signal autocorrelation
		{
			out[i]=0;
			for(j=0;j<N-i;j++)
			{
				out[i]+=x[j]*x[j+i];
			}
			out[i]/=(N-i);
			
			System.out.println("Decalage "+i+" : "+out[i]);
		}
	}
		
	public static double freq(byte[] x) //this function is based on compute(), checks the first peak after offset 0
		{
			int i;
			int freq = -1;
			int n = -1;
			double[] y = compute(x);
			if(y[0]==0)
			{
				y[0]=1;
			}
			double max;
			int imin=0;
			int imax=0;
			
			i=15;
			while(y[i]<0.6*y[0] && i<y.length-1)//array iterator to wait for peak beginning
			{
				i++;
			}
			if(i==(y.length)-2) //if reached end of computed array without peak (which means no correct signal), return 0
			{
				return(0.0);
			}
			imin=i;
			while(y[i]>0.5*y[0] && i<y.length-1)//second array iterator to determine peak end
			{
				i++;
			}
			imax=i;
			max=y[imin];
			for(i=imin;i<=imax;i++)//iterator on peak to determine actual maximum
			{
				if(y[i]>max)
				{
					max=y[i];
					n=i;
				}
			}
			
			
			freq=44100/n;//divide sample rate by autocorrelate period peak to get actual frequency
			
			return(freq);
		
		}
	
}
