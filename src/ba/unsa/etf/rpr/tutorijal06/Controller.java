
package ba.unsa.etf.rpr.tutorijal06;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
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
    public String uporediSaJmbg;
    private boolean telefonValidno;
    public String datumZaIspis = "";
    public String uporeditiDatum = "";

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
        if (n.length() < 8) return false;
        int d = 0, m = 0, g = 0;
        String dan = "", mjesec = "", godina = "";
        if (n.length() == 8) {
            dan = n.substring(0, 2);
            mjesec = n.substring(2, 4);
            godina = n.substring(4, 8);
        }
        else if (n.length() == 10) {
            if (n.charAt(2) == n.charAt(5) && (n.charAt(2) == '.' || n.charAt(2) == '/' || n.charAt(2) == '-')) {
                dan = n.substring(0, 2);
                mjesec = n.substring(3, 5);
                godina = n.substring(6, 10);
            }
        } else return false;
        try {
            d = Integer.parseInt(dan);
            m = Integer.parseInt(mjesec);
            g = Integer.parseInt(godina);
        }catch (Exception ex){}

        if (d==0 || m==0 || g==0 )return false;
        if (g > 2018 || (g == 2018 && m == 12)) return false;
        boolean daLiJeDatumDobar = true;
        try {
            LocalDate.of(g, m, d);
        } catch (DateTimeException e) {
            daLiJeDatumDobar = false;
        }
        datumZaIspis = "" + d + "." + m + "." + g +".";
        int g2 = g % 1000;
        uporediSaJmbg = "" + d + m + g2;
        return daLiJeDatumDobar;

    }

    private boolean ispravanJMBG(String n) {
        if (n.length() < 13 || n.length() > 13) return false;
        uporeditiDatum = n.substring(0, 6);
        int regija = (n.charAt(7) - '0') * 10 + (n.charAt(8) - '0');
        if (regija < 0 || regija > 96) return false;
        int jedinstveniBroj = (n.charAt(9) - '0') * 100 + (n.charAt(10) - '0') * 10 + (n.charAt(11) - '0');
        if (!((jedinstveniBroj>  0 &&jedinstveniBroj <= 499)||(jedinstveniBroj>=500 &&jedinstveniBroj<=999))) return false;
        int kontrolnaCifra = 11 - ((7 * ((n.charAt(0) - '0') + (n.charAt(6) - '0')) + 6 * ((n.charAt(1) - '0') + (n.charAt(7) - '0')) + 5 * ((n.charAt(2) - '0') + (n.charAt(8) - '0')) + 4 * ((n.charAt(3) - '0') + (n.charAt(9) - '0')) + 3 * ((n.charAt(4) - '0') + (n.charAt(10) - '0')) + 2 * ((n.charAt(5) - '0') + (n.charAt(11) - '0'))) % 11);
        if (kontrolnaCifra > 9) kontrolnaCifra = 0;
        if (kontrolnaCifra != (n.charAt(12) - '0')) return false;
        return true;
    }

    private boolean ispravanEmail(String n) {

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

    private boolean ispravanTelefon(String n) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Pattern pattern1 = Pattern.compile("\\d{3}-\\d{6}");
        Matcher matcher = pattern.matcher(n);
        Matcher matcher1 = pattern1.matcher(n);
        return (matcher.matches()|| matcher1.matches());
    }

    @FXML
    public void initialize() {
        imeValidno = false;
        prezimeValidno = false;
        indeksValidan = false;
        jmbgValidno = false;
        datumValidno = false;
        emailValidno = false;
        telefonValidno = false;
        ime.getStyleClass().add("poljeNijeIspravno");
        prezime.getStyleClass().add("poljeNijeIspravno");
        index.getStyleClass().add("poljeNijeIspravno");
        jmbg.getStyleClass().add("poljeNijeIspravno");
        datum.getStyleClass().add("poljeNijeIspravno");
        email.getStyleClass().add("poljeNijeIspravno");

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

    }


    public void dugmeKliknuto(ActionEvent actionEvent) {
        String rod = mjesto.getEditor().getText();
        String imeNovo = ime.getText();
        String prezimeNovo = prezime.getText();
        String datumNovo = datum.getText();
        String jmbgNovi = jmbg.getText();
        String izdvojiDatum = "";
        if (jmbgNovi.length() == 13) izdvojiDatum = jmbgNovi.substring(0, 7);
        if (ispravanDatum(datumNovo)) {
            datumValidno = true;
        } else {
            datumValidno = false;
            datum.getStyleClass().add("poljeNijeIspravno");
        }

        if (ispravanJMBG(jmbgNovi) && izdvojiDatum != "" && izdvojiDatum.equals(uporediSaJmbg)) {
            jmbgValidno = true;
        } else {
            jmbgValidno = false;
            jmbg.getStyleClass().add("poljeNijeIspravno");
        }
        String emailNovi = email.getText();
        if (ispravanEmail(emailNovi)) {
            emailValidno = true;

        } else {
            emailValidno = false;
            email.getStyleClass().add("poljeNijeIspravno");
        }
        telefonValidno=ispravanTelefon(telefon.getText());
        if(telefonValidno)telefon.getStyleClass().add("poljeIspravno");
        if (formularValidan()) {
            System.out.println("Student: " + imeNovo + " " + prezimeNovo + " ( " + index.getText() + " )");
            System.out.println("JMBG: " + jmbgNovi + ", datum rođenja: " + datumZaIspis +", mjesto rodđenja: "+ rod);
            System.out.println("Ulica stanovanja: " + adresa.getText() + ",broj telefona: " + telefon.getText());
            System.out.println("Email adresa: " + emailNovi);
            System.out.println(status.getValue().toString() + " student, smjer: " + smjer.getValue().toString() + " godina: " + godina.getValue());
            if (pripadnost.isSelected()) System.out.println("Postoji neka od boračke pripadnosti");
            else System.out.println("Ne postoji nikakva boračka pripadnost");
        }
        if (!formularValidan()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nije validno!");
            alert.setHeaderText("Popunjeni podaci nisu validni!");
            alert.setContentText("Polja označena sa crvenom bojom nisu validna, molimo Vas da ispravino popunite formular!");
            alert.show();
        }


    }


}