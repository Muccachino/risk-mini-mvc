package Config;

import java.util.Map;

public class NeighborRelations {

    public static void addCountryNeighbors1(Map<String, String[]> countryNeighbors) {
        countryNeighbors.put("A1", new String[] {"A2", "A4"});
        countryNeighbors.put("A2", new String[] {"A1", "A3", "A5"});
        countryNeighbors.put("A3", new String[] {"A2", "A6", "B1"});
        countryNeighbors.put("A4", new String[] {"A1", "A5", "C1"});
        countryNeighbors.put("A5", new String[] {"A2", "A4", "A6", "C2"});
        countryNeighbors.put("A6", new String[] {"A3", "A5", "B4", "C3"});

        countryNeighbors.put("B1", new String[] {"A3", "B2", "B4"});
        countryNeighbors.put("B2", new String[] {"B1", "B3", "B5"});
        countryNeighbors.put("B3", new String[] {"B2", "B6"});
        countryNeighbors.put("B4", new String[] {"B1", "B5", "A6", "D1"});
        countryNeighbors.put("B5", new String[] {"B2", "B4", "B6", "D2"});
        countryNeighbors.put("B6", new String[] {"B3", "B5", "D3"});

        countryNeighbors.put("C1", new String[] {"C2", "C4", "A4"});
        countryNeighbors.put("C2", new String[] {"C1", "C3", "C5", "A5"});
        countryNeighbors.put("C3", new String[] {"C2", "C6", "A6", "D1"});
        countryNeighbors.put("C4", new String[] {"C1", "C5"});
        countryNeighbors.put("C5", new String[] {"C2", "C4", "C6"});
        countryNeighbors.put("C6", new String[] {"C3", "C5", "D4"});

        countryNeighbors.put("D1", new String[] {"D2", "D4", "B4", "C3"});
        countryNeighbors.put("D2", new String[] {"D1", "D3", "D5", "B5"});
        countryNeighbors.put("D3", new String[] {"D2", "D6", "B6"});
        countryNeighbors.put("D4", new String[] {"D1", "D5", "C6"});
        countryNeighbors.put("D5", new String[] {"D2", "D4", "D6"});
        countryNeighbors.put("D6", new String[] {"D3", "D5"});
    }

    public static void addCountryNeighbors2(Map<String, String[]> countryNeighbors) {
        countryNeighbors.put("Aldaris", new String[] {"Korinthar"});
        countryNeighbors.put("Dornfall", new String[] {"Korinthar", "Mirgath"});
        countryNeighbors.put("Korinthar", new String[] {"Aldaris", "Dornfall", "Sylvanis", "Drakenhelm"});
        countryNeighbors.put("Mirgath", new String[] {"Dornfall", "Drakenhelm", "Eryndor"});
        countryNeighbors.put("Sylvanis", new String[] {"Korinthar"});
        countryNeighbors.put("Drakenhelm", new String[] {"Korinthar", "Mirgath", "Velstrom"});

        countryNeighbors.put("Zephyros", new String[] {"Thalrion", "Eryndor"});
        countryNeighbors.put("Thalrion", new String[] {"Zephyros", "Valkaris"});
        countryNeighbors.put("Eryndor", new String[] {"Zephyros", "Lythor", "Mirgath"});
        countryNeighbors.put("Valkaris", new String[] {"Thalrion", "Kryndor"});
        countryNeighbors.put("Lythor", new String[] {"Eryndor", "Kryndor", "Nyxora"});
        countryNeighbors.put("Kryndor", new String[] {"Valkaris", "Lythor", "Aetheris"});

        countryNeighbors.put("Velstrom", new String[] {"Galdoria", "Drakenhelm", "Nyxora"});
        countryNeighbors.put("Galdoria", new String[] {"Velstrom", "Fenris", "Volcrath"});
        countryNeighbors.put("Morathis", new String[] {"Fenris"});
        countryNeighbors.put("Fenris", new String[] {"Galdoria", "Morathis", "Zytheris"});
        countryNeighbors.put("Volcrath", new String[] {"Galdoria", "Zytheris", "Myrenor"});
        countryNeighbors.put("Zytheris", new String[] {"Fenris", "Volcrath"});

        countryNeighbors.put("Nyxora", new String[] {"Ravenscar", "Lythor", "Velstrom"});
        countryNeighbors.put("Aetheris", new String[] {"Solthar", "Kryndor"});
        countryNeighbors.put("Ravenscar", new String[] {"Nyxora", "Solthar", "Myrenor"});
        countryNeighbors.put("Solthar", new String[] {"Aetheris", "Ravenscar", "Eldarion"});
        countryNeighbors.put("Myrenor", new String[] {"Ravenscar", "Eldarion", "Volcrath"});
        countryNeighbors.put("Eldarion", new String[] {"Solthar", "Myrenor"});
    }

