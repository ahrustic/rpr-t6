
package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    public ComboBox mjesto;
    public ComboBox status;
    public ComboBox godina;
    public ComboBox smjer;
    public CheckBox pripadnost;
    public TextField ime;
    public TextField prezime;
    public TextField index;
    public TextField jmbg;
    public TextField datum;
    public TextField email;
    public TextField adresa;
    public TextField telefon;
    private boolean imeValidno;
    private boolean prezimeValidno;
    private boolean indeksValidan;
    private boolean jmbgValidno;
    private boolean datumValidno;
    private boolean emailValidno;
    private int brojTacaka = 0;

    public boolean formularValidan() {
        return (imeValidno && prezimeValidno && indeksValidan && jmbgValidno && datumValidno && emailValidno);
    }

    private boolean ispravnoIme(String n) {
        if (n.length() < 1 || n.length() > 20) return false;
        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= 'A' && n.charAt(i) <= 'Z') && !(n.charAt(i) >= 'a' && n.charAt(i) <= 'z'))
                return false;
        }
        return !n.trim().isEmpty();
    }

    private boolean ispravnoPrezime(String n) {
        if (n.length() < 1 || n.length() > 20) return false;
        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= 'A' && n.charAt(i) <= 'Z') && !(n.charAt(i) >= 'a' && n.charAt(i) <= 'z'))
                return false;
        }
        return !n.trim().isEmpty();
    }


    private boolean ispravanDatum(String n) {
        if (n.length() > 11 || n.length() < 11) return false;
        /*if (n.charAt(n.length()-1) != '.') return false;
        if (n.charAt(2) != '.') return false;
        if (n.charAt(5) != '.') return false;*/

        String regex = "^(.+).(.+.+.+.+.+).(.+).$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(n);
        if (!matcher.matches()) return false;




        int dan = Integer.parseInt(n.substring(0,1).trim());
        int mjesec = Integer.parseInt(n.substring(3,4).trim());
        int godina = Integer.parseInt(n.substring(6,10).trim());
        if (mjesec < 1 || mjesec > 12) return false;
        if (dan > 31) return false;
        if (godina > 2018) return false;
        if (godina == 2018 && mjesec == 12) return false;


        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) >= '0' && n.charAt(i) <= '9') {
                for (int j = 0; j < n.length(); j++) {
                    if (j > 0 && (n.charAt(j) == '.') && (n.charAt(j-1) == '.')) return false;
                    else if (n.charAt(j) == '.') return true;
                }
            }
        }

        return false;
    }

    private boolean ispravanJMBG(String n) {
        if (n.length() != 13) return false;
        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= '0' && n.charAt(i) <= '9')) return false;
        }
        return !n.trim().isEmpty();
    }

    private boolean ispravanEmail(String n) {
        /*if (n.charAt(n.length()-3) != '.' || n.charAt(n.length()-4) != '.') return false;
        for (int i = 0; i < n.length(); i++) if (n.charAt(i) == '@') return true;
        return false;*/

        String regex = "^(.+)@(.+.+.+.+.+).(.+)$";

        Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(n);
            if (matcher.matches()) return true;

        return false;
    }

    private boolean ispravanIndex(String n) {
        if (n.length() != 5) return false;
        for (int i = 0; i < n.length(); i++) if (!(n.charAt(i) >= '0' && n.charAt(i) <= '9')) return false;
        return !n.trim().isEmpty();
    }

    /*private boolean ispravnaAdresa(String n) {
        return !n.trim().isEmpty();
    }
    private boolean ispravanTelefon(String n) {
        for (int i = 0; i < n.length(); i++) if (n.charAt(i) == '/' || n.charAt(i) == '-') return true;
        return false;
    } NEPOTREBNO */

    @FXML
    public void initialize() {
        imeValidno = false;
        prezimeValidno = false;
        indeksValidan = false;
        jmbgValidno = false;
        datumValidno = false;
        emailValidno = false;
        //telefonValidno = false;
        //adresaValidno = false;
        ime.getStyleClass().add("poljeNijeIspravno");
        prezime.getStyleClass().add("poljeNijeIspravno");
        index.getStyleClass().add("poljeNijeIspravno");
        jmbg.getStyleClass().add("poljeNijeIspravno");
        datum.getStyleClass().add("poljeNijeIspravno");
        email.getStyleClass().add("poljeNijeIspravno");
        //telefon.getStyleClass().add("nijepopunjeno"); MOZE BITI PRAZNO
        //adresa.getStyleClass().add("nijepopunjeno"); MOZE BITI PRAZNO

        ime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravnoIme(n)) {
                    ime.getStyleClass().removeAll("poljeNijeIspravno");
                    ime.getStyleClass().add("poljeIspravno");
                    imeValidno = true;
                } else {
                    ime.getStyleClass().removeAll("poljeIspravno");
                    ime.getStyleClass().add("poljeNijeIspravno");
                    imeValidno = false;
                }
            }
        });
        prezime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravnoPrezime(n)) {
                    prezime.getStyleClass().removeAll("poljeNijeIspravno");
                    prezime.getStyleClass().add("poljeIspravno");
                    prezimeValidno = true;
                } else {
                    prezime.getStyleClass().removeAll("poljeIspravno");
                    prezime.getStyleClass().add("poljeNijeIspravno");
                    prezimeValidno = false;
                }
            }
        });
        index.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanIndex(n)) {
                    index.getStyleClass().removeAll("poljeNijeIspravno");
                    index.getStyleClass().add("poljeIspravno");
                    indeksValidan = true;
                } else {
                    index.getStyleClass().removeAll("poljeIspravno");
                    index.getStyleClass().add("poljeNijeIspravno");
                    indeksValidan = false;
                }
            }
        });
        jmbg.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanJMBG(n)) {
                    jmbg.getStyleClass().removeAll("poljeNijeIspravno");
                    jmbg.getStyleClass().add("poljeIspravno");
                    jmbgValidno = true;
                } else {
                    jmbg.getStyleClass().removeAll("poljeIspravno");
                    jmbg.getStyleClass().add("poljeNijeIspravno");
                    jmbgValidno = false;
                }
            }
        });
        datum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanDatum(n)) {
                    datum.getStyleClass().removeAll("poljeNijeIspravno");
                    datum.getStyleClass().add("poljeIspravno");
                    datumValidno = true;
                } else {
                    datum.getStyleClass().removeAll("poljeIspravno");
                    datum.getStyleClass().add("poljeNijeIspravno");
                    datumValidno = false;
                }
            }
        });
        email.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanEmail(n)) {
                    email.getStyleClass().removeAll("poljeNijeIspravno");
                    email.getStyleClass().add("poljeIspravno");
                    emailValidno = true;
                } else {
                    email.getStyleClass().removeAll("poljeIspravno");
                    email.getStyleClass().add("poljeNijeIspravno");
                    emailValidno = false;
                }
            }
        });
        /*adresa.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravnaAdresa(n)) {
                    adresa.getStyleClass().removeAll("nijepopunjeno");
                    adresa.getStyleClass().add("popunjeno");
                    adresaValidno = true;
                } else {
                    adresa.getStyleClass().removeAll("popunjeno");
                    adresa.getStyleClass().add("nijepopunjeno");
                    adresaValidno = false;
                }
            }
        });
        telefon.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanTelefon(n)) {
                    telefon.getStyleClass().removeAll("nijepopunjeno");
                    telefon.getStyleClass().add("popunjeno");
                    telefonValidno = true;
                } else {
                    telefon.getStyleClass().removeAll("popunjeno");
                    telefon.getStyleClass().add("nijepopunjeno");
                    telefonValidno = false;
                }
            }
        }); ADRESA I TELEFON IPAK MOGU BITI PRAZNI */
    }


}