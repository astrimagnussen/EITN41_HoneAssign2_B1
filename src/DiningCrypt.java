import java.math.BigInteger;

public class DiningCrypt {

    private String SA;
    private String SB;
    private String DA;
    private String DB;
    private String M;
    private String broadcast;
    private int b;


    public DiningCrypt(String SA, String SB, String DA, String DB, String M, int b) {
        this.SA = SA;
        this.SB = SB;
        this.DA = DA;
        this.DB = DB;
        this.M = M;
        this.b = b;
    }

    public String send() {
        SA = convertHex(SA);
        SB = convertHex(SB);
        DA = convertHex(DA);
        DB = convertHex(DB);
        M = convertHex(M);
        String key = computeKey(SA, SB);
        if (b == 1) {
           broadcast= computeB1Message(key,M);
        } else {

        }
        return broadcast;
    }

    private String convertHex(String hex) {
        int i = Integer.parseInt(hex, 16);
        return Integer.toBinaryString(i);
    }

    private String computeKey(String sa, String sb) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            key.append(sa.charAt(i) ^ sb.charAt(i));
        }
        return key.toString();
    }

    private String computeB1Message(String key, String m){
        StringBuilder bin = new StringBuilder();
        for (int i = 0;i<16; i++){
            bin.append(key.charAt(i)^m.charAt(i));
        }

        return String.format("%040x", new BigInteger(1, bin.toString().getBytes()));
    }


}
