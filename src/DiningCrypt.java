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

    /**
     * Method to send the messages anonymous.
     *
     * @return The output string
     */
    public String send() {
        SA = convertHex(SA);
        SB = convertHex(SB);
        DA = convertHex(DA);
        DB = convertHex(DB);
        M = convertHex(M);
        String key = computeKey(SA, SB);
        if (b == 1) {
            broadcast = computeB1Message(key);
        } else {
            key = recalculateKey(key);
            broadcast = computeB0Message(key);
        }
        return broadcast;
    }

    /**
     * Convert hexadecimal to a binary string
     *
     * @param hex is the hexadecimal string
     * @return The binary string
     */
    private String convertHex(String hex) {
        int bin = Integer.parseInt(hex, 16);
        StringBuilder sb = new StringBuilder();

        if (Integer.toBinaryString(bin).length() < 16) {
            int diff = 16 - Integer.toBinaryString(bin).length();
            for (int i = 0; i < diff; i++) {
                sb.append(0);
            }
        }

        sb.append(Integer.toBinaryString(bin));
        return sb.toString();
    }

    /**
     * Compute the key to send with.
     *
     * @param sa , the key shared with Alice
     * @param sb , the key shared with Bob.
     * @return The computed String key.
     */
    private String computeKey(String sa, String sb) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            key.append(sa.charAt(i) ^ sb.charAt(i));
        }
        return key.toString();
    }

    /**
     * Computed the sended message if b = 1.
     *
     * @param key , the key to send the message with.
     * @return
     */
    private String computeB1Message(String key) {
        StringBuilder bin = new StringBuilder();
        //Calculate the new message with the key
        for (int i = 0; i < 16; i++) {
            bin.append(key.charAt(i) ^ M.charAt(i));
        }
        //Transform Stringbuilder to hex
        int decimal = Integer.parseInt(bin.toString(), 2);
        String binary = Integer.toString(decimal, 16);

        //Take care of lossed zeroes in the beginning of the hex
        String resulted = new String();
        if (binary.length() < 4) {
            int diff = 4 - binary.length();
            switch (diff) {
                case 1:
                    resulted = "0";
                    break;
                case 2:
                    resulted = "00";
                    break;
                case 3:
                    resulted = "000";
                    break;
                case 4:
                    resulted = "0000";
                    break;
            }
        }
        resulted = resulted + binary;

        return resulted;
    }

    private String recalculateKey(String key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char ch = M.charAt(i);
            String tmp = Character.toString(ch);
            if (tmp.compareTo("1") == 0) {
                ch = key.charAt(i);
                tmp = Character.toString(ch);
                if (tmp.compareTo("1") == 0) {
                    sb.append(0);
                } else {
                    sb.append(1);
                }
            } else {
                sb.append(key.charAt(i));
            }
        }
        return sb.toString();

    }


    private String computeB0Message(String key) {
        StringBuilder sb = new StringBuilder();
        sb.append(computeB1Message(key));

        return sb.toString();
    }

}
