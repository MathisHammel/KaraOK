
public class Autocorrelate {
	public static double[] compute(byte[] x){ //compute autocorrelation and return array
		int N=x.length;
		double out[] = new double[N/2];
		int i;
		int j;
		for(i=0;i<N/2;i++)
		{
			out[i]=0;
			for(j=0;j<N-i;j++)
			{
				out[i]+=x[j]*x[j+i];
			}
			out[i]/=(N-i);
			//out[i]=Math.abs(out[i]);
		}
		
		return(out);
	}
	
	public static void computeShow(byte[] x){ //compute autocorrelation and display the array
		int N=x.length;
		double out[] = new double[N/2];
		int i;
		int j;
		for(i=0;i<N/2;i++)
		{
			out[i]=0;
			for(j=0;j<N-i;j++)
			{
				out[i]+=x[j]*x[j+i];
			}
			out[i]/=(N-i);
			//out[i]=Math.abs(out[i]);
			System.out.println("Decalage "+i+" : "+out[i]);
		}
	}
		
	public static double freq(byte[] x)
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
			while(y[i]<0.6*y[0] && i<y.length-1)
			{
				i++;
			}
			if(i==(y.length)-2)
			{
				return(0.0);
			}
			imin=i;
			while(y[i]>0.5*y[0] && i<y.length-1)
			{
				i++;
			}
			imax=i;
			max=y[imin];
			for(i=imin;i<=imax;i++)
			{
				if(y[i]>max)
				{
					max=y[i];
					n=i;
				}
			}
			
			
			freq=44100/n;
			/*while(freq>=880)
			{
				freq/=2;
			}
			while(freq<440)
			{
				System.out.println(freq);
				freq*=2;
			}*/
			return(freq);
		
		}
	
}
