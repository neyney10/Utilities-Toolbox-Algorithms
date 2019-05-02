package algorithms.sorts;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;


public class Utilities {
	
	public static void main(String[] args)
	{
	/*	int[] a = createRandomIntegerArray(15, -45, 222);
		ShellSort(a);
		System.out.println(Arrays.toString(a));
		CheckSorted(a);*/
		
		
		//im building a V2 performance test.
		//performPerformanceTest(100, 5000, 0, 10000);
		
		//More full Featured, added even O(n^2) Sorts
		performPerformanceTestV2(250, 5000, 10, 10000);
	}
	
	//temp
	private static void CheckSorted(int[] arr)
	{
		for(int i =1;i<arr.length;i++)
		{
			if(arr[i]<arr[i-1])
			{
				System.out.println("[X] NOT SORTED!!!! PROBLEM AT: arr["+i+"] with value of "+arr[i]+" and previous: "+arr[i-1] + " | result: "+arr[i-1]+" > "+arr[i]);
				return;
			}
		}
		System.out.println("[V] the array is Sorted!");
	}
	
	//Integer Binary Search
	/**
	 * The function perform Binary Search on array of Integers with Upper Bound time complexity of O(Log[N])
	 * @param [Type: int[] ] "arr" -  Array to Search in.
	 * @param [Type: int ] "item" - value to search.
	 * @return [Type: int ] the requested value if found, -1 otherwise.
	 */
	public static int BinarySearch(int[] arr,int item)
	{
		return BinarySearchHelper(arr,item,0,arr.length-1);
	}
	private static int BinarySearchHelper(int[] arr,int item, int low,int high)
	{
		if(low<=high)
		{
			int mid = (low+high)/2;
			if(arr[mid] == item)
				return arr[mid];
			
			if(low==high) //if on last iteration and not found. then return not found.
				return -1;
			
			//else if higher, then search the right side >>
			if(item > arr[mid])
				return BinarySearchHelper(arr,item,low=mid+1,high);
			
			//else then must be on the left side <<
				return BinarySearchHelper(arr,item,low,high=mid-1);
		}
		
		return -1;
	}

