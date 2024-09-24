package sokoban;

public class app {
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;


        int output = nums.length;
        int input = nums.length - 1;

        for (int i = 0; i < nums.length; i ++) {
            while (nums[input] == val) {
                if (i != input) {
                    input--;
                } else {
                    System.out.println("nums: " + nums[0] + nums[1] + nums[2] + nums[3]);
                    System.out.print(output);
                    return;
                }
                
            }

            if (nums[input] != val) {
                nums[i] = nums[input];
                nums[input] = val;
                if (i != input) {
                    input--;
                } else{
                    System.out.println("nums: " + nums[0] + nums[1] + nums[2] + nums[3]);
                    System.out.println(output);
                    return;
                }
            }
        }
    }
}
