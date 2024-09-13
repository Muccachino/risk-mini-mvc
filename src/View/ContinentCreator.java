package View;
import Config.CountryNames;
import Controller.BoardController;
import Model.Country;
import Config.CountryCoordinates;

import javax.swing.*;
import java.awt.*;

import java.util.Map;

import static Config.Helper.buildBoardConstraints;
import static java.awt.Component.CENTER_ALIGNMENT;

public class ContinentCreator {

    GridBagLayout continentLayout = new GridBagLayout();
    GridBagConstraints continentConstraints = new GridBagConstraints();
    JPanel continentPanel = new JPanel(continentLayout);

    //TODO: Change Creator to use different country and continent names

    public static Color[] allContinentColors = new Color[] {
            new Color(249,225,68),
            new Color(241,115,115),
            new Color(99,189,89),
            new Color(67,80,156),
            new Color(150,2,255),
            new Color(255,123,0)};

    public BoardController controller;

    public ContinentCreator(BoardController controller) {
        this.controller = controller;
    }

    // Different from the other creators, since the countries here are placed with a standard GridLayout instead of
    // more complex GridBagLayout
    public JPanel createBoard1(String continentName, Map<String, Country> allCountries, Map<String, CountryView> allCountryViews) {
        JPanel continent = new JPanel(new GridLayout(2, 3));

        for (int i = 1; i <= 6; i++) {
            Country c = new Country(continentName + i, continentName);
            allCountries.put(c.getName(), c);
            CountryView cView = new CountryView(controller, c);
            JPanel country = switch (continentName) {
                case "A" -> cView.createCountry(allContinentColors[0], c.getName());
                case "B" -> cView.createCountry(allContinentColors[1], c.getName());
                case "C" -> cView.createCountry(allContinentColors[2], c.getName());
                case "D" -> cView.createCountry(allContinentColors[3], c.getName());
                default -> cView.createCountry(Color.BLACK, c.getName());
            };
            allCountryViews.put(c.getName(), cView);
            continent.add(country, CENTER_ALIGNMENT);

        }

        return continent;
    }

    public static Color setCountryColor(String continentName) {
        return switch (continentName) {
            case "Rothenor", "Loria", "North America" -> allContinentColors[0];
            case "Xandria" , "Zyrath", "South America" -> allContinentColors[1];
            case "Veloria", "Vendros", "Europe" -> allContinentColors[2];
            case "Mythoria", "Myrion", "Africa" -> allContinentColors[3];
            case "Asia" -> allContinentColors[4];
            case "Australia" -> allContinentColors[5];
            default -> Color.BLACK;
        };
    }


    // Creator for all other Boards except for Board 1.
    public void createAndPlaceAllCountries(int[][] countryPlacements, Map<String, Country> allCountries, Map<String, CountryView> allCountryViews,
                                           String[] countryNames) {
        for (int i = 0; i < countryPlacements.length; i++) {
            Country newCountry = new Country(countryNames[i], CountryNames.getCorrectContinent(countryNames[i], controller.boardChoice));
            allCountries.put(newCountry.getName(), newCountry);
            CountryView cView = new CountryView(controller, newCountry);

            Color countryColor = setCountryColor(newCountry.getContinent());

            JPanel countryPanel = cView.createCountry(countryColor, newCountry.getName());
            continentPanel.add(countryPanel, buildBoardConstraints(continentConstraints,countryPlacements[i][0],countryPlacements[i][1],
                                                                                        countryPlacements[i][2],countryPlacements[i][3]));
            allCountryViews.put(newCountry.getName(), cView);
        }
    };

    public JPanel createBoard2(Map<String, Country> allCountries, Map<String, CountryView> allCountryViews) {
        
        continentLayout.columnWidths = new int[] {100,100,100,100,100,100,100,100};
        continentLayout.rowHeights = new int[] {100,100,100,100,100,100,100};

        createAndPlaceAllCountries(CountryCoordinates.allCountryPlacements2, allCountries, allCountryViews, CountryNames.countryNames2);

        return continentPanel;
    }

    public JPanel createBoard3(Map<String, Country> allCountries, Map<String, CountryView> allCountryViews) {
        
        continentLayout.columnWidths = new int[] {100,100,100,100,100,100,100,100};
        continentLayout.rowHeights = new int[] {100,100,100,100,100,100,100,100};

        createAndPlaceAllCountries(CountryCoordinates.allCountryPlacements3, allCountries, allCountryViews, CountryNames.countryNames3);

        return continentPanel;
    }

    public JPanel createBoard4(Map<String, Country> allCountries, Map<String, CountryView> allCountryViews) {
        continentLayout.columnWidths = new int[] {100,100,100,100,100,100,100,100,100,100,100,100};
        continentLayout.rowHeights = new int[] {100,100,100,100,100,100,100,100};

        createAndPlaceAllCountries(CountryCoordinates.allCountryPlacements4, allCountries, allCountryViews, CountryNames.countryNames4);

        return continentPanel;
    }

}
