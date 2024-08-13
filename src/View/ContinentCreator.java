package View;
import Controller.BoardController;
import Model.Country;

import javax.swing.*;
import java.awt.*;

import java.util.Map;

import static Model.Helper.buildBoardConstraints;
import static java.awt.Component.CENTER_ALIGNMENT;

public class ContinentCreator {

    GridBagLayout continentLayout = new GridBagLayout();
    GridBagConstraints continentConstraints = new GridBagConstraints();
    JPanel continentPanel = new JPanel(continentLayout);

    public BoardController controller;

    public ContinentCreator(BoardController controller) {
        this.controller = controller;
    }

    // Different from the other creators, since the countries here are placed with a standard GridLayout instead of
    // more complex GridBagLayout
    public JPanel createContinent1(String continentName, Map<String, Country> allCountries, Map<String, CountryView> allCountryViews) {
        JPanel continent = new JPanel(new GridLayout(2, 3));

        for (int i = 1; i <= 6; i++) {
            Country c = new Country(continentName + i, continentName);
            allCountries.put(c.getName(), c);
            CountryView cView = new CountryView(controller, c);
            JPanel country = switch (continentName) {
                case "A" -> cView.createCountry(new Color(249,225,68), c.getName());
                case "B" -> cView.createCountry(new Color(241,115,115), c.getName());
                case "C" -> cView.createCountry(new Color(99,189,89), c.getName());
                case "D" -> cView.createCountry(new Color(67,80,156), c.getName());
                default -> cView.createCountry(Color.BLACK, c.getName());
            };
            allCountryViews.put(c.getName(), cView);
            continent.add(country, CENTER_ALIGNMENT);

        }

        return continent;
    }

    //TODO: Universal Continent Creator
/*    String[] allCountryNames = new String[] {"A1", "A2", "A3", "A4", "A5", "A6",
                                            "B1", "B2", "B3", "B4", "B5", "B6",
                                            "C1", "C2", "C3", "C4", "C5", "C6",
                                            "D1", "D2", "D3", "D4", "D5", "D6"};

    Color[] allContinentColors = new Color[] {new Color(249,225,68),
                                            new Color(241,115,115),
                                            new Color(99,189,89),
                                            new Color(67,80,156),};

    int[][] allCountryPlacements = new int[][] {
            new int[] {0,0,1,1},
    };

    public JPanel createWholeContinent(String[] countryNames, Color[] continentColors, int[][] countryPlacements ) {

    };*/