    public static void addCountryNeighbors3(Map<String, String[]> countryNeighbors) {
        countryNeighbors.put("Faldris", new String[] {"Eryndell", "Kalmaris"});
        countryNeighbors.put("Eryndell", new String[] {"Faldris", "Kovaris", "Thandor"});
        countryNeighbors.put("Kovaris", new String[] {"Eryndell", "Valkir"});
        countryNeighbors.put("Thandor", new String[] {"Eryndell", "Velanthor", "Myrvalis"});
        countryNeighbors.put("Velanthor", new String[] {"Thandor", "Lyssara"});
        countryNeighbors.put("Myrvalis", new String[] {"Thandor","Tirandor"});

        countryNeighbors.put("Kalmaris", new String[] {"Vornath", "Faldris"});
        countryNeighbors.put("Vornath", new String[] {"Kalmaris", "Zenthar", "Orindor"});
        countryNeighbors.put("Lyssara", new String[] {"Zenthar", "Velanthor"});
        countryNeighbors.put("Zenthar", new String[] {"Vornath", "Lyssara", "Xyldar"});
        countryNeighbors.put("Orindor", new String[] {"Vornath", "Nimaris"});
        countryNeighbors.put("Xyldar", new String[] {"Zenthar", "Arkendell"});

        countryNeighbors.put("Tirandor", new String[] {"Drathmoor", "Myrvalis"});
        countryNeighbors.put("Valkir", new String[] {"Korvath", "Kovaris"});
        countryNeighbors.put("Drathmoor", new String[] {"Tirandor", "Falandir", "Korvath"});
        countryNeighbors.put("Falandir", new String[] {"Drathmoor", "Sylthar"});
        countryNeighbors.put("Korvath", new String[] {"Valkir", "Drathmoor", "Lyndor"});
        countryNeighbors.put("Lyndor", new String[] {"Korvath", "Valthorin"});

        countryNeighbors.put("Arkendell", new String[] {"Zorwyn", "Xyldar"});
        countryNeighbors.put("Sylthar", new String[] {"Zorwyn", "Falandir"});
        countryNeighbors.put("Zorwyn", new String[] {"Arkendell", "Sylthar", "Therond"});
        countryNeighbors.put("Nimaris", new String[] {"Therond", "Orindor"});
        countryNeighbors.put("Therond", new String[] {"Zorwyn", "Nimaris", "Valthorin"});
        countryNeighbors.put("Valthorin", new String[] {"Therond", "Lyndor"});
    }

