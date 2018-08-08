package in.core.multithreads.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import in.core.multithreads.PrimeCallable;

public class sumOfNumber {

	private static Integer N_THREADS = 10;

	public static void main(final String[] args) {
		/**
		 * Create ExecutorServce, it will create threads.
		 */
		final ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
		List<Future<Integer>> futures = new ArrayList<>();
		/**
		 * Track time
		 */
		final Long strt = System.currentTimeMillis();
		System.out.println("Strt: " + strt);

		List<SumCallable> callables = new ArrayList<>();

		for (int i = 0; i < 100; i = i + 10) {
			/**
			 * For Every 10 numbers create new task
			 */
			if (i < 10)
				callables.add(new SumCallable(i, i + 10));
			else
				callables.add(new SumCallable(i + 1, i + 10));
		}
		try {
			/**
			 * Execute all the tasks
			 */
			futures = executorService.invokeAll(callables);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		final List<Integer> integer = new ArrayList<>();
		int suminteger = 0;
		/**
		 * Collect Data from Future Object.
		 */
		for (final Future<Integer> future2 : futures) {
			try {
				suminteger = suminteger + future2.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		final Long end = System.currentTimeMillis();
		System.out.println("End: " + end);

		System.out.println("Diff: " + (end - strt));
		System.out.println("Total sum of Numbers:" + suminteger);
		// System.out.println(primes);
		/**
		 * Its mandatory that shutdown the ExecutorService, otherwise threads will keep
		 * alive.
		 */
		executorService.shutdown();
		/**
		 * Test Result with MultiThread: Time Taken: Strt: 1516185696102 End:
		 * 1516185710760 Diff: 14658 Total prime Numbers:78498
		 * 
		 */
	}

}
