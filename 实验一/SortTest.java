import java.util.*;

public class SortTest {
    public static void main(String[] args) {
        //ArrayList n = new ArrayList();
        List<Integer> n = new ArrayList<Integer>();
        //int[] n = new int[0];
        int BubbleNum=0;
        int MergeNum=0;
        int QuickNum=0;
            Random r = new Random(25);
            Scanner x = new Scanner(System.in);//构造一个Scanner对象，其传入参数为System.in
            System.out.println("请输入要生成的随机数的个数:");
            int i = x.nextInt();//读取一个int数值
            for(int j=0;j<i;j++){
                n.add(r.nextInt());
                //n[j]=r.nextInt();
            }
        int[] arr = n.stream().mapToInt(y -> y).toArray();
            Bubble B=new Bubble();
            BubbleNum=B.BubbleSort(arr,BubbleNum);
        System.out.println("冒泡算法比较操作执行"+BubbleNum+"次");
        Quick Q=new Quick();
        QuickNum=Q.QuickSort(arr, 0, arr.length-1,QuickNum);
        System.out.println("快速算法比较操作执行"+QuickNum+"次");
        Q.printQ();
        int[] temp=new int[arr.length];
        Merge M=new Merge();
        MergeNum=M.MergeSort(arr,0,arr.length-1,temp, MergeNum);
        System.out.println("合并算法比较操作执行"+MergeNum+"次");
        M.printM();
    }
}
class Bubble{
    int BubbleSort(int arr[],int BubbleNum){
        for (int i = 0; i < arr.length - 1; i++) {
            
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j + 1]; 
                    arr[j + 1] = arr[j]; 
                    arr[j] = (int) temp; 
                    BubbleNum++;
                }
            }
        }
        return BubbleNum;
    }
}
class Merge{
    int MergeRecuNum=-1;
    int MergeSort(int arr[],int left,int right,int[] temp,int MergeNum){
        MergeRecuNum++;
            int mid = (left + right) / 2;
            if (left < right) {
                MergeNum+=merge(arr, left ,mid, right, temp, MergeNum);
               
                MergeSort(arr, left, mid, temp,MergeNum);
              
                MergeSort(arr,mid + 1, right, temp, MergeNum);
            }
            return  MergeNum;
        }
        void printM(){
            System.out.println("合并算法递归操作执行"+MergeRecuNum+"次");
        }
/*
 *
 * @param arr  排序的原始数组
 * @param left  左边有序序列的初始索引
 * @param mid   中间索引
 * @param right    右边索引
 * @param temp    做中转的数组
 */
        //4 5 7 8   1 2 3 6
        public static int merge(int[] arr,int left,int mid,int right,int[] temp, int MergeNum) {
            int i=left; 
            int j=mid+1; 
            int t=0;       
           
            while(i<=mid&&j<=right) {
                if(arr[i]<arr[j]) {
                    temp[t]=arr[i++];
                
                    MergeNum++;
                }
                else {
                    temp[t]=arr[j++];
                }
           
                t++;
            }
          
            while(i<=mid) {
                temp[t]=arr[i];
                t++;
                i++;
            }
            while(j<=right) {
                temp[t]=arr[j];
                t++;
                j++;
            }
       
            int n=0;
            int tempLeft=left;
            while(tempLeft<=right){
                arr[tempLeft]=temp[n];
                n++;
                tempLeft++;
            }
            return  MergeNum;
        }
}

class Quick {
    int QuickRecuNum = -1;

    int QuickSort(int[] arr, int low, int high, int QuickNum) {
        QuickRecuNum++;
        int i, j, temp, t;
        if (low > high) {
            return 0;
        }
        i = low;
        j = high;
    
        temp = arr[low];
        while (i < j) {
            while (temp <= arr[j] && i < j) {
                j--;
                QuickNum++;
            }
         
            while (temp >= arr[i] && i < j) {
                i++;
                QuickNum++;
            }
          
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        
        arr[low] = arr[i];
        arr[i] = temp;
        
        QuickSort(arr, low, j - 1,QuickNum);
        
        QuickSort(arr, j + 1, high,QuickNum);
        return QuickNum;
    }
        void printQ () {
            System.out.println("快速算法递归操作执行" + QuickRecuNum + "次");
        }
    }