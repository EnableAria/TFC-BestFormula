package TFCBestFormula;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SeedCalculator {
    static long seedLo, seedHi, seedLo_, seedHi_;
    static int target;

    public static int getTarget(){
        target = 0;
        seedLo = 0;
        seedHi = 0;
        seedLo_ = 0;
        seedHi_ = 0;
        long seed = Long.parseLong(ListenerCreator.anvilView.seed.getText());
        String id = "";
        if(Integer.parseInt(ConfigLoad.recipeID[AnvilView.runNum][1]) == 0 && AnvilView.recipeNum >= -1){
            id = "tfc:anvil/" + ConfigLoad.recipeID[AnvilView.runNum][0];
        }
        if(Integer.parseInt(ConfigLoad.recipeID[AnvilView.runNum][1]) > 0 && AnvilView.recipeNum > -1){
            if(AnvilView.runNum != 21){
                id = "tfc:anvil/" + ConfigLoad.metalList[AnvilView.recipeNum][0] + "_" + ConfigLoad.recipeID[AnvilView.runNum][0];
            }
            else {
                if(AnvilView.recipeNum == 4){
                    id = "tfc:anvil/" + ConfigLoad.langText[29] + "_" + ConfigLoad.recipeID[AnvilView.runNum][0];
                }
                else {
                    id = "tfc:anvil/" + ConfigLoad.metalList[AnvilView.recipeNum][0] + "_" + ConfigLoad.recipeID[AnvilView.runNum][0];
                }
            }
        }
        seedCalculation(seed);
        md5Hash(id);
        if(AnvilView.runNum >= ConfigLoad.internalNum){
            target = -40;
        }
        return 40 + target;
    }

    private static void seedCalculation(long seed){
        long i = seed ^ 7640891576956012809L;
        long j = i - 7046029254386353131L;
        seedLo_ = mixStafford13(i);
        seedHi_ = mixStafford13(j);
        if ((seedLo_ | seedHi_) == 0L) {
            seedLo_ = -7046029254386353131L;
            seedHi_ = 7640891576956012809L;
        }
        seedLo = nextLong();
        seedHi = nextLong();
    }

    private static void md5Hash(String id){
        byte[] aByte;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");    //生成一个MD5加密计算摘要
            md.update(id.getBytes());   //计算md5函数
            aByte = md.digest();
            long i = fromBytes(aByte[0], aByte[1], aByte[2], aByte[3], aByte[4], aByte[5], aByte[6], aByte[7]);
            long j = fromBytes(aByte[8], aByte[9], aByte[10], aByte[11], aByte[12], aByte[13], aByte[14], aByte[15]);
            targetCalculation(i ^ seedLo, j ^ seedHi);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void targetCalculation(long seedLo_, long seedHi_){
        seedLo = seedLo_;
        seedHi = seedHi_;
        SeedCalculator.seedLo_ = seedLo_;
        SeedCalculator.seedHi_ = seedHi_;
        if ((seedLo | seedHi) == 0L) {
            seedLo = -7046029254386353131L;
            seedHi = 7640891576956012809L;
        }
        target =  nextInt(154 - 2 * 40);
    }

    private static long mixStafford13(long input) {
        input = (input ^ input >>> 30) * -4658895280553007687L;
        input = (input ^ input >>> 27) * -7723592293110705685L;
        return input ^ input >>> 31;
    }

    private static long nextLong(){
        long i = seedLo_;
        long j = seedHi_;
        long k = Long.rotateLeft(i + j, 17) + i;
        j ^= i;
        seedLo_ = Long.rotateLeft(i, 49) ^ j ^ j << 21;
        seedHi_ = Long.rotateLeft(j, 28);
        return k;
    }

    private static long fromBytes(byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8){
        return (b1 & 0xFFL) << 56
                | (b2 & 0xFFL) << 48
                | (b3 & 0xFFL) << 40
                | (b4 & 0xFFL) << 32
                | (b5 & 0xFFL) << 24
                | (b6 & 0xFFL) << 16
                | (b7 & 0xFFL) << 8
                | (b8 & 0xFFL);
    }

    public static int nextInt() {
        return (int)nextLong();
    }

    private static int nextInt(int num){
        if (num <= 0) {
            throw new IllegalArgumentException("Bound must be positive");
        } else {
            long i = Integer.toUnsignedLong(nextInt());
            long j = i * (long)num;
            long k = j & 4294967295L;
            if (k < (long)num) {
                for(int l = Integer.remainderUnsigned(~num + 1, num); k < (long)l; k = j & 4294967295L) {
                    i = Integer.toUnsignedLong(nextInt());
                    j = i * (long)num;
                }
            }
            long i1 = j >> 32;
            return (int)i1;
        }
    }
}