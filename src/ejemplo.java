import java.util.Arrays;

public class ejemplo {
    public static void main(String[] args) {
        long[] array = {5, 2, 5, 3, 6, 9};
        long result = 0;

        for (int i = 0; i < array.length; i++) {
            result = result * 10 + array[i];
        }

        System.out.println(array.length);
        System.out.println(result);

    }
}
