public class Solution852 {
    public int peakIndexInMountainArray(int[] arr) {
        int length = arr.length;
        int peakIndex = 0;
        int startIndex = 0;
        int endIndex = length - 1;
        while(!isPeak(arr, peakIndex)) {
            if (isUphill(arr, peakIndex)) {
                startIndex = peakIndex;
            } else if (isDownhill(arr, peakIndex)) {
                endIndex = peakIndex;
            }

            int sum = startIndex + endIndex;
            int temp;
            if (sum % 2 == 0) {
                temp = sum / 2;
            } else {
                temp = sum / 2 + 1;
            }

            peakIndex = temp;

        }

        return peakIndex;
    }

    // 判断是否是上坡
    private boolean isUphill(int[] arr, int i) {
        if (i == 0) {
            // 第一个，只需判断它的后一个是不是比他大
            return arr[i] < arr[i + 1];
        } else if (i == arr.length - 1) {
            // 最后一个，只需判断它是不是比前一个大
            return arr[i] > arr[i - 1];
        }
        return arr[i] > arr[i - 1] && arr[i] < arr[i + 1];
    }

    // 判断是否是下坡
    private boolean isDownhill(int[] arr, int i) {
        if (i == 0) {
            // 第一个，只需判断它是不是比他后一个大
            return arr[i] > arr[i + 1];
        } else if (i == arr.length - 1) {
            // 最后一个，只需判断它是不是比前一个小
            return arr[i] < arr[i - 1];
        }
        return arr[i] < arr[i - 1] && arr[i] > arr[i + 1];
    }

    // 是否为峰顶
    private boolean isPeak(int[] arr, int i) {
        if (i == 0) {
            // 第一个，只需判断它是不是比他后一个大
            return arr[i] > arr[i + 1];
        } else if (i == arr.length - 1) {
            // 最后一个，只需判断它是不是比前一个大
            return arr[i] > arr[i - 1];
        }
        return arr[i] > arr[i + 1] && arr[i] > arr[i - 1];
    }

    public static void main(String[] args) {
        System.out.println(7/2 + 1);
    }
}
