package sokoban;

// 35. Search Insert Position O(log(n))
public class SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        int currMin;
        int currMax;

        // checks for outofbounds and empty array
        if (nums.length == 0) return 0;
        if (nums[0] > target) return 0;
        if (nums[nums.length - 1] < target) return nums.length;

        // select first split by assigning min and max for binary search
        if ((nums[Math.round(nums.length/2)] < target && nums[nums.length - 1] >= target)) {
            // target lays in upper part of subsection
            currMin = Math.round(nums.length/2);
            currMax = nums.length - 1;
        } else {
            // target lays in lower part of subsection
            currMin = 0;
            currMax = Math.round(nums.length/2);
        }
        

        while (!(nums[currMin] == target && nums[currMax] == target && currMax == currMin)) {
            
            // Case 1: tighten upper part subsection
            if (nums[currMax - Math.round((currMax - currMin) / 2)] > target) {
                currMax = currMax - Math.round((currMax - currMin) / 2);
            
            // Case 2: tighten lower part subsection
            } else {
                currMin = currMax - Math.round((currMax - currMin) / 2);
            }

            if (nums[currMin] == target) return currMin;
            if (nums[currMax] == target && nums[currMax - 1] != target) return currMax;
            if (nums[currMin] < target && nums[currMin + 1] > target) return currMin + 1;
            if (nums[currMax] > target && nums[currMax - 1] < target) return currMax;
        }
        return 0; // should never reach this point, if it does, there is one entry in the list and is the same as target
    }


    public static void main(String[] args) {
        int[] nums = {1,5,8,10, 11, 15, 16, 16, 18};
        int target = 19;

        System.out.print(searchInsert(nums, target));
    }
}
