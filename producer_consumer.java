import java.util.List;
import java.util.ArrayList;
 class producer extends Thread
{
	List list;
	producer(List list)
	{
		list=this.list;
	}
	public void run() 
	{
		try{
		while(true)
		{
			synchronized(list){
			if(list.size()>0)
			{
				list.wait();
			}
			else{
				prod();
			}
			
			}
		}
		}
	catch(Exception e)
	{
		
	}
	}
	private void prod() throws InterruptedException
	{
		
		for(int i=1;i<6;i++){
			Thread.sleep(1000);
			list.add(i);
			System.out.println("items produced : "+i);

		}
			list.notifyAll();
	}
}
 class consumer extends Thread
{
	List list;
	consumer(List list)
	{
		list=this.list;
	}
	public void run()
	{
		try{
		while(true)
		{
			synchronized(list){
			if(list.size()==0){
			list.wait();}
			else{
			cons();
			}
			}
			
		}
		}
		catch(Exception e)
		{}
	
	}
	private void cons() throws InterruptedException
	{
		while(!list.isEmpty()){
		Thread.sleep(1000);
		
			System.out.println("items consumed : "+ list.remove(0));
		}
		list.notifyAll();
	}
}
public class ex11a
{
	public static void main(String args[]) throws Exception
	{
		List list=new ArrayList();
		producer p=new producer(list);
		consumer c=new consumer(list);
		p.start();
		c.start();
	}

}
