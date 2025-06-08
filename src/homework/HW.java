// 학번 : 22213489, 이름 : 표주원
public class HW {
    public int solution(String dartResult) {
        int[] r = new int[3];
        int i = 0;

        java.util.regex.Matcher m = java.util.regex.Pattern
                .compile("([0-9]{1,2})([SDT])([*#]?)")
                .matcher(dartResult);

        while (m.find()) {
            int v = Integer.parseInt(m.group(1));
            String b = m.group(2);
            String o = m.group(3);

            v = b.equals("S") ? (int)Math.pow(v, 1)
                    : b.equals("D") ? (int)Math.pow(v, 2)
                    : (int)Math.pow(v, 3);

            if (o.equals("*")) {
                v *= 2;
                if (i > 0) r[i - 1] *= 2;
            } else if (o.equals("#")) {
                v *= -1;
            }

            r[i++] = v;
        }

        return r[0] + r[1] + r[2];
    }
}
