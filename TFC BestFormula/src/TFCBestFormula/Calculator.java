package TFCBestFormula;

public class Calculator {
    int target;
    int[] required;
    int[] output;

    public void setValue(int target, int[] required){
        this.target = target;
        this.required = required;
    }

    public int[] calculation(){
        int i;
        for(i = 1; i <= 12; i++){
            output = Calculation.run(i, target, required);
            if(output[0] != 0){
                break;
            }
        }
        return output;
    }
}

class Calculation{
    public static int[] value = {-3, -6, 2, 7, -9, -15, 13, 16};
    public static int[] run(int n, int target, int[] required){
        int j, k = 0, target_c;
        long i, o;
        int[] octal = new int[n];
        int[] output = new int[n + required.length];

        for(i = (long)(Math.pow(8, n) - 1); i >= 0; i--){
            target_c = 0;
            o = i;
            for(j = 0; j < n; j++){ //十进制转八进制
                octal[n - j - 1] = (int)(o % 8);
                o /= 8;
            }
            for(j = 0; j < n; j++){ //计算
                target_c += value[octal[j]];
            }
            target_c += (value[required[0]] + value[required[1]] + value[required[2]]);

            if(target == target_c){ //校验
                System.arraycopy(octal, 0, output, 0, octal.length);
                System.arraycopy(required, 0, output, n, required.length);
                k = 1;
            }
            if(k == 1){ //判断
                break;
            }
        }

        return output;
    }
}