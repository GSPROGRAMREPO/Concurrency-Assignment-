import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiSum extends Thread{
	
	int[] m_arr123;
	int m_startpoint;
	int m_endpoint;
	long[] m_total;
	
	Lock lock = new ReentrantLock();
	
	MultiSum(int [] arr123, int startpoint, int endpoint, long [] total){
		
		m_arr123 = arr123;
		m_startpoint = startpoint;
		m_endpoint = endpoint;
		m_total = total;
		
	}
	

	public void run() {
		long sum = 0;
	
		for (int i = m_startpoint; i < m_endpoint; i++) {
			sum += m_arr123[i];
		}
		
		lock.lock();
		m_total[0] += sum;
		lock.unlock();
	}

}
