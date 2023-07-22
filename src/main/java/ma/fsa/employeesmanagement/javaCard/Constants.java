package ma.fsa.employeesmanagement.javaCard;

public class Constants {
    public static final byte CLA_MONAPPLET = (byte) 0x04;
    /* Constants */
    public static final byte INS_TEST_CODE_PIN = 0x00;
    public static final byte GET_EMPLOYEE_ID = (byte) 0x01;
    public final static byte GET_FIRST_NAME = (byte) 0x02;
    public final static byte GET_LAST_NAME = (byte) 0x03;

    public final static short MAX_ID_SIZE = (short) 11;
    public final static short MAX_NAME_SIZE = (short) 10;
    public final static byte INS_SELECT = (byte) 0XA4;
    public final static int SW_NO_ERROR = (int) 0x9000;
}
