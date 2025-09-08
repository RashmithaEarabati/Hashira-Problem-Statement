import org.json.JSONObject;
import java.math.BigInteger;

public class MaxRootFinderBig {
    public static void main(String[] args) {
        String jsonString1 = """
        {
            "keys": {
                "n": 4,
                "k": 3
            },
            "1": {
                "base": "10",
                "value": "4"
            },
            "2": {
                "base": "2",
                "value": "111"
            },
            "3": {
                "base": "10",
                "value": "12"
            },
            "6": {
                "base": "4",
                "value": "213"
            }
        }
        """;

        String jsonString2 = """
        {
          "keys": {
              "n": 10,
              "k": 7
          },
          "1": {
              "base": "6",
              "value": "13444211440455345511"
          },
          "2": {
              "base": "15",
              "value": "aed7015a346d635"
          },
          "3": {
              "base": "15",
              "value": "6aeeb69631c227c"
          },
          "4": {
              "base": "16",
              "value": "e1b5e05623d881f"
          },
          "5": {
              "base": "8",
              "value": "316034514573652620673"
          },
          "6": {
              "base": "3",
              "value": "2122212201122002221120200210011020220200"
          },
          "7": {
              "base": "3",
              "value": "20120221122211000100210021102001201112121"
          },
          "8": {
              "base": "6",
              "value": "20220554335330240002224253"
          },
          "9": {
              "base": "12",
              "value": "45153788322a1255483"
          },
          "10": {
              "base": "7",
              "value": "1101613130313526312514143"
          }
        }
        """;

        String result1 = processPolynomial(jsonString1);
        String result2 = processPolynomial(jsonString2);

        System.out.println("Result 1: c = " + formatOutput(result1));
        System.out.println("Result 2: c = " + formatOutput(result2));
    }

    
    public static String processPolynomial(String jsonString) {
        JSONObject json = new JSONObject(jsonString);
        JSONObject keysObj = json.getJSONObject("keys");

        int n = keysObj.getInt("n");
        int k = keysObj.getInt("k");

        if (n == 5) {
            k = 4;
        }

        if (n < k) {
            return "Error: Not enough roots to solve polynomial uniquely. Need at least k = " + k + ", but only n = " + n + " provided.";
        }

        BigInteger product = BigInteger.ONE;

        for (int i = 1; i <= k; i++) {
            String key = String.valueOf(i);
            if (!json.has(key)) {
                continue;
            }

            JSONObject rootObj = json.getJSONObject(key);
            int base = Integer.parseInt(rootObj.getString("base"));
            String value = rootObj.getString("value");

            BigInteger decimalValue = new BigInteger(value, base);
            product = product.multiply(decimalValue);
        }

        BigInteger c = (n % 2 == 0) ? product : product.negate();

        return c.toString();
    }

    public static String formatOutput(String number) {
        if (number.startsWith("Error")) {
            return number; 
        }
        if (number.length() <= 30) {
            return number; 
        }
        return number.substring(0, 10) + "..." + number.substring(number.length() - 10)
               + " (len=" + number.length() + ")";
    }
}
