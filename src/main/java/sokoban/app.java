package sokoban;

// 35. Search Insert Position
public class app {
    public static int searchInsert(int[] nums, int target) {
        int currMin;
        int currMax;

        // select first split by assigning min and max for binary search
        if (nums[nums.length - 1] - target > Math.round(nums.length/2)) {
            // target lays in upper part of subsection
            currMin = Math.round(nums.length/2);
            currMax = nums[nums.length - 1];
        } else {
            // target lays in lower part of subsection
            currMin = 0;
            currMax = Math.round(nums.length/2);
        }
        

        while (!(nums[currMin] < target && nums[currMin + 1] > target) || !(nums[currMax] < target && nums[currMax + 1] > target)) {
            
            if (nums[currMax - Math.round((currMax - currMin) / 2)] > target) {
                currMax = currMax - Math.round((currMax - currMin) / 2);
            } else {
                currMin = currMax - Math.round((currMax - currMin) / 2);
            }

            if (nums[currMin] == target) return currMin;
            if (nums[currMax] == target) return currMax;
        }

        if (nums[currMin] < target && nums[currMin + 1] > target) return currMin + 1;
        if (nums[currMax] > target && nums[currMax - 1] < target) return currMax - 1;
        return -1;
    }


    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 2;

        System.out.print(searchInsert(nums, target));
    }
}
