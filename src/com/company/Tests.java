package com.company;

import com.entities.Gutschein;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Test
    public void testSucheGutscheineNachNummer() {
        BigInteger gutscheinNummer = new BigInteger("123456789012345678");
        GutscheinFilter.sucheGutscheineNachNummer(gutscheinNummer);

        assertEquals("115-9", GutscheinFilter.getGefundenerSpieler().getId(), "vergleicht Id's");
        assertEquals("Max", GutscheinFilter.getGefundenerSpieler().getVorname(), "vergleicht Vornamen");
        assertEquals("Liebherr", GutscheinFilter.getGefundenerSpieler().getName(), "vergleich Nachnamen");

        assertEquals(new BigInteger("123456789012345678"), GutscheinFilter.getGefundenerGutschein().getNummer(), "vergleicht Gutscheinnummern");
        assertEquals("115-9", GutscheinFilter.getGefundenerGutschein().getSpielerId(), "vergleicht Id's");
        assertEquals(250.0, GutscheinFilter.getGefundenerGutschein().getAuszahlbetrag(), "vergleicht Auszahlbetrag/Wert");
        assertEquals("31.08.2020", simpleDateFormat.format(GutscheinFilter.getGefundenerGutschein().getDatum()), "vergleicht Datum");
    }

    @Test
    public void testfindeAlleGutscheineEinesSpielers() {
        String id  = "113-88";

        List<Gutschein> tester = GutscheinFilter.findeAlleGutscheineEinesSpielers(id);
        Gutschein gutschein0 = tester.get(0);

        assertEquals(new BigInteger("111222333444555666"), gutschein0.getNummer(),"vergleicht Gutscheinnummern");
        assertEquals("113-88", gutschein0.getSpielerId(), "vergleicht Id's");
        assertEquals(250.0, gutschein0.getAuszahlbetrag(), "vergleicht Auszahlbetrag/Wert");
        assertEquals("02.09.2020", simpleDateFormat.format(gutschein0.getDatum()), "vergleicht Datum");

        Gutschein gutschein1 = tester.get(1);
        assertEquals(new BigInteger("123456789012345678"), gutschein1.getNummer(), "vergleicht Gutscheinnummern");
        assertEquals(250.5, gutschein1.getAuszahlbetrag(),"vergleicht Auszahlbetrag/Wert");

        Gutschein gutschein2 = tester.get(2);
        assertEquals(new BigInteger("123412341234123412"), gutschein2.getNummer(), "vergleicht Gutscheinnummern");
        assertEquals(1000.0, gutschein2.getAuszahlbetrag(), "vergleicht Auszahlbetrag/Wert");
    }

    @Test
    public void testSortiereNachGueltigenGutscheinNummern() {
        GutscheinFilter.sortiereGutscheineNachNichtEingeloestenNummern();
        Gutschein test0 = GutscheinFilter.gutscheineNichtEingeloest.get(0);
        Gutschein test1 = GutscheinFilter.gutscheineNichtEingeloest.get(1);

        assertEquals(new BigInteger("111122223333444455"), test0.getNummer(), "vergleicht Gutscheinnummern, der nicht eingelösten Gutscheine");
        assertEquals(new BigInteger("123412341234123412"), test1.getNummer(), "vergleicht Gutscheinnummern, der nicht eingelösten Gutscheine");
    }
}
