import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //TODO:
        System.out.println("<------第一题------>");
        UseComputer ues = new UseComputer();
        ues.useCom(new Addition(), 1, 1);
        ues.useCom(new Subtract(), 3, 1);
        ues.useCom(new Multiplication(), 2, 2);
        ues.useCom(new Division(), 6, 3);

        //TODO:
        System.out.println("<------第二题------>");
        System.out.println("请输入成绩");
        int x = in.nextInt();
        try {
            if (x < 0 || x > 100) {
                throw new Exception("分数必须在 0—100 之间");
            } else {
                System.out.println(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO:
        System.out.println("<------第三题------>");
        int sum = 0;
        System.out.println("请输入N的值");
        int n = in.nextInt();
        while (n < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("N应该为非负整数");
            }
            System.out.println("请重新输入N的值");
            n = in.nextInt();
        }
        System.out.println("请输入各个整数的值");
        for (int i = 0; i < n; i++) {
            sum += in.nextInt();
        }
        if (n == 0)
            System.out.println(0);
        else
            System.out.println(sum / n);

        //TODO:
        System.out.println("<------第四题------>");
        MyDate birthday = new MyDate(2002, 10, 06);
        Employee employee = new Employee("qh", 99999, birthday) {
            @Override
            public double earnings() {
                setNumber(getNumber() + 99999);
                return getNumber();
            }
        };
        employee.earnings();
        System.out.println(employee.toString());


        //TODO:
        System.out.println("<------第五题------>");
        String s = "abcde";
        String[] words = new String[]{"a", "bb", "acd", "ace"};
        System.out.println(numMatchingSubseq(s,words));
    }

    public static int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < s.length(); ++i) {
            pos[s.charAt(i) - 'a'].add(i);
        }
        int res = words.length;
        for (String w : words) {
            if (w.length() > s.length()) {
                --res;
                continue;
            }
            int p = -1;
            for (int i = 0; i < w.length(); ++i) {
                char c = w.charAt(i);
                if (pos[c - 'a'].isEmpty() || pos[c - 'a'].get(pos[c - 'a'].size() - 1) <= p) {
                    --res;
                    break;
                }
                p = binarySearch(pos[c - 'a'], p);
            }
        }
        return res;
    }

    public static int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return list.get(left);
    }
}

//第一题用到的类
interface Compute {
    int computer(int n, int m);
}

class Division implements Compute {
    public int computer(int m, int n) {
        return m / n;
    }
}

class Addition implements Compute {
    public int computer(int n, int m) {
        return m + n;
    }
}

class Multiplication implements Compute {

    public int computer(int m, int n) {
        return m * n;
    }
}

class Subtract implements Compute {
    public int computer(int m, int n) {
        return m - n;
    }
}

class UseComputer {
    public UseComputer() {
    }

    public void useCom(Compute com, int one, int two) {
        System.out.println(com.computer(one, two));
    }
}

//第四题用到的类
class MyDate {
    int year, month, day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}

abstract class Employee {
    private String name;
    private double number;
    private MyDate birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    public Employee(String name, double number, MyDate birthday) {
        this.name = name;
        this.number = number;
        this.birthday = birthday;
    }

    public abstract double earnings();

    public String toString() {
        return "Employee{" + "姓名：" + name + "，余额：" + number + "，生日：" + birthday.year + "年"
                + birthday.month + "月" + birthday.day + "日" + "}";
    }
}

