package lang;

/**
 * Created by bjlizhitao on 2017/7/18.
 */
public enum EnumTest {
    TEST1("1", "TEST1"),
    TEST2("2", "TEST2"),
    TEST3("3", "TEST3");

    private String code;
    private String desc;

    EnumTest(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static void main(String[] args) {
        System.out.println(EnumTest.TEST1.getCode());
    }
}
