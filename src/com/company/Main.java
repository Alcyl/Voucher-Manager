package com.company;

import com.entities.Gutschein;
import com.entities.Spieler;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    private static final String PATHAUSGEGEBEN = "./Resource/data/gutschein_ausgegeben.csv";
    private static final String PATHEINGELOEST = "./Resource/data/gutschein_eingeloest.csv";
    private static final String PATHSPIELER = "./Resource/data/spieler.csv";

    private static List<Spieler> spielerAusgegeben = CsvReader.getSpieler(PATHSPIELER);
    private static List<Gutschein> gutscheineEingeloest = CsvReader.getGutscheinEingeloest(PATHEINGELOEST);
    private static List<Gutschein> gutscheineAusgegeben = CsvReader.getGutscheinAusgegeben(PATHAUSGEGEBEN);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
//        ausgabeAlles();
//        GutscheinFilter.sucheGutscheineNachNummer(new BigInteger("123456789012345678"));
//        ausgabeFindeAlleGutscheineEinesSpielers("113-88");
        GutscheinFilter.sortiereGutscheineNachNummer();
    }

    public static void ausgabeGutscheineAusgegeben() {
        System.out.println("--->AUSGEGEBEN<---");
        for (Gutschein gutschein : gutscheineAusgegeben) {
            System.out.println(gutschein.getNummer() + "\t" +
                    gutschein.getAuszahlbetrag() + "\t" +
                    gutschein.getSpielerId() + "\t" +
                    simpleDateFormat.format(gutschein.getDatum()));
        }
        System.out.println("");
    }

    public static void ausgabeGutscheineEingeloest() {
        System.out.println("--->EINGELOEST<---");
        for (Gutschein gutschein : gutscheineEingeloest) {
            System.out.println(gutschein.getNummer() + "\t" +
                    gutschein.getSpielerId() + "\t" +
                    gutschein.getAuszahlbetrag() + "\t" +
                    simpleDateFormat.format(gutschein.getDatum()));
        }
        System.out.println("");
    }

    public static void ausgabeSpieler() {
        System.out.println("--->Spieler<---");
        for (Spieler spieler : spielerAusgegeben) {
            System.out.println(spieler.getId() + "\t" +
                    spieler.getVorname() + "\t" +
                    spieler.getName());
        }
        System.out.println("");
    }

    public static void ausgabeAlles() {
        ausgabeGutscheineAusgegeben();
        ausgabeGutscheineEingeloest();
        ausgabeSpieler();
    }

    public static void ausgabeFindeAlleGutscheineEinesSpielers(String id) {
        List<Gutschein> gutscheine = GutscheinFilter.findeAlleGutscheineEinesSpielers(id);
        for (Gutschein gutschein : gutscheine) {
            System.out.println("Gutscheinnummer: " + gutschein.getNummer());
            System.out.println("Spieler-ID: " + gutschein.getSpielerId());
            System.out.println("Auszahlbetrag: " + gutschein.getAuszahlbetrag());
            System.out.println("Auszahldatum: " + simpleDateFormat.format(gutschein.getDatum()));
            System.out.println("\n");
        }
    }
}
