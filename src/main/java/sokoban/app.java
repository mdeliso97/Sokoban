package sokoban;

public class app {
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;

        int input = nums.length - 1;

        for (int i = 0; i < nums.length; i ++) {
            while (nums[input] == val) {
                if (i != input) {
                    input--;
                } else {
                    System.out.println("nums: " + nums[0] + nums[1] + nums[2] + nums[3] + nums[4] + nums[5] + nums[6] + nums[7]);
                    System.out.print(input + 1);
                    return;
                }
                
            }

            if (nums[i] == val) {
                nums[i] = nums[input];
                //nums[input] = val;
                input--;
            }

            if (i == input) {
                System.out.println("nums: " + nums[0] + nums[1] + nums[2] + nums[3] + nums[4] + nums[5] + nums[6] + nums[7]);
                System.out.print(input + 1);
                return;
            }
        }
    }
}
