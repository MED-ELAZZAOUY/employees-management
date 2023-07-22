package ma.fsa.employeesmanagement.javaCard;

import com.sun.javacard.apduio.Apdu;
import javafx.concurrent.Task;

import java.util.Arrays;

public class EmployeeTask extends Task<Boolean> {
    private byte[] entryPin;
    //EmployeeClient client = new EmployeeClient();

    public void setEntryPin(byte[] entryPin) {
        this.entryPin = entryPin;
        System.out.println(Arrays.toString(entryPin));
    }


    @Override
    protected Boolean call() throws Exception {
        EmployeeClient client = new EmployeeClient();
        client.connect();
        client.selectApplet();
        System.out.println(Arrays.toString(entryPin));

        Apdu apdu = client.sendApdu(Constants.CLA_MONAPPLET, Constants.INS_TEST_CODE_PIN, (byte) 0x00, (byte) 0x00, (byte) entryPin.length, entryPin, (byte) 0x00);
        System.out.println(Arrays.toString(entryPin));
        if (apdu.getStatus() == 0x6300) {
            client.deselectApplet();
            System.out.println("Error ");
            return false;

        } else{
            if (apdu.getStatus() == 0x9000) {
                client.deselectApplet();
                System.out.println("Succes ");

            }
        }
        return true;
    }
}

