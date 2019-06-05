package Advanced.Lesson_3.Task_2;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber implements Comparable<PhoneNumber> {
    private int countryCode;
    private int zoneCode;
    private int localNumber;

    PhoneNumber(int countryCode, int zoneCode, int localNumber) {
        this.countryCode = countryCode;
        this.zoneCode = zoneCode;
        this.localNumber = localNumber;
    }

    PhoneNumber(String pN) throws IllegalArgumentException {
        String regex = "\\+?(\\d+)[( ]+(\\d+)[) ]+(\\d+)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pN);

        if (m.find()) {
            this.countryCode = Integer.parseInt(m.group(1));
            this.zoneCode = Integer.parseInt(m.group(2));
            this.localNumber = Integer.parseInt(m.group(3));
        } else {
            throw new IllegalArgumentException("There is wrong a PhoneNumber formatting. It must match with the regex: \"" + regex + "\"");
        }
    }

    static PhoneNumber parsePhoneNumber(String pN) throws IllegalArgumentException {
        String regex = "\\+?(\\d+)[( ]+(\\d+)[) ]+(\\d+)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pN);

        if (m.find()) {
            int countryCode = Integer.parseInt(m.group(1));
            int zoneCode = Integer.parseInt(m.group(2));
            int localNumber = Integer.parseInt(m.group(3));

            return new PhoneNumber(countryCode, zoneCode, localNumber);
        }

        throw new IllegalArgumentException("There is wrong a PhoneNumber formatting. It must match with the regex: \"" + regex + "\"");
    }

    @Override
    public String toString() {
        return "+" + countryCode + " (" + zoneCode + ") " + localNumber;
    }

    @Override
    public int compareTo(PhoneNumber pN) {
        if (this.countryCode < pN.countryCode) {
            return -3;
        } else if (this.countryCode > pN.countryCode) {
            return 3;
        } else {
            if (this.zoneCode < pN.zoneCode) {
                return -2;
            } else if (this.zoneCode > pN.zoneCode) {
                return 2;
            } else {
                if (this.localNumber < pN.localNumber) {
                    return -1;
                } else if (this.localNumber > pN.localNumber) {
                    return 1;
                } else return 0;
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, zoneCode, localNumber);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof PhoneNumber && (o.toString().equals(this.toString())));
    }
}
