package in.core.multithreads.util;

import java.util.concurrent.Callable;

public class SumCallable implements Callable<Integer> {
	private final Integer start;
	private final Integer end;

	public SumCallable(final Integer start, final Integer end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum = sum + i;

		}
		System.out.println("sum values in after loop" + sum);
		return sum;
	}

}