    public JPanel createContinents2(Map<String, Country> allCountries, Map<String, CountryView> allCountryViews) {
        
        continentLayout.columnWidths = new int[] {100,100,100,100,100,100,100,100};
        continentLayout.rowHeights = new int[] {100,100,100,100,100,100,100};

        Country a1 = new Country("A1", "A");
        allCountries.put(a1.getName(), a1);
        CountryView a1View = new CountryView(controller, a1);
        JPanel countryA1Panel = a1View.createCountry(new Color(249,225,68), a1.getName());
        continentPanel.add(countryA1Panel, buildBoardConstraints(continentConstraints, 0,0, 1, 1));
        allCountryViews.put(a1.getName(), a1View);

        Country a2 = new Country("A2", "A");
        allCountries.put(a2.getName(), a2);
        CountryView a2View = new CountryView(controller, a2);
        JPanel countryA2Panel = a2View.createCountry(new Color(249,225,68), a2.getName());
        continentPanel.add(countryA2Panel, buildBoardConstraints(continentConstraints, 0,2, 1, 1));
        allCountryViews.put(a2.getName(), a2View);

        Country a3 = new Country("A3", "A");
        allCountries.put(a3.getName(), a3);
        CountryView a3View = new CountryView(controller, a3);
        JPanel countryA3Panel = a3View.createCountry(new Color(249,225,68), a3.getName());
        continentPanel.add(countryA3Panel, buildBoardConstraints(continentConstraints, 1,1, 1, 1));
        allCountryViews.put(a3.getName(), a3View);

        Country a4 = new Country("A4", "A");
        allCountries.put(a4.getName(), a4);
        CountryView a4View = new CountryView(controller, a4);
        JPanel countryA4Panel = a4View.createCountry(new Color(249,225,68), a4.getName());
        continentPanel.add(countryA4Panel, buildBoardConstraints(continentConstraints, 1,3, 1, 1));
        allCountryViews.put(a4.getName(), a4View);

        Country a5 = new Country("A5", "A");
        allCountries.put(a5.getName(), a5);
        CountryView a5View = new CountryView(controller, a5);
        JPanel countryA5Panel = a5View.createCountry(new Color(249,225,68), a5.getName());
        continentPanel.add(countryA5Panel, buildBoardConstraints(continentConstraints, 2,0, 1, 1));
        allCountryViews.put(a5.getName(), a5View);

        Country a6 = new Country("A6", "A");
        allCountries.put(a6.getName(), a6);
        CountryView a6View = new CountryView(controller, a6);
        JPanel countryA6Panel = a6View.createCountry(new Color(249,225,68), a6.getName());
        continentPanel.add(countryA6Panel, buildBoardConstraints(continentConstraints, 2,2, 1, 1));
        allCountryViews.put(a6.getName(), a6View);

        Country b1 = new Country("B1", "B");
        allCountries.put(b1.getName(), b1);
        CountryView b1View = new CountryView(controller, b1);
        JPanel countryB1Panel = b1View.createCountry(new Color(241,115,115), b1.getName());
        continentPanel.add(countryB1Panel, buildBoardConstraints(continentConstraints, 0,5, 1, 1));
        allCountryViews.put(b1.getName(), b1View);

        Country b2 = new Country("B2", "B");
        allCountries.put(b2.getName(), b2);
        CountryView b2View = new CountryView(controller, b2);
        JPanel countryB2Panel = b2View.createCountry(new Color(241,115,115), b2.getName());
        continentPanel.add(countryB2Panel, buildBoardConstraints(continentConstraints, 0,6, 1, 1));
        allCountryViews.put(b2.getName(), b2View);

        Country b3 = new Country("B3", "B");
        allCountries.put(b3.getName(), b3);
        CountryView b3View = new CountryView(controller, b3);
        JPanel countryB3Panel = b3View.createCountry(new Color(241,115,115), b3.getName());
        continentPanel.add(countryB3Panel, buildBoardConstraints(continentConstraints, 1,4, 1, 1));
        allCountryViews.put(b3.getName(), b3View);

        Country b4 = new Country("B4", "B");
        allCountries.put(b4.getName(), b4);
        CountryView b4View = new CountryView(controller, b4);
        JPanel countryB4Panel = b4View.createCountry(new Color(241,115,115), b4.getName());
        continentPanel.add(countryB4Panel, buildBoardConstraints(continentConstraints, 1,7, 1, 1));
        allCountryViews.put(b4.getName(), b4View);

        Country b5 = new Country("B5", "B");
        allCountries.put(b5.getName(), b5);
        CountryView b5View = new CountryView(controller, b5);
        JPanel countryB5Panel = b5View.createCountry(new Color(241,115,115), b5.getName());
        continentPanel.add(countryB5Panel, buildBoardConstraints(continentConstraints, 2,5, 1, 1));
        allCountryViews.put(b5.getName(), b5View);

        Country b6 = new Country("B6", "B");
        allCountries.put(b6.getName(), b6);
        CountryView b6View = new CountryView(controller, b6);
        JPanel countryB6Panel = b6View.createCountry(new Color(241,115,115), b6.getName());
        continentPanel.add(countryB6Panel, buildBoardConstraints(continentConstraints, 2,6, 1, 1));
        allCountryViews.put(b6.getName(), b6View);

        Country c1 = new Country("C1", "C");
        allCountries.put(c1.getName(), c1);
        CountryView c1View = new CountryView(controller, c1);
        JPanel countryC1Panel = c1View.createCountry(new Color(99,189,89), c1.getName());
        continentPanel.add(countryC1Panel, buildBoardConstraints(continentConstraints, 3,2, 1, 1));
        allCountryViews.put(c1.getName(), c1View);

        Country c2 = new Country("C2", "C");
        allCountries.put(c2.getName(), c2);
        CountryView c2View = new CountryView(controller, c2);
        JPanel countryC2Panel = c2View.createCountry(new Color(99,189,89), c2.getName());
        continentPanel.add(countryC2Panel, buildBoardConstraints(continentConstraints, 4,2, 1, 1));
        allCountryViews.put(c2.getName(), c2View);

        Country c3 = new Country("C3", "C");
        allCountries.put(c3.getName(), c3);
        CountryView c3View = new CountryView(controller, c3);
        JPanel countryC3Panel = c3View.createCountry(new Color(99,189,89), c3.getName());
        continentPanel.add(countryC3Panel, buildBoardConstraints(continentConstraints, 5,0, 1, 1));
        allCountryViews.put(c3.getName(), c3View);

        Country c4 = new Country("C4", "C");
        allCountries.put(c4.getName(), c4);
        CountryView c4View = new CountryView(controller, c4);
        JPanel countryC4Panel = c4View.createCountry(new Color(99,189,89), c4.getName());
        continentPanel.add(countryC4Panel, buildBoardConstraints(continentConstraints, 5,1, 1, 1));
        allCountryViews.put(c4.getName(), c4View);

        Country c5 = new Country("C5", "C");
        allCountries.put(c5.getName(), c5);
        CountryView c5View = new CountryView(controller, c5);
        JPanel countryC5Panel = c5View.createCountry(new Color(99,189,89), c5.getName());
        continentPanel.add(countryC5Panel, buildBoardConstraints(continentConstraints, 5,3, 1, 1));
        allCountryViews.put(c5.getName(), c5View);

        Country c6 = new Country("C6", "C");
        allCountries.put(c6.getName(), c6);
        CountryView c6View = new CountryView(controller, c6);
        JPanel countryC6Panel = c6View.createCountry(new Color(99,189,89), c6.getName());
        continentPanel.add(countryC6Panel, buildBoardConstraints(continentConstraints, 6,2, 1, 1));
        allCountryViews.put(c6.getName(), c6View);

        Country d1 = new Country("D1", "D");
        allCountries.put(d1.getName(), d1);
        CountryView d1View = new CountryView(controller, d1);
        JPanel countryD1Panel = d1View.createCountry(new Color(67,80,156), d1.getName());
        continentPanel.add(countryD1Panel, buildBoardConstraints(continentConstraints, 3,4, 1, 1));
        allCountryViews.put(d1.getName(), d1View);

        Country d2 = new Country("D2", "D");
        allCountries.put(d2.getName(), d2);
        CountryView d2View = new CountryView(controller, d2);
        JPanel countryD2Panel = d2View.createCountry(new Color(67,80,156), d2.getName());
        continentPanel.add(countryD2Panel, buildBoardConstraints(continentConstraints, 3,7, 1, 1));
        allCountryViews.put(d2.getName(), d2View);

        Country d3 = new Country("D3", "D");
        allCountries.put(d3.getName(), d3);
        CountryView d3View = new CountryView(controller, d3);
        JPanel countryD3Panel = d3View.createCountry(new Color(67,80,156), d3.getName());
        continentPanel.add(countryD3Panel, buildBoardConstraints(continentConstraints, 4,5, 1, 1));
        allCountryViews.put(d3.getName(), d3View);

        Country d4 = new Country("D4", "D");
        allCountries.put(d4.getName(), d4);
        CountryView d4View = new CountryView(controller, d4);
        JPanel countryD4Panel = d4View.createCountry(new Color(67,80,156), d4.getName());
        continentPanel.add(countryD4Panel, buildBoardConstraints(continentConstraints, 4,6, 1, 1));
        allCountryViews.put(d4.getName(), d4View);

        Country d5 = new Country("D5", "D");
        allCountries.put(d5.getName(), d5);
        CountryView d5View = new CountryView(controller, d5);
        JPanel countryD5Panel = d5View.createCountry(new Color(67,80,156), d5.getName());
        continentPanel.add(countryD5Panel, buildBoardConstraints(continentConstraints, 5,5, 1, 1));
        allCountryViews.put(d5.getName(), d5View);

        Country d6 = new Country("D6", "D");
        allCountries.put(d6.getName(), d6);
        CountryView d6View = new CountryView(controller, d6);
        JPanel countryD6Panel = d6View.createCountry(new Color(67,80,156), d6.getName());
        continentPanel.add(countryD6Panel, buildBoardConstraints(continentConstraints, 5,6, 1, 1));
        allCountryViews.put(d6.getName(), d6View);

        return continentPanel;
    }