    public static void addCountryNeighbors4(Map<String, String[]> countryNeighbors) {
        countryNeighbors.put("Alaska", new String[] {"Northwest Territory", "Alberta", "Kamchatka"});
        countryNeighbors.put("Northwest Territory", new String[] {"Alaska", "Greenland", "Ontario"});
        countryNeighbors.put("Greenland", new String[] {"Northwest Territory", "Quebec"});
        countryNeighbors.put("Alberta", new String[] {"Alaska", "Ontario", "Western US"});
        countryNeighbors.put("Ontario", new String[] {"Northwest Territory", "Alberta", "Quebec", "Western US"});
        countryNeighbors.put("Quebec", new String[] {"Greenland", "Ontario", "Eastern US", "Iceland"});
        countryNeighbors.put("Western US", new String[] {"Alberta", "Ontario", "Eastern US", "Central America"});
        countryNeighbors.put("Eastern US", new String[] {"Quebec", "Western US", "Central America"});
        countryNeighbors.put("Central America", new String[] {"Western US", "Eastern US", "Venezuela"});

        countryNeighbors.put("Venezuela", new String[] {"Central America", "Peru", "Brazil"});
        countryNeighbors.put("Peru", new String[] {"Venezuela", "Brazil", "Argentina"});
        countryNeighbors.put("Brazil", new String[] {"Venezuela", "Peru", "Argentina", "North Africa"});
        countryNeighbors.put("Argentina", new String[] {"Peru", "Brazil"});

        countryNeighbors.put("Iceland", new String[] {"Quebec", "Scandinavia", "Great Britain"});
        countryNeighbors.put("Scandinavia", new String[] {"Iceland", "Ukraine", "Northern Europe"});
        countryNeighbors.put("Ukraine", new String[] {"Scandinavia", "Northern Europe", "Ural"});
        countryNeighbors.put("Great Britain", new String[] {"Iceland", "Northern Europe", "Western Europe"});
        countryNeighbors.put("Northern Europe", new String[] {"Scandinavia", "Ukraine", "Great Britain", "Southern Europe"});
        countryNeighbors.put("Western Europe", new String[] {"Great Britain", "Southern Europe", "North Africa"});
        countryNeighbors.put("Southern Europe", new String[] {"Northern Europe", "Western Europe", "Egypt", "Middle East"});

        countryNeighbors.put("North Africa", new String[] {"Brazil", "Western Europe", "Egypt", "Congo"});
        countryNeighbors.put("Egypt", new String[] {"Southern Europe", "North Africa", "Congo", "East Africa", "Middle East"});
        countryNeighbors.put("Congo", new String[] {"North Africa", "Egypt", "East Africa", "South Africa"});
        countryNeighbors.put("East Africa", new String[] {"Egypt", "Congo", "Madagascar"});
        countryNeighbors.put("South Africa", new String[] {"Congo", "Madagascar"});
        countryNeighbors.put("Madagascar", new String[] {"East Africa", "South Africa"});

        countryNeighbors.put("Siberia", new String[] {"Yakutsk", "Ural", "Irkutsk"});
        countryNeighbors.put("Yakutsk", new String[] {"Siberia", "Kamchatka", "Mongolia"});
        countryNeighbors.put("Kamchatka", new String[] {"Alaska", "Yakutsk", "Mongolia"});
        countryNeighbors.put("Ural", new String[] {"Ukraine", "Siberia", "Irkutsk", "Afghanistan"});
        countryNeighbors.put("Irkutsk", new String[] {"Siberia", "Ural", "Mongolia", "China"});
        countryNeighbors.put("Mongolia", new String[] {"Yakutsk", "Kamchatka", "Irkutsk", "China", "Japan"});
        countryNeighbors.put("Afghanistan", new String[] {"Ural", "China", "Middle East", "India"});
        countryNeighbors.put("China", new String[] {"Irkutsk", "Mongolia", "Afghanistan", "Siam"});
        countryNeighbors.put("Japan", new String[] {"Mongolia"});
        countryNeighbors.put("Middle East", new String[] {"Southern Europe", "Egypt", "Afghanistan", "India"});
        countryNeighbors.put("India", new String[] {"Afghanistan", "Middle East", "Siam"});
        countryNeighbors.put("Siam", new String[] {"China", "India", "Indonesia"});

        countryNeighbors.put("Indonesia", new String[] {"Siam", "New Guinea", "Western Australia", "Eastern Australia"});
        countryNeighbors.put("New Guinea", new String[] {"Indonesia", "Western Australia", "Eastern Australia"});
        countryNeighbors.put("Western Australia", new String[] {"Indonesia", "New Guinea", "Eastern Australia"});
        countryNeighbors.put("Eastern Australia", new String[] {"Indonesia", "New Guinea", "Western Australia"});
    }
}
