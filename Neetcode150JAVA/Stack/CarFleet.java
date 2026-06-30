import java.util.Arrays;

public class CarFleet {

    public static int carFleet(int target, int[] position, int[] speed) {

        int n = position.length;

        int[][] cars = new int[n][2];

        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        Arrays.sort(cars, (a, b) -> b[0] - a[0]);

        int fleets = 0;
        double currentTime = 0;

        for (int[] car : cars) {

            double time = (double)(target - car[0]) / car[1];

            if (time > currentTime) {
                fleets++;
                currentTime = time;
            }
        }

        return fleets;
    }

    public static void main(String[] args) {

        int target = 12;

        int[] position = {10,8,0,5,3};
        int[] speed = {2,4,1,1,3};

        System.out.println(carFleet(target, position, speed));
    }
}