package ma.fsa.employeesmanagement.javaCard;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadT1Client;
import com.sun.javacard.apduio.CadTransportException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EmployeeClient {
    private static CadT1Client cad;

    public Apdu sendApdu(byte cla, byte ins, byte p1, byte p2, byte length, byte[] data, byte le) throws IOException, CadTransportException {
        Apdu apdu = new Apdu();
        apdu.command[Apdu.CLA] = cla;
        apdu.command[Apdu.INS] = ins;
        apdu.command[Apdu.P1] = p1;
        apdu.command[Apdu.P2] = p2;
        apdu.setLe(le);
        if (data != null) {
            apdu.setDataIn(data);
        }
        cad.exchangeApdu(apdu);
        return apdu;
    }

    public void connect() {
        Socket socket;

        try {
            socket = new Socket("localhost", 9025);
            socket.setTcpNoDelay(true);
            BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());
            cad = new CadT1Client(input, output);
        } catch (Exception e) {
            System.out.println("Error: Unable to connect to the JavaCard");
            return;
        }

        /* Power up the card */
        try {
            cad.powerUp();
            System.out.println("power up succes");
        } catch (Exception e) {
            System.out.println("Error: sending Powerup command to the JavaCard");
            return;
        }
    }


      // Verifies the user-provided PIN against the on-card PIN.
    boolean verifyPIN(byte[] pin) throws Exception {
        // C-APDU: [CLA, INS, P1, P2, LC, [ PIN ]]
        Apdu apdu = new Apdu();
        apdu.command[Apdu.CLA] = Constants.CLA_MONAPPLET;
        apdu.command[Apdu.INS] = Constants.INS_TEST_CODE_PIN;
        apdu.command[Apdu.P1] = 0;
        apdu.command[Apdu.P2] = 0;

        apdu.setDataIn(pin);

        System.out.println(apdu);
        cad.exchangeApdu(apdu);
        System.out.println(apdu);

        if (apdu.getStatus() == Constants.SW_NO_ERROR) {
            System.out.println("code pin OK");
            return true;
        } else {
            System.out.println("Error: pin " + apdu.getStatus());
            return false;
        }
    }


    boolean checkEmployeId() throws Exception {
        Apdu apdu = new Apdu();
        apdu.command[Apdu.CLA] = Constants.CLA_MONAPPLET;
        apdu.command[Apdu.INS] = Constants.GET_EMPLOYEE_ID;
        apdu.command[Apdu.P1] = 0;
        apdu.command[Apdu.P2] = 0;

        System.out.println(apdu);
        cad.exchangeApdu(apdu);
        System.out.println(apdu);

        if (apdu.getStatus() == Constants.SW_NO_ERROR) {
            byte[] responseData = apdu.getDataOut();
            // Extract the short from the response data
            if (responseData.length >= 2) {
                short receivedShort = (short) (((responseData[0] & 0xFF) << 8) | (responseData[1] & 0xFF));
                System.out.println("Received short: " + receivedShort);
            }

            System.out.println("OK");
            return true;
        } else {
            System.out.println("Error: " + apdu.getStatus());
            return false;
        }
    }


    public void selectApplet() throws IOException, CadTransportException {
        /* Select the applet: create the SELECT APDU command */
        Apdu apdu = new Apdu();
        apdu.command[Apdu.CLA] = 0x00;
        apdu.command[Apdu.INS] = Constants.INS_SELECT;
        apdu.command[Apdu.P1] = 0x04;
        apdu.command[Apdu.P2] = 0x00;
        byte[] appletAID = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x00, 0x00};
        apdu.setDataIn(appletAID);
        cad.exchangeApdu(apdu);
        if (apdu.getStatus() != Constants.SW_NO_ERROR) {
            System.out.println("Error: selecting the applet");
            System.exit(1);
        } else {
            System.out.println("Applet selected");
        }
    }

    public void deselectApplet() {
        /* Power down the card */
        try {
            cad.powerDown();
        } catch (Exception e) {
            System.out.println("Error: sending Powerdown command to the JavaCard");
            return;
        }
    }

    private static byte[] parseByteArray(String s) {
        byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (byte) Integer.parseInt(s.substring(i, i + 2), 16);
        }
        return array;
    }
}