	//Integer Linear Search
		/**
	 * The function perform Linear Search on array of Integers with Upper Bound time complexity of O(N)
	 * @param [Type: int[] ] "arr" -  Array to Search in.
	 * @param [Type: int ] "item" - value to search.
	 * @return [Type: int ] the requested value if found, -1 otherwise.
	 */
	public static int LinearSearch(int[] arr, int item)
	{
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i] == item)
				return arr[i];
		}
		return -1;
	}
	
	
	//O(n^2) sorts
	//Integer BubbleSort
	/**
	 * The function perform BubbleSort on array of Integers with Upper Bound time complexity of O(N^2)
 	* @param [Type: int[] ] "arr" -  Array to sort.
 	*/
	public static void BubbleSort(int[] arr)
	{
		for(int i = 0 ; i< arr.length;i++)
			for(int j = 0 ; j<arr.length-i-1;j++ )
			{
				if( arr[j]>arr[j+1]) //if 2 near elements are not ordered.
					Swap(arr,j,j+1); //then swap/order them.
			}
	}
	
	//Integer SelectionSort
	/**
	 * The function perform SelectionSort on array of Integers with Upper Bound time complexity of O(N^2)
 	* @param [Type: int[] ] "arr" -  Array to sort.
 	*/
	public static void SelectionSort(int[] arr)
	{
		int min;
		for(int i = 0 ; i< arr.length;i++)
		{
			min = i;
			for(int j = i+1 ; j<arr.length;j++ )
			{
				if( arr[min]>arr[j]) //find min
					min = j;
			}
			Swap(arr,i,min); //set min at correct place.
		}
	}
	
	//Integer InsertionSort
	/**
	 * The function perform InsertionSort on array of Integers with Upper Bound time complexity of O(N^2)
 	* @param [Type: int[] ] "arr" -  Array to sort.
 	*/
	public static void InsertionSort(int[] arr)
	{
		int element, j;
		for(int i = 1 ; i< arr.length;i++)
		{
			element = i;
			j=i-1;
		    while (j >= 0 && arr[j] > element) //as long as not find his place.
		    {
		        arr[j+1] = arr[j]; //Advance;
		        j = j-1;
		   }
		}
	}
	
	
		//Integer ShellSort
		/**
		 * The function perform ShellSort on array of Integers with Upper Bound time complexity of O(N^(1.5))
	 	* @param [Type: int[] ] "arr" -  Array to sort.
	 	*/
		public static void ShellSort(int[] arr)
		{
			int shell,temp,j;
			for(shell = arr.length/2  ; shell > 0 ; shell/=2)
			{
			for(int i = shell; i <arr.length;i++)
			{
				temp = arr[i];
				for(j = i ; j>=shell && arr[j-shell] > temp;j-=shell) //InsertionSort
				{
					arr[j] = arr[j-shell];
				}
				arr[j] = temp;
			}
				
			}
		}
	
	
	//Integer MergeSort
		/**
	 * The function perform MergeSort on array of Integers with Upper Bound time complexity of O(N*Log[N])
	 * (Note: this sort requires N extra memory space, Probably Faster than QuickSort when used on array of Objects)
	 * @param [Type: int[] ] "arr" -  Array to sort.
	 */
	public static void MergeSort(int[] arr)
	{
		MergeSortHelper(arr, 0, arr.length-1);
	}
	private static void MergeSortHelper(int[] arr,int start,int end)
	{
		if(start == end) //if len is is 1
			return; //stop
		
		int mid = (start+end)/2;
		MergeSortHelper(arr,start,mid); //split
		MergeSortHelper(arr,mid+1,end); //split
		
		//now Merge parts
		Merge(arr,start,mid,end);
	}
	private static void Merge(int[] arr,int start1,int mid, int end2)
	{
		int end1 = mid;
		int start2 = mid+1;
		int startindex = start1; //temp
		
		int size = end2-start1+1;
		int[] temp = new int[size];
		int index = 0;
		
		while(start1 <= end1 && start2 <= end2)
		{
			if(arr[start1] <= arr[start2])
				temp[index++] = arr[start1++];
			else
				temp[index++] = arr[start2++];
		}
		
		while(start1 <= end1)
			temp[index++] = arr[start1++];
		
		while(start2 <= end2)
			temp[index++] = arr[start2++];
		
		
		for(int i = 0; i <size;i++)
			arr[startindex++] = temp[i];
		
	}

	//Integer QuickSort
	 //Lomuto Version
	 	/**
	 * The function perform QuickSort on array of Integers using "Lomuto" partition scheme, with Upper Bound time complexity of O(N*Log[N])
	 * @param [Type: int[] ] "arr" -  Array to Sort.
	 */
	public static void QuickSortLomuto(int[] arr)
	{
		QuickSortHelperLomuto(arr, 0, arr.length-1);
	}
	private static void QuickSortHelperLomuto(int[] arr,int start,int end)
	{
		//Floor | Stopping condition | Recursion's base.
		if(start>=end)
			return;
		
		//save the position for the recursive function later on.
		int startTemp = start;
		
		//Choose a pivot, choose the first element.
		int pivotPos = start;
		
		//run pivot's delegate, find the starting position to start searching for correct place to place the pivot.
		while(start<end && arr[pivotPos]>=arr[start+1])
		{start++;}

		//find Pivot's place and arrange the elements according to pivot's size.
		for(int i = start+1;i<=end;i++)
		{
			if(arr[pivotPos]>=arr[i])
				Swap(arr,++start,i);
		}
		
		//Place pivot in it's place.
		Swap(arr,pivotPos,start); 
		pivotPos = start;
		
		QuickSortHelperLomuto(arr,startTemp,pivotPos-1); //Recurse: sort the left side of the array.
		QuickSortHelperLomuto(arr,pivotPos+1,end); //Recurse: sort the right side of the array.
		
	}
	 //Hoare Version
	 	 	/**
	 * The function perform QuickSort on array of Integers using "Hoare" partition scheme, with Upper Bound time complexity of O(N*Log[N])
	 * @param [Type: int[] ] "arr" -  Array to Sort.
	 */
	public static void QuickSortHoare(int[] arr)
	{
		QuickSortHelperHoare(arr, 0, arr.length-1);
	}
	private static void QuickSortHelperHoare(int[] arr,int start,int end)
	{ // I didnt succesfuly write it.
		if(start>=end)
			return;

		int pivot = arr[start];
		
		int i=start-1, j=end+1;
		while(i<j)
		{
			do
			{
				i++;
			}
			while(pivot > arr[i]);
				
			do
			{
				j--;
			}
			while(pivot < arr[j]);
				

			if(i>=j)
				break;

			Swap(arr, i, j);
				
		}

		QuickSortHelperHoare(arr, start, j);
		QuickSortHelperHoare(arr, j+1, end);
	}
	
	//HeapSort
	 /** The function perform HeapSort on array of Integers using a virtual heap from array and the sort is not stable, with Upper Bound time complexity of O(N*Log[N])
	 * @param [Type: int[] ] "arr" -  Array to Sort.
	 */
	public static void HeapSort(int[] arr)
	{
		int end = arr.length-1;
		BuildHeap(arr);
		for(int i = end ;i >= 1 ;i-- )
		{
			Swap(arr,0,i);
			Heapify(arr,0,i);
			
		}
	}
	
	 /** the function receive an array and creates a Max-Heap from it. Time Complexity of O(N*Log[N])
	 * @param [Type: int[] ] "arr" -  Array to build from.
	 */
	private static void BuildHeap(int[] arr)
	{
		for(int i=(arr.length-1)/2;i>=0;i--)
			Heapify(arr,i,arr.length);
	}
	
	 /** the function receive an array and apply small fixes to a heap structure to make it Max-Heap. Time Complexity of O(Log[N])
	 * @param [Type: int[] ] "arr" -  Array to build from.
	 * @param [Type: int ] "base" -  which 'node' to fix.
	 * @param [Type: int ] "end" -  virtual end length of the array.
	 */
	private static void Heapify(int[] arr, int base,int end)
	{
		int root = base;
		int child_left = root*2+1;
		int child_right = root*2+2;

		int largest = root;
		
		if(child_left < end && arr[child_left] > arr[root])
			largest = child_left;
		
		if(child_right < end && arr[child_right] > arr[largest])
			largest = child_right;
		
		if(largest != root)
		{
			Swap(arr,root,largest);
			Heapify(arr,largest,end);
		}
	}

	//BucketSort
	/**
	 * The function perform BucketSort on array of Integers with upper bound time complexity of O(n^2) avg case is O(N+K)
	 * (Note: this sort requires N extra memory space.
	 * @param [Type: int[] ] "arr" -  Array to sort.
	 * @param [Type: int ] "bucket_amount" -  amount of buckets. can improve performance.
	 */
	public static void BucketSort(int[] arr,int bucket_amount)
	{
		int max = getMax(arr) , bucket_size =  (max/bucket_amount)+1;
		
		//Divide into buckets
		LinkedList<Integer>[] buckets = createNewBuckets(bucket_amount);
		
		for(int num : arr)
		{
			buckets[num/bucket_size].add(num);
		}
		
		//Sort each bucket
		int k = 0;
		for(LinkedList<Integer> bucket : buckets)
		{
			//SORT LINKED LIST
			Collections.sort(bucket);
			for(Integer num : bucket)
			{
				arr[k++] = num;
			}
		}
	}
	
	//CountingSort
	/**
	 * The function perform CountingSort or BoundedSort on array of Integers with time complexity of O(N)
	 * (Note: this sort requires Exponential extra memory space.
	 * @param [Type: int[] ] "arr" -  Array to sort.
	 */
	public static void CountingSort(int[] arr)
    {
        int max = getMax(arr);
        int buckets[] = new int[max+1];
        
        for(int num : arr)
        {
            buckets[num]++;
        }
        
        int k = 0;
        for(int j=0;j<buckets.length;j++)
        {
            for(int i=0;i<buckets[j];i++)
            {
                arr[k] = j;
                k++;
            }
        }
	}

	/**
	 * The function retrieves the max element in an arr.
	 * @param [Type: int[] ] "arr" -  Array to search.
	 * @return [Type: int ] max element.
	 */
    private static int getMax(int[] arr)
    {
        int max = -1;
        for(int n : arr)
        {
            if(n>max)
                max=n;
        }
        return max;
    }
	
	//RadixSort
	/**
	 * The function perform RadixSort or BaseSort on array of Integers with time complexity of O(K*N)
	 * (Note: this sort requires 2N extra memory space.
	 * --------- can be optimized --------
	 * @param [Type: int[] ] "arr" -  Array to sort.
	 */
    public static void RadixSort(int[] arr)
    {
        int max = getAmountOfDigits(getMax(arr));
        
        int exp = 10;
        int div = 1;
        
        for(int d=0;d<max;d++)
        {
        	int[] temp = new int[arr.length];
        	
        	int k = 0;
        	
        	for(int d2=0;d2<10;d2++)
        	{
        		for(int num : arr)
        		{
        			if(((num%exp)/div)==d2)
        				temp[k++] = num;
        		}
        	}
        	
        	exp *=10;
            div *= 10;
        	
    		for(int i =0;i<temp.length;i++)
    		{
    			arr[i] = temp[i];
    		}
        }

	}
    
	/**
	 * The function perform RadixSort or BaseSort on array of Integers using CountingSort with time complexity of O(K*N) 
	 * (Note: this sort requires N extra memory space.
	 * @param [Type: int[] ] "arr" -  Array to sort.
	 */
    public static void RadixSort_With_CountingSort(int[] arr)
    {
    	if(arr.length<=1) return;
    	
        int max = getAmountOfDigits(getMax(arr));
        
       	int[] count_array = new int[10];
    	int[] temp = new int[arr.length];

    	
        int exp = 10;
        int div = 1;
        
        for(int d=0;d<max;d++)
        {
        	
            for(int i = 0;i<temp.length;i++)
            {
            	temp[i] = arr[i];
            }
            
            for(int i = 0 ;i<count_array.length;i++)
            {
            	count_array[i]=0;
            }
            
        	
        	for(int num : temp)
        	{
        		count_array[(num%exp)/div]++;
        	}
        	
          	for(int i=1;i<count_array.length;i++)
        	{
        		count_array[i] += count_array[i-1];
        	}
          	
          	
          	for(int i=temp.length-1;i>=0;i--)
        	{
          		count_array[(temp[i]%exp)/div]--;
        		arr[count_array[(temp[i]%exp)/div]] = temp[i];
        	}
          	
        	exp *=10;
            div *= 10;
        	
        }
        
	}
    
	/**
	 * The function perform RadixSort or BaseSort on array of Integers with Lists using CountingSort with time complexity of O(K*N) 
	 * (Note: this sort requires N extra memory space.
	 * @param [Type: int[] ] "arr" -  Array to sort.
	 */
    public static void RadixSort_With_Lists(int[] arr)
    {
        int max = getAmountOfDigits(getMax(arr));
        
        LinkedList<Integer>[] buckets = createNewBuckets(10);
        
        int exp = 10;
        int div = 1;
        for(int i =1;i<=max;i++)
        {
            for(int num : arr)
            {
                buckets[(num%exp)/div].addLast(num);
            }

            int k=0;
            for(int j=0;j<buckets.length;j++)
            {
               while(buckets[j].size()>0)
                    arr[k++] = buckets[j].removeFirst();
			}
			
			exp *=10;
            div *= 10;
        }
        
	}
	
  //Other
    
	/**
	 * The function retrieves the amount of digits in integer in base 10.
	 * @param [Type: int ] "num" -  number to test on.
	 * @return [Type: int ] amount of digits.
	 */
    private static int getAmountOfDigits(int num)
    {
        if(num==0)
            return 1;
        double n =  num;
        return (int) (Math.log10(n))+1;
	}
    
    private static int getAmountOfDigits(long num)
    {
        if(num==0)
            return 1;
        double n =  num;
        return (int) (Math.log10(n))+1;
	}
	
	/**
	 * The function creates for RadixSort 10 newly Bucket with LinkedList each for the digits 0-9,.
	 * @param [Type: int ] "amount" -  amount of lists/buckets to create.
	 * @return [Type: LinkedList<Integer>[] ] the newly Created Bucket.
	 */
    private static LinkedList<Integer>[] createNewBuckets(int amount)
    {
        LinkedList<Integer>[] buckets =  new LinkedList[amount];
        for(int i =0;i<buckets.length;i++)
        {
            buckets[i] = new LinkedList<Integer>();
        }
        return buckets;
    }
	
		 	/**
	 * The function returns Random array of integers with given values.
	 * @param [Type: int ] "size" -  the size of the array to build.
	 * @param [Type: int ] "minValue" -  the minimal value of stored element in the array,
	 * @param [Type: int ] "maxValue" -  the maximal  value of stored element in the array.
	 * @return [Type: int[] ] the newly created array with custom size, min and max values.
	 */
	public static int[] createRandomIntegerArray(int size, int minValue, int maxValue)
	{
		int[] array = new int[size];
		for(int i=0;i<array.length;i++)
			array[i] = minValue + (int) (Math.random()*(maxValue-minValue+1));
		
		return array;
	}
	
	
	
 	/**
* The function performs different types of utilities with randomized arrays and given test scenarios such as amount of tests and size of arrays, using different test such as multiple same elements and already ordered arrays to test performance.
* [MergeSort] [BucketSort] [HeapSort] [CountingSort] [RadixSort] [QuickSort (Lomuto)] [QuickSort (Hoare)] [Java's Built-in sort using Dual Pivot QuickSort] [BubbleSort] [SelectionSort] [InsertionSort] [ShellSort] [Binary Search] [Linear Search]
* @param [Type: int ] "times" - the amount of times to run each test.
* @param [Type: int ] "size" -   the size of the array to build.
* @param [Type: int ] "minValue" -  the minimal  value of stored element in the array.
* @param [Type: int ] "maxValue" -  the maximal  value of stored element in the array.
*/
	public static void performPerformanceTestV2(int times, int size, int minValue,int maxValue)//TODO: added Searches
	{
		int randomElemenet;
		DecimalFormat myFormatter = new DecimalFormat("###,###");
		ArrayList<TupleV2> results = new ArrayList<>();

		if(minValue < 0 )
		{
			System.out.println("Please Set minValue be Non-Negative value.");
			return;
		}
		
		System.out.println("Please wait while performing tests...");
		AlgorithmTest t = new AlgorithmTest();
		for(int i =0; i<16;i++)
		{
			switch(i)
			{
				case 0:
					System.out.println(AlgorithmTest.titles[0]);
					break;
				case 1:
					System.out.println(AlgorithmTest.titles[1]);
					break;
				case 4:
					System.out.println(AlgorithmTest.titles[2]);
					break;
				case 5:
					System.out.println(AlgorithmTest.titles[3]);
					break;
				case 8:
					System.out.println(AlgorithmTest.titles[4]);
					break;
				case 12:
					System.out.println(AlgorithmTest.titles[5]);
					break;
			}
			TupleV2 tup = t.Test(times,size, minValue, maxValue, i);
			System.out.println(tup.toString(myFormatter)+"\n");
			results.add(tup);
		}

		
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("----------- Sort Results -----------");
		System.out.println("-----------[ Arrays count: "+times+", Array size: "+size+", min: "+minValue+", max: "+maxValue+" ]-----------");
		System.out.println("-----------[ From Fastest to Slowest, time in Nano-Seconds (n^-9) by AVG ]-----------");
		int k = 1;
		TupleV2.sort(results);
		for(TupleV2 result : results)
		{
			System.out.println("["+(k++)+"] >> "+result.toStringShort(myFormatter));
		}
		
	}
	
			 	/**
	 * The function performs different types of utilities with randomized arrays and given test scenarios such as amount of tests and size of arrays.
	 * [MergeSort] [BucketSort] [HeapSort] [CountingSort] [RadixSort] [QuickSort (Lomuto)] [QuickSort (Hoare)] [Java's Built-in sort using Dual Pivot QuickSort] [Binary Search] [Linear Search]
	 * @param [Type: int ] "times" - the amount of times to run each test.
	 * @param [Type: int ] "size" -   the size of the array to build.
	 * @param [Type: int ] "minValue" -  the minimal  value of stored element in the array.
	 * @param [Type: int ] "maxValue" -  the maximal  value of stored element in the array.
	 */@Deprecated 
	public static void performPerformanceTest(int times,int size,int minValue,int maxValue)
	{
		long starttime,totaltime,avgtime,mintime,maxtime;
		int[] arr;
		int randomElemenet;
		DecimalFormat myFormatter = new DecimalFormat("###,###");
		ArrayList<Tuple> results = new ArrayList<>();


		
		System.out.println("-----------HeapSort-----------");
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			HeapSort(arr);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for HeapSort: "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("HeapSort", (avgtime/times)));
		
		System.out.println("-----------BucketSort-----------");
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		int bucket_size = (int) Math.sqrt(size);
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			BucketSort(arr,bucket_size);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for BucketSort (sqrt(n) buckets) (Non-Negative Numbers Only): "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("BucketSort (sqrt(n) buckets)", (avgtime/times)));
		
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		bucket_size = size/10;
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			BucketSort(arr,bucket_size);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for BucketSort (n/10 buckets) (Non-Negative Numbers Only): "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("BucketSort (n/10 buckets) buckets)", (avgtime/times)));
		
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		bucket_size = (int) Math.log(size);
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			BucketSort(arr,bucket_size);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for BucketSort (ln(n) buckets) (Non-Negative Numbers Only): "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("BucketSort (ln(n) buckets) buckets)", (avgtime/times)));
		
		System.out.println("-----------CountingSort-----------");
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			CountingSort(arr);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for CountingSort (Non-Negative Numbers Only): "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("CountingSort", (avgtime/times)));
		
		System.out.println("-----------RadixSort/BaseSort-----------");
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			RadixSort(arr);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for RadixSort (Non-Negative Numbers Only): "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("RadixSort", (avgtime/times)));
		
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			RadixSort_With_CountingSort(arr);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for RadixSort (Using CountingSort) (Non-Negative Numbers Only): "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("RadixSort (Using CountingSort)", (avgtime/times)));
		
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			RadixSort_With_Lists(arr);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for RadixSort (Using Lists) (Non-Negative Numbers Only): "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("RadixSort (Using Lists)", (avgtime/times)));
		
		System.out.println("-----------MergeSort & QuickSort-----------");
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			MergeSort(arr);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for MergeSort: "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("MergeSort", (avgtime/times)));
		
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			QuickSortLomuto(arr);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for QuickSort (Lomuto's partition scheme): "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("QuickSort (Lomuto's partition scheme)", (avgtime/times)));
		
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			QuickSortHoare(arr);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for QuickSort (Hoare's partition scheme): "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("QuickSort (Hoare's's partition scheme)", (avgtime/times)));
		
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			arr = createRandomIntegerArray(size, minValue, maxValue);
			starttime = System.nanoTime();
			Arrays.sort(arr);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for Built-in java's sort: "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		results.add(new Tuple("Built-in java's sort", (avgtime/times)));
		
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("-----------Searches-----------");
		arr = Utilities.createRandomIntegerArray(size, minValue, maxValue);
		QuickSortLomuto(arr);
		
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			randomElemenet = arr[(int)(Math.random()*arr.length)];
			starttime = System.nanoTime();
			BinarySearch(arr, randomElemenet);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for BinarySearch: "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		
		mintime = Long.MAX_VALUE;
		maxtime = 0;
		avgtime = 0;
		for(int i=0;i<times;i++)
		{
			randomElemenet = arr[(int)(Math.random()*arr.length)];
			starttime = System.nanoTime();
			LinearSearch(arr, randomElemenet);
			totaltime = System.nanoTime()-starttime;
			avgtime += totaltime;
			mintime = (totaltime<mintime)? totaltime : mintime;
			maxtime = (totaltime>maxtime)? totaltime : maxtime;
		}
		System.out.println("Average time for LinearSearch: "+myFormatter.format((avgtime/times)) + " | Max: "+myFormatter.format(maxtime)+" | Min: "+myFormatter.format(mintime));
		
		
		
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("-----------Sort Results-----------");
		System.out.println("-----------[ Arrays count: "+times+", Array size: "+size+", min: "+minValue+", max: "+maxValue+" ]-----------");
		System.out.println("-----------[ From Fastest to Slowest, time in Nano-Seconds (n^-9) by AVG ]-----------");
		int k = 1;
		Tuple.sort(results);
		for(Tuple result : results)
		{
			System.out.println("["+(k++)+"] >> "+result.x +" <---> "+ myFormatter.format(result.y));
		}
	}	
	private static void Swap(int[] arr,int index1,int index2)
	{
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	private static void MirrorArray(int[] arr)
	{
		for(int i = 0;i<arr.length/2;i++)
		{
			Swap(arr,i,arr.length-i-1);
		}
	}
	
	private static class Tuple{ 
		  public String x; 
		  public Long y; 
		  public Tuple(String x, Long y) { 
		    this.x = x; 
		    this.y = y; 
		  }  
		    
		  
		public String toString(DecimalFormat myFormatter) {
		
			return x + " <---> " + myFormatter.format(y);
		}
		  
		  public static void sort(ArrayList<Tuple> arr) //selection sort (?)
		  {
			  Tuple min,element ,temp;
			  int index;
			  for(int i=0;i<arr.size()-1;i++) 
			  {
				  min = arr.get(i);
				  index = i;
				  for(int j = i+1;j<arr.size();j++)
				  {
					  element = arr.get(j);
					  if(element.y < min.y)
					  {
						  min = element;
						  index = j;
					  }
					  
				  }
				  //swap
				  temp = arr.get(i);
				  arr.set(i, min);
				  arr.set(index, temp);
			  }
		  }
		}
	
	private static class TupleV2{ 
		  public String name;
		  public long[] avg_time = new long[4];
		  public long[] min_time = new long[4];
		  public long[] max_time = new long[4];
		  public TupleV2(String name, long[] avg, long[] min, long[] max) { 
			  this.name = name;
			  this.avg_time = avg;
			  this.min_time = min;
			  this.max_time = max;
		  }  
		    
		  
		public String toString(DecimalFormat myFormatter) {
		
			return format(myFormatter);
		}
		public String toStringShort(DecimalFormat myFormatter) {
			
			return name + " <--> " + myFormatter.format(avg_time[0]);
		}
		private String format(DecimalFormat myFormatter)
		{
			String[] test_names = {"Random  ","Mirrored","Sorted  ","Repeated"};
	
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i<4;i++)
			{
				String s = 	String.format("%1$5s %2$15s %3$15s %4$15s",name+": ["+test_names[i]+"] >> ","Avg: "+myFormatter.format(avg_time[i])+" | ","Max: "+myFormatter.format(max_time[i])+" | ","Min: "+myFormatter.format(min_time[i]));
				sb.append(s);
				if(i!=3)
					sb.append("\n");
			}
			
			return sb.toString();
		}
		  
		  public static void sort(ArrayList<TupleV2> arr) //selection sort (?)
		  {
			  TupleV2 min,element ,temp;
			  int index;
			  for(int i=0;i<arr.size()-1;i++) 
			  {
				  min = arr.get(i);
				  index = i;
				  for(int j = i+1;j<arr.size();j++)
				  {
					  element = arr.get(j);
					  if(element.avg_time[0] < min.avg_time[0])
					  {
						  min = element;
						  index = j;
					  }
					  
				  }
				  //swap
				  temp = arr.get(i);
				  arr.set(i, min);
				  arr.set(index, temp);
			  }
		  }
		} 

	public static class AlgorithmTest
	{	
		public static String[] algorithms = {"HeapSort","BucketSort (n/10 Buckets)"
				,"BucketSort (sqrt(n) Buckets)"
				,"BucketSort (ln(n) Buckets)"
				,"Counting Sort"
				,"RadixSort"
				,"RadixSort (Using CountingSort)"
				,"RadixSort (Using Lists)"
				,"MergeSort"
				,"QuickSort (Lomuto's partition scheme)"
				,"QuickSort (Hoare's partition scheme)"
				,"Java's Built-in sort"
				,"BubbleSort"
				,"SelectionSort"
				,"InsertionSort"
				,"ShellSort"};
		
		public static String[] titles = {"-------------- HeapSort --------------"
				,"-------------- BucketSort --------------"
				,"-------------- CountingSort --------------"
				,"-------------- RadixSort --------------"
				,"-------------- MergeSort & QuickSort --------------"
				,"-------------- O(n^2) Sorting Algorithms (Bubble,Insertion,Selection,Shell) --------------"
				};
		
		public static String[] info = {"BucketSort, RadixSort and CountingSort works only on Non-Negative numbers!"};
		
		public TupleV2 Test(int times,int size,int minValue,int maxValue,int type)
		{
			int optimize = 5;
			times+=optimize; //optimize results
			long starttime=0, totaltime;
			int[] arr = new int[0];
			long[] mintime = new long[4];
			Arrays.fill(mintime, Long.MAX_VALUE);
			long[] maxtime = new long[4];
			long[] avgtime = new long[4];
			
			for(int t=0;t<4;t++)//Different Tests
			for(int i=0;i<times;i++)
			{
				switch(t)
				{
					case 0: //Complete Random Array
						arr = createRandomIntegerArray(size, minValue, maxValue);
						break;
					case 1: //Opposite Sorted Array
						MirrorArray(arr);
						break;
					case 2: //Sorted Array
						//Just take the array from previous part.
						break;
					case 3: //Almost Random Array
						if(minValue == 0) minValue=1;
						arr = createRandomIntegerArray(size,maxValue - (maxValue)/minValue, maxValue); //need to create a new function for this
						break;
				}
				switch(type)
				{
					case 0:
						starttime = System.nanoTime();
						HeapSort(arr);
						break;
					case 1:
						starttime = System.nanoTime();
						BucketSort(arr,size/10);
						break;
					case 2:
						int sqrt_size = (int) Math.sqrt(size);
						starttime = System.nanoTime();
						BucketSort(arr,sqrt_size);
						break;
					case 3:
						int ln_size = (int) Math.log(size);
						starttime = System.nanoTime();
						BucketSort(arr,ln_size);
						break;
					case 4:
						starttime = System.nanoTime();
						CountingSort(arr);
						break;
					case 5:
						starttime = System.nanoTime();
						RadixSort(arr);
						break;
					case 6:
						starttime = System.nanoTime();
						RadixSort_With_CountingSort(arr);
						break;
					case 7:
						starttime = System.nanoTime();
						RadixSort_With_Lists(arr);
						break;
					case 8:
						starttime = System.nanoTime();
						MergeSort(arr);
						break;
					case 9:
						starttime = System.nanoTime();
						QuickSortLomuto(arr);
						break;
					case 10:
						starttime = System.nanoTime();
						QuickSortHoare(arr);
						break;
					case 11:
						starttime = System.nanoTime();
						Arrays.sort(arr);
						break;
					case 12:
						starttime = System.nanoTime();
						BubbleSort(arr);
						break;
					case 13:
						starttime = System.nanoTime();
						SelectionSort(arr);
						break;
					case 14:
						starttime = System.nanoTime();
						InsertionSort(arr);
						break;
					case 15:
						starttime = System.nanoTime();
						ShellSort(arr);
						break;

				}
				
				totaltime = System.nanoTime()-starttime;
				if(i>=optimize){
				avgtime[t] += totaltime;
				mintime[t] = (totaltime<mintime[t])? totaltime : mintime[t];
				maxtime[t] = (totaltime>maxtime[t])? totaltime : maxtime[t];
				}
			}
			
			for(int i = 0;i<avgtime.length;i++)
				avgtime[i] = (avgtime[i]/(times-optimize));
				
			return new TupleV2(algorithms[type], avgtime,mintime,maxtime);
		}

	}
}
