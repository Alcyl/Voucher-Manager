package com.company;

import com.entities.Gutschein;
import com.entities.Spieler;

import java.io.*;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvReader {
    private static final String SEMICOLON_DELIMITER = ";";

    public static List<Spieler> getSpieler(String filepath){
        List<Spieler> alleSpieler = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = "";
            br.readLine();

            while ((line = br.readLine()) != null) {
                Spieler spieler = new Spieler();
                String[] spielerSplit = line.split(SEMICOLON_DELIMITER);
                spieler.setId(spielerSplit[0]);
                spieler.setVorname(spielerSplit[1]);
                spieler.setName(spielerSplit[2]);

                alleSpieler.add(spieler);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alleSpieler;
    }

    public static List<Gutschein> getGutscheinEingeloest(String pfad){
        List<Gutschein> alleGutscheine = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(pfad))) {
            String line = "";
            br.readLine();

            while ((line = br.readLine()) != null) {
                Gutschein gutschein = new Gutschein();
                String[] gutscheinSplit = line.split(SEMICOLON_DELIMITER);
                gutschein.setNummer(new BigInteger(gutscheinSplit[0]));
                gutschein.setSpielerId(gutscheinSplit[1]);
                gutschein.setAuszahlbetrag(Double.valueOf(gutscheinSplit[2]));
                gutschein.setDatum(new SimpleDateFormat("dd.MM.yyyy").parse((gutscheinSplit[3])));

                alleGutscheine.add(gutschein);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return alleGutscheine;
    }

    public static List<Gutschein> getGutscheinAusgegeben(String pfad){
        List<Gutschein> alleGutscheine = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(pfad))) {
            String line = "";
            br.readLine();

            while ((line = br.readLine()) != null) {
                Gutschein gutschein = new Gutschein();
                String[] gutscheinSplit = line.split(SEMICOLON_DELIMITER);
                gutschein.setNummer(new BigInteger(gutscheinSplit[0]));
                gutschein.setAuszahlbetrag(Double.valueOf(gutscheinSplit[1]));
                gutschein.setSpielerId(gutscheinSplit[2]);
                gutschein.setDatum(new SimpleDateFormat("dd.MM.yyyy").parse(gutscheinSplit[3]));

                alleGutscheine.add(gutschein);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return alleGutscheine;
    }

}
