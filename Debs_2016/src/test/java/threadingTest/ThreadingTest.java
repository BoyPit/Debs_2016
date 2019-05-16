package threadingTest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Before;
import org.junit.Test;

import entity.Evenement;
import threading.Producer;

public class ThreadingTest {
	
	
	Producer threadProducer;
	ArrayBlockingQueue<Evenement> sortie;
	@Before
	public void setUp() throws Exception {
		sortie = new ArrayBlockingQueue<Evenement>(1000);
		 threadProducer = new Producer(sortie,"posts.dat","comments.dat");
	}
	
	
	@Test
	public void testInputQueue() throws InterruptedException
	{
		Thread t1 = new Thread(threadProducer);
		t1.start();
		t1.join();
		Evenement date1;
		Evenement date2;
		for(int i =0; i< sortie.size()-1; i++)
		{
			 date1 = sortie.take();
			 date2 = sortie.take();
			 assertEquals(-1, date1.getTimestamp().compareTo(date2.getTimestamp()));
		}
	}

	@Test
	public void testReading() throws FileNotFoundException, IOException {

	File testFile = threadProducer.getFileFromResources("posts.dat");
	
	try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
	    String line;
	    while ((line = br.readLine()) != null) {
	    //   System.out.println(line);
	    }
	}
	
	}

}
