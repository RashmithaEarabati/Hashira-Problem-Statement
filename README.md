# 📌 MaxRootFinderBig  

A Java program to process polynomial root data (in JSON format), compute the constant term `c` and print the 'c' value  

The program supports multiple bases (binary, octal, decimal, hexadecimal, custom up to base-36) and handles very large numbers without overflow.  

---

## 🔹 Features
- Parses JSON input for polynomial root values.  
- contains the root values as base and value.  
- Multiplies the first `k` roots to compute the polynomial constant `c`.  
- Handles **extremely large numbers** using `BigInteger`.  
- Returns `c` as a **String** (safe from overflow).  
- 

---

## 🔹 Example Code

```java
String jsonString1 = """
{
    "keys": { "n": 4, "k": 3 },
    "1": { "base": "10", "value": "4"
