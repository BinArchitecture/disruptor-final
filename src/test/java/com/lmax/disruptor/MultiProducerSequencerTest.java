package com.lmax.disruptor;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class MultiProducerSequencerTest {

	@Test
	public void test() {
		int bufferSize = 8;
		final MultiProducerSequencer multiProducerSequencer=new MultiProducerSequencer(bufferSize,new YieldingWaitStrategy());
		int size=2;
		for(;;){
		  final AtomicInteger ai=new AtomicInteger(0);
		for(int i=0;i<size;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					long x=multiProducerSequencer.next();
					System.out.println(x);
					ai.getAndAdd(1);
				}
			}).start();
		}
		while(true){
			if(ai.get()==size)
				break;
		}
		}
	}

}
