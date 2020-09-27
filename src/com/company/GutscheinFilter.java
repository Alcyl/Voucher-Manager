package com.company;

import com.entities.Gutschein;
import com.entities.Spieler;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GutscheinFilter {
    private static final String PATHAUSGEGEBEN = "./Resource/data/gutschein_ausgegeben.csv";
    private static final String PATHEINGELOEST = "./Resource/data/gutschein_eingeloest.csv";
    private static final String PATHSPIELER = "./Resource/data/spieler.csv";

    private static List<Gutschein> gutscheineEingeloest = CsvReader.getGutscheinEingeloest(PATHEINGELOEST);
    private static List<Gutschein> gutscheineAusgegeben = CsvReader.getGutscheinAusgegeben(PATHAUSGEGEBEN);
    private static List<Spieler> alleSpieler = CsvReader.getSpieler(PATHSPIELER);

    public static List<Gutschein> gutscheineNichtEingeloest = new ArrayList<>();
    private static Gutschein gefundenerGutschein = new Gutschein();

    public static void sucheGutscheineNachNummer(BigInteger gutscheinNummer) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        // TODO: Error handling
//        int zaehler = 1;
        for (Gutschein gutschein : gutscheineEingeloest) {
            if (gutschein.getNummer().equals(gutscheinNummer)) {
                gefundenerGutschein.setNummer(gutschein.getNummer());
                gefundenerGutschein.setSpielerId(gutschein.getSpielerId());
                gefundenerGutschein.setAuszahlbetrag(gutschein.getAuszahlbetrag());
                gefundenerGutschein.setDatum(gutschein.getDatum());

                for (Spieler spieler : alleSpieler) {
                    if (gutschein.getSpielerId().equals(spieler.getId())) {
                        gefundenerSpieler.setId(spieler.getId());
                        gefundenerSpieler.setVorname(spieler.getVorname());
                        gefundenerSpieler.setName(spieler.getName());
                    }
                }
            }
//            else if (zaehler == gutscheineEingeloest.size()) {
//                System.out.println("Gutscheinnummer wurde nicht gefunden!");
//            }
//            zaehler++;
        }
//        TODO: NullPointerException
        if (gefundenerGutschein.getNummer().equals(gutscheinNummer)) {
            System.out.println("--->Gefundener Spieler<---\n" +
                    gefundenerSpieler.getId() + "\t" +
                    gefundenerSpieler.getVorname() + "\t" +
                    gefundenerSpieler.getName());

            System.out.println("--->Gefundener Gutschein<---\n" +
                    gefundenerGutschein.getNummer() + "\t" +
                    gefundenerGutschein.getSpielerId() + "\t" +
                    gefundenerGutschein.getAuszahlbetrag() + "\t" +
                    simpleDateFormat.format(gefundenerGutschein.getDatum()));
        } else {
            System.out.println("Gutscheinnummer wurde nicht gefunden!");
        }
    }


    public static List<Gutschein> findeAlleGutscheineEinesSpielers(String id) {
        // TODO: Error handling, Id leer oder nicht vorhanden siehe oben
//        int zaehler = 1;
        List<Gutschein> gutscheine = new ArrayList<>();
        for (Gutschein gutschein : gutscheineAusgegeben) {
            if (gutschein.getSpielerId().equals(id)) {
                gutscheine.add(gutschein);
            }
//            else if (zaehler == gutscheineAusgegeben.size()) {
//                System.out.println("Keine Gutscheine gefunden!");
//            }
//            zaehler++;
        }
        return gutscheine;
    }

    public static List<Gutschein> sortiereGutscheineNachNichtEingeloestenNummern() {
        for (Gutschein gutschein : gutscheineAusgegeben) {
            if (!containsGutscheinnummer(gutscheineEingeloest, gutschein.getNummer())) {
                gutscheineNichtEingeloest.add(gutschein);
            }
        }
//        Collections.sort(gutscheineNichtEingeloest, new Sort());
        return gutscheineNichtEingeloest;
    }

    public static boolean containsGutscheinnummer(final List<Gutschein> list, final BigInteger nummer){
        return list.stream().anyMatch(o -> o.getNummer().equals(nummer));
    }

    public static Gutschein getGefundenerGutschein() {
        return gefundenerGutschein;
    }

    private static Spieler gefundenerSpieler = new Spieler();

    public static Spieler getGefundenerSpieler() {
        return gefundenerSpieler;
    }
}