    public JPanel createContinents3(Map<String, Country> allCountries, Map<String, CountryView> allCountryViews) {
        
        continentLayout.columnWidths = new int[] {100,100,100,100,100,100,100,100};
        continentLayout.rowHeights = new int[] {100,100,100,100,100,100,100,100};

        Country a1 = new Country("A1", "A");
        allCountries.put(a1.getName(), a1);
        CountryView a1View = new CountryView(controller, a1);
        JPanel countryA1Panel = a1View.createCountry(new Color(249,225,68), a1.getName());
        continentPanel.add(countryA1Panel, buildBoardConstraints(continentConstraints, 0,2, 1, 1));
        allCountryViews.put(a1.getName(), a1View);

        Country a2 = new Country("A2", "A");
        allCountries.put(a2.getName(), a2);
        CountryView a2View = new CountryView(controller, a2);
        JPanel countryA2Panel = a2View.createCountry(new Color(249,225,68), a2.getName());
        continentPanel.add(countryA2Panel, buildBoardConstraints(continentConstraints, 1,1, 1, 1));
        allCountryViews.put(a2.getName(), a2View);

        Country a3 = new Country("A3", "A");
        allCountries.put(a3.getName(), a3);
        CountryView a3View = new CountryView(controller, a3);
        JPanel countryA3Panel = a3View.createCountry(new Color(249,225,68), a3.getName());
        continentPanel.add(countryA3Panel, buildBoardConstraints(continentConstraints, 2,0, 1, 1));
        allCountryViews.put(a3.getName(), a3View);

        Country a4 = new Country("A4", "A");
        allCountries.put(a4.getName(), a4);
        CountryView a4View = new CountryView(controller, a4);
        JPanel countryA4Panel = a4View.createCountry(new Color(249,225,68), a4.getName());
        continentPanel.add(countryA4Panel, buildBoardConstraints(continentConstraints, 2,2, 1, 1));
        allCountryViews.put(a4.getName(), a4View);

        Country a5 = new Country("A5", "A");
        allCountries.put(a5.getName(), a5);
        CountryView a5View = new CountryView(controller, a5);
        JPanel countryA5Panel = a5View.createCountry(new Color(249,225,68), a5.getName());
        continentPanel.add(countryA5Panel, buildBoardConstraints(continentConstraints, 2,3, 1, 1));
        allCountryViews.put(a5.getName(), a5View);

        Country a6 = new Country("A6", "A");
        allCountries.put(a6.getName(), a6);
        CountryView a6View = new CountryView(controller, a6);
        JPanel countryA6Panel = a6View.createCountry(new Color(249,225,68), a6.getName());
        continentPanel.add(countryA6Panel, buildBoardConstraints(continentConstraints, 3,2, 1, 1));
        allCountryViews.put(a6.getName(), a6View);

        Country b1 = new Country("B1", "B");
        allCountries.put(b1.getName(), b1);
        CountryView b1View = new CountryView(controller, b1);
        JPanel countryB1Panel = b1View.createCountry(new Color(241,115,115), b1.getName());
        continentPanel.add(countryB1Panel, buildBoardConstraints(continentConstraints, 0,5, 1, 1));
        allCountryViews.put(b1.getName(), b1View);

        Country b2 = new Country("B2", "B");
        allCountries.put(b2.getName(), b2);
        CountryView b2View = new CountryView(controller, b2);
        JPanel countryB2Panel = b2View.createCountry(new Color(241,115,115), b2.getName());
        continentPanel.add(countryB2Panel, buildBoardConstraints(continentConstraints, 1,6, 1, 1));
        allCountryViews.put(b2.getName(), b2View);

        Country b3 = new Country("B3", "B");
        allCountries.put(b3.getName(), b3);
        CountryView b3View = new CountryView(controller, b3);
        JPanel countryB3Panel = b3View.createCountry(new Color(241,115,115), b3.getName());
        continentPanel.add(countryB3Panel, buildBoardConstraints(continentConstraints, 2,4, 1, 1));
        allCountryViews.put(b3.getName(), b3View);

        Country b4 = new Country("B4", "B");
        allCountries.put(b4.getName(), b4);
        CountryView b4View = new CountryView(controller, b4);
        JPanel countryB4Panel = b4View.createCountry(new Color(241,115,115), b4.getName());
        continentPanel.add(countryB4Panel, buildBoardConstraints(continentConstraints, 2,5, 1, 1));
        allCountryViews.put(b4.getName(), b4View);

        Country b5 = new Country("B5", "B");
        allCountries.put(b5.getName(), b5);
        CountryView b5View = new CountryView(controller, b5);
        JPanel countryB5Panel = b5View.createCountry(new Color(241,115,115), b5.getName());
        continentPanel.add(countryB5Panel, buildBoardConstraints(continentConstraints, 2,7, 1, 1));
        allCountryViews.put(b5.getName(), b5View);

        Country b6 = new Country("B6", "B");
        allCountries.put(b6.getName(), b6);
        CountryView b6View = new CountryView(controller, b6);
        JPanel countryB6Panel = b6View.createCountry(new Color(241,115,115), b6.getName());
        continentPanel.add(countryB6Panel, buildBoardConstraints(continentConstraints, 3,5, 1, 1));
        allCountryViews.put(b6.getName(), b6View);

        Country c1 = new Country("C1", "C");
        allCountries.put(c1.getName(), c1);
        CountryView c1View = new CountryView(controller, c1);
        JPanel countryC1Panel = c1View.createCountry(new Color(99,189,89), c1.getName());
        continentPanel.add(countryC1Panel, buildBoardConstraints(continentConstraints, 4,2, 1, 1));
        allCountryViews.put(c1.getName(), c1View);

        Country c2 = new Country("C2", "C");
        allCountries.put(c2.getName(), c2);
        CountryView c2View = new CountryView(controller, c2);
        JPanel countryC2Panel = c2View.createCountry(new Color(99,189,89), c2.getName());
        continentPanel.add(countryC2Panel, buildBoardConstraints(continentConstraints, 5,0, 1, 1));
        allCountryViews.put(c2.getName(), c2View);

        Country c3 = new Country("C3", "C");
        allCountries.put(c3.getName(), c3);
        CountryView c3View = new CountryView(controller, c3);
        JPanel countryC3Panel = c3View.createCountry(new Color(99,189,89), c3.getName());
        continentPanel.add(countryC3Panel, buildBoardConstraints(continentConstraints, 5,2, 1, 1));
        allCountryViews.put(c3.getName(), c3View);

        Country c4 = new Country("C4", "C");
        allCountries.put(c4.getName(), c4);
        CountryView c4View = new CountryView(controller, c4);
        JPanel countryC4Panel = c4View.createCountry(new Color(99,189,89), c4.getName());
        continentPanel.add(countryC4Panel, buildBoardConstraints(continentConstraints, 5,3, 1, 1));
        allCountryViews.put(c4.getName(), c4View);

        Country c5 = new Country("C5", "C");
        allCountries.put(c5.getName(), c5);
        CountryView c5View = new CountryView(controller, c5);
        JPanel countryC5Panel = c5View.createCountry(new Color(99,189,89), c5.getName());
        continentPanel.add(countryC5Panel, buildBoardConstraints(continentConstraints, 6,1, 1, 1));
        allCountryViews.put(c5.getName(), c5View);

        Country c6 = new Country("C6", "C");
        allCountries.put(c6.getName(), c6);
        CountryView c6View = new CountryView(controller, c6);
        JPanel countryC6Panel = c6View.createCountry(new Color(99,189,89), c6.getName());
        continentPanel.add(countryC6Panel, buildBoardConstraints(continentConstraints, 7,2, 1, 1));
        allCountryViews.put(c6.getName(), c6View);

        Country d1 = new Country("D1", "D");
        allCountries.put(d1.getName(), d1);
        CountryView d1View = new CountryView(controller, d1);
        JPanel countryD1Panel = d1View.createCountry(new Color(67,80,156), d1.getName());
        continentPanel.add(countryD1Panel, buildBoardConstraints(continentConstraints, 4,5, 1, 1));
        allCountryViews.put(d1.getName(), d1View);

        Country d2 = new Country("D2", "D");
        allCountries.put(d2.getName(), d2);
        CountryView d2View = new CountryView(controller, d2);
        JPanel countryD2Panel = d2View.createCountry(new Color(67,80,156), d2.getName());
        continentPanel.add(countryD2Panel, buildBoardConstraints(continentConstraints, 5,4, 1, 1));
        allCountryViews.put(d2.getName(), d2View);

        Country d3 = new Country("D3", "D");
        allCountries.put(d3.getName(), d3);
        CountryView d3View = new CountryView(controller, d3);
        JPanel countryD3Panel = d3View.createCountry(new Color(67,80,156), d3.getName());
        continentPanel.add(countryD3Panel, buildBoardConstraints(continentConstraints, 5,5, 1, 1));
        allCountryViews.put(d3.getName(), d3View);

        Country d4 = new Country("D4", "D");
        allCountries.put(d4.getName(), d4);
        CountryView d4View = new CountryView(controller, d4);
        JPanel countryD4Panel = d4View.createCountry(new Color(67,80,156), d4.getName());
        continentPanel.add(countryD4Panel, buildBoardConstraints(continentConstraints, 5,7, 1, 1));
        allCountryViews.put(d4.getName(), d4View);

        Country d5 = new Country("D5", "D");
        allCountries.put(d5.getName(), d5);
        CountryView d5View = new CountryView(controller, d5);
        JPanel countryD5Panel = d5View.createCountry(new Color(67,80,156), d5.getName());
        continentPanel.add(countryD5Panel, buildBoardConstraints(continentConstraints, 6,6, 1, 1));
        allCountryViews.put(d5.getName(), d5View);

        Country d6 = new Country("D6", "D");
        allCountries.put(d6.getName(), d6);
        CountryView d6View = new CountryView(controller, d6);
        JPanel countryD6Panel = d6View.createCountry(new Color(67,80,156), d6.getName());
        continentPanel.add(countryD6Panel, buildBoardConstraints(continentConstraints, 7,5, 1, 1));
        allCountryViews.put(d6.getName(), d6View);

        return continentPanel;
    }

}
