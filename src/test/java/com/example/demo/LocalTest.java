package com.example.demo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.*;
import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.demo.dto.SettleReportFormResultDTO;
import com.example.demo.enums.OrderTypeEnum;
import com.example.demo.pojo.FaultInverter;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserType;
import com.example.demo.proxy.UserInvokeHandle;
import com.example.demo.service.IUserService;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.EnumUtil;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.security.action.GetPropertyAction;

import java.io.*;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.AccessController;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author haojie.cui
 * @since 2021/12/17 15:48
 */
public class LocalTest {

    @Test
     void test1() {
        List<Integer> nums = Arrays.asList(8, 4, 2, 9, 6, 7);
        System.out.println("stream.count() = " + nums.stream().count());
        Integer reduce = nums.stream().reduce(0, (n1, n2) -> n1 + n2);
        System.out.println("reduce = " + reduce);
        Stream<Integer> stream = nums.stream();
        nums.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).forEach(System.out::println);
        nums.sort((a, b) -> b.compareTo(a));
        System.out.println("nums = " + nums);
        System.out.println("nums.stream().max((a,b) -> a.compareTo(b)).get() = " + nums.stream().max(Integer::compareTo).get());
        System.out.println("nums.stream().min((a, b) -> a.compareTo(b)).get() = " + nums.stream().min(Integer::compareTo).get());
        System.out.println("nums.stream().findFirst().orElse(1) = " + nums.stream().anyMatch(e -> e == 2));
    }

    @Test
    public void test2() {
        Integer r = new Random().nextInt(2) + 1;
        System.out.println("r = " + r);
        long ra = Math.round(Math.random() * 10);
        System.out.println("ra = " + ra);

        double random = Math.random();
        System.out.println("random = " + random);

        BigDecimal num = new BigDecimal("356.16659");
        System.out.println(num.setScale(1, BigDecimal.ROUND_UP)); // scale: 精确到小数点后几位,进行四舍五入
        System.out.println(num.setScale(2, RoundingMode.DOWN));//不进行四舍五入

        System.out.println("DateUtil.date(1639981168836l) = " + DateUtil.date(1639981168836l));
    }

    @Test
    public void test3() {
        System.out.println("LocalDateTime.now().plusDays(1) = " + LocalDateTime.now().plusDays(1));
        System.out.println("LocalDate.now().plusDays(1) = " + LocalDate.now().plusDays(1));
        DateTime time = DateUtil.parse("2021-12-21 12:12:12", "yyyy-MM-dd HH:mm:ss");
        DateTime dateTime2 = DateUtil.beginOfDay(time);
        DateTime dateTime = DateUtil.truncate(DateUtil.offsetDay(time, 1), DateField.DAY_OF_MONTH);
        System.out.println("dateTime = " + dateTime);
        long between = DateUtil.between(new Date(), DateUtil.offsetDay(DateUtil.truncate(DateUtil.parse("2021-12-20 10:41:36", "yyyy-MM-dd HH:mm:ss"), DateField.DAY_OF_MONTH), 1), DateUnit.HOUR);
        System.out.println("between = " + between);
        // DateUtil.beginOfDay(new Date()) 等同 DateUtil.truncate(new Date(), DateField.DAY_OF_MONTH)
        DateTime dateTime1 = DateUtil.offsetDay(DateUtil.beginOfDay(new Date()), 1);
        System.out.println("dateTime1 = " + dateTime1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String of = LocalDateTime.of(2021, 12, 22, 14, 30, 20).format(dateTimeFormatter);
        System.out.println("of = " + of);
        String now = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println("now = " + now);
        // 2月前
        System.out.println("dateTimeFormatter.toString() = " + dateTimeFormatter.toString());
        String beforeMonth = DateUtil.offsetMonth(dateTime2, -2).toString("yyyy-MM-dd HH:mm:ss");
        System.out.println("beforeMonth = " + beforeMonth);

    }

    @Test
    public void test4() {
        String str = "perfect,";
        System.out.println("str.substring(0, str.length() - 1) = " + str.lastIndexOf(","));

        Set<String> sets = new HashSet<>();
        sets.add("C001");
        sets.add("C002");
        sets.add("C001");
        sets.forEach(System.out::println);
    }

    @Test
    public void test5() throws Exception {
        String path = Objects.requireNonNull(User.class.getClassLoader().getResource("")).getPath();
        String objectFilePath = path + "out.txt";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(objectFilePath));
        objectOutputStream.writeObject(User.builder().userName("z").build());

        ObjectInput objectInput = new ObjectInputStream(new FileInputStream(objectFilePath));
        User user = (User) objectInput.readObject();
        System.out.println("user = " + user);
        System.out.println("path = " + objectFilePath);

    }

    @Test
    public void test6() throws IOException, CloneNotSupportedException {
        User user = new User("z", 1);
        User user1 = (User) user.clone();
        System.out.println("user1 = " + user1);
        List<String> a = null;
        a = Arrays.asList("1", "2", "3");
        a = Arrays.asList("1", "2");
        a = Arrays.asList("100", "200");
        System.out.println("a = " + a);
    }

    @Test
    public void test7() {
        List<UserType> userTypes = new ArrayList<>();
        userTypes.add(UserType.builder().code(1).value("内部").build());
        userTypes.add(UserType.builder().code(2).value("外部").build());

        List<User> users = new ArrayList<>();
        users.add(User.builder().userType(1).userName("A").build());
        users.add(User.builder().userType(2).userName("A").build());
        users.add(User.builder().userType(1).userName("B").build());
        users.add(User.builder().userType(2).userName("C").build());
        users.add(User.builder().userType(2).userName("D").build());
        users.add(User.builder().userType(1).userName("E").build());
        users.add(User.builder().userType(2).userName("E").build());

        //  Map<Integer, List<User>>
        Map<Integer, List<User>> collect = users.stream().collect(Collectors.groupingBy(User::getUserType, Collectors.toList()));
        System.out.println("collect = " + JSONUtil.toJsonStr(collect));
        // 每个类型对应的数据量
        Map<Integer, Long> collect1 = users.stream().collect(Collectors.groupingBy(User::getUserType, Collectors.counting()));
        System.out.println("collect = " + JSONUtil.toJsonStr(collect1));

        Map<String, List<Integer>> collect2 = users.stream().collect(Collectors.groupingBy(User::getUserName, Collectors.mapping(User::getUserType, Collectors.toList())));
        System.out.println("collect = " + JSONUtil.toJsonStr(collect2));

        Map<Integer, UserType> collect3 = userTypes.stream().collect(Collectors.toMap(UserType::getCode, e -> e));
        System.out.println("JSONUtil.toJsonStr(collect3 = " + JSONUtil.toJsonStr(collect3));
        Map<Integer, UserType> collect4 = userTypes.stream().collect(Collectors.toMap(UserType::getCode, Function.identity(), (a, b) -> a));
        System.out.println("JSONUtil.toJsonStr(collect4 = " + JSONUtil.toJsonStr(collect4));

        Map<Integer, Map<Integer, List<UserType>>> collect5 = userTypes.stream().collect(Collectors.groupingBy(UserType::getCode, Collectors.groupingBy(UserType::getCode, Collectors.toList())));
        System.out.println("JSONUtil.toJsonStr(collect5 = " + JSONUtil.toJsonStr(collect5));
        int a = 300, b = 10;
        Integer i = 300;
        System.out.println(a==i);

        Integer g = -128, h = -128;
        System.out.println(g == h);

    }

    @Test
    public void test8() {
        JSONObject jobj = new JSONObject();
        jobj.putOpt("name", "张阿红");
        jobj.putOpt("age", 12);

        System.out.println("jobj.get(\"age\") = " + jobj.get("age"));
        Set<String> set = new HashSet<>(Arrays.asList("http", "https"));
        System.out.println(JSONUtil.toJsonPrettyStr(set));

        Date currDate = new Date();
        currDate = DateUtil.offsetDay(DateUtil.parseDate(DateUtil.today()), 1);
        LocalDate date = LocalDate.now().plusDays(1);
        Date date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("currDate = " + currDate);
        System.out.println(DateUtil.today());
        System.out.println("date = " + date1);
    }

    @Test
    public void test9() {
        User user = new User("abc", 208);
        User user1 = new User("def", 20);
        BeanUtil.copyProperties(user, user1, true);
        System.out.println("user1 = " + user1);

        int i = RandomUtil.randomInt(2);
        System.out.println("i = " + i);

        long time = System.currentTimeMillis();
        String s = DateUtil.date(time).toString("yyyy-MM-dd HH:mm:ss");
        System.out.println("s = " + s);
        String javaHome = AccessController.doPrivileged(
                new GetPropertyAction("java.home"));
        System.out.println("javaHome = " + javaHome);

        BigDecimal settlementAmount = BigDecimal.ZERO;
        System.out.println("settlementAmount = " + settlementAmount);
    }

    @Test
    public void test10() {
        String inverterPower = "12.78,10.90,20.01";
        int[] ints = StrUtil.splitToInt(inverterPower, ",");
        int asInt = Arrays.stream(ints).min().getAsInt();
        System.out.println("asInt = " + asInt);
        Integer num = 1023;
        String s = String.valueOf(num);
        System.out.println("s = " + s);
    }

    @Test
    public void test11() {
        List<FaultInverter> inverterList = new ArrayList<>();
        FaultInverter f1 = FaultInverter.builder().inverterSn("SN0001").inverterPower(125).build();
        FaultInverter f2 = FaultInverter.builder().inverterSn("SN0003").inverterPower(124).build();
        FaultInverter f3 = FaultInverter.builder().inverterSn("SN0004").inverterPower(124).build();
        inverterList.add(f1);
        inverterList.add(f2);
        inverterList.add(f3);
        // 取逆变器功率最小值记录
        FaultInverter faultInverter = inverterList.stream().min(Comparator.comparing(FaultInverter::getInverterPower)).get();
        System.out.println("faultInverter = " + faultInverter);
        String orderNo = "WX20220120000001";
        inverterList = inverterList.stream().map(e -> {
            e.setOrderNo(orderNo);
            return e;
        }).collect(Collectors.toList());

    }

    @Test
    public void test12() {
        Integer[] a = {9, 1, 5, 7, 3};
/*        int temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length -i - 1; j++) {
                if(a[j] < a[j + 1]){
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }*/
        Arrays.sort(a);
        Arrays.sort(a, Comparator.reverseOrder());
        for (int i : a) {
            System.out.println("i = " + i);
        }

        System.out.println("-----------------整数倒序-----------------");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        nums.sort((u, e) -> e.compareTo(u));
        nums.forEach(System.out::println);
    }

    @Test
    public void test13() {
        User u1 = new User("a", 18);
        User u2 = new User("a", 18);
        System.out.println(StrUtil.format("hashCode比较对象:{}", (u1.hashCode() == u2.hashCode())));
        System.out.println(StrUtil.format("toString比较对象:{}", u1.toString().equals(u2.toString())));
        System.out.println(StrUtil.format("equals比较对象:{}", u1.equals(u2)));
        System.out.println(u1 == u2);

        Integer num1 = 2;
        int num2 = 7;
        System.out.println("ratio = " + NumberUtil.round((double) num1 / (double) num2, 2, RoundingMode.DOWN));

        BigDecimal increaseDecreaseAmount = new BigDecimal("-100.023");
        System.out.println("increaseDecreaseAmount = " + increaseDecreaseAmount);
    }

    //售后PC:管理看板
    @Test
    // 工单省公司别及闭环率
    public void test14() {
        Map<String, List> provinceRatioMap = new HashMap<>();
        User u1 = new User("b", 1);
        User u2 = new User("b", 1);
        User u3 = new User("a", 1);
        User u4 = new User("a", 1);
        User u5 = new User("c", 1);
        User u6 = new User("d", 1);
        User u7 = new User("d", 1);
        User u8 = new User("b", 1);
        User u9 = new User("e", 1);
        User u10 = new User("d", 1);
        User u11 = new User("d", 1);

        List<User> userList = Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11);
        List<User> endList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(userList)) {
            Map<String, Integer> map = userList.stream().collect(Collectors.groupingBy(User::getUserName, Collectors.summingInt(User::getUserType)));
            if (CollectionUtil.isNotEmpty(map)) {
                List<String> stringList = new ArrayList<>(map.keySet());
                for (String s : stringList) {
                    User user = new User();
                    user.setUserName(s);
                    user.setCount(map.get(s));
                    user.setRatio(NumberUtil.round((double) map.get(s) / (double) userList.size(), 2, RoundingMode.DOWN));
                    endList.add(user);
                }

                endList.sort((a, b) -> b.getCount().compareTo(a.getCount()));
            }
        }
        List<String> companyList = endList.stream().map(User::getUserName).collect(Collectors.toList());
        List<Integer> countList = endList.stream().map(User::getCount).collect(Collectors.toList());
        List<BigDecimal> ratioList = endList.stream().map(User::getRatio).collect(Collectors.toList());
        provinceRatioMap.put("companyList", companyList);
        provinceRatioMap.put("countList", countList);
        provinceRatioMap.put("ratioList", ratioList);

        // 最终结果
        System.out.println("provinceRatioMap = " + provinceRatioMap);

        endList.forEach(System.out::println);
    }

    @Test
    public void test15() {
        // 上上月1号至上上月末
        String beginOfLastMonth = DateUtil.beginOfMonth(DateUtil.offsetMonth(new Date(), -2)).toString("yyyy.MM.dd");
        String endOfLastMonth = DateUtil.endOfMonth(DateUtil.offsetMonth(new Date(), -2)).toString("yyyy.MM.dd");
        System.out.println(StrUtil.format("beginOfLastMonth-endOfLastMonth: {}-{}", beginOfLastMonth, endOfLastMonth));

        System.out.println(DateUtil.beginOfYear(new Date()).toString("yyyy-MM-dd"));
        System.out.println(DateUtil.beginOfMonth(new Date()).toString("yyyy-MM-dd"));
        System.out.println(DateUtil.beginOfDay(new Date()).toString("yyyy-MM-dd"));
    }

    @Test
    public void test16() {
        try {
            Class<User> c = User.class;
            User u1 = c.newInstance();
            u1.setUserName("通过.class new");
            System.out.println("u1 = " + u1);
            System.out.println("-------------------------");
            Class<User> user = (Class<User>) Class.forName("com.example.demo.pojo.User");
            //System.out.println("user.getName() = " + user.getName());
            User u2 = user.newInstance();
            u2.setUserName("通过 class.forname new");
            System.out.println("u2 = " + u2);
            System.out.println("------------------------");
            Class<User> user1 = (Class<User>) Class.forName("com.example.demo.pojo.User", true, User.class.getClassLoader());
            User u3 = user1.newInstance();
            u3.setUserName("通过类加载器new");
            System.out.println("u3 = " + u3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test17() throws UnknownHostException {
        String name = EnumUtil.getByEnum(OrderTypeEnum.class, "REPAIR");
        System.out.println("name = " + name);

        String host = InetAddress.getLocalHost().getHostAddress();
        System.out.println("host = " + host);

        Random s = new Random();
        System.out.println("s.nextInt(100) = " + s.nextInt(2));

        System.out.println("RandomUtil.randomInt(1,3) = " + RandomUtil.randomInt(2));

        String[] strs = {"a", "a1", "a12", "a123", "a1234"};

        for (String str : strs) {
            boolean bo = Pattern.matches("^a[0-9]+$", str);
            if (bo) {
                System.out.println("匹配：" + str);
            }
        }
        boolean bo = Pattern.matches("^.*[\u4e00-\u9fa5]+.*$", "abc中文abc");
        System.out.println(bo);
    }

    @Test
    public void test18() {
        User user = new User();
        user.setUserName("chan");
        User user1 = user.clone();
        System.out.println();
        Integer num = 100;
        double d = (double) num;
        System.out.println(d);
        System.out.println(100L / d);

        System.out.println(String.valueOf(DateUtil.month(DateUtil.parse("2021-12-04"))));

        String dateStr = DateUtil.beginOfDay(new Date()).toString("yyyy-MM-dd");
        System.out.println("dateStr = " + dateStr);
    }

    @Test
    public void test19() {
        boolean pattern = Pattern.matches("^a[^bc]$", "ac");
        if (pattern) {
            System.out.println("ac");
        }

    }

    @Test
    public void test23() {
        List<SettleReportFormResultDTO> list = new ArrayList<>(10);
        list.add(SettleReportFormResultDTO.builder().shouldSettleCountSum(100L).build());
        list.add(SettleReportFormResultDTO.builder().shouldSettleCountSum(120L).build());
        list.add(SettleReportFormResultDTO.builder().shouldSettleCountSum(140L).build());

       // list.sort((a, b) -> b.getShouldSettleCountSum().compareTo(a.getShouldSettleCountSum()));
       list.sort(Comparator.comparing(SettleReportFormResultDTO::getShouldSettleCountSum));
        list.forEach(System.out::println);


        //System.out.println("String.format(\"%s-%s\", \"a\",\"b\") = " + String.format("%s-%s-%d", "a", "b", 100));
    }

    @Test
    public void test01() {
        int a = 1, b = 5, c = 0;
        for (int i = a; i <= b; i++) {
            a *= i;
        }
        System.out.println(a);

        BigDecimal big = BigDecimal.valueOf(10);
        System.out.println("big = " + big);


        Pattern pattern = Pattern.compile("[0-9]*@[A-Za-z]");
        Matcher ma = pattern.matcher("1301290234567@u");
       boolean bo = Pattern.matches("[0-9]*@[A-Za-z]","1301290234567@u");
        if (bo) {
            System.out.println("a");
        } else {
        }


            System.out.println("23%24 == 0 = " +( 23%24 == 0));

        int begin = DateUtil.month(DateUtil.parse(null));
        System.out.println("begin = " + begin);

        LinkedHashMap<String, Object> mapRes = new LinkedHashMap<>();
        mapRes.put("key", 0);
        mapRes.put("key1", 0);
        mapRes.keySet().forEach(e -> System.out.println(e));

    }

    @Test
    public void testi1() {
        double t = new BigDecimal("0").doubleValue();
        System.out.println("t = " + t);
        String str = "9.90";
        str = str.toString().replace("0$", "");
        System.out.println("str = " + str);
        System.out.println(BigDecimal.ZERO);

        BigDecimal dec = new BigDecimal("-20.00".replaceFirst(".00", ""));
        System.out.println("dec = " + dec);
        String d = "-20.900";

        BigDecimal deci =  d.toString().endsWith(".00") ? new BigDecimal(d.replace(".00", ""))
                : d.toString().endsWith("0") ? new BigDecimal(d.replaceFirst("0+$", "")) : new BigDecimal(d);
        System.out.println("deci = " + deci);

       String year = DateUtil.format(new Date(), "yy");
        System.out.println("year = " + year);
    }

    @Test
    public void testi2() {
        HttpResponse response = HttpUtil.createPost("http://api.map.baidu.com/routematrix/v2/riding?")
                .execute();
      //  System.out.println("response.body() = " + response.body());
        System.out.println("abc".indexOf("c"));
        System.out.println("");
    }
    @Test
    public void test06(){
        //1,2,....12
        String str = "12,12,127";
        String reg = "^(([1-9]|1[0-2]),)*([1-9]|1[0-2])$";
        Pattern pattern=Pattern.compile(reg);
        Matcher isNum=pattern.matcher(str);
        System.out.println(isNum.matches());

    }
    @Test
    public void testi3() {
        String str = "abcobjabcdobj";
        System.out.println(str.contentEquals("obja"));
        final String na = new String("ab");
        na.concat("a");
       final UserType u = new UserType();
        u.setCode(1);
        u.setValue("a");

        UserType u1 = u;
        u1.setValue("h");
        u1.setCode(2);
        System.out.println("u = " + u + ",hcode = " + u.hashCode());
        System.out.println("u1 = " + u1+ ",hcode = " + u1.hashCode());

        UserType ut1 = new UserType();
        ut1.setCode(5);
        ut1.setValue("500");
        UserType ut2 = new UserType();
        ut2.setCode(10);
        ut2.setValue("1000");
        BeanUtil.copyProperties(ut1, ut2);
        System.out.println(ut1);

        Long l = 10L;
        int i = 10;
        double d = 10.5;
        System.out.println(l==i);System.out.println(i==d);
        String s1 = new String("1234567");
        String s2 =  new String("1234567");
        System.out.println(s1.hashCode() + "--------" + s2.hashCode());
    }

    @Test
    public void test04() {
    /*    //0000 0001
        //0000 0010
       // 0000 0011
        int a = 1, b = 2;
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println("a = " + a + ",b = " + b);*/
    /*Map<String, Integer> map = new HashMap<>(16);
        map.put("a", 10);
        Set<String> set = new HashSet<>(12);
        System.out.println(-1>>>4);*/
        UserType userType = new UserType();
        userType.setCode(1);
        userType.setValue("value");
        userType.setUser(User.builder().userType(10).userName("user").build());
       UserType userType1 = userType.clone();
        //UserType userType1 = new UserType();
        BeanUtil.copyProperties(userType, userType1);
        System.out.println(userType);
        System.out.println(userType1);

        System.out.println(userType.getUser() == userType1.getUser());
        System.out.println(userType.getUser() == userType1.getUser());
        userType1.getUser().setUserName("copy");
        System.out.println("----------later----------");

        System.out.println(userType);
        System.out.println(userType1);

    }

    @Test
    public void test05() throws UnknownHostException {
        String uid = UUID.randomUUID(true).toString(true);
        System.out.println(uid);
        AtomicInteger atint = new AtomicInteger(2);
        System.out.println("atint.get() = " + atint.getAndAdd(10));
        System.out.println(atint.getAndIncrement()); System.out.println(atint.getAndIncrement()); System.out.println("atint.get() = " + atint.get());
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        System.out.println(InetAddress.getLocalHost().getHostName());
        System.out.println(InetAddress.getLocalHost().getCanonicalHostName());

        User user = new User();
        System.out.println("User.class.getTypeName() = " + User.class.getTypeName());
        System.out.println("user.getClass().getSimpleName() = " + user.getClass().getSimpleName());
        System.out.println("user.getClass().getCanonicalName() = " + user.getClass().getCanonicalName());

    }
    @Test
    void test07(){
       Date l1 = DateUtil.parse("2022-09-05 12:45:12", "yyyy-MM-dd HH:mm:ss");
        Date l2 = DateUtil.parse("2022-09-08 10:10:10", "yyyy-MM-dd HH:mm:ss");
        long day = DateUtil.between(l1,l2,DateUnit.DAY);
        System.out.println("day = " + day);

        long num = (78 + 10) /10;
        System.out.println(num);
        Date l3 = DateUtil.parse("2022-09-16 10:10:10", "yyyy-MM-dd HH:mm:ss");
       boolean bo = DateUtil.beginOfDay(DateUtil.offsetDay(l3, 1)).after(new Date());
        System.out.println(DateUtil.beginOfDay(DateUtil.offsetDay(l3, 1)));
        System.out.println("bo = " + bo);


        LocalDateTime start=LocalDateTime.of(2018,4,1,10,10,10);
        LocalDateTime end=LocalDateTime.of(2018,3,28,10,10,11);
        Duration duration=Duration.between(start,end);
        System.out.println(duration.toMillis());
        System.out.println(duration.toMinutes());
        System.out.println(duration.toHours());
        System.out.println(duration.toDays());

    }

    @Test
    void test08(){
        List<User> users = new ArrayList<>(10);
        users.add(User.builder().userName("a").userType(0).count(10).build());
        users.add(User.builder().userName("cc").userType(1).count(10).build());
        users.add(User.builder().userName("a").userType(2).count(10).build());

        Map<String, List<User>> maps = new LinkedHashMap<>(12);
        maps = users.stream().collect(Collectors.groupingBy(e -> e.getUserName() + e.getCount(), Collectors.toList()));

        System.out.println(maps);

        System.out.println("DateUtil.beginOfDay(new Date()) = " + DateUtil.dayOfMonth(new Date()));

        double d = 0.88d;
        System.out.println(d < 1);

    }

    @Test
    void test09() {
        IUserService iUserService = new UserServiceImpl();
        UserInvokeHandle invokeHandle = new UserInvokeHandle(iUserService);
        IUserService iUserService1 = (IUserService) Proxy.newProxyInstance(iUserService.getClass().getClassLoader(), iUserService.getClass().getInterfaces(), invokeHandle);
        iUserService1.getByToken("");

        LocalDate localDate = DateUtil.offsetDay(new Date(), 1).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("localDate = " + localDate);

        System.out.println("124".getBytes());
    }

    @Test
    void httpUtil(){
        String url = "https://apigw.trinablue.com/csde-after-sale-api/V1.0.0.0/test/api/enums";
        String enums = HttpUtil.get(url);
        Object data = JSON.parseObject(enums).get("data");
        Object orderType = JSON.parseObject(data.toString()).get("orderType");
       // System.out.println("orderType = " + orderType);

        url = "https://apigw.trinablue.com/csde-after-sale-api/V1.0.0.0/test/api/dict";
        Map<String,Object> map = new HashMap<>(12);
        map.put("parentCode", "part-G1");
        String result = HttpUtil.createGet(url).form(map).execute().body();
     //   System.out.println("JSON.parse(result) = " + JSON.parse(result));

        url = "https://apigw.trinablue.com/csde-after-sale-api/V1.0.0.0/test/home-page/rightPart";
        String result1 = HttpUtil.createGet(url).header(Header.AUTHORIZATION, "Bearer G-523a8953-fad5-4cab-be03-8b08612b4e3d").execute().body();
        System.out.println("JSON.parse(result1) = " + JSON.parse(result1));
    }

}

